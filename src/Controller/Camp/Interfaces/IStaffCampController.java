package src.Controller.Camp.Interfaces;

import src.Entity.Staff;
import src.Entity.Camp;
import java.util.List;

/**
 * The IStaffCampController interface defines methods for managing camps by staff members.
 */
public interface IStaffCampController {

    void createCamp(Staff staff, String campName, String startingDate, String endingDate,
            String registrationClosingDate, String userGroup, String location, int totalSlots, int committeeSlots,
            String description, String staffInCharge, boolean visibility);

    int checkCamp(Staff staff, String campName);

    void editCamp(Staff staff, String campName, int changeIdx, String stringChange, int intChange);

    void deleteCamp(Staff staff, String campName);

    void toggleVisibility(Staff staff, String campName, boolean visibility);

    void viewAllCamps(String filter, String option);

    List<Camp> viewOwnCamps(Staff staff);
}
