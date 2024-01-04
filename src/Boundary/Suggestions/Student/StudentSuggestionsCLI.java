package src.Boundary.Suggestions.Student;

import src.Boundary.DisplayMenu.Student.StudentCLI;
import src.Controller.Suggestions.Interfaces.ICampCommitteeSuggestionsController;
import src.Entity.*;
import java.util.*;

/**
 * The StudentSuggestionsCLI class represents the user interface for managing suggestions as a student.
 */
public class StudentSuggestionsCLI extends StudentCLI {
    ICampCommitteeSuggestionsController suggestionsController;

    public StudentSuggestionsCLI(Student student,
            ICampCommitteeSuggestionsController suggestionsController) {
        this.suggestionsController = suggestionsController;

        // Check if student is a comm member
        if (student.getCampCommittee() == null) {
            System.out.println("This student is not a camp committee member. Unable to submit suggestions!");
            return;
        }
    }

    public void display() {
        System.out.println("====Suggestions Management====");
        System.out.println("1. Submit suggestions");
        System.out.println("2. Edit suggestions");
        System.out.println("3. View suggestions");
        System.out.println("4. Delete suggestions");
        System.out.println("5. Exit");
        System.out.println("========================");
    }

    
    /**
     * Submits a suggestion from the student.
     *
     * @param student The student submitting the suggestion.
     */
    private void submitSuggestion(Student student) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter suggestion: ");
        String suggestion = in.nextLine();
        System.out.println("Please enter the name of the camp to submit suggestion to: ");
        String campName = in.nextLine();
        suggestionsController.submitSuggestion(student, suggestion, campName);
    }

    /**
     * Edits the student's suggestions.
     *
     * @param student The student editing the suggestion.
     */
    private void editSuggestions(Student student) {
        suggestionsController.viewSuggestions(student);
        Scanner in = new Scanner(System.in);
        System.out.println("What would you like to edit your suggestion to?: ");
        String suggestion = in.nextLine();
        System.out.println("Which suggestion would you like to edit?: ");
        while(!in.hasNextInt()){
            System.out.println("Please input a number.");
            System.out.println("Which suggestion would you like to edit?: ");
            in.next();
        }
        int idx = in.nextInt();

        suggestionsController.editSuggestion(student, suggestion, idx);
    }

    /**
     * Views the student's suggestions.
     *
     * @param student The student viewing their suggestions.
     */
    private void viewSuggestion(Student student) {
        System.out.println("Here is your suggestion.");
        suggestionsController.viewSuggestions(student);
    }

    /**
     * Deletes a suggestion submitted by the student.
     *
     * @param student The student deleting the suggestion.
     */
    private void deleteSuggestion(Student student) {
        // suggestionsController.viewSuggestions(student);
        Scanner in = new Scanner(System.in);
        System.out.println("Which would you like to delete?");
        System.out.println("Here is a list of the suggestions: ");
        suggestionsController.viewSuggestions(student);
        System.out.print("Option: ");
        while(!in.hasNextInt()){
            System.out.println("Please input a number.");
            System.out.println("Which suggestion would you like to delete?: ");
            in.next();
        }
        int option = in.nextInt();
        suggestionsController.deleteSuggestion(student, option);
        System.out.println("Suggestion deleted successfully!");
    }

    public void main(Student student) {
        this.display(); // displaying options
        Scanner in = new Scanner(System.in);
        int option;
        do {
            while(!in.hasNextInt()){
                System.out.println("Please input a valid number.");
                in.next();
            }
            option = in.nextInt();
            switch (option) {
                case 1:
                    submitSuggestion(student);
                    break;
                case 2:
                    editSuggestions(student);
                    break;
                case 3:
                    viewSuggestion(student);
                    ;
                    break;
                case 4:
                    deleteSuggestion(student);
                    break;
                default:
                    System.out.println("Invalid Option please re-input a valid option");
                    System.out.println("----------------------------------------------");
            }
            this.display();
        } while (option != 5);
    }
}
