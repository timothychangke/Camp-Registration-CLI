package src.Controller.Camp.Data;
import java.util.*;
import src.Entity.Camp;
public class Compare implements Comparator<Camp>{
    public int compare(Camp a, Camp b){
        return a.getCampName().compareTo(b.getCampName());
    }
}
