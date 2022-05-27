package comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3350.termsetter.Persistence.DomainSpecific.EnrollPersistence;
import comp3350.termsetter.Persistence.DomainSpecific.Student;

public class EnrollAccess implements EnrollPersistence {

    Connection connect = null;
    private final String dbPath;

    public EnrollAccess(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    public List<String> getStudentEnrollment(String studentID) {
        List<String> currentCourses = new ArrayList<>();

        try {
            connect = connection();

            PreparedStatement statement = connect.prepareStatement("select * from enrollment natural join courses where student_id = ?;");
            statement.setString(1, studentID);
            ResultSet resultSet = statement.executeQuery();

            // collect
            while (resultSet.next()) {
                final String course_id = resultSet.getString("course_id");
                final String course_name = resultSet.getString("course_name");
                final String credit_hours = resultSet.getString("credit_hours");
                final String section = resultSet.getString("section");
                final String days = resultSet.getString("days");
                final String time = resultSet.getString("time");
                final String period = resultSet.getString("period");

                // put them in a list for now
                final String course = course_id + "@" + course_name + "@" + credit_hours + "@" + section + "@" + days + "@" + time + "@" + period;
                currentCourses.add(course);
            }
            statement.close();
            resultSet.close();
            connect.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return currentCourses;
    }

    public void enroll(String sID, String cID, String section) {

        try {
            connect = this.connection();

            // query
            PreparedStatement statement = connect.prepareStatement("INSERT INTO enrollment VALUES (?,?,?)");
            statement.setString(1, sID);
            statement.setString(2, cID);
            statement.setString(3, section);


            //Update DB
            statement.executeUpdate();
            statement.close();
            connect.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void unenroll(String sID, String section, String cID) {

        try {
            connect = this.connection();

            //query
            PreparedStatement statement = connect.prepareStatement("DELETE FROM enrollment " +
                    "where course_ID = ? AND student_id = ? AND section = ?");
            statement.setString(1, cID);
            statement.setString(2, sID);
            statement.setString(3, section);

            //Update DB
            statement.executeUpdate();
            statement.close();
            connect.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
