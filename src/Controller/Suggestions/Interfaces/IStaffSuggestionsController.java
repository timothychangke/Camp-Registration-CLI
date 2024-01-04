package src.Controller.Suggestions.Interfaces;

import src.Entity.Staff;

public interface IStaffSuggestionsController {
    void viewSuggestions(Staff staff);

    void approveSuggestions(Staff staff, int index);
}
