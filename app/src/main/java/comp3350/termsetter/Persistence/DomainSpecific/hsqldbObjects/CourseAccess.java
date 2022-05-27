package comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3350.termsetter.Persistence.DomainSpecific.CoursePersistence;

public class CourseAccess implements CoursePersistence {

    Connection connect = null;
    private final String dbPath;

    public CourseAccess(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    public List<String> getAllFaculties() {
        List<String> facultyList = new ArrayList<>();

        try {
            connect= connection();
            //query
            PreparedStatement statement = connect.prepareStatement("select * from faculty;");
            ResultSet resultSet = statement.executeQuery();

            // collect
            while (resultSet.next()) {

                String f = resultSet.getString("faculty_name");
                facultyList.add(f);
            }
            statement.close();
            resultSet.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return facultyList;
    }

    public List<String> getCourseByFaculty(String facultyName) {
        List<String> courseCatalog = new ArrayList<>();

        try {
            connect = connection();

            //query
            PreparedStatement statement = connect.prepareStatement("select distinct course_id, course_name, CREDIT_HOURS from courses where faculty_name = ?;");
            statement.setString(1, facultyName);
            ResultSet resultSet = statement.executeQuery();

            // collect
            while (resultSet.next()) {
                final String course_id = resultSet.getString("course_id");
                final String course_name = resultSet.getString("course_name");
                final String course_cred = resultSet.getString("CREDIT_HOURS");

                courseCatalog.add(course_id);
                courseCatalog.add(course_name);
                courseCatalog.add(course_cred);
            }
            statement.close();
            resultSet.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return courseCatalog;
    }

    public List<String> getSectionByCourse(String facultyName, String courseID) {
        List<String> sectionDetails = new ArrayList<>();

        try {
            connect = connection();

            //query
            PreparedStatement statement = connect.prepareStatement("select * from courses where faculty_name = ? and course_id = ?;");
            statement.setString(1, facultyName);
            statement.setString(2, courseID);
            ResultSet resultSet = statement.executeQuery();

            // collect
            while (resultSet.next()) {
                final String section = resultSet.getString("section");
                final String days = resultSet.getString("days");
                final String time = resultSet.getString("time");
                final String period = resultSet.getString("period");

                sectionDetails.add(section);
                sectionDetails.add(days);
                sectionDetails.add(time);
                sectionDetails.add(period);
            }
            statement.close();
            resultSet.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return sectionDetails;
    }

    /* Why does the DB have responsibility of opening(connecting) the DB but closing goes to the logic layer?
    // Is there a way we can do both in the logic layer? -Eriq
    // Maybe toggle connection here or there? Its not broken so idc but let me know your preference*/
    public void closeConnection() throws SQLException {
        connect.close();
    }
}
