package comp3350.termsetter.Presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import comp3350.termsetter.Logic.AccessManager;
import comp3350.termsetter.Logic.AccountValidation;
import comp3350.termsetter.Persistence.DomainSpecific.Student;
import comp3350.termsetter.Persistence.StudentPersistence;
import comp3350.termsetter.R;

public class AccountUpdateEmail extends AppCompatActivity {
    private static Context mContext;
    private StudentPersistence database;
    private boolean validate;
    private EditText newEmail;
    private EditText newEmailConfirm;
    private AccountValidation accountValidation;

    private AccessManager accessManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_update_email);

        mContext = getApplicationContext();
        //database = new StubDatabase(mContext,"test.db");
        accessManager = new AccessManager();
        database = accessManager.getStudentPersistence();
        displayProfile();

    }

    private void displayProfile()  {
        Student student = database.getCurrentStudentID();
        TextView studentEmail = findViewById(R.id.userInfoCurrentEmail);
        studentEmail.setText(student.getEmailAddress());
    }

    public void updateEmail(View view) {
        accountValidation = new AccountValidation();

        newEmail = findViewById(R.id.editTextNewEmail);
        newEmailConfirm = findViewById(R.id.editTextConfirmEmail);

        String inputNewEmail = newEmail.getText().toString();
        String inputNewEmailConfirm = newEmailConfirm.getText().toString();

        if (accountValidation.validEmail(inputNewEmail) || accountValidation.validEmail(inputNewEmailConfirm)) {
            if (accountValidation.confirmEmail(inputNewEmail, inputNewEmailConfirm)) {
                if (database.updateEmail(inputNewEmail)) {
                    Toast.makeText(AccountUpdateEmail.this, "Email changes!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AccountUpdateEmail.this, AccountManagementMenu.class);
                    startActivity(intent);
                }
            }
            else {
                Toast.makeText(AccountUpdateEmail.this, "Please confirm your emails match!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(AccountUpdateEmail.this, "Please ensure your emails are valid!", Toast.LENGTH_SHORT).show();
        }
    }

    public void emailBackToAccountMenu(View view) {
        Intent intent = new Intent(this, AccountManagementMenu.class);
        startActivity(intent);
    }
}