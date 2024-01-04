package src.Boundary.Enquiries.Student;

import src.Boundary.DisplayMenu.Student.StudentCLI;
import src.Controller.Camp.Data.CampDataHandler;
import src.Controller.Enquiries.Interfaces.IStudentEnquiriesController;
import src.Entity.*;
import java.util.*;

/**
 * The StudentEnquiryCLI class provides a text-based user interface for students to manage enquiries
 * in the CAMs Registration System.
 */
public class StudentEnquiryCLI extends StudentCLI {
    private IStudentEnquiriesController enquiryController;
    private CampDataHandler campDataHandler;

    public StudentEnquiryCLI(Student student,
            IStudentEnquiriesController enquiryController,
            CampDataHandler campDataHandler) {
        this.enquiryController = enquiryController;
        this.campDataHandler = campDataHandler;
    }

    public void display() {
        System.out.println("====Enquiry Management====");
        System.out.println("1. Submit enquiry");
        System.out.println("2. Edit enquiry");
        System.out.println("3. View enquiry");
        System.out.println("4. Delete enquiry");
        System.out.println("5. Exit");
        System.out.println("========================");
    }

    
    /**
     * Allows a student to submit an enquiry for a specific camp.
     *
     * @param student The student submitting the enquiry.
     */
    public void submitEnquiry(Student student) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the enquiry: ");
        String enquiry = in.nextLine();

        System.out.println("Enter the camp name you wish to enquire about: ");
        String camp_name = in.nextLine();

        List<Camp> camp_list = campDataHandler.getCampList();
        for (Camp c : camp_list) {
            
            if (c.getCampName().equals(camp_name))
            {
                if  (c.getUserGroup().equals(student.getFaculty()) && c.getVisibility() == true)
                {
                    enquiryController.submitEnquiry(student, c, enquiry);
                    return;
                }
                else
                {
                    System.out.println("This camp is not accessible to you!");
                    return;
                }
            }
            
        }
        System.out.println("Invalid camp name!");
    }

    /**
     * Allows a student to edit an existing enquiry.
     *
     * @param student The student editing the enquiry.
     */
    public void editEnquiry(Student student) {
        Scanner sc = new Scanner(System.in);
        enquiryController.viewEnquiries(student);
        if(enquiryController.getStudentEnquiries(student).isEmpty()){
            System.out.println("There are no enquiries to edit currently");
            return;
        }
        System.out.println("Enter Enquiry index to edit: ");
        while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer option");
                sc.next();
            }
        int index = sc.nextInt();
        System.out.println("Enter new Enquiry: ");
        sc.nextLine();
        String new_enquiry_text = sc.nextLine();
        enquiryController.editEnquiries(student, index, new_enquiry_text);
        enquiryController.viewEnquiries(student);
    }

    public void viewEnquiry(Student student) {
        enquiryController.viewEnquiries(student);
    }

    /**
     * Allows a student to delete an existing enquiry.
     *
     * @param student The student deleting the enquiry.
     */
    public void deleteEnquiry(Student student) {
        Scanner sc = new Scanner(System.in);
        enquiryController.viewEnquiries(student);
        if(enquiryController.getStudentEnquiries(student).isEmpty()){
            System.out.println("There are no enquiries to delete");
            return;
        }
        System.out.println("Enter Enquiry index to delete: ");
        while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer option");
                sc.next();
        }
        int index = sc.nextInt();

        enquiryController.deleteEnquiry(student, index);
        enquiryController.viewEnquiries(student);
    }

    public void main(Student student) {
        this.display(); // displaying options
        Scanner in = new Scanner(System.in);
        int option;
        do {
            while (!in.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer option");
                in.next();
                display();
            }
            option = in.nextInt();
            switch (option) {
                case 1:
                    submitEnquiry(student);
                    break;
                case 2:
                    editEnquiry(student);
                    break;
                case 3:
                    viewEnquiry(student);
                    ;
                    break;
                case 4:
                    deleteEnquiry(student);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid Option please re-input a valid option");
                    break;
            }
            this.display();
        } while (option != 5);
    }
}
