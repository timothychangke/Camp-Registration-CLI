package src.Boundary.Mains;

import src.Controller.*;
import src.Controller.Camp.Data.CampDataHandler;
import src.Controller.Enquiries.Data.EnquiriesDataHandler;
import src.Controller.Suggestions.data.SuggestionsDataHandler;
import src.Boundary.Login.LoginCLI;
import src.Boundary.Mains.Staff.StaffTypeMain;
import src.Boundary.Mains.Student.StudentTypeMain;

public class Main {
    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        /*collects student data from the student_list and staff_list csv files.
        Additionally intialises the dataHandlers which are used for storing the data*/
        StaffDataHandler staffDataHandler = new StaffDataHandler("src/db/staff_list.csv");
        StudentDataHandler studentDataHandler = new StudentDataHandler("src/db/student_list.csv");
        CampDataHandler campDataHandler = new CampDataHandler();
        SuggestionsDataHandler suggestionDataHandler = new SuggestionsDataHandler();
        EnquiriesDataHandler enquiriesDataHandler = new EnquiriesDataHandler();

        LoginCLI login = new LoginCLI(studentDataHandler.getStudentList(), staffDataHandler.getStaffList());
        

        while (true) {
            int login_int = login.loginMenu();
            String userID = login.getUserID();
            String type = login.getType();
            if (type == "Student") {
                StudentTypeMain studentTypeMain = new StudentTypeMain(studentDataHandler, userID, campDataHandler,
                        suggestionDataHandler, enquiriesDataHandler);
                studentTypeMain.cli();
            } else if (type == "Staff") {
                StaffTypeMain staffTypeMain = new StaffTypeMain(staffDataHandler, userID, campDataHandler,
                        suggestionDataHandler, enquiriesDataHandler);
                staffTypeMain.cli();
            }

        }
        /*
         * System.out.println("\n");
         * Student tim = new Student("KIH0004", "TIM", "12345", "SCSE", null);
         * Student kim = new Student("KIH0004", "KIM", "12345", "SCSE", null);
         * Staff prof = new Staff("Staff", "Prof", "12345", "SCSE");
         * 
         * Camp c = new Camp("SCSE_1", "2023-12-20", "2023-12-21", "2023-12-15", "SCSE",
         * "SCSE", 10, 10, "SF", "Hello", true);
         * Camp cc = new Camp("SCSE_2", "2023-12-22", "2023-12-30", "2023-12-15",
         * "SCSE", "SCSE", 10, 10, "SF", "Hello", false);
         * PerformanceReportController prc = new PerformanceReportController();
         * CampReportController crc = new CampReportController();
         * 
         * StudentCampController scc = new StudentCampController(null);
         * scc.registerSlot(kim, cc, false);
         * scc.registerSlot(tim, cc, true);
         * System.out.print(crc.generateReport(cc,"Attendee"));
         * 
         * 
         * /*
         * Student student = new Student("KIMH0004", "Kim", "12345", "SCSE", false);
         * Camp c = new Camp("SCSE_camp", "2020-02-02", "2020-01-02", "SCSE", "SCSE",
         * 10, 10, "Fun", "Malcom");
         * Camp c2 = new Camp("NBS_camp", "2020-02-02", "2020-01-02", "SCSE", "SCSE",
         * 10, 10, "Fun", "Malcom");
         * Camp c3 = new Camp("MAE_camp", "2020-02-02", "2020-01-02", "SCSE", "SCSE",
         * 10, 10, "Fun", "Malcom");
         * CampCommMember campCommMem = new CampCommMember("KIMH0004", "Kim", "12345",
         * "SCSE", 0);
         * Staff staff = new Staff("TCHANGKA001", "Dr Timothy", "12345", "SCSE");
         * 
         * StudentEnquiriesController sc = new StudentEnquiriesController(student);
         * CampCommEnquiriesController ccc = new
         * CampCommEnquiriesController(campCommMem);
         * StaffEnquiriesController scc = new StaffEnquiriesController(staff);
         * 
         * //student can submit, view, edit, delete
         * System.out.println("Student functionality");
         * sc.submitEnquiry(c, "Enquiry 1");
         * sc.submitEnquiry(c2, "Enquiry 2");
         * sc.submitEnquiry(c3, "Enquiry 3");
         * sc.editEnquiries(sc.getStudentEnquiries(0), "New Enquiry");
         * sc.viewEnquiries();
         * sc.deleteEnquiry(sc.getStudentEnquiries(0));
         * sc.viewEnquiries();
         * 
         * //staff can view and reply
         * System.out.println("Staff functionality");
         * scc.viewEnquiries();
         * scc.replyEnquiries("nicely", sc.getStudentEnquiries(0));
         * scc.viewEnquiries();
         * 
         * //campcomm can view and reply
         * System.out.println("CampComm functionality");
         * ccc.viewEnquiries();
         * ccc.replyEnquiries("override", sc.getStudentEnquiries(0));
         * ccc.viewEnquiries();
         */

    }
}
