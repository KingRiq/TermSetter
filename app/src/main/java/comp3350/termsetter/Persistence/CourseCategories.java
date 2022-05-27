package comp3350.termsetter.Persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.termsetter.Persistence.Faculty;

public class CourseCategories
{
    private final List<Faculty> faculties;

    public CourseCategories() { faculties = new ArrayList<>(); }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public Faculty getFaculty(int index) {
        return faculties.get(index);
    }

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

}