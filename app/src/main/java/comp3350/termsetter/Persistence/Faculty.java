package comp3350.termsetter.Persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Faculty implements Serializable {
    private final String name;
    private final List<CourseOffering> listOfCourses;

    public Faculty(String name) {
        this.name = name;
        listOfCourses = new ArrayList<>();
    }

    public List<CourseOffering> getCourses() {
        return listOfCourses;
    }

    public String getName() {
        return name;
    }

    public CourseOffering getCourse(int index) {
        return listOfCourses.get(index);
    }

    public void addCourses(CourseOffering course) {
        listOfCourses.add((course));
    }

    public List<CourseOffering> getCoursesByLevel(int lv) {
        ArrayList<CourseOffering> theCourses = new ArrayList<CourseOffering>();
        for (int i = 0; i < listOfCourses.size(); i++) {
            String code = listOfCourses.get(i).getCourseCode();
            int startInd = 4;
            if(Character.isDigit(code.charAt(3))){ // handle some course codes that contain 3 letters
                startInd = 3;
            }
            int start = Integer.parseInt(code.substring(startInd, startInd+1));
            if(start == lv){
                theCourses.add(listOfCourses.get(i));
            }
        }
        return (List) theCourses;
    }

}

