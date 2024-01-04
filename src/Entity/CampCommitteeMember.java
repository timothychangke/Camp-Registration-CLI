package src.Entity;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

/**
 * The CampCommitteeMember class represents a camp committee member.
 * It extends the User class and includes information about registered camps and committee points.
 */
public class CampCommitteeMember extends User{
    private List<Camp> registeredCamp;
    private int points;
    // private List<Suggestion> suggestions;
    // private List<Enquiry> enquiries;

    public CampCommitteeMember(String userID, String name, String password, String faculty, int points) {
        super(name, userID, password, faculty);
        this.points = points;
        // this.suggestions = new ArrayList<>();
        // this.enquiries = new ArrayList<>();
        this.registeredCamp = new ArrayList<>();
    }

    
    /** 
     * @return int
     */
    public int getPoints() 
    {
        return this.points;
    }

    public List<Camp> getRegisteredCamp() 
    {
        return registeredCamp;
    }

    public void setRegisteredCamps(Camp c)
    {   
        this.registeredCamp.add(c);
    }
}
    // public void viewCampDetails() {
    //     for (Camp camp : this.registeredCamp) {
    //         camp.viewCampDetails();
    //     }
    // }
//     public void submitSuggestion(Suggestion suggestion) {
//         suggestions.add(suggestion);
//     }
//     public void viewAndReplyToEnquiries() {
//         for (Enquiry enquiry : enquiries) {
//             enquiry.viewEnquiryDetails();

//             System.out.print("Do you want to reply to this enquiry? (yes/no): ");
//             Scanner scanner = new Scanner(System.in);
//             String response = scanner.nextLine().toLowerCase();

//             if (response.equals("yes")) {
//                 System.out.print("Enter your reply: ");
//                 String replyText = scanner.nextLine();

//                 enquiry.setReply(replyText);
//                 this.points++;
//             }
//         }
//     }
//     public void viewSuggestions() {
//         for (Suggestion suggestion : suggestions) {
//             suggestion.viewSuggestionDetails();
//         }
//     }
//     public void editSuggestion(int suggestionIndex, String newSuggestion) {
//         if (suggestionIndex >= 0 && suggestionIndex < suggestions.size()) {
//             Suggestion suggestion = suggestions.get(suggestionIndex);
//             if (!suggestion.isApproved()) {
//                 suggestion.editSuggestion(newSuggestion);
//                 System.out.println("Suggestion has been updated.");
//             } else {
//                 System.out.println("Cannot edit an approved suggestion.");
//             }
//         } else {
//             System.out.println("Invalid suggestion index. Please provide a valid index.");
//         }
//     }
//     public void deleteSuggestion(int suggestionIndex) {
//         if (suggestionIndex >= 0 && suggestionIndex < suggestions.size()) {
//             Suggestion suggestion = suggestions.get(suggestionIndex);
//             if (!suggestion.isApproved()) {
//                 suggestions.remove(suggestionIndex);
//                 System.out.println("Suggestion has been deleted.");
//             } else {
//                 System.out.println("Cannot delete an approved suggestion.");
//             }
//         } else {
//             System.out.println("Invalid suggestion index. Please provide a valid index.");
//         }
//     }
// }