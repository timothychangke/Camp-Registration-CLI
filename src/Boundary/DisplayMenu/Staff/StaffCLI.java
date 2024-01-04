package src.Boundary.DisplayMenu.Staff;

import java.util.*;

import src.Boundary.Login.Display;

/**
 * The StaffCLI class provides a text-based user interface for staff members to
 * interact with various functionalities in the CAMs Registration System.
 */
public class StaffCLI implements Display {

    public void display() {
        System.out.println("================Staff Menu===================");
        System.out.println("Hello! What do you want to do today?");
        System.out.println("1. Change Password");
        System.out.println("2. Camps");
        System.out.println("3. Enquiry");
        System.out.println("4. Suggestions");
        System.out.println("5. Report");
        System.out.println("6. Log out");
        System.out.println("=============================================");
    }

    
     /**
     * Reads and returns the selected option from the staff menu.
     *
     * @return An integer representing the selected option.
     */
    public int StaffMenu() {
        this.display();// display of options
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer option");
            sc.next();
            display();
        }
        int option = sc.nextInt();
        return option;
    }
}
