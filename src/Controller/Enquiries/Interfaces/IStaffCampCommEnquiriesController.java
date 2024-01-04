package src.Controller.Enquiries.Interfaces;

import src.Entity.User;

public interface IStaffCampCommEnquiriesController extends IEnquiriesController{
    public abstract void viewEnquiries(User user); 
    public abstract void replyEnquiries(User user, String reply, int index);
    public boolean enquiriesCheckIsEmpty();
}
