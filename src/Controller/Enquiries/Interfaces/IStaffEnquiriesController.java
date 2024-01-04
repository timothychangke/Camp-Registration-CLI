package src.Controller.Enquiries.Interfaces;

import src.Entity.Camp;
import src.Entity.Staff;
import java.util.List;

public interface IStaffEnquiriesController extends IStaffCampCommEnquiriesController{

    public List<Camp> getStaffCreatCamps(Staff staff);

}
