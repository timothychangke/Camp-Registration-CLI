package src.Controller;
import src.Entity.Student;
import java.util.*;
import java.io.*;

/**
 * The StudentDataHandler class is responsible for managing student data within the CAMs Registration System.
 * It reads student data from a CSV file and stores it in a list.
 */
public class StudentDataHandler {
    //used to store the data of the students who are registered within the camp registration system
    private List<Student> studentList;
    //used to read from student_csv file and storing the data in studentList
    public StudentDataHandler(String file_path) throws Exception
    {
        //used for reading student_list.csv file
        studentList = new ArrayList<Student>();
        String splitBy = ",";  
        String line = "";  

        //Scanner sc = new Scanner(new File("src/db/staff_list.csv"));
        BufferedReader br = new BufferedReader(new FileReader(file_path));  
        int z = 0;
        while ((line = br.readLine()) != null)   //returns a Boolean value  
        {  
            if (z == 0) // To remove the header row 
            {
                z++;
                continue;
            }
            String[] student_details = line.split(splitBy);    // use comma as separator  
            String name = student_details[0];
            String userID = student_details[1].split("@")[0];
            String faculty = student_details[2];
            String password = student_details[3];
            Student student = new Student(userID, name, password, faculty, null);
            studentList.add(student);
        }  
        br.close();
    }

    
    /** 
     * @return List<Student>
     */
    public List<Student> getStudentList()
    {
        //returns the list of students who have an account
        return this.studentList;
    }
}
