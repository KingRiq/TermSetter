package comp3350.termsetter.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import comp3350.termsetter.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openAccountManagementMenu(View view) {
        Intent intent = new Intent(this, AccountManagementMenu.class);
        startActivity(intent);
    }

    public void openTimetableView(View view) {
        Intent intent = new Intent(this, Timetable.class);
        startActivity(intent);
    }

    public void openTranscript(View view) {
        Intent intent = new Intent(this, Transcript.class);
        startActivity(intent);
    }

    public void openOfferedClassesCategories(View view) {
        Intent intent = new Intent(this, OfferedClassesCategories.class);
        startActivity(intent);
    }

    public void openUnenrollmentView(View view) {
        Intent intent = new Intent(this, Unenrollment.class);
        startActivity(intent);
    }

    public void onClickLogOutButton(View view) {
        Toast.makeText(this, "See you again soon!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}