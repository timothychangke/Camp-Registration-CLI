package src.Controller.Enquiries.Staff;
import src.Tablecreation.Table;
import src.Controller.Enquiries.Data.EnquiriesDataHandler;
import src.Controller.Enquiries.Interfaces.IStaffEnquiriesController;
import src.Entity.*;
import java.util.*;

/**
 * Controller class for managing enquiries related to staff.
 * This class handles operations such as viewing and replying to enquiries related to the camps created by staff.
 */
public class StaffEnquiriesController implements IStaffEnquiriesController{
    private EnquiriesDataHandler dataHandler;

    public StaffEnquiriesController(EnquiriesDataHandler data) {
        super();
        this.dataHandler = data;
    }
    
    /**
     * Retrieves a list of camps created by a specific staff member.
     *
     * @param staff The staff member whose camps are to be retrieved.
     * @return A list of Camp objects that the staff member created.
     */
    public List<Camp> getStaffCreatCamps(Staff staff) {
        return staff.getOwnCamps();
    }

    /**
     * Checks if there are any enquiries present in the data handler.
     *
     * @return A boolean indicating whether the enquiries list is empty (true) or not (false).
     */
    public boolean enquiriesCheckIsEmpty() {
        if (dataHandler.checkEmpty()) {
            return true;
        }
        return false;
    }

     /**
     * Displays all enquiries related to the camps created by a specific staff member.
     * The enquiries are presented in a tabular format including details such as enquiry number,
     * camp name, the enquiry text, the student who posted, and the reply status.
     *
     * @param user The staff member (user) whose camp enquiries are to be viewed.
     */
    public void viewEnquiries(User user) {
        Staff staff = (Staff) user;
        boolean enquiried = false;
        if (dataHandler.checkEmpty()) {
            System.out.println("No Enquiries to display.");
            return;
        }
        int i=1;
        Table enquiryTable=new Table();
        enquiryTable.setShowVerticalLines(true);
        enquiryTable.setHeaders("Enquiry No","Camp Name", "Enquiry", "Posted by", "Reply");
        for (Camp camp : getStaffCreatCamps(staff)) {
            for (Enquiries enquiry : dataHandler.getEnquiriesList()) {
                if (enquiry.getCamp().getCampName().equals( camp.getCampName())) {
                    enquiried = true;
                    enquiryTable.addRow(""+i++,enquiry.getCamp().getCampName(),enquiry.getEnquiry(),enquiry.getStudent().getName(),(enquiry.getReply() != null ? enquiry.getReply() : "Not replied yet"));
                }
            }
        }
        if (!enquiried) {
            System.out.println("No enquiries have been made yet.");
            return;
        }
        enquiryTable.print();
    }

    /**
     * Replies to a specific enquiry made to a camp created by the staff member.
     * 
     * @param user The staff member replying to the enquiry.
     * @param reply The reply text to the enquiry.
     * @param index The index of the enquiry in the list of enquiries.
     */
    public void replyEnquiries(User user, String reply, int index) {
        Staff staff = (Staff) user;

        List<Enquiries> enquiriesList = dataHandler.getEnquiriesList();
        List<Camp> ownCampList = getStaffCreatCamps(staff);
        int true_index = index;
        for (int i = 0; i < enquiriesList.size(); i++) {
            Enquiries curr_Enquiries = enquiriesList.get(i);
            for (Camp c: ownCampList) {
                if (c.getCampName().equals(curr_Enquiries.getCamp().getCampName())) {
                    true_index--;
                    if (true_index == 0) {
                        if (enquiriesList.get(i).getReply() == "") {
                            enquiriesList.get(i).setReply(reply);
                            dataHandler.setEnquiriesList(enquiriesList);
                            return;
                        }
                        else {
                            System.out.println("Enquiry has already been replied");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("The required index is not present.");
        return;
    }

}
