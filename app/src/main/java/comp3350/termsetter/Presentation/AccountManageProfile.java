package comp3350.termsetter.Presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import comp3350.termsetter.Logic.AccessManager;
import comp3350.termsetter.Persistence.DomainSpecific.Student;
import comp3350.termsetter.Persistence.StudentPersistence;
import comp3350.termsetter.R;

public class AccountManageProfile extends AppCompatActivity {
    private static Context mContext;
    private StudentPersistence database;
    private AccessManager accessManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage_profile2);

        mContext = getApplicationContext();
       // database = new StubDatabase(mContext,"test.db");
        accessManager = new AccessManager();
        database = accessManager.getStudentPersistence();
        displayProfile();


    }

    private void displayProfile() {
        Student student = database.getCurrentStudentID();

        TextView studentName = findViewById(R.id.userInfoName);
        TextView studentID = findViewById(R.id.userInfoStudentID);
        TextView studentEmail = findViewById(R.id.userInfoEmail);
        TextView studentPhone = findViewById(R.id.userInfoPhone);

        studentName.setText(student.getName());
        studentID.setText(student.getStudentID());
        studentEmail.setText(student.getEmailAddress());
        studentPhone.setText(student.getPhoneNumber());
    }

    public void profileBackToAccountMenu(View view) {
        Intent intent = new Intent(this, AccountManagementMenu.class);
        startActivity(intent);
    }
}

