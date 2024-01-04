package src.Boundary.Mains.Student;

import src.Entity.*;
import src.Boundary.Camps.Student.StudentCampCLI;
import src.Boundary.DisplayMenu.Student.StudentCLI;
import src.Boundary.Enquiries.Student.StudentEnquiryCLI;
import src.Boundary.Mains.IMain;
import src.Controller.changePasswordController;
import src.Controller.Camp.Data.CampDataHandler;
import src.Controller.Camp.Interfaces.IStudentCampController;
import src.Controller.Camp.Student.StudentCampController;
import src.Controller.Enquiries.Data.EnquiriesDataHandler;
import src.Controller.Enquiries.Interfaces.IStudentEnquiriesController;
import src.Controller.Enquiries.Student.StudentEnquiriesController;

/**
 * The StudentMain class represents the main entry point and user interaction handler
 * for students in the CAMs Registration System. It allows students to access and manage
 * various functionalities, including camp management, inquiries, and changing passwords.
 */
public class StudentMain implements IMain {
    private Student student;
    private CampDataHandler campDataHandler;
    private EnquiriesDataHandler enquiriesDataHandler;
    public StudentMain(Student student, CampDataHandler campDataHandler, EnquiriesDataHandler enquiriesDataHandler) {
        this.student = student;
        this.campDataHandler = campDataHandler;
        this.enquiriesDataHandler=enquiriesDataHandler;
    }

    
    /**
     * Handles the user interaction and provides access to various functionalities for students.
     *
     * @return An integer indicating the status of the interaction. 0 represents a successful
     *         completion of the interaction, and any other value indicates an ongoing interaction.
     */
    public int cli() {
        IStudentCampController studentCampController = new StudentCampController(campDataHandler);
        StudentEnquiriesController studentEnquiriesController = new StudentEnquiriesController(enquiriesDataHandler);
        StudentCLI studentCLI = new StudentCLI();
        int option = studentCLI.StudentMenu();
        switch (option) {
            case 1:
                changePasswordController cp = new changePasswordController(student);
                break;
            case 2:
                StudentCampCLI studentCampCLI = new StudentCampCLI(student, studentCampController);
                studentCampCLI.main();
                break;
            case 3:
                StudentEnquiryCLI studentEnquiryCLI = new StudentEnquiryCLI(student, studentEnquiriesController, campDataHandler);
                studentEnquiryCLI.main(student);
                break;
            case 4:
                System.out.print("Thank you for using the CAMs system. Have a nice day.");
                return 0;
            default: System.out.println("You have inputted an invalid option!");
        }
        return 1;
    }
}
