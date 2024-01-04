package src.Boundary.Mains.CampCommiteeMember;

import src.Entity.*;
import src.Boundary.Camps.Student.StudentCampCLI;
import src.Boundary.DisplayMenu.CampCommiteeMember.CampCommCLI;
import src.Boundary.Enquiries.StudentCampCommEnquiriesCLI;
import src.Boundary.Enquiries.CampCommitteMember.CampCommEnquiriesCLI;
import src.Boundary.Enquiries.Student.StudentEnquiryCLI;
import src.Boundary.Mains.IMain;
import src.Boundary.Report.CampCommReportCLI;
import src.Boundary.Suggestions.Student.StudentSuggestionsCLI;
import src.Controller.changePasswordController;
import src.Controller.Camp.Data.CampDataHandler;
import src.Controller.Camp.Interfaces.IStudentCampController;
import src.Controller.Camp.Student.StudentCampController;
import src.Controller.Enquiries.CampCommiteeMember.CampCommEnquiriesController;
import src.Controller.Enquiries.Data.EnquiriesDataHandler;
import src.Controller.Enquiries.Interfaces.ICampCommEnquiriesController;
import src.Controller.Enquiries.Interfaces.IStudentEnquiriesController;
import src.Controller.Enquiries.Student.StudentEnquiriesController;
import src.Controller.Report.Camp.CampReportController;
import src.Controller.Suggestions.CampCommitteeMember.CampCommitteeSuggestionsController;
import src.Controller.Suggestions.Interfaces.ICampCommitteeSuggestionsController;
import src.Controller.Suggestions.data.SuggestionsDataHandler;

/**
 * The main class for Camp Committee Members, providing access to various functionalities.
 */
public class CampCommMain implements IMain {
    private Student student;
    private CampDataHandler campDataHandler;
    private SuggestionsDataHandler suggestionsDataHandler;
    private EnquiriesDataHandler enquiriesDataHandler;

    public CampCommMain(Student student, CampDataHandler campDataHandler,
            SuggestionsDataHandler suggestionsDataHandler, EnquiriesDataHandler enquiriesDataHandler) {
        this.student = student;
        this.campDataHandler = campDataHandler;
        this.suggestionsDataHandler = suggestionsDataHandler;
        this.enquiriesDataHandler = enquiriesDataHandler;
    }

    
    /** 
     * @return int
     */
    public int cli() {
        IStudentCampController studentCampController = new StudentCampController(campDataHandler);
        CampCommCLI campCommCLI = new CampCommCLI();
        ICampCommitteeSuggestionsController compSuggestionController = new CampCommitteeSuggestionsController(
                suggestionsDataHandler);
        while (true) {
            int option = campCommCLI.StudentMenu();
            switch (option) {
                case 1:
                    changePasswordController cp = new changePasswordController(student);
                    break;
                case 2:
                    StudentCampCLI studentCampCLI = new StudentCampCLI(student,
                            studentCampController);
                    studentCampCLI.main();
                    break;
                case 3: // Enquiry
                StudentCampCommEnquiriesCLI studentCampCommEnquiriesCLI = new StudentCampCommEnquiriesCLI();
                int enquiryOption = studentCampCommEnquiriesCLI.enquiryPageOption();
                if (enquiryOption == 1) {
                    IStudentEnquiriesController studentEnquiriesController = new StudentEnquiriesController(
                            enquiriesDataHandler);
                    StudentEnquiryCLI studentEnquiryCLI = new StudentEnquiryCLI(student,
                            studentEnquiriesController, campDataHandler);
                    studentEnquiryCLI.main(student);
                    break;
                } else if (enquiryOption == 2) {
                    ICampCommEnquiriesController campCommEnquiriesController = new CampCommEnquiriesController(
                            enquiriesDataHandler);
                    CampCommEnquiriesCLI campCommEnquiriesCLI = new CampCommEnquiriesCLI(
                            campCommEnquiriesController);
                    campCommEnquiriesCLI.main(student);
                    break;
                }
                break;
                case 4:
                    StudentSuggestionsCLI studentSuggestionsCLI = new StudentSuggestionsCLI(student,
                            compSuggestionController);
                    studentSuggestionsCLI.main(student);
                    break;
                case 5:
                    CampReportController campReportController = new CampReportController();
                    CampCommReportCLI campCommReportCLI = new CampCommReportCLI(student, campReportController, studentCampController);
                    campCommReportCLI.main();
                    break;    
                case 6:
                    System.out.println("Have a nice day!");
                    return 0;
                default:
                    System.out.println("You have inputted an invalid option!");
            }
        }
    }
}
