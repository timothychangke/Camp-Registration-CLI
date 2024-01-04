package src.Boundary.Mains.Staff;

import java.util.ArrayList;
import java.util.List;

import src.Boundary.Mains.IMain;
import src.Controller.StaffDataHandler;
import src.Controller.Camp.Data.CampDataHandler;
import src.Controller.Enquiries.Data.EnquiriesDataHandler;
import src.Controller.Suggestions.data.SuggestionsDataHandler;
import src.Entity.Camp;
import src.Entity.Staff;

/**
 * The StaffTypeMain class represents the main entry point and user interaction handler
 * for staff-type users in the CAMs Registration System. It allows staff members to
 * access and manage their own camps, reports, suggestions, and inquiries.
 */
public class StaffTypeMain implements IMain {
    private StaffDataHandler staffDataHandler;
    private String userID;
    private CampDataHandler campDataHandler;
    private SuggestionsDataHandler suggestionDataHandler;
    private EnquiriesDataHandler enquiriesDataHandler;
    private int logout = 0;

    public StaffTypeMain(StaffDataHandler staffDataHandler, String userID, CampDataHandler campDataHandler,
            SuggestionsDataHandler suggestionDataHandler, EnquiriesDataHandler enquiriesDataHandler) {
        this.staffDataHandler = staffDataHandler;
        this.userID = userID;
        this.campDataHandler = campDataHandler;
        this.suggestionDataHandler = suggestionDataHandler;
        this.enquiriesDataHandler = enquiriesDataHandler;
    }

    
/**
 * Displays the main menu and handles user interactions for staff-type users.
 *
 * @return An integer representing the result of user interaction.
 *         0 - User wants to logout.
 */
    public int cli() {
        for (Staff staff : staffDataHandler.getStaffList()) {
            if (userID.equals(staff.getUserID())) {

                // Initializing staff's ownCamp with our initial data in excel file
                List<Camp> camp_list = campDataHandler.getCampList();
                List<Camp> new_camp_list = new ArrayList<Camp>();
                for (Camp c : camp_list) {
                    if (c.getStaffInCharge().equals(staff.getUserID())) {
                        new_camp_list.add(c);
                    }
                }
                // have to loop thru to print
                staff.setOwnCamp(new_camp_list);

                StaffMain staffMain = new StaffMain(staff, campDataHandler, suggestionDataHandler, enquiriesDataHandler);
                do {
                    logout = staffMain.cli();
                } while (logout != 0);
            }
        }
        return 0;
    }
}
