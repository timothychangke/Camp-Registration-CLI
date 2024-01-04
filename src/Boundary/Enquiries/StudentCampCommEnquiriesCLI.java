package src.Boundary.Enquiries;

import src.Boundary.DisplayMenu.Student.StudentCLI;

import java.util.*;

/**
 * The StudentCampCommEnquiriesCLI class represents the user interface for accessing enquiries as a student or camp committee member.
 */
public class StudentCampCommEnquiriesCLI extends StudentCLI {
    public StudentCampCommEnquiriesCLI() {
    }

    /**
     * Displays the options for accessing enquiries.
     */
    public void display() {
        System.out.println("=====================================================================");
        System.out.println("Do you want to open enquiries as a Student or as a CampCommMember?");
        System.out.println("1. Student");
        System.out.println("2. Camp Committee Member");
        System.out.println("3. Exit");
        System.out.println("Option: ");
        System.out.println("======================================================================");
    }

    
    /**
     * Gets the user's choice for accessing enquiries.
     *
     * @return The user's choice as an integer.
     */
    public int enquiryPageOption() {
        Scanner sc = new Scanner(System.in);
        this.display();
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer option");
            sc.next();
            display();
        }
        int choice = sc.nextInt();
        return choice;
    }
}
