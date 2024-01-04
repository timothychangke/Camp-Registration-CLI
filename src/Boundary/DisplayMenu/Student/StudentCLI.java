package src.Boundary.DisplayMenu.Student;

import java.util.*;

import src.Boundary.Login.Display;

/**
 * The StudentCLI class provides a text-based user interface for students to navigate the CAMs Registration System.
 */
public class StudentCLI implements Display {

    public StudentCLI() {
    }

    public void display() {
        System.out.println("============Student Menu=================");
        System.out.println("Hello! What do you want to do today?");
        System.out.println("1. Change Password");
        System.out.println("2. Camps");
        System.out.println("3. Enquiry");
        System.out.println("4. Log out");
        System.out.println("==========================================");
    }

    
    /**
     * Retrieves and returns the student's menu choice.
     *
     * @return An integer representing the selected menu option.
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