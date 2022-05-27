package comp3350.termsetter.Tests;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import static org.junit.Assert.*;

import comp3350.termsetter.Persistence.DomainSpecific.Student;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.StudentAccess;
import comp3350.termsetter.utils.TestUtils;

public class StudentAccessTest {

    StudentAccess sa;
    private File tempDB;
    Student test1;

    @Before
    public void setup() throws IOException {
        this.tempDB = TestUtils.copyDB();
        sa = new StudentAccess(this.tempDB.getAbsolutePath().replace(".script", ""));
        test1 = new Student("Test Student", "yolo1234", "teststu@myumanitoba.ca", "2049058739", "testS");
    }

    @Test
    public void testStudentAccessExists() throws SQLException{
        System.out.println("\nStarting testStudentAccessExists: object exists after creation\n");
        assertNotNull(sa);
        System.out.println("End testStudentAccessExists: object exists after creation\n");
    }

    @Test
    public void testInsertStudent() throws SQLException {
        System.out.println("\nStarting testInsertStudent: object exists after creation\n");
        assertNotNull(sa.insertStudent(test1));
        System.out.println("End testInsertStudent: object exists after creation\n");
    }

    @Test
    public void testGetStudent() throws SQLException {
        System.out.println("\nStarting testGetStudent: objects are retrieved\n");
        sa.insertStudent(test1);
        assertNotNull(sa.getStudent(test1.getStudentID()));
        System.out.println("End testGetStudent: objects are retrieved\n");
    }

    @Test
    public void testCurrentStudentID() throws SQLException {
        System.out.println("\nStarting testCurrentStudentID: object exists after creation\n");
        sa.insertStudent(test1);
        sa.setCurrentStudentID(test1.getStudentID());
        assertNotNull(sa.getCurrentStudentID());
        System.out.println("End testCurrentStudentID: object exists after creation\n");
    }

    @Test
    public void testUpdatePassword() throws SQLException {
        System.out.println("\nStarting testUpdatePassword: object exists after creation\n");
        sa.insertStudent(test1);
        sa.setCurrentStudentID(test1.getStudentID());
        String nPass = "update123";
        assertTrue(sa.updatePassword(nPass));
        assertEquals(sa.getCurrentStudentID().getPassword(), nPass);
        System.out.println("End testUpdatePassword: object exists after creation\n");
    }

    @Test
    public void testUpdateEmail() throws SQLException {
        System.out.println("\nStarting testUpdateEmail: object exists after creation\n");
        sa.insertStudent(test1);
        sa.setCurrentStudentID(test1.getStudentID());
        String nEmail = "newteststu@myumanitoba.ca";
        assertTrue(sa.updateEmail(nEmail));
        assertEquals(sa.getCurrentStudentID().getEmailAddress(), nEmail);
        System.out.println("End testUpdateEmail: object exists after creation\n");
    }

}
