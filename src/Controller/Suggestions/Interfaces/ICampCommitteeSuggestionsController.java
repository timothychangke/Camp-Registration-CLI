package src.Controller.Suggestions.Interfaces;

import src.Entity.Student;

public interface ICampCommitteeSuggestionsController extends ISuggestionController{
    int checkStatus(Student student);

    void submitSuggestion(Student student, String suggestion, String campName);

    void editSuggestion(Student campCommitteeMember, String suggestion, int index);

    void deleteSuggestion(Student campCommitteeMember, int index);
}
