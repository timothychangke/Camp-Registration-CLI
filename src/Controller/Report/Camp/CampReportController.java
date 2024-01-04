package src.Controller.Report.Camp;

import java.util.*;

import src.Controller.Report.Interface.ICampReportController;
import src.Entity.*;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;

/**
 * The CampReportController class is responsible for generating camp reports and saving them to files.
 */
public class CampReportController implements ICampReportController{

    public CampReportController(){
        return;
    }

    
    /**
     * Generates a camp report for the specified camp and saves it to a file.
     *
     * @param c The Camp object for which to generate the report.
     * @return A String containing the generated camp report.
     */
    public String generateReport(Camp c){
        
        //no filter 
        String string = new String("");
        String filename = c.getCampName()+".txt";
        String dirName = "src/db";
        
            //add all camp details into file
            List<Student> student_list = c.getListOfStudents(); //get list of students
            string = String.join("\n","Camp Name: "+c.getCampName(),"Starting Date: "+c.getStartingDate(),"Ending Date: "+c.getEndingDate(),"Registration closing date: "+c.getRegistrationClosingDate(),"User Group: "+c.getUserGroup(),"Location: "+c.getLocation(),"Available Slots: "+Integer.toString(c.getAvailableSlots()),"Total Slots: "+Integer.toString(c.getTotalSlots()),"Camp Committee Slots: "+Integer.toString(c.getCommitteeSlots()),"Description: "+c.getDescription(),"StaffInCharge: "+c.getStaffInCharge(),"Visibility: "+Boolean.toString(c.getVisibility()));

            for(int i =0; i<student_list.size();i++){
                    Student s = student_list.get(i);
                    if (c.getCampName().equals(s.getCampCommittee())){
                        string = String.join(" ",string,"\n","Camp Committee Member: ", s.getName());
                    }
                    else{
                        string = String.join(" ",string,"\n","Attendee: ", s.getName());
                    }
                }
            try {
            File dir = new File(dirName);
            File file = new File(dir,filename);
            if (!file.isFile()) {
              file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            pw.println(string);
            pw.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return string;

    }

    /**
     * Generates a camp report for the specified camp and filter type and saves it to a file.
     *
     * @param c      The Camp object for which to generate the report.
     * @param filter The filter type for the report (e.g., "Attendee" or "Camp Committee").
     * @return A String containing the generated camp report.
     */
    public String generateReport(Camp c,String filter){
        
        String string = new String("");
        String filename = c.getCampName()+".txt";
        String dirName = "src/db";
        
        //add all camp details into string
        string = String.join("\n","Camp Name: "+c.getCampName(),"Starting Date: "+c.getStartingDate(),"Ending Date: "+c.getEndingDate(),"Registration closing date: "+c.getRegistrationClosingDate(),"User Group: "+c.getUserGroup(),"Location: "+c.getLocation(),"Available Slots: "+Integer.toString(c.getAvailableSlots()),"Total Slots: "+Integer.toString(c.getTotalSlots()),"Camp Committee Slots: "+Integer.toString(c.getCommitteeSlots()),"Description: "+c.getDescription(),"StaffInCharge: "+c.getStaffInCharge(),"Visibility: "+Boolean.toString(c.getVisibility()));
        
        List<Student> student_list = c.getListOfStudents(); //get list of students
        
        if(filter.equals("Attendee")){ //filter for Attendees
            for(Student s:student_list){
                if (!c.getCampName().equals(s.getCampCommittee())){
                    string = String.join("\n",string,s.getName());
                    string = String.join("",string, "Attendee");
                }

            }
        }
        else if(filter.equals("Camp Committee")){ //filter for Camp Comm
            for(Student s:student_list){
                if (c.getCampName().equals(s.getCampCommittee())){
                    string = String.join("\n",string,s.getName());
                    string = String.join("",string, "Camp Committee Member");
                }
            }

            }
            try {
            File dir = new File(dirName);
            File file = new File(dir,filename);
            if (!file.isFile()) {
              file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            pw.println(string);
            pw.close();
          } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
            
        return string;

    }

}