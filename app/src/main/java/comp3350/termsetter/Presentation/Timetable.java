package comp3350.termsetter.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import comp3350.termsetter.Logic.AccessManager;
import comp3350.termsetter.Logic.TimetableLogic;
import comp3350.termsetter.Persistence.CourseOffering;
import comp3350.termsetter.Persistence.CourseSection;
import comp3350.termsetter.Persistence.DomainSpecific.Student;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.EnrollAccess;
import comp3350.termsetter.Persistence.Main;
import comp3350.termsetter.Persistence.StudentPersistence;
import comp3350.termsetter.R;
import comp3350.termsetter.UIAdapters.RecyclerTimetableDataAdapter;

public class Timetable extends AppCompatActivity implements OnItemSelectedListener {

    List<CourseOffering> enrolledCourses;
    List<CourseSection> enrolledSections;
    EnrollAccess enrollAccess;
    Student student;

    RecyclerTimetableDataAdapter recyclerAdapter;
    TimetableLogic display;
    List<List<CourseOffering>> coursesByDay = new ArrayList<List<CourseOffering>>();
    List<List<CourseSection>> sectionsByDay = new ArrayList<List<CourseSection>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        initData();
        initWidgets();
    }

    private void initData() {
        enrolledCourses = new ArrayList<CourseOffering>();
        enrolledSections = new ArrayList<CourseSection>();

        // Get currently enrolled student
        AccessManager accessManager = new AccessManager();
        StudentPersistence database = accessManager.getStudentPersistence();
        student = database.getCurrentStudentID();

        // Get student's enrollments
        String path = Main.getDBPathName();
        enrollAccess = new EnrollAccess(path);
        List<String> results = enrollAccess.getStudentEnrollment(student.getStudentID());
        for(int i = 0; i < results.size(); i++) {
            String result = results.get(i);
            String[] tokens = result.split("@");
            CourseOffering currCourse = new CourseOffering(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
            enrolledCourses.add(currCourse);
            CourseSection currClass = new CourseSection(tokens[3], tokens[4], tokens[5], tokens[6]);
            enrolledSections.add(currClass);
        }

        // Initiate timetable logic and day lists
        display = new TimetableLogic(enrolledCourses, enrolledSections);
        int numDays =  getResources().getStringArray(R.array.days_array).length;
        String days = "MTWRF";
        for(int i = 0; i < numDays; i++){
            String day = days.substring(i, i+1);
            List<CourseOffering> coursesForDay = display.getCourse(day);
            coursesByDay.add(coursesForDay);
            List<CourseSection> sectionsForDay = display.getSection(day);
            sectionsByDay.add(sectionsForDay);
        }
    }

    private void initWidgets() {
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.daySpinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.days_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        List<CourseOffering> courses = coursesByDay.get(position);
        List<CourseSection> sections = sectionsByDay.get(position);
        RecyclerView classes = (RecyclerView)findViewById(R.id.recycleEnrolledClasses);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        classes.setLayoutManager(layoutManager);
        recyclerAdapter= new RecyclerTimetableDataAdapter(courses, sections, getResources().getColor(R.color.cardBackground1));
        classes.setAdapter(recyclerAdapter);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        parent.setSelection(0);
    }

    public void timetableBackToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}