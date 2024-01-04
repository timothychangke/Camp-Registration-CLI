package src.Controller.Camp.Data;
import src.Entity.*;
import java.util.*;
import src.Controller.Camp.Data.Compare;

/**
 * The CampDataHandler class manages a list of camp information.
 */
public class CampDataHandler {
    private List<Camp> campList;

    public CampDataHandler()
    {
        campList = new ArrayList<Camp>();

        // Adding initial camp
        Camp t0 = new Camp("SCSE_0", "2024-04-02", "2024-04-05", "2024-04-15", "SCSE", "SCSE", 10, 10, "Fun", "HUKUMAR", true);
        campList.add(t0);

        Camp t1 = new Camp("SCSE_1", "2024-05-02", "2024-05-05", "2024-04-15", "SCSE", "SCSE", 10, 10, "Fun", "HUKUMAR", true);
        campList.add(t1);

        Camp t2 = new Camp("SCSE_2", "2024-06-03", "2024-06-06", "2024-04-15", "SCSE", "SCSE", 10, 10, "Fun", "ANWIT", true);
        campList.add(t2);

        Camp t3 = new Camp("SCSE_3", "2024-04-03", "2024-04-06", "2024-04-15", "SCSE", "SCSE", 10, 10, "Fun", "HUKUMAR", true);
        campList.add(t3); // overlaps with SCSE_0

        Camp t4 = new Camp("SCSE_4", "2024-01-02", "2024-01-05", "2023-11-20", "SCSE", "SCSE", 10, 10, "Fun", "HUKUMAR", true);
        campList.add(t4); // registration closing date has passed

        Camp tt = new Camp("SCSE_10", "2024-01-02", "2024-01-05", "2023-11-30", "SCSE", "SCSE", 1, 1, "Fun", "ANWIT", true);
        campList.add(tt); // registration closing date has passed

        Camp t5 = new Camp("ADM_1", "2024-05-12", "2024-05-14", "2024-04-15", "ADM", "ADM", 10, 10, "Fun", "OURIN", true);
        campList.add(t5);

        Camp t6 = new Camp("EEE", "2024-03-01", "2024-03-03", "2024-02-15", "EEE", "EEE", 10, 10, "Fun", "UPAM", true);
        campList.add(t6);

        Camp t7 = new Camp("NBS", "2024-02-01", "2024-02-03", "2024-01-15", "NBS", "NBS", 10, 10, "Fun", "ARVI", true);
        campList.add(t7);
    }

    
    /** 
     * @return List<Camp>
     */
    public List<Camp> getCampList()
    {
        Collections.sort(campList,new Compare());
        return campList;
    }

    public void setCampList(List<Camp> new_list)
    {
        this.campList = new_list;
    }
}
