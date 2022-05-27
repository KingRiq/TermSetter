package comp3350.termsetter.Tests;

import org.junit.Before;
import org.junit.Test;

import comp3350.termsetter.Logic.EnrollmentLogic;
import comp3350.termsetter.Persistence.*;
import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EnrollmentLogicTestM {

    EnrollmentLogic eL;

    @Before
    public void setContext()
    {
        eL = mock(EnrollmentLogic.class);
    }

    @Test
    public void testAddCourseSuccess()
    {
        System.out.println("\nStarting testAddCourseSuccess: course is not a duplicate\n");

        CourseOffering successCourse = new CourseOffering("ENVR1000", "Env", 3);
        when(eL.addCourse(successCourse)).thenReturn(true);
        assertTrue(eL.addCourse(successCourse));
        verify(eL).addCourse(successCourse);

        System.out.println("\nEnding testAddCourseSuccess: course is not a duplicate\n");
    }

    @Test
    public void testAddCourseFail()
    {
        System.out.println("\nStarting testAddCourseFail: course is a duplicate\n");

        CourseOffering failCourse = new CourseOffering("COMP3350", "SE", 3);
        when(eL.addCourse(failCourse)).thenReturn(false);
        assertFalse(eL.addCourse(failCourse));
        verify(eL).addCourse(failCourse);

        System.out.println("\nEnding testAddCourseFail: course is a duplicate\n");
    }

    @Test
    public void testAddSectionSuccess()
    {
        System.out.println("\nStarting testAddSectionSuccess: section does not have a conflict\n");

        CourseSection successSection = new CourseSection("A03","TR", "14:31-15:31", "2021/01/18-2021/04/18");
        when(eL.addSection(successSection)).thenReturn(true);
        assertTrue(eL.addSection(successSection));
        verify(eL).addSection(successSection);

        System.out.println("\nEnding testAddSection: section does not have a conflict\n");
    }

    @Test
    public void testAddSectionSameTimeDiffDay()
    {
        System.out.println("\nStarting testAddSectionSameTimeDiffDay: section does not have a conflict\n");

        CourseSection successSection = new CourseSection("A02","TR", "11:30-12:30", "2021/01/18-2021/04/18");
        when(eL.addSection(successSection)).thenReturn(true);
        assertTrue(eL.addSection(successSection));
        verify(eL).addSection(successSection);

        System.out.println("\nEnding testAddSectionSameTimeDiffDay: section does not have a conflict\n");
    }

    @Test
    public void testAddSectionFail()
    {
        System.out.println("\nStarting testAddSectionFail: section has a conflict\n");

        CourseSection failSection = new CourseSection("A01","MWF", "11:30-12:30", "2021/01/18-2021/04/18");
        when(eL.addSection(failSection)).thenReturn(false);
        assertFalse(eL.addSection(failSection));
        verify(eL).addSection(failSection);

        System.out.println("\nEnding testAddSectionFail: section has a conflict\n");
    }

    @Test
    public void testHasNoTimeConflict()
    {
        System.out.println("\nStarting testHasNoTimeConflict: section has no conflict\n");

        String conflictingDays = "MWF";
        String notConflictingTime = "8:30-9:20";
        when(eL.checkConflict(conflictingDays,notConflictingTime)).thenReturn(false);
        assertFalse(eL.checkConflict(conflictingDays, notConflictingTime));
        verify(eL).checkConflict(conflictingDays,notConflictingTime);

        System.out.println("\nEnding testHasNoTimeConflict: section has no conflict\n");
    }

    @Test
    public void testHasNoDayConflict()
    {
        System.out.println("\nStarting testHasNoDayConflict: section has no conflict\n");

        String notConflictingDays = "TR";
        String conflictingTime = "11:30-12:00";
        when(eL.checkConflict(notConflictingDays,conflictingTime)).thenReturn(false);
        assertFalse(eL.checkConflict(notConflictingDays, conflictingTime));
        verify(eL).checkConflict(notConflictingDays,conflictingTime);

        System.out.println("\nEnding testHasNoDayConflict: section has no conflict\n");
    }

    @Test
    public void testHasConflict()
    {
        System.out.println("\nStarting testHasConflict: section has conflict\n");

        String conflictingDays = "MWF";
        String conflictingTime = "11:30-12:00";
        when(eL.checkConflict(conflictingDays,conflictingTime)).thenReturn(true);
        assertTrue(eL.checkConflict(conflictingDays, conflictingTime));
        verify(eL).checkConflict(conflictingDays,conflictingTime);

        System.out.println("\nEnding testHasConflict: section has conflict\n");
    }

    @Test
    public void testHasNoDuplicate()
    {
        System.out.println("\nStarting testHasNoDuplicate: course has no duplicate\n");

        String notDuplicateCourse = "COMP2080";
        when(eL.checkCodeDup(notDuplicateCourse)).thenReturn(false);
        assertFalse(eL.checkCodeDup(notDuplicateCourse));
        verify(eL).checkCodeDup(notDuplicateCourse);

        System.out.println("\nEnding testHasConflict: course has no duplicate\n");
    }

    @Test
    public void testHasDuplicate()
    {
        System.out.println("\nStarting testHasDuplicate: course has duplicate\n");

        String duplicateCourse = "COMP3350";
        when(eL.checkCodeDup(duplicateCourse)).thenReturn(true);
        assertTrue(eL.checkCodeDup(duplicateCourse));
        verify(eL).checkCodeDup(duplicateCourse);

        System.out.println("\nEnding testHasDuplicate: course has duplicate\n");
    }
}
