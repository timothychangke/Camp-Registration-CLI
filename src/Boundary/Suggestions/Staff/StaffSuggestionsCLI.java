package src.Boundary.Suggestions.Staff;

import src.Entity.Staff;
import java.util.*;

import src.Boundary.DisplayMenu.Staff.StaffCLI;
import src.Controller.Suggestions.Interfaces.IStaffSuggestionsController;

public class StaffSuggestionsCLI extends StaffCLI {
    private Staff staff;
    IStaffSuggestionsController suggestController;

    public StaffSuggestionsCLI(Staff staff, IStaffSuggestionsController suggestController) {
        this.staff = staff;
        this.suggestController = suggestController;
    }

    public void display() {
        System.out.println("====Suggestions Management====");
        System.out.println("1. View suggestions");
        System.out.println("2. Approve suggestions");
        System.out.println("3. Exit");
        System.out.println("========================");
    }

    public void viewSuggestion() {
        suggestController.viewSuggestions(staff);
    }

    public void approveSuggestion() {
        Scanner in = new Scanner(System.in);
        System.out.println("Which suggestion would you like to approve?");
        System.out.println("Here is your list of suggestions: ");
        suggestController.viewSuggestions(staff);
        System.out.print("Option: ");
        while(!in.hasNextInt()){
            System.out.println("Please input a number.");
            System.out.println("Which suggestion would you like to approve?: ");
            in.next();
        }
        int option = in.nextInt();
        suggestController.approveSuggestions(staff, option);
    }

    public void main() {
        this.display(); // displaying options
        Scanner in = new Scanner(System.in);
        int option;
        do {
            option = in.nextInt();
            switch (option) {
                case 1:
                    viewSuggestion();
                    break;
                case 2:
                    approveSuggestion();
                    break;
                default:
                    System.out.println("Invalid Option please re-input a valid option");
                    System.out.println("----------------------------------------------");
            }
            this.display();
        } while (option != 3);
    }
}
