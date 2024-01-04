package src.Boundary.Enquiries.Staff;

import java.util.*;

import src.Boundary.DisplayMenu.Staff.StaffCLI;
import src.Controller.Enquiries.Interfaces.IStaffEnquiriesController;
import src.Entity.Staff;

/**
 * The StaffEnquiriesCLI class provides a user interface for staff members to manage enquiries.
 */
public class StaffEnquiriesCLI extends StaffCLI {
    private IStaffEnquiriesController enquiriesController;

    public StaffEnquiriesCLI(IStaffEnquiriesController enquiriesController) {
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
     * Allows a staff member to view enquiries.
     *
     * @param staff The staff member viewing the enquiries.
     */
    private void viewEnquiries(Staff staff) {
        enquiriesController.viewEnquiries(staff);
    }

     /**
     * Allows a staff member to reply to enquiries.
     *
     * @param staff The staff member replying to enquiries.
     */
    private void replyEnquiries(Staff staff) {
        Scanner sc = new Scanner(System.in);
        enquiriesController.viewEnquiries(staff);
        if (enquiriesController.enquiriesCheckIsEmpty()) {
            System.out.println("Enquiry list is empty.");
            return;
        }
        System.out.println("Which enquiries do you like to reply to?");
        System.out.print("Option: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer option");
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
        enquiriesController.replyEnquiries(staff, reply, option);
    }

    
    public void main(Staff staff) {
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
                    viewEnquiries(staff);
                    break;
                case 2:
                    replyEnquiries(staff);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Option please re-input a valid option");
                    System.out.println("----------------------------------------------");
            }
            this.display();
        } while (option != 3);
    }
}