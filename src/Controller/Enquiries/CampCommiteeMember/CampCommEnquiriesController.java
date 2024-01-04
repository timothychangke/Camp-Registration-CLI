package src.Controller.Enquiries.CampCommiteeMember;

import src.Tablecreation.Table;
import java.util.List;

import src.Controller.Enquiries.Data.EnquiriesDataHandler;
import src.Controller.Enquiries.Interfaces.ICampCommEnquiriesController;
import src.Entity.*;


/**
 * The CampCommEnquiriesController class handles camp committee member's enquiries.
 */
public class CampCommEnquiriesController implements ICampCommEnquiriesController {
    private EnquiriesDataHandler dataHandler;

    public CampCommEnquiriesController(EnquiriesDataHandler data) {
        super();
        this.dataHandler = data;
    }

    
    /** 
     * @param student
     * @return List<Camp>
     */
    public List<Camp> getCampCommOwnCamp(Student student) {
        return student.getRegisteredCamps();
    }

    public boolean enquiriesCheckIsEmpty() {
        if (dataHandler.checkEmpty()) {
            return true;
        }
        return false;
    }

     /**
     * Displays enquiries for the camp committee member.
     *
     * @param user The user (camp committee member) requesting to view enquiries.
     */
    public void viewEnquiries(User user) {
        Student student = (Student) user;
        boolean enquiried = false;
        if (dataHandler.checkEmpty()) {
            System.out.println("No Enquiries to display.");
            return;
        }
        Table table = new Table();
        int i = 1;
        table.setHeaders("Enquiry No", "Camp Name", "Enquiry", "Posted by", "Reply");
        table.setShowVerticalLines(true);
        for (Camp camp : getCampCommOwnCamp(student)) {
            if (camp.getCampName().equals(student.getCampCommittee())) {
                for (Enquiries enquiry : dataHandler.getEnquiriesList()) {
                    if (enquiry.getCamp().getCampName().equals(camp.getCampName())) {
                        enquiried = true;
                        table.addRow("" + i++, enquiry.getCamp().getCampName(), enquiry.getEnquiry(),
                                enquiry.getStudent().getName(),
                                (enquiry.getReply() != null ? enquiry.getReply() : "Not replied yet"));
                    }
                }
            }

        }
        if (!enquiried) {
            System.out.println("No enquiries have been made yet.");
        }
        table.print();
    }

    /**
     * Replies to a specific enquiry.
     *
     * @param user   The user (camp committee member) replying to the enquiry.
     * @param reply  The reply message.
     * @param index  The index of the enquiry to reply to.
     */
    public void replyEnquiries(User user, String reply, int index) {
        Student student = (Student) user;
        List<Enquiries> enquiriesList = dataHandler.getEnquiriesList();
        List<Camp> ownCampList = getCampCommOwnCamp(student);
        int true_index = index;
        for (int i = 0; i < enquiriesList.size(); i++) {
            Enquiries curr_Enquiries = enquiriesList.get(i);
            for (Camp c : ownCampList) {
                if (c.getCampName().equals(curr_Enquiries.getCamp().getCampName())
                        && student.getCampCommittee().equals(c.getCampName())) {
                    true_index--;
                    if (true_index == 0) {
                        if (enquiriesList.get(i).getReply() == "") {
                            if (enquiriesList.get(i).getStudent().getName().equals(student.getName())) {
                                System.out.println("Cannot reply to the same Enquiry");
                                return;
                            } else {
                                enquiriesList.get(i).setReply(reply);
                                dataHandler.setEnquiriesList(enquiriesList);
                                System.out.println("Enquiries successfully replied to!");
                                int point = student.getPoints();
                                student.setPoints(point + 1);
                                System.out.println("Pointed awards to " + student.getName());
                                return;
                            }
                        } else {
                            System.out.println("Enquiry has already been replied");
                            return;
                        }

                    }
                }
            }
        }
        System.out.println("You have not entered a valid index");
        return;
    }
}
