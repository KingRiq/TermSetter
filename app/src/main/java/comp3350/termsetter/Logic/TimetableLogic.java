package comp3350.termsetter.Logic;

import java.util.ArrayList;
import java.util.List;

import comp3350.termsetter.Persistence.CourseOffering;
import comp3350.termsetter.Persistence.CourseSection;

public class TimetableLogic {
    List<CourseOffering> studentCourses;
    List<CourseSection> studentSections;
    int coursesNum;

    public TimetableLogic (List<CourseOffering> enrolledCourses, List<CourseSection> enrolledSections) {
        studentCourses = enrolledCourses;
        studentSections = enrolledSections;
        coursesNum = studentCourses.size();
    }

    public List<CourseOffering> getCourse(String day) {
        List<CourseOffering> courses = new ArrayList<>();
        List<CourseSection> sections = new ArrayList<>();

        for (int i = 0; i < coursesNum; i++) {
            CourseOffering course = studentCourses.get(i);
            CourseSection section = studentSections.get(i);
            if (section.getDays().contains(day)) {
                courses.add(course);
                sections.add(section);
            }
        }
        List<CourseOffering> result = sort(courses, sections);
        return result;
    }

    public List<CourseSection> getSection(String day) {
        List<CourseSection> sections = new ArrayList<>();

        for (int i = 0; i < coursesNum; i++) {
            CourseSection section = studentSections.get(i);
            if (section.getDays().contains(day)) {
                sections.add(section);
            }
        }
        List<CourseSection> result = sort(sections);
        return result;
    }

    public List<CourseSection> sort(List<CourseSection> studentSections) {
        int size = studentSections.size();
        for (int i = 1; i < size; ++i) {
            int j = i - 1;
            CourseSection sectionKey = studentSections.get(i);
            int keyTime = parseTime(studentSections.get(i).getTimeSlot());

            while (j >= 0 && parseTime(studentSections.get(j).getTimeSlot()) > keyTime) {
                studentSections.set(j+1, studentSections.get(j));
                j = j - 1;
            }
            studentSections.set(j+1, sectionKey);
        }
        return studentSections;
    }

    public List<CourseOffering> sort(List<CourseOffering> studentCourses, List<CourseSection> studentSections) {
        int size = studentSections.size();
        for (int i = 1; i < size; ++i) {
            int j = i - 1;
            CourseSection sectionKey = studentSections.get(i);
            CourseOffering courseKey = studentCourses.get(i);
            int keyTime = parseTime(studentSections.get(i).getTimeSlot());

            while (j >= 0 && parseTime(studentSections.get(j).getTimeSlot()) > keyTime) {
                studentSections.set(j + 1, studentSections.get(j));
                studentCourses.set(j + 1, studentCourses.get(j));
                j = j - 1;
            }
            studentCourses.set(j + 1, courseKey);
            studentSections.set(j + 1, sectionKey);
        }
        return studentCourses;
    }

    public static int parseTime(String time)
    {
        String[] timeSlot = time.split("-");
        String[] timeParts = timeSlot[0].split(":");
        int hour = Integer.parseInt(timeParts[0].trim());
        int mins = Integer.parseInt(timeParts[1].trim());
        return calculateMinutes(hour,mins);
    }

    //helper for changing time into minutes
    public static int calculateMinutes(int hour, int minute) {
        return hour*60+minute; }
}