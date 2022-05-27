package comp3350.termsetter.Tests;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import comp3350.termsetter.Logic.EnrollmentLogic;
import comp3350.termsetter.Persistence.*;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.EnrollAccess;
import comp3350.termsetter.utils.TestUtils;

import static org.junit.Assert.*;

public class EnrollmentIntegrationTest
{
    EnrollmentLogic eL;
    EnrollAccess eA;
    private File tempDB;
    String testStudent = "test1"; // Default enrollment: COMP3350 A01 TR 09:30-10:20

    // To max out classes
    String additionalCourse1 = "COMP1010";
    CourseSection additionalSection1 = new CourseSection("A02", "MWF", "09:30-10:20", "2021/01/18-2021/04/18");
    String additionalCourse2 = "COMP3190";
    CourseSection additionalSection2 = new CourseSection("A02", "MWF", "10:30-11:20", "2021/01/18-2021/04/18");
    String additionalCourse3 = "COMP2080";
    CourseSection additionalSection3 = new CourseSection("A03", "MWF", "11:30-12:20", "2021/01/18-2021/04/18");
    String additionalCourse4 = "COMP1020";
    CourseSection additionalSection4 = new CourseSection("A01", "MWF", "14:30-15:20", "2021/01/18-2021/04/18");
    String additionalCourse5 = "ACC3081";
    CourseSection additionalSection5 = new CourseSection("A02", "TR", "14:30-15:20", "2021/01/18-2021/04/18");
    String excessCourse = "COMP4060";
    CourseSection excessSection = new CourseSection("A03", "MWF", "12:30-13:20", "2021/01/18-2021/04/18");

    @Before
    public void init() throws IOException {
        this.tempDB = TestUtils.copyDB();
        eA = new EnrollAccess(this.tempDB.getAbsolutePath().replace(".script", ""));
    }


    @Test
    public void testGetSuccessMessage() {
        System.out.println("\nStarting testGetSuccessMessage: success message present\n");

        String successCourse = "ACC1110";
        CourseSection successSection = new CourseSection("A01","TR", "10:30-11:30", "2021/01/18-2021/04/18");
        eL = new EnrollmentLogic(testStudent, successCourse, successSection);
        assertTrue(eL.getMessage().contains("Successfully enrolled"));

        System.out.println("\nEnding testGetSuccessMessage: success message present\n");
    }

    @Test
    public void testGetConflictMessage() {
        System.out.println("\nStarting testGetConflictMessage: conflict message present\n");

        String conflictCourse = "ACC1100";
        CourseSection conflictSection = new CourseSection("A02","TR", "09:30-10:20", "2021/01/18-2021/04/18");
        eL = new EnrollmentLogic(testStudent, conflictCourse, conflictSection);
        assertTrue(eL.getMessage().contains("Error: Failed to enroll due to a time conflict"));

        System.out.println("\nEnding testGetConflictMessage: conflict message present\n");
    }

    @Test
    public void testGetDuplicateMessage() {
        System.out.println("\nStarting testGetDuplicateMessage: duplicate message present\n");

        String dupCode = "COMP3350";
        CourseSection dupClass = new CourseSection("A02","MWF", "09:30-10:20", "2021/01/18-2021/04/18");
        eL = new EnrollmentLogic(testStudent, dupCode, dupClass);
        assertEquals(eL.getMessage(), "Error: Already enrolled in this course!");

        System.out.println("\nEnding testGetDuplicateMessage: duplicate message present\n");
    }

    @Test
    public void testGetClassLimitMessage() {
        System.out.println("\nStarting testGetClassLimitMessage: exceeding maximum classes message present\n");

        eL = new EnrollmentLogic(testStudent, additionalCourse1, additionalSection1);
        assertTrue(eL.getMessage().contains("Successfully enrolled"));
        eL = new EnrollmentLogic(testStudent, additionalCourse2, additionalSection2);
        assertTrue(eL.getMessage().contains("Successfully enrolled"));
        eL = new EnrollmentLogic(testStudent, additionalCourse3, additionalSection3);
        assertTrue(eL.getMessage().contains("Successfully enrolled"));
        eL = new EnrollmentLogic(testStudent, additionalCourse4, additionalSection4);
        assertTrue(eL.getMessage().contains("Successfully enrolled"));
        eL = new EnrollmentLogic(testStudent, additionalCourse5, additionalSection5);
        assertTrue(eL.getMessage().contains("Successfully enrolled"));

        eL = new EnrollmentLogic(testStudent, excessCourse, excessSection);
        assertEquals(eL.getMessage(), "You cannot enroll in more than six courses. You must drop a class to add a new one.");

        System.out.println("\nEnding testGetClassLimitMessage: exceeding maximum classes message present\n");
    }

}
