package src.Controller.Camp.Student;
import java.util.*;
import src.Tablecreation.Table;
import src.Controller.Camp.CampController;
import src.Controller.Camp.Data.CampDataHandler;
import src.Controller.Camp.Interfaces.IStudentCampController;
import src.Entity.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import src.Utils.compareDate;
/**
 * Represents a controller for managing student interactions with camps.
 * This class extends CampController and implements IStudentCampController.
 */
public class StudentCampController extends CampController implements IStudentCampController{
    protected CampDataHandler campdatahandler;

    public StudentCampController(CampDataHandler campdatahandler)
    {
        super(campdatahandler);
        this.campdatahandler = campdatahandler;
    }

    
 /**
     * Retrieves a list of camps available for a given student based on the student's faculty.
     * Only camps that are visible, have slots available, and match the student's faculty are included.
     *
     * @param s The student for whom to retrieve available camps.
     * @return A list of Camp objects representing the available camps for the student.
     */
    public List<Camp> getCamp(Student s)
    {
        List<Camp> available_camp_list = new ArrayList<Camp>();
        List<Camp> camp_list = this.campdatahandler.getCampList();
        for (Camp c : camp_list){
            if (c.getUserGroup().equals(s.getFaculty()) && c.getVisibility() == true && c.getTotalSlots() > 0)
            {
                available_camp_list.add(c);            }
        }
        return available_camp_list;
    }

    
   /**
     * Displays available camps for a student with the option to filter the camps by location, camp name, or date.
     * The camps are displayed in a table format with detailed information.
     *
     * @param s       The student for whom the camps are to be displayed.
     * @param filter  The filter criterion ("Location", "Camp Name", "Date", or other).
     * @param option  The specific filter option (e.g., location name, camp name, date range).
     */
    public void viewCamp(Student s, String filter, String option)
    {
        List<Camp> available_camp_list = new ArrayList<Camp>();
        List<Camp> camp_list = this.campdatahandler.getCampList();
        Table table=new Table();
        table.setHeaders("Camp name","Camp Dates","Registration Closing Date","Location","Available slots", "Description","Staff in Charge");
        table.setShowVerticalLines(true);
        switch(filter)
        {
            case "Location": // filtering for location 
                for (Camp c : camp_list){
                    if (c.getLocation().equals(option) && c.getUserGroup().equals(s.getFaculty()) && c.getVisibility() == true && c.getTotalSlots() >= 0)
                    {
                        available_camp_list.add(c);
                        table.addRow(c.getCampName(), c.getStartingDate()+"~"+c.getEndingDate(),c.getRegistrationClosingDate(),c.getLocation(),""+c.getAvailableSlots(),c.getDescription(),c.getStaffInCharge());
                    }
                }
                break;
            case "Camp Name": // filter by camp name
                for (Camp c : camp_list){
                    if (c.getCampName().equals(option) && c.getUserGroup().equals(s.getFaculty()) && c.getVisibility() == true && c.getTotalSlots() >= 0)
                    {
                        available_camp_list.add(c);
                        table.addRow(c.getCampName(), c.getStartingDate()+"~"+c.getEndingDate(),c.getRegistrationClosingDate(),c.getLocation(),""+c.getAvailableSlots(),c.getDescription(),c.getStaffInCharge());
                    }
                }
                break;
            case "Date": // filter by date
                compareDate cd = new compareDate();
                String[] splitted = option.split(",");
                String start_date = splitted[0];
                String end_date = splitted[1];
                
                for (Camp c : camp_list){
                    if (cd.compare(start_date, c.getStartingDate())<0 && cd.compare(end_date, c.getEndingDate())>0 && c.getUserGroup().equals(s.getFaculty()) && c.getVisibility() == true && c.getTotalSlots() >= 0)
                    {
                        available_camp_list.add(c);
                        table.addRow(c.getCampName(), c.getStartingDate()+"~"+c.getEndingDate(),c.getRegistrationClosingDate(),c.getLocation(),""+c.getAvailableSlots(),c.getDescription(),c.getStaffInCharge());
                    }
                }
                break;
            default:
            for (Camp c : camp_list){
                if (c.getUserGroup().equals(s.getFaculty()) && c.getVisibility() == true && c.getTotalSlots() >= 0)
                {
                    available_camp_list.add(c);
                    table.addRow(c.getCampName(), c.getStartingDate()+"~"+c.getEndingDate(),c.getRegistrationClosingDate(),c.getLocation(),""+c.getAvailableSlots(),c.getDescription(),c.getStaffInCharge());

                }
            }
        }
        table.print();
    }

    
    /**
     * Registers a student for a camp and optionally as a camp committee member.
     * Checks various conditions like registration deadlines, available slots, and faculty restrictions.
     * Also ensures no date clashes with other registered camps.
     *
     * @param s               The student registering for the camp.
     * @param c               The camp for which the student is registering.
     * @param committeeChoice Boolean flag indicating if the student also wants to join the camp committee.
     */
    public void registerSlot(Student s, Camp c, boolean committeeChoice) // true to join the committee
    {
        // Checking if student has already registered for camp
        for (Camp cc: s.getRegisteredCamps())
        {
            if (cc.getCampName().equals(c.getCampName()))
            {
                System.out.println("Student has already registered for this camp!");
                return;
            }
        }

        // Checking if deadline has passed
        compareDate cd = new compareDate();
        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int result = cd.compare(current_date, c.getRegistrationClosingDate());
        if (result > 0){
            System.out.println("The deadline has passed!");
            return;
        }
        
        // Checking if there are available slots left 
        if (c.getAvailableSlots() <= 0){
            System.out.println("There are no more slots left. Sorry!");
            return;
        }

        // Checking if camp is his faculty's
        if (c.getUserGroup().equals(s.getFaculty()) == false)
        {
            System.out.println("You cannot register for other schools' camp!");
            return;
        }

        // Checking for any date clashes 
        List<Camp> registeredCampList = s.getRegisteredCamps();
        for (Camp cc: registeredCampList){
            if (cd.checkForClash(cc.getStartingDate(), cc.getEndingDate(), c.getStartingDate(), c.getEndingDate())==0)
            {
                System.out.println("There is a clash in date!");
                return;
            }
        }

        // Checking if student has withdrawn from this camp before
        List<Camp> signedUpCamps = s.getSignedUpCamps();
        for (Camp ccc: signedUpCamps){
            if (ccc.getCampName().equals(c.getCampName())){
                System.out.println("Student has withdrawn from this camp before. Cannot register again!");
                return;
            }
        }

        // Checking if student is already in another committee
        if (committeeChoice){
            if (s.getCampCommittee() != null)
            {
                System.out.println("Student is already in another camp's committee. Unable to join!");
                return;
            }
            else{
                // mark him as committee of this camp
                if(c.getCommitteeSlots()<=0){
                    System.out.println("No Camp Committee Slots available");
                    return;
                }
                s.setCampComittee(c.getCampName());
                int comm_slots = c.getCommitteeSlots();
                c.setCommitteeSlots(comm_slots-1);
            }
        }      

        // Student can be registered!

        System.out.println("Succesfully Registered!");
        List<Camp> registeredcamp = s.getRegisteredCamps();
        registeredcamp.add(c);
        s.setRegisteredCamps(registeredcamp);
        List<Camp> signedupcamps = s.getSignedUpCamps();
        signedupcamps.add(c);
        s.setSignedUpCamps(signedupcamps);

        // writing in student into camp's student list 
        List<Student> student_list = c.getListOfStudents();
        student_list.add(s);
        c.setListOfStudents(student_list);

        // deducting from camp's available slot
        int slots = c.getAvailableSlots();
        c.setAvailableSlots(slots-1);

        // altering student's camp committee status as per the input parameter 
        if (committeeChoice){
            s.setCampComittee(c.getCampName());
        }

    }

    
   /**
     * Allows a student to withdraw from a registered camp.
     * Ensures the student is currently registered for the camp and not a committee member of the camp.
     *
     * @param s The student who wishes to withdraw from the camp.
     * @param c The camp from which the student wishes to withdraw.
     */
    public void withdrawCamp(Student s, Camp c)
    {
        // Ensuring student has signed up for a camp
        if (s.getRegisteredCamps().size() == 0)
        {
            System.out.println("Student has not signed up for any camps!");
            return;
        }
        
        if (s.getCampCommittee()!=null&&s.getCampCommittee().equals(c.getCampName()))
        {
            System.out.println("Student is a committee member for this camp. Unable to withdraw!");
            return;
        }
        // delete student from camp's student list 
        List<Student> student_list = c.getListOfStudents();
        for (Student ss : student_list){
            if (ss == s)
            {
                student_list.remove(ss);
                c.setListOfStudents(student_list);
                List<Camp> camp_list = s.getRegisteredCamps();
                // delete camp from student's registeredCamps list
                for (Camp cc: camp_list)
                {
                    if (cc.equals(c)){
                        camp_list.remove(cc);
                        s.setRegisteredCamps(camp_list);
                        // increase available slot of camp by 1
                        int slots = c.getAvailableSlots();
                        c.setAvailableSlots(slots+1);

                        // check if student was a camp committee member for this camp
                        if (c.getCampName().equals(s.getCampCommittee())){
                            s.setCampComittee(null); // reset camp committee status
                            int comm_slots = c.getCommitteeSlots(); // add 1 to the number of committee slots still available for that camp
                            c.setCommitteeSlots(comm_slots+1);
                        }
                        System.out.println("Student successfully withdrawn!");
                        return;
                    }
                }
                System.out.println("This student has not registered for this camp!");
                return;
            }
            System.out.println("This student has not registered for this camp!");
            return;
        }
        System.out.println("Student was not successfully withdrawn!");
    }

    
    /**
     * Displays a list of camps that a student has registered for.
     * Shows the camp name and the student's role (attendee or committee member) in a table format.
     *
     * @param s The student whose registered camps are to be displayed.
     * @return A list of Camp objects representing the camps the student is registered for.
     */
    public List<Camp> viewRegisteredCamps(Student s)
    {
        Table table=new Table();
        table.setHeaders("Camp Name","Role");
        table.setShowVerticalLines(true);
        for (Camp c: s.getRegisteredCamps()){
            
            String role = "Attendee";
            if (s.getCampCommittee()!=null&&s.getCampCommittee().equals(c.getCampName())){
                role = "Camp Committee Member";
            }
            table.addRow(c.getCampName(),role);
        }
        table.print();
        return s.getRegisteredCamps();
    }
}
