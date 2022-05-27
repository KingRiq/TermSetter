package comp3350.termsetter.Tests;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.EnrollAccess;

public class EnrollAccessTestM {

    EnrollAccess ea;
    List<String> enrollments;
    String testStudent;
    String testCourse;
    String testSection;

    @Before
    public void setup(){
        ea = mock(EnrollAccess.class);
        enrollments = new ArrayList<>();
        testStudent = "test1";
        testCourse = "COMP1010";
        testSection = "A01";
    }

    @Test
    public void testEnrollAccessExists(){
        System.out.println("\nStarting testEnrollAccessExists: object exists after creation\n");
        assertNotNull(ea);
        System.out.println("End testEnrollAccessExists: object exists after creation\n");
    }

    @Test
    public void testGetEnrollments(){
        System.out.println("\nStarting testGetEnrollments: objects are retrieved\n");
        when(ea.getStudentEnrollment(testStudent)).thenReturn(enrollments);
        enrollments = ea.getStudentEnrollment(testStudent);
        assertNotNull(enrollments);
        verify(ea).getStudentEnrollment(testStudent);
        System.out.println("End testGetEnrollments: objects are retrieved\n");
    }

    @Test
    public void testEnroll(){
        System.out.println("\nStarting testEnroll: valid operation performed\n");
        doThrow(IllegalArgumentException.class).when(ea).enroll(null, null, null);
        ea.enroll(testStudent,testCourse, testSection);
        verify(ea).enroll(testStudent,testCourse,testSection);
        System.out.println("End testEnroll: valid operation performed\n");
    }


    @Test
    public void testUnenroll(){
        System.out.println("\nStarting testUnenroll: valid operation performed\n");
        doThrow(IllegalArgumentException.class).when(ea).unenroll(null, null, null);
        ea.unenroll(testStudent, testSection, testCourse);
        verify(ea).unenroll(testStudent,testSection,testCourse);
        System.out.println("End testUnenroll: valid operation performed\n");
    }
}
