package src.Entity;
import java.util.*;
import src.Controller.Camp.Data.Compare;

/**
 * The Student class represents a user who is a student and may be a camp committee member.
 * It extends the User class and includes additional attributes and methods related to students.
 */
public class Student extends User{
    private String campCommittee = null; // will contain the campName for which he is the campcomm of
    private List<Camp> registeredCamps;
    private List<Camp> signedUpCamps;
    private int points = 0;
    
    public Student(String userID, String name, String password, String faculty, String campCommittee)
    {
        super(userID, name, password, faculty);
        this.campCommittee = campCommittee;
        registeredCamps = new ArrayList<Camp>();
        signedUpCamps = new ArrayList<Camp>();
    }

    
    /** 
     * @return int
     */
    public int getPoints()
    {
        return this.points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public String getCampCommittee()
    {
        return this.campCommittee;
    }

    public void setCampComittee(String choice)
    {
        this.campCommittee = choice;
    }

    public List<Camp> getRegisteredCamps()
    {
        
        return this.registeredCamps;
    }

    public void setRegisteredCamps(List<Camp> temp)
    {
        Collections.sort(temp,new Compare());
        this.registeredCamps = temp;
    }

    public List<Camp> getSignedUpCamps()
    {
        return this.signedUpCamps;
    }

    public void setSignedUpCamps(List<Camp> temp)
    {
        this.signedUpCamps = temp;
    }
}
