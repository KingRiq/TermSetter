package comp3350.termsetter.Persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseOffering implements Serializable
{
    private final String courseCode;
    private final String name;
    private final int creditHours;
    private final List<CourseSection> sections;

    public CourseOffering(String courseCode, String name, int creditHours)
    {
        this.courseCode = courseCode;
        this.name = name;
        this.creditHours = creditHours;
        sections = new ArrayList<>();
    }

    public List<CourseSection> getSections() {
        return sections;
    }

    public CourseSection getCourse(int index) {
        return sections.get(index);
    }

    public String getName() {
        return name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void addSection(CourseSection section) {
        sections.add((section));
    }

    public boolean equals(CourseOffering otherCourse){
        return courseCode.equals(otherCourse.getCourseCode()) && name.equals(otherCourse.getName()) && creditHours == otherCourse.getCreditHours();
    }


}
