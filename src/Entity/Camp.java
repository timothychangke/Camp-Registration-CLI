package src.Entity;
import java.util.*;

/**
 * The Camp class includes information such as camp name, dates, location, slots, and description.
 */
public class Camp {
    private String campName;
    private String starting_date;
    private String ending_date;
    private String registrationClosingDate;
    private String userGroup;
    private String location;
    private int totalSlots;
    private int committeeSlots;
    private String description;
    private String staffInCharge;
    private List<Student> listOfStudents;
    private boolean visibility = true;
    private int availableSlots;

    public Camp(String campName, 
                String starting_date,
                String ending_date, 
                String registrationClosingDate,
                String userGroup,
                String location,
                int totalSlots,
                int committeeSlots,
                String description,
                String staffInCharge,
                boolean visibility)
                {
                    this.campName = campName;
                    this.starting_date = starting_date;
                    this.ending_date = ending_date;
                    this.registrationClosingDate = registrationClosingDate;
                    this.userGroup = userGroup;
                    this.location = location;
                    this.totalSlots = totalSlots;
                    this.committeeSlots = committeeSlots;
                    this.description = description;
                    this.staffInCharge = staffInCharge;
                    this.listOfStudents = new ArrayList<Student>();
                    this.visibility = visibility;
                    this.availableSlots = totalSlots;
                }

        
        /** 
         * @return String
         */
        public String getCampName()
        {
            return this.campName;
        }

        public void setCampName(String name)
        {   
            this.campName = name;
        }

        public String getStartingDate()
        {
            return this.starting_date;
        }

        public void setStartingDate(String start)
        {
            this.starting_date = start;
        }

        public String getEndingDate()
        {
            return this.ending_date;
        }

        public void setEndingDate(String end)
        {
            this.ending_date = end;
        }

        public String getRegistrationClosingDate()
        {
            return this.registrationClosingDate;
        }

        public void setRegistrationClosingDate(String date)
        {
            this.registrationClosingDate = date;
        }

        public String getUserGroup()
        {
            return this.userGroup;
        }

        public void setUserGroup(String usergroup)
        {
            this.userGroup = usergroup;
        }

        public String getLocation()
        {
            return this.location;
        }

        public void setLocation(String location)
        {
            this.location = location;
        }

        public int getTotalSlots()
        {
            return this.totalSlots;
        }

        public void setTotalSlots(int slots)
        {
            this.totalSlots = slots;
            this.availableSlots = slots - listOfStudents.size();
        }

        public int getCommitteeSlots()
        {
            return this.committeeSlots;
        }

        public void setCommitteeSlots(int slots)
        {
            this.committeeSlots = slots;
        }

        public String getDescription()
        {
            return this.description;
        }

        public void setDescription(String desc)
        {
            this.description = desc;
        }

        public String getStaffInCharge()
        {
            return this.staffInCharge;
        }

        public void setStaffInCharge(String staff)
        {
            this.staffInCharge = staff;
        }

        public List<Student> getListOfStudents()
        {
            return this.listOfStudents;
        }

        public void setListOfStudents(List<Student> Students)
        {
            this.listOfStudents = Students;
        }

        public boolean getVisibility()
        {
            return this.visibility;
        }

        public void setVisibility(boolean change)
        {
            this.visibility = change;
        }

        public int getAvailableSlots()
        {
            return this.availableSlots;
        }

        public void setAvailableSlots(int slots)
        {
            this.availableSlots = slots;
        }
}
