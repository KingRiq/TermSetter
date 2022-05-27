package comp3350.termsetter.Tests;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import static org.junit.Assert.*;

import comp3350.termsetter.Logic.AccessManager;
import comp3350.termsetter.Logic.AccountValidation;
import comp3350.termsetter.Logic.Services;
import comp3350.termsetter.Persistence.DomainSpecific.Student;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.StudentAccess;
import comp3350.termsetter.Persistence.StudentPersistence;
import comp3350.termsetter.utils.TestUtils;

public class StudentIntegrationTest {

    AccessManager ac;
    StudentAccess sa;
    AccountValidation av;
    private File tempDB;
    Student student;

    @Before
    public void setup() throws IOException {
        this.tempDB = TestUtils.copyDB();
        ac = new AccessManager();
        student = new Student("test", "test11", "test@myumanitoba.ca", "2034469008", "test1");
        av = new AccountValidation();
    }

    @Test
    public void testStudentPersistenceExists(){
        System.out.println("\nStarting testStudentPersistenceExists: object is retrieved\n");
        StudentPersistence  sp = ac.getStudentPersistence();
        assertNotNull(sp);
        System.out.println("End testStudentPersistenceExists: object is retrieved\n");
    }

    @Test
    public void testRetrieveStudent(){
        System.out.println("\nStarting testRetrieveStudent: object is retrieved\n");
        Student theStudent = ac.getStudent(student.getStudentID());
        assertNotNull(theStudent);
        System.out.println("End testRetrieveStudent: object is retrieved\n");
    }

    @Test
    public void testManualAddedStudent() {
        System.out.println("\nStarting testManualAddedStudent: object exists after creation\n");
        ac.insertStudent(student);
        assertNotNull(ac.getStudent(student.getStudentID()));
        System.out.println("End testManualAddedStudent: object exists after creation\n");
    }

    @Test
    public void testStudentRecordExists() {
        System.out.println("\nStarting testStudentRecordExists: given a new StudentID \n");
        boolean result = av.studentExists(student.getStudentID());
        assertTrue(result);
        System.out.println("End testStudentRecordExists: given a new StudentID\n");
    }

    @Test
    public void testVerifyStudent(){
        System.out.println("\nStarting testVerifyStudent: student credentials exist and are correct\n");
        assertTrue(av.verifyStudent(student.getStudentID(), student.getPassword()));
        System.out.println("End testVerifyStudent: student credentials exist and are correct\n");
    }

    @Test
    public void testVerifyCurrentPassword(){
        System.out.println("\nStarting testVerifyCurrentPassword: password matched with student\n");
        assertTrue(av.verifyCurrentPassword(student.getPassword(), student));
        System.out.println("End testVerifyCurrentPassword: password matched with student\n");
    }

    @Test
    public void testNonexistentStudent(){
        System.out.println("\nStarting testNonexistentStudent: student does not exist\n");
        assertFalse(av.verifyStudent("parrot", student.getPassword()));
        System.out.println("End testNonexistentStudent: student does not exist\n");
    }

    @Test
    public void testInvalidateStudent(){
        System.out.println("\nStarting testInvalidateStudent: student credentials are wrong\n");
        assertFalse(av.verifyStudent(student.getStudentID(), "parrot123"));
        System.out.println("End testInvalidateStudent: student credentials are wrong\n");
    }

}
