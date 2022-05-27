package comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3350.termsetter.Logic.AccountValidation;
import comp3350.termsetter.Persistence.DomainSpecific.Student;
import comp3350.termsetter.Persistence.StudentPersistence;

public class StudentAccess implements StudentPersistence {

    Connection connect = null;
    private final String dbPath;
    private static String currentID = null;

    public StudentAccess(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {

        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }


    public Student insertStudent(Student student) {

        // first connect
        try{
            connect = this.connection();

            //query
            PreparedStatement statement = connect.prepareStatement("INSERT INTO students VALUES (?,?,?,?,?);");
            statement.setString(5, student.getStudentID());
            statement.setString(1, student.getName());
            statement.setString(2, student.getPassword());
            statement.setString(3, student.getEmailAddress());
            statement.setString(4, student.getPhoneNumber());
            // ResultSet resultSet = statement.executeQuery();

            //Update DB
            statement.executeUpdate();
            connect.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return student;
    }

    public Student getStudent(String student_id) {
        // will change this later
        Student student = null;
        try {
            connect = this.connection();

            //query
            PreparedStatement statement = connect.prepareStatement("select * from students where student_id = ?");
            statement.setString(1, student_id);
            ResultSet resultSet = statement.executeQuery();

            //collect the data from the query
            while (resultSet.next()) {

                // Enter data into fields and create a new student
                final String studentID = resultSet.getString("student_id");
                final String name = resultSet.getString("name");
                final String passID = resultSet.getString("password");
                final String phoneNumber = resultSet.getString("phoneNum");
                final String email = resultSet.getString("email");

                student = new Student(name, passID, email, phoneNumber, studentID);
            }
            //whoever uses this method, check if the student is null or not.
            connect.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public boolean isEmpty() {
        List<String> studentIDs = new ArrayList<>();

        try {
            // first connect
            connect = this.connection();

            //query
            PreparedStatement statement = connect.prepareStatement("select * from students");
            ResultSet resultSet = statement.executeQuery();

            connect.close();
            return resultSet.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<String> getAllStudents() {
        List<String> studentIDs = new ArrayList<>();

        // first connect
        try {
            connect = connection();
            PreparedStatement statement = connect.prepareStatement("select * from students");
            ResultSet resultSet = statement.executeQuery();

            //collect
            while (resultSet.next()) {

                //just get the ID's for now
                final String student_id = resultSet.getString("student_id");

                //put them in a list for now
                studentIDs.add(student_id);
            }
            connect.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return studentIDs;
    }

    public void setCurrentStudentID(String sID) {
        this.currentID = sID;
    }

    @Override
    public Student getCurrentStudentID() {
        return getStudent(currentID);
    }

    @Override
    public boolean updatePassword(String password) {
        if ((currentID != null) && (getStudent(currentID)) != null) {
            Student student = getStudent(currentID);

            try {
                connect = this.connection();
                PreparedStatement statement = connect.prepareStatement("UPDATE students " +
                        "SET password = ? WHERE student_id = ?");
                statement.setString(1, password);
                statement.setString(2, currentID);

                statement.executeUpdate();
                statement.close();
                connect.close();
                return true;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateEmail(String email) {
        if ((currentID != null) && (getStudent(currentID)) != null) {
            Student student = getStudent(currentID);

            try {
                connect = this.connection();


                PreparedStatement statement = connect.prepareStatement("UPDATE students " +
                        "SET email = ? WHERE student_id = ?");
                statement.setString(1, email);
                statement.setString(2, currentID);

                statement.executeUpdate();
                statement.close();
                connect.close();
                return true;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
