package src.Boundary.Camps.Student;

import src.Boundary.DisplayMenu.Student.StudentCLI;
import src.Controller.Camp.Interfaces.IStudentCampController;
import src.Entity.*;
import java.util.*;

// Option 2 - Camps
public class StudentCampCLI extends StudentCLI {
    private Student student;
    IStudentCampController campController;

    public StudentCampCLI(Student student,
            IStudentCampController studentCampController) {
        this.student = student;
        this.campController = studentCampController;
    }

    public void display() {
        System.out.println("====Camp Management====");
        System.out.println("1. View camps");
        System.out.println("2. View registered camps");
        System.out.println("3. Register for camps");
        System.out.println("4. Withdraw for camps");
        System.out.println("5. Exit");
        System.out.println("========================");
    }

    private void viewCamps() {
        // ARGUMENTS:
        // For "Location", type in location to see
        // For "Camp Name", type in camp name
        // For "Date", type in "starting_date,ending_date"

        Scanner sc = new Scanner(System.in);
        System.out.println("Please provide which feature to filter by: (Location, Camp Name, Date). Press any letter to input default");
        String filter = sc.nextLine();
        while(filter.isEmpty()==true){
            System.out.println("Please input a valid filter");
            System.out.println("Please provide which feature to filter by: (Location, Camp Name, Date). Press any letter to input default");
            filter=sc.nextLine();
        }
        String option = "none";

        if (filter.equals("Location") == true || filter.equals("Camp Name") == true || filter.equals("Date") == true) {
            System.out.println("Please provide which Location/CampName/Date (YYYY-MM-DD,YYYY-MM-DD format) you want to see: ");
            option = sc.next();
        }
        campController.viewCamp(student, filter, option);
    }

    private void registerCamp() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Which camp would you like to join? (Enter Camp Name)");
            String camp_to_join = sc.nextLine();
            System.out.println("Would you like to join as a camp committee member? [y,n]");
            String campcomm = sc.nextLine();
            while(campcomm.equals("y")==false&&campcomm.equals("n")==false){
                System.out.println("Please input the correct option");
                System.out.println("Would you like to join as a camp committee member? [y,n]");
                campcomm=sc.next();
            }

            List<Camp> camp_list = campController.getCamp(student);
            for (Camp c : camp_list) {
                if (c.getCampName().equals(camp_to_join)) {
                    campController.registerSlot(student, c, campcomm.equals("y") ? true : false);
                    return;
                }
            }
            System.out.println("Please check the camp name again!\n");
        }
    }

    private void withdrawCamp() {
        if (student.getRegisteredCamps().size() == 0) {
            System.out.println("Student has not signed up for any camps!");
            return;
        }

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Which camp would you like to withdraw from? (Provide Camp Name)");
            String withdraw_camp = sc.nextLine();

            List<Camp> camp_list = campController.getCamp(student);
            for (Camp c : camp_list) {
                if (c.getCampName().equals(withdraw_camp)) {
                    campController.withdrawCamp(student, c);
                    return;
                }
            }
            System.out.println("Please check the camp name again!");
        }
    }

    private void viewRegisteredCamps() {
        campController.viewRegisteredCamps(student);
    }

    public void main() {
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
                    viewCamps();
                    break;
                case 2:
                    viewRegisteredCamps();
                    break;
                case 3:
                    registerCamp();
                    ;
                    break;
                case 4:
                    withdrawCamp();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid Option please re-input a valid option");
                    System.out.println("----------------------------------------------");
            }
            this.display();
        } while (option != 5);
    }
}
