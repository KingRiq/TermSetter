package comp3350.termsetter.Tests;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.termsetter.Persistence.*;
import static org.junit.Assert.*;

public class ClassDataStructureTest {

    @Test
    public void testCreateCourseCategories() {
        System.out.println("\nStarting testCreateCourseCategories: object exists after creation\n");
        CourseCategories ct = new CourseCategories();
        assertNotNull(ct);
        System.out.println("\nEnd testCreateCourseCategories: object exists after creation\n");
    }

    @Test
    public void testAddFaculty() {
        System.out.println("\nStarting testAddFaculty: correct object retrieved\n");
        CourseCategories ct = new CourseCategories();
        String fName = "Faculty of tests";
        ct.addFaculty(new Faculty(fName));
        Faculty myFaculty = ct.getFaculty(0);
        assertEquals(myFaculty.getName(), fName);
        System.out.println("\nEnd testAddFaculty: correct object retrieved\n");
    }

    @Test
    public void testGetFacultyArray() {
        System.out.println("\nStarting testGetFacultyArray: retrieving a list\n");
        CourseCategories ct = new CourseCategories();
        String fName1 = "Faculty of tests";
        String fName2 = "Faculty of exams";
        ct.addFaculty(new Faculty(fName1));
        ct.addFaculty(new Faculty(fName2));
        ArrayList<Faculty> faculties = (ArrayList<Faculty>) ct.getFaculties();
        assertEquals(faculties.get(0).getName(), fName1);
        assertEquals(faculties.get(1).getName(), fName2);
        System.out.println("\nEnd testGetFacultyArray: retrieving a list\n");
    }

    @Test
    public void testCreateFaculty() {
        System.out.println("\nStarting testCreateFaculty: object exists after creation\n");
        Faculty f = new Faculty("A Faculty");
        assertNotNull(f);
        System.out.println("\nEnd testCreateFaculty: object exists after creation\n");
    }

    @Test
    public void testAddCourse() {
        System.out.println("\nStarting testAddCourse: correct object retrieved\n");
        Faculty f = new Faculty("Faculty of tests");
        String courseName = "Testing1020";
        f.addCourses(new CourseOffering("TEST1020", courseName, 3));
        CourseOffering myCourse = f.getCourse(0);
        assertEquals(myCourse.getName(), courseName);
        System.out.println("\nEnd testAddCourse: correct object retrieved\n");
    }

    @Test
    public void testGetCourseArray() {
        System.out.println("\nStarting testGetCourseArray: retrieving a list\n");
        Faculty f = new Faculty("Computer Science");
        String code1 = "COMP4040";
        String code2 = "COMP5050";
        f.addCourses(new CourseOffering(code1, "Arbitrary", 2));
        f.addCourses(new CourseOffering(code2, "Arbitrary", 3));
        ArrayList<CourseOffering> co = (ArrayList<CourseOffering>) f.getCourses();
        assertEquals(co.get(0).getCourseCode(), code1);
        assertEquals(co.get(1).getCourseCode(), code2);
        System.out.println("\nEnd testGetCourseArray: retrieving a list\n");
    }

    @Test
    public void testGetCourseByLevel() {
        System.out.println("\nStarting testGetCourseByLevel: correct item returned\n");
        Faculty f = new Faculty("Computer Science");
        String code1 = "COMP1000";
        String code2 = "COMP2000";
        String code3 = "COMP3000";
        String code4 = "COMP4000";
        f.addCourses(new CourseOffering(code1, "Arbitrary", 2));
        f.addCourses(new CourseOffering(code2, "Arbitrary", 3));
        f.addCourses(new CourseOffering(code3, "Arbitrary", 2));
        f.addCourses(new CourseOffering(code4, "Arbitrary", 3));
        List<CourseOffering> lv2 = f.getCoursesByLevel(2);
        assertEquals(lv2.get(0).getCourseCode(), code2);
        System.out.println("\nEnd testGetCourseByLevel: correct item returned\n");
    }

    @Test
    public void testGet3CharCourseByLevel() {
        System.out.println("\nStarting testGet3CharCourseByLevel: correct item returned\n");
        Faculty f = new Faculty("Computer Science");
        String code1 = "MIS1000";
        String code2 = "MIS2000";
        String code3 = "MIS3000";
        String code4 = "MIS4000";
        f.addCourses(new CourseOffering(code1, "Arbitrary", 2));
        f.addCourses(new CourseOffering(code2, "Arbitrary", 3));
        f.addCourses(new CourseOffering(code3, "Arbitrary", 2));
        f.addCourses(new CourseOffering(code4, "Arbitrary", 3));
        List<CourseOffering> lv4 = f.getCoursesByLevel(4);
        assertEquals(lv4.get(0).getCourseCode(), code4);
        System.out.println("\nEnd testGet3CharCourseByLevel: correct item returned\n");
    }

    @Test
    public void testCreateCourseOffering() {
        System.out.println("\nStarting testCreateCourseOffering: object exists after creation\n");
        CourseOffering co = new CourseOffering("COU8888", "How to not fail as tester", 3);
        assertNotNull(co);
        System.out.println("\nEnd testCreateCourseOffering: object exists after creation\n");
    }

    @Test
    public void testAddSection() {
        System.out.println("\nStarting testAddSection: correct object retrieved\n");
        CourseOffering co = new CourseOffering("COFF1000", "Drinking Coffee", 3);
        String prof = "A Coffee Mug";
        co.addSection(new CourseSection("A01", "MW", "1:00-2:00", prof));
        CourseSection mySection = co.getCourse(0);
        assertEquals(mySection.getPeriod(), prof);
        System.out.println("\nEnd testAddSection: correct object retrieved\n");
    }

