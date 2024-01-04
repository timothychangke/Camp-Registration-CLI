package src.Entity;

/**
 * The Suggestions class represents a suggestion made by a student for a camp.
 * It includes information such as the suggestion text, the student who made the suggestion,
 * the approval status, and the camp name.
 */
public class Suggestions {
    private String suggestion;
    private Student student;
    private boolean approval;
    private String campName;

    public Suggestions(String Suggestion, Student student, boolean approval, String campName)
    {
        this.suggestion = Suggestion;
        this.student = student;
        this.approval = approval;
        this.campName = campName;
    }

    
    /** 
     * @return String
     */
    public String getSuggestion()
    {
        return this.suggestion;
    }

    public void setSuggestion(String Suggestion)
    {
        this.suggestion = Suggestion;
    }

    public Student getCampCommittee()
    {
        return this.student;
    }

    public boolean getApproval()
    {
        return this.approval;
    }

    public void setApproval(boolean approval)
    {
        this.approval = approval;
    }

    public Student getStudent()
    {
        return this.student;
    }

    public String getCampName()
    {
        return this.campName;
    }

}
