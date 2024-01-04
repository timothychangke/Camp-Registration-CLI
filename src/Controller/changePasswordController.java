package src.Controller;
import src.Entity.*;
import java.util.*;

public class changePasswordController {
    User u;

    public changePasswordController(User u)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter your new password: ");
        String new_password = sc.next();

        // Making sure new password is not zero length (ERROR HANDLING)
        if (new_password.length()==0)
        {
            System.out.println("\nError: New password has to be of non-zero length!\n");
            return;
        }
        u.setPassword(new_password);
        System.out.println("Password changed successfully.");
    }


}
