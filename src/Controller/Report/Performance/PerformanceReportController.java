package src.Controller.Report.Performance;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import src.Controller.Report.Interface.ICampPerformanceReportController;
import src.Entity.*;

/**
 * The PerformanceReportController class is responsible for generating
 * camp committee performance reports.
 */
public class PerformanceReportController implements ICampPerformanceReportController{
    
    
    /**
     * Generates a performance report for a given camp committee and saves it to a file.
     *
     * @param c The Camp object representing the camp committee.
     * @return A String containing the generated performance report.
     */
    public String generateReport(Camp c){
        String string = new String("Camp Commitee Performance Report");
        
        List<Student> student_list = c.getListOfStudents(); //get list of students
        for(Student s:student_list){
            if (c.getCampName().equals(s.getCampCommittee())){
                string = String.join("\n",string,s.getName());
                string = String.join(" ",string,Integer.toString(s.getPoints()));
            }
        }
        String filename = c.getCampName()+".txt";
        String dirName = "src/db";
        try {
            File dir = new File(dirName);
            File performancefile = new File(dir,filename);
            if (!performancefile.isFile()) {
              performancefile.createNewFile();
            }
            PrintWriter pw = new PrintWriter(performancefile);
            pw.println(string);
            pw.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return string;
    }
}
