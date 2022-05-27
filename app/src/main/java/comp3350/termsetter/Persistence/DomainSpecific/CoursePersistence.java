package comp3350.termsetter.Persistence.DomainSpecific;

import java.sql.SQLException;
import java.util.List;

public interface CoursePersistence {

    public List<String> getAllFaculties();
    public List<String> getCourseByFaculty(String facultyName);
    public List<String> getSectionByCourse(String facultyName, String courseID);

}
