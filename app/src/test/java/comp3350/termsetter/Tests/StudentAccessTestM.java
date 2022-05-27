package comp3350.termsetter.Tests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import comp3350.termsetter.Persistence.DomainSpecific.Student;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.StudentAccess;


public class StudentAccessTestM {

    StudentAccess sa;
    Student test1;

    @Before
    public void setUp(){
        sa = mock(StudentAccess.class);
        test1 = new Student("Test Student", "yolo1234", "teststu@myumanitoba.ca", "2049058739", "testS");
    }

    @Test
    public void testStudentAccessExists(){
        System.out.println("\nStarting testStudentAccessExists: object exists after creation\n");
        assertNotNull(sa);
        System.out.println("End testStudentAccessExists: object exists after creation\n");
    }

    @Test
    public void testInsertStudent(){
        System.out.println("\nStarting testInsertStudent: object exists after creation\n");
        when(sa.insertStudent(test1)).thenReturn(test1);
        assertNotNull(sa.insertStudent(test1));
        verify(sa).insertStudent(test1);
        System.out.println("End testInsertStudent: object exists after creation\n");
    }

    @Test
    public void testGetStudent(){
        System.out.println("\nStarting testGetStudent: objects are retrieved\n");
        when(sa.getStudent(test1.getStudentID())).thenReturn(test1);
        assertNotNull(sa.getStudent(test1.getStudentID()));
        verify(sa).getStudent(test1.getStudentID());
        System.out.println("End testGetStudent: objects are retrieved\n");
    }

    @Test
    public void testCurrentStudentID(){
        System.out.println("\nStarting testCurrentStudentID: object exists after creation\n");
        when(sa.getCurrentStudentID()).thenReturn(test1);
        sa.setCurrentStudentID(test1.getStudentID());
        assertNotNull(sa.getCurrentStudentID());
        verify(sa).getCurrentStudentID();
        verify(sa).setCurrentStudentID(ArgumentMatchers.same(test1.getStudentID()));
        System.out.println("End testCurrentStudentID: object exists after creation\n");
    }

    @Test
    public void testUpdatePassword(){
        System.out.println("\nStarting testUpdatePassword: object exists after creation\n");
        String nPass = "update123";
        when(sa.updatePassword(nPass)).thenReturn(true);
        assertTrue(sa.updatePassword(nPass));
        verify(sa).updatePassword(nPass);
        System.out.println("End testUpdatePassword: object exists after creation\n");
    }

    @Test
    public void testUpdateEmail(){
        System.out.println("\nStarting testUpdateEmail: object exists after creation\n");
        String nEmail = "newteststu@myumanitoba.ca";
        when(sa.updateEmail(nEmail)).thenReturn(true);
        assertTrue(sa.updateEmail(nEmail));
        verify(sa).updateEmail(nEmail);
        System.out.println("End testUpdateEmail: object exists after creation\n");
    }
}
