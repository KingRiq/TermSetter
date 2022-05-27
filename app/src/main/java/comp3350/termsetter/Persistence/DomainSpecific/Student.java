package comp3350.termsetter.Persistence.DomainSpecific;

public class Student {

    private String Name;
    private String Password;
    private final String Phone;
    private String Email;
    private final String studentID;

    // constructor
    public Student(String name, String password, String email, String phone, String studentID) {
        this.Name = name;
        this.Password = password;
        this.Email = email;
        this.Phone = phone;
        this.studentID = studentID;
    }

    /* Getters */
    public String getStudentID() {
        return this.studentID;
    }

    public String getName() {
        return this.Name;
    }

    public String getPassword() {
        return this.Password;
    }

    public String getPhoneNumber() {
        return this.Phone;
    }

    public String getEmailAddress() {
        return this.Email;
    }

    /* Setters */

    // purpose: sets username
    // input: the String to assign to user id
    public void setStudentName(String input) {
        this.Name = input;
    }

    public void setPassword(String input) {
        this.Password = input;
    }

    public void setEmail(String input) {
        this.Email = input;
    }

}
