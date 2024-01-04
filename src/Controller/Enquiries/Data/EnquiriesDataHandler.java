package src.Controller.Enquiries.Data;
import src.Entity.Enquiries;
import java.util.*;

/**
 * Data handler class for managing the list of enquiries.
 * This class is responsible for various operations on the enquiries list, such as retrieval, addition, deletion, and checks.
 */
public class EnquiriesDataHandler {
    private List<Enquiries> enquiriesList;

    public EnquiriesDataHandler()
    {
        enquiriesList = new ArrayList<Enquiries>();
    }

    
    /**
     * Retrieves the current list of enquiries.
     *
     * @return A list of Enquiries objects.
     */
    public List<Enquiries> getEnquiriesList()
    {
        return enquiriesList;
    }

    public void setEnquiriesList(List<Enquiries> newList)
    {
        this.enquiriesList = newList;
    }

    public void addToEnquiriesList(Enquiries enquiry) {
        this.enquiriesList.add(enquiry);
    }

    public void deleteEnquiry(Enquiries enquiry) {
        this.enquiriesList.remove(enquiry);
    }

    public boolean checkEmpty() {
        return this.enquiriesList.isEmpty();
    }

    public int listLength() {
        return this.enquiriesList.size();
    }

    public int findEnquiriy(Enquiries enquiry) {
        return this.enquiriesList.indexOf(enquiry);
    }
}
