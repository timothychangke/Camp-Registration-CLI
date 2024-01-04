package src.Boundary.Mains.Student;

import src.Boundary.Mains.IMain;
import src.Boundary.Mains.CampCommiteeMember.CampCommMain;
import src.Controller.StudentDataHandler;
import src.Controller.Camp.Data.CampDataHandler;
import src.Controller.Enquiries.Data.EnquiriesDataHandler;
import src.Controller.Suggestions.data.SuggestionsDataHandler;
import src.Entity.Student;

/**
 * The StudentTypeMain class represents the main entry point and user interaction handler
 * for student-type users in the CAMs Registration System. It distinguishes between regular
 * students and student camp committee members, allowing them to access and manage their
 * respective functionalities, such as camp registration, inquiries, suggestions, and more.
 */
public class StudentTypeMain implements IMain {
    private StudentDataHandler studentDataHandler;
    private String userID;
    private CampDataHandler campDataHandler;
    private SuggestionsDataHandler suggestionDataHandler;
    private EnquiriesDataHandler enquiriesDataHandler;
    private int logout = 0;

    public StudentTypeMain(StudentDataHandler studentDataHandler, String userID, CampDataHandler campDataHandler, SuggestionsDataHandler suggestionDataHandler, EnquiriesDataHandler enquiriesDataHandler) {
        this.studentDataHandler = studentDataHandler;
        this.userID = userID;
        this.campDataHandler = campDataHandler;
        this.suggestionDataHandler = suggestionDataHandler;
        this.enquiriesDataHandler = enquiriesDataHandler;
    }

    
    /**
 * Displays the main menu and handles user interactions for student users.
 *
 * @return An integer representing the result of user interaction.
 *         0 - User wants to logout.
 */
    public int cli() {
        for (Student student : studentDataHandler.getStudentList()) {
            do {
                if (userID.equals(student.getUserID())) {
                    if (student.getCampCommittee() != null) {
                        CampCommMain campCommMain = new CampCommMain(student, campDataHandler,
                                suggestionDataHandler, enquiriesDataHandler);
                        logout = campCommMain.cli();
                    }

                    else {
                        StudentMain studentMain = new StudentMain(student, campDataHandler, enquiriesDataHandler);
                        logout = studentMain.cli();
                    }
                }
            } while (logout != 0);
        }
        return 0;
    }
}
