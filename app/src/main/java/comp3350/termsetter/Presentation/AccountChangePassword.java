package comp3350.termsetter.Presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import comp3350.termsetter.Logic.AccessManager;
import comp3350.termsetter.Logic.AccountValidation;
import comp3350.termsetter.Persistence.DomainSpecific.StubDatabase;
import comp3350.termsetter.Persistence.DomainSpecific.Student;
import comp3350.termsetter.Persistence.StudentPersistence;
import comp3350.termsetter.R;

public class AccountChangePassword extends AppCompatActivity {
    private static Context mContext;
    private StudentPersistence database;
    private boolean validate;
    private EditText oldPassword;
    private EditText newPassword;
    private EditText newPasswordConfirm;
    private AccountValidation accountValidation;
    private Student student;
    private Button change;

    private AccessManager accessManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change_password);

        mContext = getApplicationContext();
        database = new StubDatabase(mContext,"test.db");

        accessManager = new AccessManager();
        database = accessManager.getStudentPersistence();
    }


    public void onClickConfirmButton(View view) {
        oldPassword = findViewById(R.id.editTextCurrentPassword);
        newPassword = findViewById(R.id.editTextNewPassword);
        newPasswordConfirm = findViewById(R.id.editTextConfirmPassword);
        accountValidation = new AccountValidation();

        String inputOldPassword = oldPassword.getText().toString();
        String inputNewPassword = newPassword.getText().toString();
        String inputNewPasswordConfirm = newPasswordConfirm.getText().toString();

        if (!accountValidation.validPassword(inputNewPassword) || !accountValidation.validPassword(inputNewPasswordConfirm)) {
            Toast.makeText(AccountChangePassword.this, "Please enter valid password!", Toast.LENGTH_SHORT).show();
        }
        else {
            if (accountValidation.verifyCurrentPassword(inputOldPassword, database.getCurrentStudentID())) {
                if (accountValidation.confirmPassword(inputNewPassword, inputNewPasswordConfirm)) {
                    if (database.updatePassword(inputNewPassword)) {
                        Toast.makeText(AccountChangePassword.this, "Password has been changed!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AccountChangePassword.this, AccountManagementMenu.class);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(AccountChangePassword.this, "Please verify that the passwords match!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(AccountChangePassword.this, "Your current password is invalid!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void passwordBackToAccountMenu(View view) {
        Intent intent = new Intent(this, AccountManagementMenu.class);
        startActivity(intent);
    }
}