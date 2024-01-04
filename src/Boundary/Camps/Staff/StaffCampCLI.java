package src.Boundary.Camps.Staff;
import src.Tablecreation.Table;
import src.Boundary.DisplayMenu.Staff.StaffCLI;
import src.Controller.Camp.Interfaces.IStaffCampController;
import src.Entity.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The StaffCampCLI class provides a command-line interface for staff members to manage camps.
 * It extends StaffCLI and includes methods for creating, editing, deleting, and viewing camps, as well as toggling camp visibility.
 */
public class StaffCampCLI extends StaffCLI {
    private Staff staff;
    private IStaffCampController staffCampController;

    public StaffCampCLI(Staff staff,
            IStaffCampController staffCampController) {
        this.staff = staff;
        this.staffCampController = staffCampController;
    }

     /**
     * Displays the main menu options for camp management.
     */
    public void display() {
        System.out.println("====Camp Management====");
        System.out.println("1. Create camps");
        System.out.println("2. Edit camps");
        System.out.println("3. Delete camps");
        System.out.println("4. View all camps");
        System.out.println("5. View camps made by you");
        System.out.println("6. Toggle Visibility");
        System.out.println("7. Exit");
        System.out.println("========================");
    }

     /**
     * Allows staff to create a new camp by providing various details.
     */

    private void createCamp() {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Please enter Camp name, starting date, ending date, registration closing date, user group, location, total slots, committee slots, description, staff in charge and the visibility");
        System.out.print("Camp name: ");
        String campname = sc.nextLine();
        while(campname.isEmpty()){
            System.out.println("Cannot leave this field empty. Please input a camp name");
            System.out.print("Camp name: ");
            campname=sc.next();
        }
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        System.out.print("Starting date(format is YYYY-MM-DD): ");
        String starting_date = sc.next();
        Matcher matcher=pattern.matcher(starting_date);
        while(!matcher.matches()){
            System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
            System.out.print("Starting date(format is YYYY-MM-DD): ");
            starting_date = sc.next();
            matcher=pattern.matcher(starting_date);
        }
        System.out.print("Ending date(format is YYYY-MM): ");
        String ending_Date = sc.next();
        matcher=pattern.matcher(ending_Date);
        while(!matcher.matches()){
            System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
            System.out.print("Ending date(format is YYYY-MM): ");
            ending_Date = sc.next();
            matcher=pattern.matcher(ending_Date);
        }
        System.out.print("Registration closing date(format is YYYY-MM-DD): ");
        String regist = sc.next();
        matcher=pattern.matcher(regist);
        while(!matcher.matches()){
            System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
            System.out.print("Registration closing date(format is YYYY-MM-DD): ");
            regist= sc.next();
            matcher=pattern.matcher(regist);
        }
        System.out.print("User Group: ");
        String usergroup = sc.next();
        while (usergroup.equals(staff.getFaculty()) == false) {
            System.out.println("Error! Please Input the right faculty");
            System.out.print("User Group: ");
            usergroup = sc.next();
        }
        System.out.print("Location: ");
        sc.nextLine();
        String location = sc.nextLine();

        System.out.print("Total Slots: ");
        while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer option");
                sc.next();
        }
        int total_slots = sc.nextInt();

        int comm_slots = -1; // Initialize to an invalid value

