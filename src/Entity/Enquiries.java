package src.Entity;

/**
 * The Enquiries class represents an enquiry made by a student about a camp.
 * It contains information about the student, camp, enquiry text, and any replies.
 */
public class Enquiries {
    private Student student;
    private Camp camp;
    private String enquiry;
    private String reply;

    public Enquiries(Student student, Camp camp, String enquiry) {
        this.student = student;
        this.camp = camp;
        this.enquiry = enquiry;
        this.reply = "";
    }

    
    /** 
     * @return Student
     */
    public Student getStudent() {
        return student;
    }

    public Camp getCamp() {
        return camp;
    }

    public String getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(String enquiry) {
        this.enquiry = enquiry;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public void viewEnquiryDetails() {
        System.out.println("Enquiry from Student: " + student.getName());
        System.out.println("Camp: " + camp.getCampName());
        System.out.println("Enquiry: " + enquiry);

        if (reply != null) {
            System.out.println("Reply: " + reply);
        } else {
            System.out.println("No reply available yet.");
        }
    }
}
