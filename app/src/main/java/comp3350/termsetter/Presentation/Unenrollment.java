package comp3350.termsetter.Presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.termsetter.Logic.AccessManager;
import comp3350.termsetter.Persistence.CourseOffering;
import comp3350.termsetter.Persistence.CourseSection;
import comp3350.termsetter.Persistence.DomainSpecific.Student;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.EnrollAccess;
import comp3350.termsetter.Persistence.Main;
import comp3350.termsetter.Persistence.StudentPersistence;
import comp3350.termsetter.R;
import comp3350.termsetter.UIAdapters.RecyclerCurrClassDataAdapter;
import comp3350.termsetter.UIAdapters.RecyclerRemoveClassDataAdapter;

public class Unenrollment extends AppCompatActivity {

    List<CourseOffering> enrolledCourses;
    List<CourseSection> enrolledSections;
    EnrollAccess enrollAccess;
    Student student;
    RecyclerRemoveClassDataAdapter recyclerAdapter;
    RecyclerView classes;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unenrollment);
        initData();
        initWidgets();
    }

    private void initData() {
        enrolledCourses = new ArrayList<>();
        enrolledSections = new ArrayList<>();

        AccessManager accessManager = new AccessManager();
        StudentPersistence database = accessManager.getStudentPersistence();
        student = database.getCurrentStudentID();

        String path = Main.getDBPathName();
        enrollAccess = new EnrollAccess(path);

        List<String> results = enrollAccess.getStudentEnrollment(student.getStudentID());

        for(int i = 0; i < results.size(); i++){
            String result = results.get(i);
            String[] tokens = result.split("@");
            CourseOffering currCourse = new CourseOffering(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
            enrolledCourses.add(currCourse);
            CourseSection currClass = new CourseSection(tokens[3], tokens[4], tokens[5], tokens[6]);
            enrolledSections.add(currClass);
        }
    }

    private void initWidgets() {
        classes = (RecyclerView)findViewById(R.id.recycleEnrolledClasses);
        layoutManager = new LinearLayoutManager(this);
        classes.setLayoutManager(layoutManager);
        recyclerAdapter= new RecyclerRemoveClassDataAdapter(enrolledCourses, enrolledSections);
        classes.setAdapter(recyclerAdapter);
    }

    public void unenrollmentBackToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void unenrollClicked(View view){
        boolean unenrolled = false;

        for(int i = 0; i < enrolledCourses.size(); i++){
            CheckBox item = layoutManager.findViewByPosition(i).findViewById(R.id.course_id);
            System.out.println(i + " : " + item.getText());
            if(item != null){
                if(item.isChecked()){
                    unenrolled = true;
                    enrollAccess.unenroll(student.getStudentID(), enrolledSections.get(i).getSection(), enrolledCourses.get(i).getCourseCode());
                }
            }
        }

        if(unenrolled) {
            Toast.makeText(this, "Dropped selected classes.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
            Toast.makeText(this, "No classes selected, choose classes to unenroll from.", Toast.LENGTH_LONG).show();

    }
}