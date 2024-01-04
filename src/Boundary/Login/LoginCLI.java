package src.Boundary.Login;

import src.Controller.changePasswordController;
import src.Entity.*;
import java.util.*;

/**
 * The LoginCLI class provides a command-line interface for user login and authentication.
 */
public class LoginCLI implements Display {
    private List<Student> student_list;
    private List<Staff> staff_list;
    private String userID;
    private String type;

    public LoginCLI(List<Student> student_list, List<Staff> staff_list) {
        this.student_list = student_list;
        this.staff_list = staff_list;
    }

    /**
     * Displays the login menu options to the user.
     */
    public void display() {
        System.out.println("===============================================");
        System.out.println("Hello User! Choose one of the following!");
        System.out.println("1. Student");
        System.out.println("2. Staff Member");
        System.out.println("===============================================");
    }

    
    /** 
     * @return String
     */
    public String getUserID() {
        return this.userID;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Displays the login menu and handles user login and authentication.
     *
     * @return An integer representing the user's role (1 for Student, 2 for Staff).
     */
    public int loginMenu() {
        System.out.println("Welcome to the the CAMs Registration System!");
        this.display();// display of options
        String input, password;
        boolean success = false;
        Scanner sc = new Scanner(System.in);
        int option;
        do {
            System.out.print("Option: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer option");
                sc.next();
                display();
            }
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Please enter your StudentID: ");
                    input = sc.next();
                    success = loginUserName(input, option);
                    if (success) {
                        System.out.println("Please enter your password: ");
                        password = sc.next();
                        success = loginPassword(input, password, option);
                        if (success == true) {
                            System.out.println("Authorized\n");
                            this.userID = input;
                            this.type = "Student";
                            checkChangePassword(input, password, option);
                            return 1;
                        } else {
                            System.out.println("Invalid Password\n");
                        }
                    } else {
                        System.out.println("Invalid login\n");
                    }
                    break;
                case 2:
                    System.out.println("Please enter your userID: ");
                    input = sc.next();
                    success = loginUserName(input, option);
                    if (success) {
                        System.out.println("Please enter your password: ");
                        password = sc.next();
                        success = loginPassword(input, password, option);
                        if (success == true) {
                            System.out.println("Authorized\n");
                            this.type = "Staff";
                            this.userID = input;
                            checkChangePassword(input, password, option);
                            return 2;
                        } else {
                            System.out.println("Invalid Password\n");
                        }
                    } else {
                        System.out.println("Invalid login\n");
                    }
                    break;
                default:
                    System.out.println("You have enter an invalid option");
            }
            this.display();

        } while (true);
        // sc.close();
    }

    /**
     * Checks if a user's first login requires a password change and initiates the change if needed.
     *
     * @param userId   The user's userID.
     * @param password The user's password.
     * @param option   The login option (1 for Student, 2 for Staff).
     */
    private void checkChangePassword(String userId, String password, int option) {
        if (option == 1) {
            for (Student student : student_list) {
                if (student.getUserID().equals(userID)) {
                    if (student.getPassword().equals("password")) {
                        System.out.println("========================================================================");
                        System.out.println("As this is your first login, you are required to change your password.");
                        changePasswordController cp = new changePasswordController(student);
                        return;
                    }
                }
            }
        } else {
            for (Staff staff : staff_list) {
                if (staff.getUserID().equals(userID)) {
                    if (staff.getPassword().equals("password")) {
                        System.out.println("========================================================================");
                        System.out.println("As this is your first login, you are required to change your password.");
                        changePasswordController cp = new changePasswordController(staff);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Checks if a user's userID is valid and exists in the database.
     *
     * @param userID The user's userID to check.
     * @param option The login option (1 for Student, 2 for Staff).
     * @return true if the userID is valid and exists, false otherwise.
     */
    private boolean loginUserName(String userID, int option) {
        if (option == 1) {
            for (Student student : student_list) {
                if (student.getUserID().equals(userID)) {
                    return true; // studentID resides in database
                }
            }
        } else {
            for (Staff staff : staff_list) {
                if (staff.getUserID().equals(userID)) {
                    return true; // staffID resides in database
                }
            }
        }
        return false;
    }

    /**
     * Checks if a user's userID and password match.
     *
     * @param userID The user's userID to check.
     * @param option The login option (1 for Student, 2 for Staff).
     * @param password 
     * @return true if the userID and password match, false otherwise.
     */
    private boolean loginPassword(String userID, String password, int option) {
        if (option == 1) {
            for (Student student : student_list) {
                if (student.getUserID().equals(userID) && student.getPassword().equals(password)) {
                    return true;
                }
            }
        } else {
            for (Staff staff : staff_list) {
                if (staff.getUserID().equals(userID) && staff.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
