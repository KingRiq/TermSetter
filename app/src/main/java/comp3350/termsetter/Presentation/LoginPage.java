package comp3350.termsetter.Presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

import comp3350.termsetter.Logic.AccessManager;
import comp3350.termsetter.Logic.AccountValidation;
import comp3350.termsetter.Persistence.DBImporter;
import comp3350.termsetter.Persistence.DomainSpecific.StubDatabase;
import comp3350.termsetter.Persistence.Main;
import comp3350.termsetter.Persistence.StudentPersistence;
import comp3350.termsetter.R;

public class LoginPage extends AppCompatActivity {
    private static Context mContext;
    private StudentPersistence database;
    private EditText eID;
    private EditText ePassword;
    private Button eLogin;
    private AccountValidation accountValidation;
    private AccessManager accessManager;
    String currAccount = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        if(!Main.databaseIsImported()){
            try {
                DBImporter.copyDatabaseToDevice(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mContext = getApplicationContext();

        initUIComponents();

        // Comment this database to switch to Real database
        // Uncomment this database to switch to Stub database
        //database = new StubDatabase(mContext,"test.db");

        // if DB is real
        if(!(database instanceof StubDatabase)) {
            accessManager = new AccessManager();
            database = accessManager.getStudentPersistence();
            //Toast.makeText(LoginPage.this, "REAL DB", Toast.LENGTH_SHORT).show();

        }
        else
        {
            accessManager = new AccessManager(mContext);
            database = accessManager.getStudentPersistence();
            //Toast.makeText(LoginPage.this, "Fake DB", Toast.LENGTH_SHORT).show();
        }
    }

    private void initUIComponents(){
        eID = findViewById(R.id.editTextUserID);
        ePassword = findViewById(R.id.editTextPassword);
        eLogin = findViewById(R.id.buttonLogin);
    }

    public void onClickLoginButton(View view) {
        accountValidation = new AccountValidation();

        String inputID = eID.getText().toString();
        String inputPassword = ePassword.getText().toString();

        if (accountValidation.verifyStudent(inputID, inputPassword)) {
            database.setCurrentStudentID(inputID);

            Toast.makeText(LoginPage.this, "Welcome " + inputID + " !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginPage.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(LoginPage.this, "ID or password is incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickCreateAccountButton(View view) {
        Toast.makeText(this, "Loading Account Registration...", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }
}