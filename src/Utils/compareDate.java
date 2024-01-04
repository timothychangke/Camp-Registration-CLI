package src.Utils;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for comparing and handling dates.
 * Provides methods to compare dates and check for clashes between date ranges.
 */
public class compareDate {
    
    
    /**
     * Compares two dates and returns an integer based on their chronological order.
     * Returns a negative integer, zero, or a positive integer as the first date is earlier than, 
     * equal to, or later than the second date, respectively.
     *
     * @param date_1 The first date in the format "yyyy-MM-dd".
     * @param date_2 The second date in the format "yyyy-MM-dd".
     * @return An integer representing the comparison result: negative if date_1 is earlier,
     *         zero if equal, and positive if date_1 is later than date_2.
     */
    public int compare(String date_1, String date_2)
    {
        try{ // Checking if deadline has passed
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date curr_1 = sdf.parse(date_1);
            Date curr_2 = sdf.parse(date_2);
            int result = curr_1.compareTo(curr_2);
            return result;
        } catch (java.text.ParseException e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Checks for a clash between two date ranges. Returns 0 if there is a clash and 1 if there is no clash.
     * A clash is considered to exist if either of the dates in the second range fall within the first range or vice versa.
     *
     * @param date_1 Start date of the first date range in the format "yyyy-MM-dd".
     * @param date_2 End date of the first date range in the format "yyyy-MM-dd".
     * @param date_3 Start date of the second date range in the format "yyyy-MM-dd".
     * @param date_4 End date of the second date range in the format "yyyy-MM-dd".
     * @return An integer indicating if there is a clash (0) or not (1).
     */
    public int checkForClash(String date_1, String date_2, String date_3, String date_4)
    { // date_1 and date_2 is starting and end date for current camp 
        // date_3 and date_4 is the starting and end date for camp to join
        if ((this.compare(date_3, date_1)>0 && this.compare(date_3, date_2)<0) 
        || (this.compare(date_4, date_1)>0 && this.compare(date_4, date_2)<0)
        || this.compare(date_3, date_1)<0 && this.compare(date_4, date_2)>0)
        {
            return 0; // means there is a clash
        }
        return 1; // means there is no clash
    }
}
