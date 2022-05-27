package comp3350.termsetter.Tests;

import org.junit.Before;
import org.junit.Test;

import comp3350.termsetter.Logic.EnrollmentLogic;
import comp3350.termsetter.Persistence.*;
import static org.junit.Assert.*;

public class EnrollmentLogicTest
{
    EnrollmentLogic eL;

    @Before
    public void setContext()
    {
        String courseCode = "COMP3350";
        CourseSection selectedClass = new CourseSection("A01","MWF", "11:30-12:30", "2021/01/18-2021/04/18");
        eL = new EnrollmentLogic("test1", courseCode, selectedClass);
    }


    @Test
    public void testAddCourseSuccess()
    {
        System.out.println("\nStarting testAddCourseSuccess: course is not a duplicate\n");

        CourseOffering successCourse = new CourseOffering("ENVR1000", "Env", 3);
        assertTrue(eL.addCourse(successCourse));

        System.out.println("\nEnding testAddCourseSuccess: course is not a duplicate\n");
    }

    @Test
    public void testAddCourseFail()
    {
        System.out.println("\nStarting testAddCourseFail: course is a duplicate\n");

        CourseOffering failCourse = new CourseOffering("COMP3350", "SE", 3);
        assertFalse(eL.addCourse(failCourse));

        System.out.println("\nEnding testAddCourseFail: course is a duplicate\n");
    }

    @Test
    public void testAddSectionSuccess()
    {
        System.out.println("\nStarting testAddSectionSuccess: section does not have a conflict\n");

        CourseSection successSection = new CourseSection("A03","TR", "14:31-15:31", "2021/01/18-2021/04/18");
        assertTrue(eL.addSection(successSection));

        System.out.println("\nEnding testAddSection: section does not have a conflict\n");
    }

    @Test
    public void testAddSectionSameTimeDiffDay()
    {
        System.out.println("\nStarting testAddSectionSameTimeDiffDay: section does not have a conflict\n");

        CourseSection successSection = new CourseSection("A02","TR", "11:30-12:30", "2021/01/18-2021/04/18");
        assertTrue(eL.addSection(successSection));

        System.out.println("\nEnding testAddSectionSameTimeDiffDay: section does not have a conflict\n");
    }

    @Test
    public void testAddSectionFail()
    {
        System.out.println("\nStarting testAddSectionFail: section has a conflict\n");

        CourseSection failSection = new CourseSection("A01","MWF", "11:30-12:30", "2021/01/18-2021/04/18");
        assertFalse(eL.addSection(failSection));

        System.out.println("\nEnding testAddSectionFail: section has a conflict\n");
    }

    @Test
    public void testHasNoTimeConflict()
    {
        System.out.println("\nStarting testHasNoTimeConflict: section has no conflict\n");

        String conflictingDays = "MWF";
        String notConflictingTime = "8:30-9:20";
        assertFalse(eL.checkConflict(conflictingDays, notConflictingTime));

        System.out.println("\nEnding testHasNoTimeConflict: section has no conflict\n");
    }

    @Test
    public void testHasNoDayConflict()
    {
        System.out.println("\nStarting testHasNoDayConflict: section has no conflict\n");

        String notConflictingDays = "TR";
        String conflictingTime = "11:30-12:00";
        assertFalse(eL.checkConflict(notConflictingDays, conflictingTime));

        System.out.println("\nEnding testHasNoDayConflict: section has no conflict\n");
    }

    @Test
    public void testHasConflict()
    {
        System.out.println("\nStarting testHasConflict: section has conflict\n");

        String conflictingDays = "MWF";
        String conflictingTime = "11:30-12:00";
        assertTrue(eL.checkConflict(conflictingDays, conflictingTime));

        System.out.println("\nEnding testHasConflict: section has conflict\n");
    }

    @Test
    public void testHasConflictOverlap()
    {
        System.out.println("\nStarting testHasConflictOverlap: section has conflict\n");

        String conflictingDay = "M";
        String conflictingTime = "11:30-12:00";
        assertTrue(eL.checkConflict(conflictingDay, conflictingTime));

        System.out.println("\nEnding testHasConflictOverlap: section has conflict\n");
    }

    @Test
    public void testHasNoDuplicate()
    {
        System.out.println("\nStarting testHasNoDuplicate: course has no duplicate\n");

        String notDuplicateCourse = "COMP2080";
        assertFalse(eL.checkCodeDup(notDuplicateCourse));

        System.out.println("\nEnding testHasConflict: course has no duplicate\n");
    }

    @Test
    public void testHasDuplicate()
    {
        System.out.println("\nStarting testHasDuplicate: course has duplicate\n");

        String duplicateCourse = "COMP3350";
        assertTrue(eL.checkCodeDup(duplicateCourse));

        System.out.println("\nEnding testHasDuplicate: course has duplicate\n");
    }

    @Test
    public void testDayOverlap()
    {
        System.out.println("\nStarting testDayOverlap: days are overlapping\n");

        String day1 = "M";
        String day2 = "MWF";
        assertTrue(eL.daysOverlap(day1,day2));

        System.out.println("\nEnding testDayOverlap: days are overlapping\n");
    }

    @Test
    public void testParseTime()
    {
        System.out.println("\nStarting testParseTime: expected return\n");

        String time = "0:0";
        assertEquals(eL.parseTime(time), 0);

        System.out.println("\nEnding testParseTime: expected return\n");
    }

    @Test
    public void testCalculateMinutes()
    {
        System.out.println("\nStarting testCalculateMinutes: expected return\n");

        int hours = 2;
        int minutes = 10;
        int totalMinutes = hours * 60 + minutes;
        assertEquals(eL.calculateMinutes(hours, minutes), totalMinutes);

        System.out.println("\nEnding testCalculateMinutes: expected return\n");
    }

}
