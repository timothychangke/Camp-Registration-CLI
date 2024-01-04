package src.Controller.Enquiries.Interfaces;

import src.Entity.User;

public interface ICampCommEnquiriesController extends IStaffCampCommEnquiriesController{
    void viewEnquiries(User user);

    void replyEnquiries(User user, String reply, int index);
}
