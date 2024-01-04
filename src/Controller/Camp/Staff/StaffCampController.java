package src.Controller.Camp.Staff;
import src.Tablecreation.Table;
import src.Controller.Camp.CampController;
import src.Controller.Camp.Data.CampDataHandler;
import src.Controller.Camp.Interfaces.IStaffCampController;
import src.Entity.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.Utils.*;
/**
 * A controller class for managing camp-related operations by staff.
 * This class extends CampController and implements IStaffCampController.
 */
public class StaffCampController extends CampController implements IStaffCampController {
    private CampDataHandler campdatahandler;

    public StaffCampController(CampDataHandler campdatahandler) {
        super(campdatahandler);
        this.campdatahandler = campdatahandler;
    }

    
    /**
     * Creates a new camp with specified details. Ensures that the staff can only create camps for their own faculty,
     * checks for date validity, and avoids duplicate camp names.
     *
     * @param staff The staff member creating the camp.
     * @param campName The name of the camp.
     * @param starting_date The starting date of the camp.
     * @param ending_date The ending date of the camp.
     * @param registrationClosingDate The closing date for camp registration.
     * @param userGroup The target user group for the camp.
     * @param location The location of the camp.
     * @param totalSlots The total number of slots available in the camp.
     * @param committeeSlots The number of slots available for committee members.
     * @param description A brief description of the camp.
     * @param staffInCharge The staff member in charge of the camp.
     * @param visibility The visibility status of the camp (true for visible, false for hidden).
     */
    public void createCamp(Staff staff, String campName, String starting_date, String ending_date,
            String registrationClosingDate, String userGroup, String location, int totalSlots, int committeeSlots,
            String description, String staffInCharge, boolean visibility) {
        // Staff can only make camp for his/her own school
        if (staff.getFaculty().equals(userGroup) == false) {
            System.out.println("\nStaff cannot make a camp for other schools!\n");
            return;
        }

        // Making sure end date is later than start date (ERROR HANDLING)
        compareDate cd = new compareDate();
        if (cd.compare(starting_date, ending_date) > 0) {
            System.out.println("\nCamp's ending date has to be later than the starting_date!\n");
            return;
        }

        // Making sure camp name is not a duplicate (ERROR HANDLING)
        List<Camp> existing_camp_list = campdatahandler.getCampList();
        for (Camp c : existing_camp_list) {
            if (c.getCampName().equals(campName)) {
                System.out.println("\nDuplicate camp name detected!\n");
                return;
            }
        }

        // Making sure camp's deadline is earlier than start date (ERROR HANDLING)
        if (cd.compare(starting_date, registrationClosingDate) < 0) {
            System.out.println("\nRegistration closing date should be earlier than the starting date!\n");
            return;
        }

        Camp new_camp = new Camp(campName, starting_date, ending_date, registrationClosingDate, userGroup, location,
                totalSlots, committeeSlots, description, staffInCharge, visibility);
        List<Camp> camp_list = campdatahandler.getCampList();
        camp_list.add(new_camp);
        campdatahandler.setCampList(camp_list);
        staff.addOwnCamp(new_camp);
    }
    /**
     * Checks whether a given camp can be edited by a staff member based on faculty association.
     *
     * @param staff The staff member attempting to check the camp.
     * @param campName The name of the camp to be checked.
     * @return An integer indicating the result of the check (0 for error, 1 for no error).
     */
    public int checkCamp(Staff staff, String campName) // 0 - error, 1 - no error
    {
        List<Camp> totalCampList = campdatahandler.getCampList();
        for (Camp c : totalCampList) {
            if (c.getCampName().equals(campName)) {
                if (c.getUserGroup().equals(staff.getFaculty())) {
                    return 1;
                } else {
                    System.out.println("\nError: Staff can only edit his/her own faculty's camp!\n");
                    return 0;
                }
            }
        }
        System.out.println("\nError: Camp name doesn't exist in our database!\n");
        return 0;
    }

