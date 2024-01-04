package src.Controller.Suggestions.CampCommitteeMember;
import java.util.*;
import src.Entity.Suggestions;
import src.Entity.Student;
import src.Controller.Suggestions.SuggestionController;
import src.Controller.Suggestions.Interfaces.ICampCommitteeSuggestionsController;
import src.Controller.Suggestions.data.SuggestionsDataHandler;

public class CampCommitteeSuggestionsController extends SuggestionController implements ICampCommitteeSuggestionsController{
    private SuggestionsDataHandler datahandler;

    public CampCommitteeSuggestionsController(SuggestionsDataHandler data) 
    {
        super(data);
        this.datahandler = data;
    }

    
    /** 
     * @param s
     * @return int
     */
    public int checkStatus(Student s)
    {
        if (s.getCampCommittee() != null)
        {
            return 1; // is a camp committee member
        }
        else{
            return 0; // not a camp committee member
        }
    }

    public void submitSuggestion(Student student, String suggestion, String campName)
    {
        // Making sure suggestion is not zero length (ERROR HANDLING)
        if (suggestion.length()==0)
        {
            System.out.println("Suggestion has to be of non-zero length!");
            return;
        }

        if (checkStatus(student) == 0)
        {
            System.out.println("Student is not a camp committee member!");
        }

        // Check if student is camp committee member of the camp
        if (student.getCampCommittee().equals(campName) == false)
        {
            System.out.println("Student is not a camp committee member of this camp. Cannot submit suggestion!");
            return;
        }

        List<Suggestions> data = datahandler.getSuggestionsList();
        Suggestions new_suggestion = new Suggestions(suggestion, student, false, campName);
        data.add(new_suggestion);
        datahandler.setSuggestionList(data);

        // Award point to camp comm member
        int point = student.getPoints();
        student.setPoints(point+1);
        System.out.println("Pointed awards to " + student.getName());

        System.out.println("Suggestion successfully submitted!");
    }

    public void editSuggestion(Student CampCommitteeMember, String suggestion, int index)
    {
        // Making sure suggestion is not zero length (ERROR HANDLING)
        if (suggestion.length()==0)
        {
            System.out.println("\nError: Suggestion has to be of non-zero length!\n");
            return;
        }

        List<Suggestions> suggestionList = datahandler.getSuggestionsList();
        int true_idx = index;

        // Making sure index is not out of bounds (ERROR HANDLING)
        if (index > suggestionList.size())
        {
            System.out.println("\nError: Chosen index is out of range!\n");
            return;
        }
        for (int i = 0; i < suggestionList.size(); i++)
        {
            if (suggestionList.get(i).getCampCommittee().getUserID().equals( CampCommitteeMember.getUserID()))
            {
                true_idx--;
                if (true_idx==0){
                    suggestionList.get(i).setSuggestion(suggestion);
                    System.out.println("Edited successfully!");
                    datahandler.setSuggestionList(suggestionList);
                    return;
                }
            }
        }
    }

    public void deleteSuggestion(Student CampCommitteeMember, int index)
    {
        List<Suggestions> suggestionList = datahandler.getSuggestionsList();

        // Making sure index is not out of bounds (ERROR HANDLING)
        if (index > suggestionList.size())
        {
            System.out.println("\nError: Chosen index is out of range!\n");
            return;
        }

        int true_index = index;

        for (int i = 0; i < suggestionList.size(); i++)
        {
            if (suggestionList.get(i).getCampCommittee().getUserID().equals(CampCommitteeMember.getUserID()))
            {
                true_index--;
                if (true_index == 0){
                    suggestionList.remove(i);
                    datahandler.setSuggestionList(suggestionList);
                    System.out.println("Removed successfully");
                    return;
                }
            }
        }
    }
}
