package src.Boundary.DisplayMenu.CampCommiteeMember;

import java.util.*;

import src.Boundary.Login.Display;

/**
 * The CampCommCLI class provides a user interface for Camp Committee Members (CampComm) to navigate the CAMs Registration System.
 */
public class CampCommCLI implements Display {

    public CampCommCLI() {

    }

    public void display() {
        System.out.println("=============Camp Committee CLI=================");
        System.out.println("Hello! What do you want to do today?");
        System.out.println("1. Change Password");
        System.out.println("2. Camps");
        System.out.println("3. Enquiry");
        System.out.println("4. Suggestion");
        System.out.println("5. Report");
        System.out.println("6. Log out");
    }

    
    /** 
     * @return int
     */
    public int StudentMenu() {
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
