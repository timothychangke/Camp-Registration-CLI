package src.Boundary.Report;

import src.Boundary.DisplayMenu.Student.StudentCLI;
import src.Controller.Camp.Student.StudentCampController;
import src.Controller.Report.Camp.CampReportController;
import src.Controller.Report.Interface.*;
import src.Controller.Camp.Interfaces.*;
import src.Entity.*;
import java.util.*;

/**
 * The CampCommReportCLI class represents the user interface for generating camp reports as a camp committee member.
 */

public class CampCommReportCLI extends StudentCLI{
    private Student student;
    private CampReportController campReportController;
    private IStudentCampController studentCampController;

    public CampCommReportCLI (Student student, CampReportController campReportController, IStudentCampController studentCampController)
    {
        this.campReportController = campReportController;
        this.student = student;
        this.studentCampController = studentCampController;
    }

    public void display()
    {
        System.out.println("====Report Management====");
        System.out.println("1. Generate Camp Report");
        System.out.println("2. Generate Filtered Camp Report");
        System.out.println("3. Exit");
        System.out.println("========================");
    }

    
   /**
     * Generates a filtered camp report based on attendee or camp committee members.
     *
     * @param camp The camp for which the report is generated.
     */
    public void generateFilteredCampReport(Camp camp)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("====Choose Filter====");
        System.out.println("1.Attendee");
        System.out.println("2.Camp Comm Members");
        while(!in.hasNextInt()){
            System.out.println("You entered an invalid output choose the filter again");
            in.next();
        }
        int choice = in.nextInt();
        if(choice == 1)
        {
            System.out.println(campReportController.generateReport(camp,"Attendee"));
        }
        else if (choice == 2)
        {
            System.out.println(campReportController.generateReport(camp,"Camp Committee"));
        }
        else{
            System.out.println("You have input an invalid choice");
        }
    }

    /**
     * Generates a camp report for the specified camp.
     *
     * @param camp The camp for which the report is generated.
     */
    public void generateCampReport(Camp camp)
    {

        System.out.println(campReportController.generateReport(camp));
    }

    public void main()
    {
        Scanner sc = new Scanner(System.in);
        display();
        while(!sc.hasNextInt()){
            System.out.println("You entered an invalid option");
            sc.next();
        }
        int option = sc.nextInt();
        //List<Camp> camp_list = studentCampController.viewRegisteredCamps(student);
        List<Camp> camp_list = student.getRegisteredCamps();
        for(Camp c : camp_list)
        {
            if (c.getCampName().equals(student.getCampCommittee()))
            {   
                if(option == 1){
                    generateCampReport(c);
                    break;
                }
                else if (option == 2){
                    generateFilteredCampReport(c);
                    break;
                }
            }
        }
    }

}
