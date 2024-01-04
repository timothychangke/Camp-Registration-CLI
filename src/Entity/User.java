package src.Entity;

/**
 * The User class represents a basic user entity with common attributes like userID, name, password, and faculty.
 */
public class User {
    private String userID;
    private String name;
    private String password;
    private String faculty;

    public User()
    {
        
    }

    
    public User(String userID, String name, String password, String faculty)
    {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.faculty = faculty;
    }

    
    /** 
     * @return String
     */
    public String getUserID()
    {
        return this.userID;
    }
    
    public String getName()
    {
        return this.name;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFaculty()
    {
        return this.faculty;
    }
}
