package comp3350.termsetter.Tests;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import comp3350.termsetter.Logic.OfferedClassLogic;
import comp3350.termsetter.Persistence.CourseCategorySQLDriver;
import comp3350.termsetter.Persistence.CourseOffering;
import comp3350.termsetter.Persistence.CourseSection;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.CourseAccess;
import comp3350.termsetter.Persistence.Faculty;
import comp3350.termsetter.utils.TestUtils;
import static org.junit.Assert.*;

public class CourseCategoryIntegrationTest {

    OfferedClassLogic classLogic;
    CourseCategorySQLDriver sql;
    private File tempDB;

    @Before
    public void setup() throws IOException, SQLException {
        this.tempDB = TestUtils.copyDB();
        CourseAccess courseAccess = new CourseAccess(this.tempDB.getAbsolutePath().replace(".script", ""));
        sql = new CourseCategorySQLDriver(courseAccess);
    }

    @Test
    public void testLogicMediatorCreate() throws SQLException {
        System.out.println("\nStarting testLogicMediatorCreate: object exists after creation\n");
        classLogic = new OfferedClassLogic(true, null);
        assertNotNull(classLogic);
        System.out.println("End testLogicMediatorCreate: object exists after creation\n");
    }

    @Test
    public void testSQLDriverExists(){
        System.out.println("\nStarting TestSQLDriverExists: object exists after creation\n");
        assertNotNull(sql);
        System.out.println("End TestSQLDriverExists: object exists after creation\n");
    }

    @Test
    public void testCategoriesinHierarchy() throws SQLException {
        System.out.println("\nStarting testCategoriesInHierarchy: object is retrieved\n");
        List<Faculty> faculties = sql.getFaculties();
        assertNotNull(faculties);
        System.out.println("End testCategoriesInHierarchy: object is retrieved\n");
    }

    @Test
    public void testFacultiesinHierarchy() throws SQLException {
        System.out.println("\nStarting testFacultiesInHierarchy: retrieved objects exist\n");
        List<Faculty> faculties = sql.getFaculties();
        for(int i = 0; i < faculties.size(); i++){
            assertNotNull(faculties.get(i));
        }
        System.out.println("End testFacultiesInHierarchy: retrieved objects exist\n");
    }

    @Test
    public void testCoursesinHierarchy() throws SQLException {
        System.out.println("\nStarting testCoursesInHierarchy: retrieved objects exist\n");
        List<Faculty> faculties = sql.getFaculties();
        for(int i = 0; i < faculties.size(); i++){
            List<CourseOffering> courses = faculties.get(i).getCourses();
            for(int j = 0; j < courses.size(); j++){
                assertNotNull(courses.get(j));
            }
        }
        System.out.println("End testCoursesInHierarchy: retrieved objects exist\n");
    }

    @Test
    public void testSectionsinHierarchy() throws SQLException {
        System.out.println("\nStarting testSectionsInHierarchy: retrieved objects exist\n");
        List<Faculty> faculties = sql.getFaculties();
        for(int i = 0; i < faculties.size(); i++){
            List<CourseOffering> courses = faculties.get(i).getCourses();
            for(int j = 0; j < courses.size(); j++){
                List<CourseSection> sections = courses.get(j).getSections();
                for(int k = 0; k < sections.size(); k++){
                    assertNotNull(sections.get(k));
                }
            }
        }
        System.out.println("End testSectionsInHierarchy: retrieved objects exist\n");
    }

}