        while (comm_slots < 0 || comm_slots > 10) {
            try {
                System.out.print("Committee Slots(between 0 and 10): ");
                comm_slots = sc.nextInt();

                if (comm_slots < 0 || comm_slots > 10) {
                    System.out.println("Input a valid number between 0 and 10");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                sc.next(); // Clear the invalid input from the scanner
            }
        }
        System.out.print("Description: ");
        sc.nextLine();
        String description = sc.nextLine();
        boolean visi = false;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Visibility (true/false): ");
                visi = sc.nextBoolean();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
                sc.next();
            }
        }

        staffCampController.createCamp(staff,
                campname,
                starting_date,
                ending_Date, regist,
                usergroup, location, total_slots, comm_slots, description, staff.getUserID(), visi);
    }

    /**
     * Displays camps based on filtering criteria provided by staff.
     */
    private void viewCamps() {
        // ARGUMENTS:
        // For "Location", type in location to see
        // For "Camp Name", type in camp name
        // For "Date", type in "starting_date,ending_date"

        Scanner sc = new Scanner(System.in);
        System.out.println("Please provide which feature to filter by: (Location, CampName, Date). Press any key to enter default");
        String filter = sc.next();
        String option = "none";
        if (filter.equals("Location") || filter.equals("CampName") || filter.equals("Date")) {
            System.out.println("Please provide which Location/CampName/Date (YYYY-MM-DD,YYYY-MM-DD format) you want to see: ");
            option = sc.next();
        }

        staffCampController.viewAllCamps(filter, option);
    }

    /**
     * Allows staff to edit the attributes of a camp.
     */
    private void editCamps() {
        int opt;
        Scanner sc = new Scanner(System.in);
        System.out.println("Which camp do you want to edit? (Please provide Camp name) ");
        String name = sc.next();
        System.out.print(
                "Which attribute would you like to change? \n1: Camp name\n 2: starting date\n 3: ending date\n 4: registration closing date\n 5: user group\n 6: location\n 7: total slots\n 8: committee slots\n 9: description\n 10: staff in charge\n");
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer option");
                sc.next();
                display();
            }
            opt = sc.nextInt();
        }while (opt > 10 || opt < 1);
        System.out.print("What would you like to change it to? ");
        int int_change = 0;
        String str_change = "";
        if (opt == 7 || opt == 8) {
            while(!sc.hasNextInt()){
                System.out.println("Please input a integer");
                sc.next();
            }
            int_change = sc.nextInt();
        } else {
            str_change = sc.next();
        }
        staffCampController.editCamp(staff, name, opt, str_change, int_change);
    }

    /**
     * Allows staff to delete a camp.
     */
    private void deleteCamp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which camp would you like to delete?");
        String campName = sc.nextLine();
        staffCampController.deleteCamp(staff, campName);
    }

    
    /**
     * Displays a list of camps created by the staff.
     *
     * @param staff The staff member for whom to display the camps.
     */
    private void viewOwnCamps(Staff staff) {
        List<Camp> camp_list = staffCampController.viewOwnCamps(staff);
        Table table=new Table();
        table.setHeaders("Camp name","Camp Dates","Registration Closing Date","Location","Available slots", "Description","Staff in Charge");
        table.setShowVerticalLines(true);
        for (Camp c : camp_list) {
            table.addRow(c.getCampName(), c.getStartingDate()+"~"+c.getEndingDate(),c.getRegistrationClosingDate(),c.getLocation(),""+c.getAvailableSlots(),c.getDescription(),c.getStaffInCharge());
        }
        table.print();
    }
 
    /**
     * Toggles the visibility of a camp.
     */
    private void toggleVisibility() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which camp would you like to toggle? (Insert camp name)");
        String campnamee = sc.next();
        System.out.println("What would you like to toggle the visibility to? (true or false)");
        boolean validInput = false;
        boolean visibility=false;
        while (!validInput) {
            try {
                System.out.print("Visibility (true/false): ");
                visibility = sc.nextBoolean();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
                sc.next();
            }
        }
        staffCampController.toggleVisibility(staff, campnamee, visibility);
    }

    /**
     * Main method to execute the camp management interface.
     */
    public void main() {
        this.display(); // displaying options
        Scanner sc = new Scanner(System.in);
        int option;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer option");
                sc.next();
                display();
            }
            option = sc.nextInt();
            switch (option) {
                case 1:
                    createCamp();
                    break;
                case 2:
                    editCamps();
                    break;
                case 3:
                    deleteCamp();
                    break;
                case 4:
                    viewCamps();
                    break;
                case 5:
                    viewOwnCamps(staff);
                    break;
                case 6:
                    toggleVisibility();
                    break;
                default:
                    System.out.println("Invalid Option please re-input a valid option");
                    System.out.println("==============================================");
            }
            this.display();
        } while (option != 7);
}
}
