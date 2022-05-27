package comp3350.termsetter.Tests;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.EnrollAccess;
import comp3350.termsetter.utils.TestUtils;

public class EnrollAccessTest {

    EnrollAccess ea;
    private File tempDB;
    String testStudent = "test1"; // has 1 default enrollment
    String testCourse = "COMP1010";
    String testSection = "A01";

    @Before
    public void setup() throws IOException {
        this.tempDB = TestUtils.copyDB();
        ea = new EnrollAccess(this.tempDB.getAbsolutePath().replace(".script", ""));
    }

    @Test
    public void testEnrollAccessExists() throws SQLException{
        System.out.println("\nStarting testEnrollAccessExists: object exists after creation\n");
        assertNotNull(ea);
        System.out.println("End testEnrollAccessExists: object exists after creation\n");
    }

    @Test
    public void testGetEnrollments() throws SQLException {
        System.out.println("\nStarting testGetEnrollments: objects are retrieved\n");
        List<String> enrollments = ea.getStudentEnrollment(testStudent);
        assertNotNull(enrollments);
        System.out.println("End testGetEnrollments: objects are retrieved\n");
    }

    @Test
    public void testGetEnrollmentsResultSetComplete() throws SQLException {
        System.out.println("\nStarting testGetEnrollmentsResultSetComplete: objects are retrieved\n");
        List<String> enrollments = ea.getStudentEnrollment(testStudent);
        String[] resultSet = enrollments.get(0).split("@");
        assertEquals(resultSet.length, 7);
        System.out.println("End testGetEnrollmentsResultSetComplete: objects are retrieved\n");
    }

    @Test
    public void testInvalidEnroll() throws SQLException {
        System.out.println("\nStarting testInvalidEnroll: invalid operation not performed\n");
        ea.enroll(testStudent,"", testSection);

        List<String> enrollments = ea.getStudentEnrollment(testStudent);
        System.out.println("End testInvalidEnroll: invalid operation not performed\n");
    }

    @Test
    public void testValidEnroll() throws SQLException {
        System.out.println("\nStarting testValidEnroll: valid operation performed\n");
        ea.enroll(testStudent, testCourse, testSection);
        
        List<String> enrollments = ea.getStudentEnrollment(testStudent);
        String[] resultSet = enrollments.get(0).split("@");
        assertEquals(resultSet[0], testCourse);
        assertEquals(resultSet[3], testSection);
        assertEquals(enrollments.size(), 2);
        System.out.println("End testValidEnroll: valid operation performed\n");
    }

    @Test
    public void testUnenroll() throws SQLException {
        System.out.println("\nStarting testUnenroll: valid operation performed\n");
        ea.unenroll(testStudent, testSection, testCourse);
        List<String> enrollments = ea.getStudentEnrollment(testStudent);
        assertEquals(enrollments.size(), 1);
        System.out.println("End testUnenroll: valid operation performed\n");
    }

}
