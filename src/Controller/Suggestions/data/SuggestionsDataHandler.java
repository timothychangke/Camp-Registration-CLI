package src.Controller.Suggestions.data;
import src.Entity.Suggestions;
import java.util.*;

/**
 * The SuggestionsDataHandler class manages a list of suggestions.
 */
public class SuggestionsDataHandler {
    private List<Suggestions> suggestionsList;

    public SuggestionsDataHandler()
    {
        suggestionsList = new ArrayList<Suggestions>();
    }

    
    /** 
     * @return List<Suggestions>
     */
    public List<Suggestions> getSuggestionsList()
    {
        return suggestionsList;
    }

    public void setSuggestionList(List<Suggestions> newList)
    {
        this.suggestionsList = newList;
    }
}
