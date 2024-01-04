package src.Controller.Suggestions.Staff;
import src.Tablecreation.Table;
import java.util.*;
import src.Entity.Suggestions;
import src.Entity.Staff;
import src.Controller.Suggestions.SuggestionController;
import src.Controller.Suggestions.Interfaces.IStaffSuggestionsController;
import src.Controller.Suggestions.data.SuggestionsDataHandler;
import src.Entity.*;

/**
 * Controller class for managing suggestions related to staff.
 * Extends SuggestionController and implements IStaffSuggestionsController, providing functionality for viewing and approving suggestions.
 */
public class StaffSuggestionsController extends SuggestionController implements IStaffSuggestionsController{
    private SuggestionsDataHandler datahandler;
    // private List<String> string_list;

    public StaffSuggestionsController(SuggestionsDataHandler data)
    {
        super(data);
        this.datahandler = data;
    }

    
    /**
     * Displays suggestions related to the camps created by a specific staff member.
     * The suggestions are presented in a tabular format including details such as suggestion number,
     * the suggestion text, the user ID of the camp committee member who made the suggestion, and the approval status.
     *
     * @param staff The staff member whose camp suggestions are to be viewed.
     */
    public void viewSuggestions(Staff staff)
    {
        //string_list= new ArrayList<String>();
        List<Suggestions> suggestions = datahandler.getSuggestionsList();
        List<Camp> ownCampList = staff.getOwnCamps(); // this .getOwnCamps will be null 
        Table table=new Table();
        int j=1;
        table.setHeaders("Suggestion no","Suggestion","userID","Approved");
        table.setShowVerticalLines(true);
        for (int i = 0; i < suggestions.size(); i++)
        {
            Suggestions curr_sugg = suggestions.get(i);
            for (Camp c: ownCampList){
                if (c.getCampName().equals(curr_sugg.getCampName())){
                    //string_list.add(suggestions.get(i).getSuggestion());
                    table.addRow(""+j++,suggestions.get(i).getSuggestion(),suggestions.get(i).getCampCommittee().getUserID(),""+suggestions.get(i).getApproval());
                }
            }
        }
        table.print();
    }

    /**
     * Approves a specific suggestion made for a camp created by the staff member.
     * It also increments the points of the camp committee member who made the suggestion.
     *
     * @param staff The staff member approving the suggestion.
     * @param index The index of the suggestion in the list of suggestions to be approved.
     */
    public void approveSuggestions(Staff staff, int index)
    {
        List<Suggestions> suggestions = datahandler.getSuggestionsList();
        List<Camp> ownCampList = staff.getOwnCamps();

        int true_index = index;

        for (int i = 0; i < suggestions.size(); i++)
        {
            Suggestions curr_sugg = suggestions.get(i);
            for (Camp c: ownCampList){
                if (c.getCampName().equals(curr_sugg.getCampName())){
                    true_index--;
                    if (true_index==0){
                        if (suggestions.get(i).getApproval()==true)
                        {
                            System.out.println("Error!: suggestion is already approved.");
                            return;
                        }
                        suggestions.get(i).setApproval(true);
                        int points = suggestions.get(i).getCampCommittee().getPoints();
                        suggestions.get(i).getCampCommittee().setPoints(points+1);
                        return;
                    }
                }
            }
        }
        /*suggestions.get(index-1).setApproval(true);
        Suggestions approved_suggestion = suggestions.get(index-1);

        // have to give points to campcomm member who suggested this
        int points = approved_suggestion.getCampCommittee().getPoints();
        approved_suggestion.getCampCommittee().setPoints(points+1); */
    }
}
