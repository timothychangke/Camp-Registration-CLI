package src.Controller.Camp.Interfaces;

import src.Entity.Camp;
import java.util.List;
import src.Entity.Student;
/**
 * The IStudentCampController interface defines methods for managing camp-related operations for students.
 */
public interface IStudentCampController {
    List<Camp> getCamp(Student s);

    void viewCamp(Student s, String filter, String option);

    void registerSlot(Student s, Camp c, boolean committeeChoice);

    void withdrawCamp(Student s, Camp c);

    List<Camp> viewRegisteredCamps(Student s);
}
