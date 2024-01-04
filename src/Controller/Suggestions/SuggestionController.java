package src.Controller.Suggestions;

import java.util.*;
import src.Entity.Suggestions;
import src.Controller.Suggestions.data.SuggestionsDataHandler;
import src.Entity.User;
import src.Tablecreation.Table;

/**
 * Controller class for managing suggestions related to camps.
 * It interacts with the SuggestionsDataHandler to fetch and display suggestions.
 */
public class SuggestionController {
    private SuggestionsDataHandler data;

    public SuggestionController() {
    }

    public SuggestionController(SuggestionsDataHandler data) {
        this.data = data;
    }

    
    /**
     * Displays suggestions for a specific camp committee member.
     * The suggestions are presented in a tabular format including details such as suggestion number,
     * the suggestion text, the associated camp name, and the approval status.
     *
     * @param campComm The camp committee member (User) for whom to view suggestions.
     */
    public void viewSuggestions(User campComm) {
        List<Suggestions> suggestionList = data.getSuggestionsList();
        Table table=new Table();
        int j=1;
        table.setHeaders("Suggestions No","Suggestions","Camp Name", "Approved");
        table.setShowVerticalLines(true);
        for (int i = 0; i < suggestionList.size(); i++) {
            if (suggestionList.get(i).getCampCommittee().getUserID().equals(campComm.getUserID())) {

                table.addRow(""+j++,suggestionList.get(i).getSuggestion(),suggestionList.get(i).getCampName(),""+suggestionList.get(i).getApproval());

            }
        }
        table.print();
    }
}
