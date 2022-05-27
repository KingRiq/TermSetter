package comp3350.termsetter.Persistence;

import java.sql.SQLException;
import java.util.List;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.CourseAccess;

public class CourseCategorySQLDriver implements CourseCategoryPersistence{

    private CourseCategories categories;
    private CourseAccess courseAccess = null;

    public CourseCategorySQLDriver(CourseAccess courseAccess) throws SQLException {
        this.courseAccess = courseAccess;
        init();
        runQueries();
    }

    public List<Faculty> getFaculties(){
        return categories.getFaculties();
    }

    private void init() { categories = new CourseCategories(); }

    private void runQueries() throws SQLException {
        List<String> facultyResultSet = courseAccess.getAllFaculties();

        for ( int i = 0; i < facultyResultSet.size(); i++ ){

            String facultyName = facultyResultSet.get(i);
            Faculty currFaculty = new Faculty(facultyName);

            List<String> courseResultSet = courseAccess.getCourseByFaculty(facultyName);

            for ( int j = 0; j < courseResultSet.size(); j+=3 ) {

                String courseID = courseResultSet.get(j);
                String courseName = courseResultSet.get(j+1);
                String courseCred = courseResultSet.get(j+2);

                CourseOffering currCourse = new CourseOffering(courseID, courseName, Integer.parseInt(courseCred));
                List<String> sectionResultSet = courseAccess.getSectionByCourse(facultyName, courseID);

                for ( int k = 0; k < sectionResultSet.size(); k+=4 ) {
                    CourseSection currSection = new CourseSection(sectionResultSet.get(k), sectionResultSet.get(k+1), sectionResultSet.get(k+2), sectionResultSet.get(k+3));
                    currCourse.addSection(currSection);
                }

                currFaculty.addCourses(currCourse);
            }
            categories.addFaculty(currFaculty);
        }

        courseAccess.closeConnection();

    }
}