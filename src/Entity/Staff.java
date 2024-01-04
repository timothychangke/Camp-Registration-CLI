package src.Entity;
import java.util.*;
import src.Controller.Camp.Data.Compare;

/**
 * The Student class represents a user who is a staff member.
 * It extends the User class and includes additional attributes and methods related to staff.
 */
public class Staff extends User{
    private List<Camp> ownCampList;

    public Staff(String userID, String name, String password, String faculty)
    {
        super(userID, name, password, faculty);
        ownCampList = new ArrayList<Camp>();
    }

    
    /** 
     * @param camp
     */
    public void addOwnCamp(Camp camp)
    {
        this.ownCampList.add(camp);
    }

    public List<Camp> getOwnCamps()
    {
        Collections.sort(ownCampList,new Compare());
        return ownCampList;
    }
    
    public void deleteOwnCamp(int index)
    {
        this.ownCampList.remove(index);
    }

    public void setOwnCamp(List<Camp> new_camp)
    {
        this.ownCampList = new_camp;
    }
}