     /**
     * Edits an existing camp based on the given changes. Only allows editing of camps owned by the staff member.
     *
     * @param staff The staff member editing the camp.
     * @param campName The name of the camp to be edited.
     * @param change_idx The index indicating the attribute to be changed.
     * @param string_change The new string value for the attribute (if applicable).
     * @param int_change The new integer value for the attribute (if applicable).
     */
    public void editCamp(Staff staff, String campName, int change_idx, String string_change, int int_change) {
        List<Camp> ownCampList = staff.getOwnCamps();
        Scanner sc=new Scanner(System.in);
        boolean edit=false;
        compareDate cd = new compareDate();
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher;
        for (Camp c : ownCampList) {
            if (c.getCampName().equals(campName)) {
                edit=true;
                switch (change_idx) {
                    case 1:
                        c.setCampName(string_change);
                        break;
                    case 2:
                        matcher=pattern.matcher(string_change);
                        while(!matcher.matches()){
                               System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
                               System.out.print("Starting date(format is YYYY-MM-DD): ");
                               string_change = sc.next();
                               matcher=pattern.matcher(string_change);
                        }
                        if(cd.compare(string_change, c.getEndingDate())>0||cd.compare(string_change,c.getRegistrationClosingDate())<0){
                            System.out.println("You have entered an invalid date");
                            return;
                        }
                        c.setStartingDate(string_change);
                        break;
                    case 3:
                        matcher=pattern.matcher(string_change);
                        while(!matcher.matches()){
                               System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
                               System.out.print("Ending date(format is YYYY-MM-DD): ");
                               string_change = sc.next();
                               matcher=pattern.matcher(string_change);
                        }
                        if(cd.compare(string_change, c.getStartingDate())<0||cd.compare(string_change,c.getRegistrationClosingDate())<0){
                            System.out.println("You have entered an invalid date");
                            return;
                        }
                        c.setEndingDate(string_change);
                        break;
                    case 4:
                        matcher=pattern.matcher(string_change);
                        while(!matcher.matches()){
                               System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
                               System.out.print("Registration closing date(format is YYYY-MM-DD): ");
                               string_change = sc.next();
                               matcher=pattern.matcher(string_change);
                        }
                        if(cd.compare(string_change, c.getStartingDate())>0||cd.compare(string_change,c.getEndingDate())>0){
                            System.out.println("You have entered an invalid date");
                            return;
                        }
                        c.setRegistrationClosingDate(string_change);
                        break;
                    case 5:
                        c.setUserGroup(string_change);
                        break;
                    case 6:
                        if(string_change.isEmpty()){
                            System.out.println("Must input location");
                            return;
                        }
                        c.setLocation(string_change);
                        break;
                    case 7:
                        c.setTotalSlots(int_change);
                        break;
                    case 8:
                        if(int_change<0||int_change>10){
                            System.out.println("Invalid number of committee slots");
                            return;
                        }
                        c.setCommitteeSlots(int_change);
                        break;
                    case 9:
                        if(string_change.isEmpty()){
                            System.out.println("Description cannot be empty");
                            return;
                        }
                        c.setDescription(string_change);
                        break;
                    case 10:
                        if(string_change.isEmpty()){
                            System.out.println("Staff in charge cannot be empty");
                            return;
                        }
                        c.setStaffInCharge(string_change);
                        break;
                }
            }
        }
        if(edit==false){
            System.out.println("You have entered an invalid camp name or the camp is not yours");
            return;
        }
        staff.setOwnCamp(ownCampList);

        List<Camp> camplist = campdatahandler.getCampList();
        for (Camp c : camplist) {
            if (c.getCampName().equals(campName)) {
                switch (change_idx) {
                    case 1:
                        c.setCampName(string_change);
                        break;
                    case 2:
                        c.setStartingDate(string_change);
                        break;
                    case 3:
                        c.setEndingDate(string_change);
                        break;
                    case 4:
                        c.setRegistrationClosingDate(string_change);
                        break;
                    case 5:
                        c.setUserGroup(string_change);
                        break;
                    case 6:
                        c.setLocation(string_change);
                        break;
                    case 7:
                        c.setTotalSlots(int_change);
                        break;
                    case 8:
                        c.setCommitteeSlots(int_change);
                        break;
                    case 9:
                        c.setDescription(string_change);
                        break;
                    case 10:
                        c.setStaffInCharge(string_change);
                        break;
                }
            }
        }
        campdatahandler.setCampList(camplist);
        System.out.println("Edited Successfully");
    }
      /**
     * Deletes a camp based on the given name. Only allows deletion of camps owned by the staff member.
     *
     * @param staff The staff member who wants to delete the camp.
     * @param campName The name of the camp to be deleted.
     */
    public void deleteCamp(Staff staff, String campName) {
        List<Camp> ownCampList = staff.getOwnCamps();
        boolean valid=false;
        for (int i = 0; i < ownCampList.size(); i++) {
            if (ownCampList.get(i).getCampName().equals(campName)) {
                ownCampList.remove(i);
                valid=true;
                break;
            }
        }
        if(!valid){
            System.out.println("The name of the camp you are trying to delete is invalid");
            return;
        }
        staff.setOwnCamp(ownCampList);

        List<Camp> camp_list = campdatahandler.getCampList();
        for (int i = 0; i < camp_list.size(); i++) {
            if (camp_list.get(i).getCampName().equals(campName)) {
                camp_list.remove(i);
                break;
            }
        }
        campdatahandler.setCampList(camp_list);
        System.out.println("Camp has been successfully deleted");
    }

