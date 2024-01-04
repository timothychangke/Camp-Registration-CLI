package src.Controller.Enquiries.Interfaces;

import src.Entity.Camp;
import java.util.List;
import src.Entity.Enquiries;
import src.Entity.Student;

public interface IStudentEnquiriesController extends IEnquiriesController{

    public List<Enquiries> getStudentEnquiries(Student student);

    public Enquiries getStudentEnquiries(Student student, int index);

    void editEnquiries(Student student, int index, String newEnquiryText);

    void submitEnquiry(Student student, Camp camp, String enquiryText);

    void deleteEnquiry(Student student, int index);
}
