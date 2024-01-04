package src.Controller.Enquiries.Student;

import src.Tablecreation.Table;
import src.Controller.Enquiries.Data.EnquiriesDataHandler;
import src.Controller.Enquiries.Interfaces.IStudentEnquiriesController;
import src.Entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for handling student enquiries.
 * This class is responsible for managing the student's enquiries including retrieving, viewing, editing,
 * submitting, and deleting enquiries.
 */
public class StudentEnquiriesController implements IStudentEnquiriesController{
    private EnquiriesDataHandler dataHandler;

    public StudentEnquiriesController(EnquiriesDataHandler data) {
        super();
        this.dataHandler = data;
    }

    
    /**
     * Retrieves a list of enquiries made by a specific student.
     *
     * @param student The student whose enquiries are to be retrieved.
     * @return A list of Enquiries objects pertaining to the student.
     */
    public List<Enquiries> getStudentEnquiries(Student student) {
        List<Enquiries> studentsEnquiries = new ArrayList<>();
        for (Enquiries equiries : dataHandler.getEnquiriesList()) {
            if (equiries.getStudent().equals(student)) {
                studentsEnquiries.add(equiries);
            }
        }
        return studentsEnquiries;
    }
     /**
     * Retrieves a specific enquiry made by a student based on the index.
     *
     * @param student The student whose enquiry is to be retrieved.
     * @param index The index of the enquiry in the list.
     * @return The Enquiries object at the specified index.
     */
    public Enquiries getStudentEnquiries(Student student, int index) {
        return getStudentEnquiries(student).get(index);
    }

    /**
     * Displays all enquiries made by a student in a tabular format.
     *
     * @param user The user (student) whose enquiries are to be viewed.
     */
    public void viewEnquiries(User user) {
        Student student = (Student) user;
        if (getStudentEnquiries(student).isEmpty()) {
            System.out.println("This student has not made any enquiries");
            return;
        }
        int i = 1;
        Table table=new Table();
        table.setShowVerticalLines(true);
        table.setHeaders("Enquiry Number","Camp","Enquiry","Reply");
        for (Enquiries enquiry : getStudentEnquiries(student)) {
            table.addRow(""+i, enquiry.getCamp().getCampName(),enquiry.getEnquiry(),(enquiry.getReply() != null ? enquiry.getReply() : "Not replied yet"));
            i++;
        }
        table.print();
    }

    /**
     * Edits an existing enquiry made by a student.
     *
     * @param student The student editing the enquiry.
     * @param index The index of the enquiry to be edited.
     * @param newEnquiryText The new text for the enquiry.
     */
    public void editEnquiries(Student student, int index, String newEnquiryText) {
        if (newEnquiryText.length() == 0) {
            System.out.println("\nError: Enquiry has to be of non-zero length!\n");
            return;
        }
        List<Enquiries> enquiriesList = dataHandler.getEnquiriesList();
        int true_idx = index;
        if (index<=0||index > enquiriesList.size()) {
            System.out.println("\nError: Chosen index is out of range!\n");
            return;
        }
        for (int i = 0; i < enquiriesList.size(); i++) {
            if(enquiriesList.get(i).getStudent().getUserID().equals(student.getUserID())) {
                true_idx--;
                if (true_idx == 0) {
                    if (enquiriesList.get(i).getReply().equals("")) {
                        enquiriesList.get(i).setEnquiry(newEnquiryText); 
                        dataHandler.setEnquiriesList(enquiriesList);
                        return; 
                    }
                    else {
                        System.out.println("You can only edit your own enquiries before they are processed\n");
                        return;
                    }
                }
            }
        }
    }

    /**
     * Submits a new enquiry for a student regarding a specific camp.
     *
     * @param student The student submitting the enquiry.
     * @param camp The camp about which the enquiry is made.
     * @param enquiryText The text of the enquiry.
     */
    public void submitEnquiry(Student student, Camp camp, String enquiryText) {
        if (enquiryText.length() == 0) {
            System.out.println("Enquiry has to be of non-zero length!");
            return;
        }
        List<Enquiries> enquiryList = dataHandler.getEnquiriesList();
        Enquiries enquiry = new Enquiries(student, camp, enquiryText);
        enquiryList.add(enquiry);
        dataHandler.setEnquiriesList(enquiryList);
        System.out.println("Enquiry submitted successfully\n");
    }

    /**
     * Deletes an enquiry made by a student based on the given index.
     *
     * @param student The student whose enquiry is to be deleted.
     * @param index The index of the enquiry to be deleted.
     */
    public void deleteEnquiry(Student student, int index) {
        List <Enquiries> enquiriesList = dataHandler.getEnquiriesList();
        if (index > enquiriesList.size()) {
            System.out.println("\nError: Chosen index is out of range!\n");
            return;
        }
        int true_index = index;
        for (int i = 0; i < enquiriesList.size(); i++) {
            if (enquiriesList.get(i).getStudent().equals(student)) {
                true_index--;
                if (true_index == 0) {
                    enquiriesList.remove(i);
                    dataHandler.setEnquiriesList(enquiriesList);
                    System.out.println("Removed successfully");
                    return;
                }
            }
        }
    }
}