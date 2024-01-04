package src.Controller;
import src.Entity.Staff;
import java.util.*;
import java.io.*;

public class StaffDataHandler {
    //used to store all the staff registered with the Camp Registration System 
    private List<Staff> staffList;

    /**Used to read staff data from staff_list 
     * @param file_path
     * @throws Exception
     */
    public StaffDataHandler(String file_path) throws Exception  
    {
        staffList = new ArrayList<Staff>();
        String splitBy = ",";  
        String line = "";  

        BufferedReader br = new BufferedReader(new FileReader(file_path));  
        int z = 0;
        while ((line = br.readLine()) != null)   //returns a Boolean value  
        {  
            if (z == 0) // To remove the header row 
            {
                z++;
                continue;
            }
            String[] staff_details = line.split(splitBy);    // use comma as separator  
            String name = staff_details[0];
            String userID = staff_details[1].split("@")[0];
            String faculty = staff_details[2];
            String password = staff_details[3];
            Staff staff = new Staff(userID, name, password, faculty);
            staffList.add(staff);
        }  
        br.close();
    }

    /**This returns the list of staff registered with the camp
     * @return
     */
    public List<Staff> getStaffList()
    {
        //return list of staff who are registered with the camp system
        return this.staffList;
    }
}
