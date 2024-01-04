package src.Boundary.Enquiries.CampCommitteMember;

import java.util.*;

import src.Boundary.DisplayMenu.Student.StudentCLI;
import src.Controller.Enquiries.Interfaces.ICampCommEnquiriesController;
import src.Entity.Student;

/**
 * The CampCommEnquiriesCLI class provides a text-based user interface for Camp Committee Members
 * to manage enquiries in the CAMs Registration System.
 */
public class CampCommEnquiriesCLI extends StudentCLI {
    ICampCommEnquiriesController enquiriesController;

    public CampCommEnquiriesCLI(ICampCommEnquiriesController enquiriesController) {
        this.enquiriesController = enquiriesController;
    }

    public void display() {
        System.out.println("====Enquiries Management====");
        System.out.println("1. View Enquiries");
        System.out.println("2. Reply Enquiries");
        System.out.println("3. Exit");
        System.out.println("========================");
    }

    
    /**
     * View enquiries and display them for a specific student.
     *
     * @param student The student whose enquiries are to be viewed.
     */
    private void viewEnquiries(Student student) {
        enquiriesController.viewEnquiries(student);
    }

    /**
     * View enquiries and display them for a specific student.
     *
     * @param student The student whose enquiries are to be viewed.
     */
    private void replyEnquiries(Student student) {
        Scanner sc = new Scanner(System.in);
        enquiriesController.viewEnquiries(student);
        if (enquiriesController.enquiriesCheckIsEmpty()) {
            System.out.println("Enquiry list is empty.");
            return;
        }
        System.out.println("Which enquiries do you like to reply to?");
        System.out.print("Option: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid integer option");
            sc.next();
        }
        int option = sc.nextInt();
        System.out.println("What would you like to reply?");
        sc.nextLine();
        String reply = sc.nextLine();
        while(reply.isEmpty()){
            System.out.println("Error reply cannot be empty. Please give a reply");
            System.out.println("What would you like to reply?");
            sc.nextLine();
            reply = sc.nextLine();
        }
        enquiriesController.replyEnquiries(student, reply, option);
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
                    viewEnquiries(student);
                    break;
                case 2:
                    replyEnquiries(student);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Option please re-input a valid option");
                    System.out.println("----------------------------------------------");
                    break;
            }
            this.display();
        } while (option != 3);
    }
}