    @Test
    public void testGetSectionArray() {
        System.out.println("\nStarting testGetSectionArray: retrieving a list\n");
        CourseOffering co = new CourseOffering("YES2021", "We're so Done", 3);
        String red = "Red Prof";
        String yellow = "Yellow Prof";
        co.addSection(new CourseSection("A01", "Everyday", "3:30-9:20", red));
        co.addSection(new CourseSection("A02", "M", "9:20-3:30", yellow));
        ArrayList<CourseSection> s = (ArrayList<CourseSection>) co.getSections();
        assertEquals(s.get(0).getPeriod(), red);
        assertEquals(s.get(1).getPeriod(), yellow);
        System.out.println("\nEnd testGetSectionArray: retrieving a list\n");
    }

    @Test
    public void testCourseEqual() {
        System.out.println("\nStarting testCourseEqual: objects are equal\n");
        String courseCode = "TEST1000";
        String name = "How to test";
        int creditHours = 9;
        CourseOffering c = new CourseOffering(courseCode, name, creditHours);
        assertTrue(c.equals(new CourseOffering(courseCode, name, creditHours)));
        System.out.println("\nEnd testCourseEqual: objects are equal\n");
    }

    @Test
    public void testCourseNotEqual() {
        System.out.println("\nStarting testCourseNotEqual: objects are not equal\n");
        String courseCode = "TEST1000";
        String name = "How to test";
        int creditHours = 9;
        CourseOffering c = new CourseOffering(courseCode, name, creditHours);
        assertFalse(c.equals(new CourseOffering(courseCode, name, 10)));
        System.out.println("\nEnd testCourseNotEqual: objects are not equal\n");
    }

    @Test
    public void testCreateSection() {
        System.out.println("\nStarting testCreateSection: object exists after creation\n");
        CourseSection s = new CourseSection("T01", "MTWRF", "8:30-9:30", "Test Dr.");
        assertNotNull(s);
        System.out.println("\nEnd testCreateSection: object exists after creation\n");
    }

    @Test
    public void testGetSection() {
        System.out.println("\nStarting testGetSection: value retrieved is correct\n");
        String code = "T01";
        CourseSection s = new CourseSection(code, "MTWRF", "8:30-9:30", "Test Dr.");
        assertEquals(code, s.getSection());
        System.out.println("\nEnd testGetSection: value retrieved is correct\n");
    }

    @Test
    public void testGetDays() {
        System.out.println("\nStarting testGetDays: value retrieved is correct\n");
        String days = "MTWRF";
        CourseSection s = new CourseSection("T01", days, "8:30-9:30", "Test Dr.");
        assertEquals(days, s.getDays());
        System.out.println("\nEnd testGetDays: value retrieved is correct\n");
    }

    @Test
    public void testGetTime() {
        System.out.println("\nStarting testGetTime: value retrieved is correct\n");
        String time = "8:30-9:30";
        CourseSection s = new CourseSection("T01", "MTWRF", time, "Test Dr.");
        assertEquals(time, s.getTimeSlot());
        System.out.println("\nEnd testGetTime: value retrieved is correct\n");
    }

    @Test
    public void testGetPeriod() {
        System.out.println("\nStarting testGetPeriod: value retrieved is correct\n");
        String period = "2021/01/18-2021/04/18";
        CourseSection s = new CourseSection("T01", "MTWRF", "8:30-9:30", period);
        assertEquals(period, s.getPeriod());
        System.out.println("\nEnd testGetPeriod: value retrieved is correct\n");
    }

    @Test
    public void testCourseAvailable() {
        System.out.println("\nStarting testCourseAvailable: value is true\n");
        CourseSection s = new CourseSection("T01", "MTWRF", "8:30-9:30", "Test Dr.");
        int maxOccupancy = 20;
        int occupants = 19;
        s.setMaxOccupancy(maxOccupancy);
        for(int i = 0; i < occupants; i++){
            s.enroll();
        }
        assertTrue(s.courseAvailable());
        System.out.println("\nEnd testCourseAvailable: value is true\n");
    }

    @Test
    public void testCourseNotAvailable() {
        System.out.println("\nStarting testCourseNotAvailable: value is false\n");
        CourseSection s = new CourseSection("T01", "MTWRF", "8:30-9:30", "Test Dr.");
        int maxOccupancy = 20;
        s.setMaxOccupancy(maxOccupancy);
        for(int i = 0; i < maxOccupancy; i++){
            s.enroll();
        }
        assertFalse(s.courseAvailable());
        System.out.println("\nEnd testCourseNotAvailable: value is false\n");
    }

    @Test
    public void testSectionEqual() {
        System.out.println("\nStarting testSectionEqual: objects are equal\n");
        String section = "T01";
        String days = "MTWRF";
        String timeSlot = "8:30-9:30";
        String period = "2021/01/18-2021/04/18";
        CourseSection s = new CourseSection(section, days, timeSlot, period);
        assertTrue(s.equals(new CourseSection(section, days, timeSlot, period)));
        System.out.println("\nEnd testSectionEqual: objects are equal\n");
    }

    @Test
    public void testSectionNotEqual() {
        System.out.println("\nStarting testSectionNotEqual: objects are not equal\n");
        String section = "T01";
        String days = "MTWRF";
        String timeSlot = "8:30-9:30";
        String period = "2021/01/18-2021/04/18";
        CourseSection s = new CourseSection(section, days, timeSlot, period);
        assertFalse(s.equals(new CourseSection(section, "MTWR", timeSlot, period)));
        System.out.println("\nEnd testSectionNotEqual: objects are not equal\n");
    }

}

