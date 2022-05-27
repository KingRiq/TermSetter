package comp3350.termsetter.Persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.Integer.parseInt;

public class CourseCategoryDriver implements CourseCategoryPersistence {

    private CourseCategories categories;

    public CourseCategoryDriver(InputStream is) throws IOException {
        init(is);
    }

    public List<Faculty> getFaculties(){
        return categories.getFaculties();
    }


    private void init(InputStream is) throws IOException {

        // max 3 sections per course
        final int MAX_VALUE = 3;

        // String Arrays to fill sections constructors
        String[] days = {"MWF", "TR"};
        String[] timeSlots = {"8:30-9:20", "11:30-12:45", "4:00-5:15", "2:30-3:45", "12:30-1:20"};
        String[] sectionArray = {"A01", "A02", "A03"};
        String instructor = "Dr. Andrea Bunt";
        Faculty faculty = null;
        categories = new CourseCategories();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //Read the file and populate Course Objects
        String i = br.readLine();
        while (i != null) {
            if (i.charAt(0) == '$') {
                faculty = new Faculty(i.substring(1));
                categories.addFaculty(faculty);
            } else {
                String[] p = i.split("@");
                CourseOffering course = new CourseOffering(p[0], p[1], parseInt(p[2]));
                faculty.addCourses(course);

                // populate the Section Objects
                for (int x = 0; x < MAX_VALUE; x++) {
                    int randomDays = (int) (Math.random() * days.length);
                    int randomTime = (int) (Math.random() * timeSlots.length);
                    CourseSection section = new CourseSection(sectionArray[x], days[randomDays], timeSlots[randomTime], instructor);
                    course.addSection(section);
                }
            }
            i = br.readLine();
        }
        br.close();
    }

}