    private int checkForCamp(Staff staff, String campName)
    {
        List<Camp> camp_list = campdatahandler.getCampList();
        for (Camp c: camp_list)
        {
            if (c.getCampName().equals(campName))
            {
                if (c.getUserGroup().equals(staff.getFaculty())==false)
                {
                    System.out.println("\nError: Staff cannot toggle visibility of other faculty's camps!\n");
                    return 0;
                }
            }
        }
        return 1;
    }
     
    /**
     * Toggles the visibility of a camp. Checks if the camp belongs to the staff's faculty before toggling.
     *
     * @param staff The staff member toggling the visibility.
     * @param campName The name of the camp for which visibility is being toggled.
     * @param visibility The desired visibility status (true for visible, false for hidden).
     */
    public void toggleVisibility(Staff staff, String campName, boolean visibility) // index of the camp in ownCampList
    {
        if (checkForCamp(staff, campName)==0)
        {
            return;
        }
        
        // Checking if camp name is valid
        List<Camp> camp_list = campdatahandler.getCampList();
        boolean found = false;
        for (Camp c : camp_list) {
            if (c.getCampName().equals(campName)) {
                found = true;
            }
        }
        if (found == false) {
            System.out.println("There is no such camp in the database");
            return;
        }

        // Changing staff's own camp
        List<Camp> ownCampList = staff.getOwnCamps();
        for (int i = 0; i < ownCampList.size(); i++) {
            if (ownCampList.get(i).getCampName().equals(campName)) {
                ownCampList.get(i).setVisibility(visibility);
                break;
            }
        }
        staff.setOwnCamp(ownCampList);

        // Changing global camp

        for (int i = 0; i < camp_list.size(); i++) {
            if (camp_list.get(i).getCampName().equals(campName)) {
                camp_list.get(i).setVisibility(visibility);
                break;
            }
        }
        campdatahandler.setCampList(camp_list);
        System.out.println("Visibility was successfully changed");
    }

    /**
     * Views all camps with the option to filter by location, camp name, or date.
     *
     * @param filter The filter criterion (e.g., "Location", "Camp Name", "Date").
     * @param option The specific value for the chosen filter criterion.
     */
    public void viewAllCamps(String filter, String option) {
        List<Camp> camp_list = campdatahandler.getCampList();
        Table table=new Table();
        table.setHeaders("Camp name","Camp Dates","Registration Closing Date","Location","Available slots", "Description","Staff in Charge");
        table.setShowVerticalLines(true);
        switch (filter) {
            case "Location":
                for (Camp c : camp_list) {
                    if (c.getLocation().equals(option)) {
                        table.addRow(c.getCampName(), c.getStartingDate()+"~"+c.getEndingDate(),c.getRegistrationClosingDate(),c.getLocation(),""+c.getAvailableSlots(),c.getDescription(),c.getStaffInCharge());
                    }
                }
                break;

            case "Camp Name":
                for (Camp c : camp_list) {
                    if (c.getCampName().equals(option)) {
                        table.addRow(c.getCampName(), c.getStartingDate()+"~"+c.getEndingDate(),c.getRegistrationClosingDate(),c.getLocation(),""+c.getAvailableSlots(),c.getDescription(),c.getStaffInCharge());
                    }
                }
                break;

            case "Date":
                compareDate cd = new compareDate();
                String[] splitted = option.split(",");
                String start_date = splitted[0];
                String end_date = splitted[1];
                for (Camp c : camp_list) {
                    if (cd.compare(start_date, c.getStartingDate()) < 0
                            && cd.compare(end_date, c.getEndingDate()) > 0) {
                                table.addRow(c.getCampName(), c.getStartingDate()+"~"+c.getEndingDate(),c.getRegistrationClosingDate(),c.getLocation(),""+c.getAvailableSlots(),c.getDescription(),c.getStaffInCharge());
                    }
                }
                break;

            default:
                for (Camp c : camp_list) {
                    table.addRow(c.getCampName(), c.getStartingDate()+"~"+c.getEndingDate(),c.getRegistrationClosingDate(),c.getLocation(),""+c.getAvailableSlots(),c.getDescription(),c.getStaffInCharge());
                }
                break;
        }
        table.print();

    }
    
    /**
     * Returns a list of camps that are associated with the given staff member.
     *
     * @param s The staff member whose camps are to be viewed.
     * @return A list of Camp objects representing the staff member's own camps.
     */
    public List<Camp> viewOwnCamps(Staff s) {
        /*
         * List<Camp> camp_list = campdatahandler.getCampList();
         * List<Camp> new_camp_list = new ArrayList<Camp>();
         * for (Camp c: camp_list)
         * {
         * if (c.getStaffInCharge().equals(s.getUserID()))
         * {
         * new_camp_list.add(c);
         * }
         * }
         * // have to loop thru to print
         * s.setOwnCamp(new_camp_list);
         */
        return s.getOwnCamps();
    }
}
