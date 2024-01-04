package src.Boundary.Report;
import java.util.*;
import src.Entity.*;
import src.Boundary.DisplayMenu.Staff.StaffCLI;
import src.Controller.Camp.Interfaces.IStaffCampController;
import src.Controller.Report.Interface.ICampPerformanceReportController;
import src.Controller.Report.Interface.ICampReportController;

/**
 * The StaffReportCLI class represents the user interface for generating reports as a staff member.
 */
public class StaffReportCLI extends StaffCLI
{
    private Staff staff;
    private ICampReportController campReportController;
    private ICampPerformanceReportController performanceReportController;
    private IStaffCampController staffCampController;

    public StaffReportCLI (Staff staff, ICampReportController campReportController, ICampPerformanceReportController performanceReportController, IStaffCampController staffCampController)
    {
        this.campReportController = campReportController;
        this.staff = staff;
        this.performanceReportController = performanceReportController;
        this.staffCampController = staffCampController;
    }

    public void display()
    {
        System.out.println("====Report Management====");
        System.out.println("1. Generate Camp Report");
        System.out.println("2. Generate Performance Report");
        System.out.println("3. Generate Filtered Camp Report");
        System.out.println("4. Exit");
        System.out.println("========================");
    }
    
    
    /** 
     * @param camp
     */
    public void generateCampReport(Camp camp)
    {

        System.out.println(campReportController.generateReport(camp));
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
            System.out.println("Invalid option");
        }
    }
    public void main()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("====Report Management====");
        List<Camp> camp_list = staffCampController.viewOwnCamps(staff);
        if (camp_list.size()==0)
        {
            System.out.println("You have no camps currently");
            return;
        }
        else
        {   
            System.out.println("Select camp to generate report");
            int i = 1;
            for (Camp c: camp_list)
                {
                       System.out.println("Camp number: "+(i++)+" Camp name: " + c.getCampName() );
                }        
        }
        int campNo = in.nextInt();
        Camp camp = camp_list.get(campNo-1);
        this.display();
        int option;
        do{
            while(!in.hasNextInt()){
                System.out.println("You entered an invalid option");
                in.next();
            }
            option = in.nextInt();
            switch(option)
            {
                case 1:
                    generateCampReport(camp);
                    break;
                case 2:
                    System.out.println(performanceReportController.generateReport(camp));  
                    break;
                case 3:
                    generateFilteredCampReport(camp);
                    break;
            }
            
            this.display();
        }while (option !=4);
    }
}


