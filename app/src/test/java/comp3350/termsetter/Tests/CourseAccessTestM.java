package comp3350.termsetter.Tests;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.CourseAccess;


public class CourseAccessTestM {

    CourseAccess ca;
    List<String> faculties;
    List<String> courses;
    List<String> sections;
    String theFaculty;
    String theCourse;

    @Before
    public void setup(){
        ca = mock(CourseAccess.class);
        faculties = new ArrayList<>();
        courses = new ArrayList<>();
        sections = new ArrayList<>();
    }

    @Test
    public void testCourseAccessExists(){
        System.out.println("\nStarting testCourseAccessExists: object exists after creation\n");
        assertNotNull(ca);
        System.out.println("End testCourseAccessExists: object exists after creation\n");
    }

    @Test
    public void testGetFaculties() throws SQLException {
        System.out.println("\nStarting testGetFaculties: objects are retrieved\n");

        when(ca.getAllFaculties()).thenReturn(faculties);
        faculties = ca.getAllFaculties();
        assertNotNull(faculties);
        verify(ca).getAllFaculties();

        System.out.println("End testGetFaculties: objects are retrieved\n");
    }

    @Test
    public void testGetCourseByFaculty()
    {
        System.out.println("\nStarting testGetCourseByFaculty: objects are retrieved\n");

        when(ca.getCourseByFaculty(theFaculty)).thenReturn(courses);
        courses = ca.getCourseByFaculty(theFaculty);
        assertNotNull(courses);
        verify(ca).getCourseByFaculty(theFaculty);

        System.out.println("End testGetCourseByFaculty: objects are retrieved\n");
    }

    @Test
    public void testGetSectionByCourse(){
        System.out.println("\nStarting testGetSectionByCourse: objects are retrieved\n");

        when(ca.getSectionByCourse(theFaculty,theCourse)).thenReturn(sections);
        sections = ca.getSectionByCourse(theFaculty, theCourse);
        assertNotNull(sections);
        verify(ca).getSectionByCourse(theFaculty,theCourse);

        System.out.println("End testGetSectionByCourse: objects are retrieved\n");
    }

}
