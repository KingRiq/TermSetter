package comp3350.termsetter.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import comp3350.termsetter.Logic.AccessManager;
import comp3350.termsetter.Logic.EnrollmentLogic;
import comp3350.termsetter.Persistence.CourseOffering;
import comp3350.termsetter.Persistence.CourseSection;
import comp3350.termsetter.Persistence.DomainSpecific.Student;
import comp3350.termsetter.Persistence.Faculty;
import comp3350.termsetter.Persistence.StudentPersistence;
import comp3350.termsetter.R;

public class OfferedClassesDetail extends AppCompatActivity {

    CourseOffering course;
    EnrollmentLogic eL;
    Student student;
    Faculty faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_classes_detail);
        initData();
        initWidgets();
    }

    private void initData(){
        AccessManager accessManager = new AccessManager();
        StudentPersistence database = accessManager.getStudentPersistence();
        student = database.getCurrentStudentID();
    }

    private void initWidgets() {
        faculty = (Faculty) getIntent().getSerializableExtra("faculty");
        course = (CourseOffering) getIntent().getSerializableExtra("course");
        TextView selectedClass = findViewById(R.id.headerCourseCode);
        selectedClass.setText(course.getCourseCode());
        TextView selectedClassName = findViewById(R.id.headerCourseName);
        selectedClassName.setText(course.getName());
        TextView selectedClassCred = findViewById(R.id.textCourseCredits);
        selectedClassCred.setText(course.getCreditHours() + ".00CR");

        List<CourseSection> sections = course.getSections();
        for(int i = 0; i < sections.size(); i++){
            int num = i + 1;
            CourseSection theSection = sections.get(i);

            int sectionID = getResources().getIdentifier("radioButtonS" + num, "id", getPackageName());
            RadioButton section = findViewById(sectionID);
            section.setText(theSection.getSection());

            int daysID = getResources().getIdentifier("textS" + num + "Days", "id", getPackageName());
            TextView days = findViewById(daysID);
            days.setText(theSection.getDays());

            int timesID = getResources().getIdentifier("textS" + num + "Time", "id", getPackageName());
            TextView times = findViewById(timesID);
            times.setText(theSection.getTimeSlot());

            int periodID = getResources().getIdentifier("textS" + num + "Period", "id", getPackageName());
            TextView period = findViewById(periodID);
            period.setText(theSection.getPeriod());

        }
    }

    public void openCategories(View view) {
        String section = "";
        String timeSlot = "";
        String days = "";
        String period = "";
        int s1_id = getResources().getIdentifier("radioButtonS1", "id", getPackageName());
        int s2_id = getResources().getIdentifier("radioButtonS2", "id", getPackageName());
        RadioGroup rg = findViewById(R.id.radioGroupSections);
        if (rg.getCheckedRadioButtonId() == s1_id) {
            TextView sectionTxt = findViewById(R.id.radioButtonS1);
            section = (String) sectionTxt.getText();
            TextView timeSlotTxt = findViewById(R.id.textS1Time);
            timeSlot = (String) timeSlotTxt.getText();
            TextView daysTxt = findViewById(R.id.textS1Days);
            days = (String) daysTxt.getText();
            TextView periodTxt = findViewById(R.id.textS1Period);
            period = (String) periodTxt.getText();
        }
        else if (rg.getCheckedRadioButtonId() == s2_id) {
            TextView sectionTxt = findViewById(R.id.radioButtonS2);
            section = (String) sectionTxt.getText();
            TextView timeSlotTxt = findViewById(R.id.textS2Time);
            timeSlot = (String) timeSlotTxt.getText();
            TextView daysTxt = findViewById(R.id.textS2Days);
            days = (String) daysTxt.getText();
            TextView periodTxt = findViewById(R.id.textS2Period);
            period = (String) periodTxt.getText();
        }
        else {
            TextView sectionTxt = findViewById(R.id.radioButtonS3);
            section = (String) sectionTxt.getText();
            TextView timeSlotTxt = findViewById(R.id.textS3Time);
            timeSlot = (String) timeSlotTxt.getText();
            TextView daysTxt = findViewById(R.id.textS3Days);
            days = (String) daysTxt.getText();
            TextView periodTxt = findViewById(R.id.textS3Period);
            period = (String) periodTxt.getText();
        }

        CourseSection theSection = new CourseSection(section, days, timeSlot, period);
        eL = new EnrollmentLogic(student.getStudentID(), course.getCourseCode(), theSection);
        String message = eL.getMessage();

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, OfferedClassesCategories.class);
        startActivity(intent);
    }

    public void detailBackToView(View view) {
        Intent intent = new Intent(this, OfferedClassesView.class);
        intent.putExtra("faculty", faculty);
        startActivity(intent);
    }
}