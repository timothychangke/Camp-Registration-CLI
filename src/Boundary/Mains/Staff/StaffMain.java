package src.Boundary.Mains.Staff;

import src.Entity.*;
import src.Controller.changePasswordController;
import src.Controller.Camp.Data.CampDataHandler;
import src.Controller.Camp.Interfaces.IStaffCampController;
import src.Controller.Camp.Staff.StaffCampController;
import src.Controller.Enquiries.Data.EnquiriesDataHandler;
import src.Controller.Enquiries.Interfaces.IStaffEnquiriesController;
import src.Controller.Enquiries.Staff.StaffEnquiriesController;
import src.Controller.Report.Camp.CampReportController;
import src.Controller.Report.Interface.ICampPerformanceReportController;
import src.Controller.Report.Interface.ICampReportController;
import src.Controller.Report.Performance.PerformanceReportController;
import src.Controller.Suggestions.Interfaces.IStaffSuggestionsController;
import src.Controller.Suggestions.Staff.StaffSuggestionsController;
import src.Controller.Suggestions.data.SuggestionsDataHandler;
import src.Boundary.Camps.Staff.StaffCampCLI;
import src.Boundary.DisplayMenu.Staff.StaffCLI;
import src.Boundary.Enquiries.Staff.StaffEnquiriesCLI;
import src.Boundary.Mains.IMain;
import src.Boundary.Suggestions.Staff.StaffSuggestionsCLI;
import src.Boundary.Report.*;

/**
 * The StaffMain class represents the main entry point and user interaction handler
 * for staff members in the CAMs Registration System. It allows staff members to access
 * and manage various functionalities, including camp management, inquiries, suggestions,
 * and generating reports.
 */
public class StaffMain implements IMain{
    private Staff staff;
    private CampDataHandler campDataHandler;
    private SuggestionsDataHandler suggestionsDataHandler;
    private EnquiriesDataHandler enquiriesDataHandler;

    public StaffMain(Staff staff, CampDataHandler campDataHandler, SuggestionsDataHandler suggestionsDataHandler, EnquiriesDataHandler enquiriesDataHandler) {
        this.staff = staff;
        this.campDataHandler = campDataHandler;
        this.suggestionsDataHandler = suggestionsDataHandler;
        this.enquiriesDataHandler = enquiriesDataHandler;
    }

    
    /**
     * Handles the user interaction and provides access to various functionalities for staff members.
     *
     * @return An integer indicating the status of the interaction. 0 represents a successful
     *         completion of the interaction, and any other value indicates an ongoing interaction.
     */
    public int cli() {
        IStaffCampController staffCampController = new StaffCampController(campDataHandler);
        StaffCLI staffCLI = new StaffCLI();
        IStaffSuggestionsController staffSuggestionController = new StaffSuggestionsController(suggestionsDataHandler);
        int option = staffCLI.StaffMenu();
        switch (option) {
            case 1:
            changePasswordController cp = new changePasswordController(staff);
            case 2:
                StaffCampCLI staffCampCLI = new StaffCampCLI(staff, staffCampController);
                staffCampCLI.main();
                break;
            case 3:
                IStaffEnquiriesController staffEnquiriesController = new StaffEnquiriesController(enquiriesDataHandler);
                StaffEnquiriesCLI staffEnquiriesCLI = new StaffEnquiriesCLI(staffEnquiriesController);
                staffEnquiriesCLI.main(staff);
                break;
            case 4:
                StaffSuggestionsCLI staffSuggestionsCLI = new StaffSuggestionsCLI(staff,
                        staffSuggestionController);
                staffSuggestionsCLI.main();
                break;
            case 5:
                ICampReportController campReportController = new CampReportController();
                ICampPerformanceReportController performanceReportController = new PerformanceReportController();
                StaffReportCLI staffReportCLI = new StaffReportCLI(staff, campReportController, performanceReportController, staffCampController);
                staffReportCLI.main();
                break;
                
            case 6:
                System.out.print("Thank you for using the CAMs system. Have a nice day.");
                return 0;
            default: System.out.println("You have inputted an invalid option!");
        }
        return 1;
    }
}
