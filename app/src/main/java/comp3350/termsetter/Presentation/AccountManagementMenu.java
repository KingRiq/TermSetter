package comp3350.termsetter.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import comp3350.termsetter.R;

public class AccountManagementMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management_menu);
    }

    public void openManageProfile(View view) {
        Intent intent = new Intent(this, AccountManageProfile.class);
        startActivity(intent);
    }

    public void openUpdateEmail(View view) {
        Intent intent = new Intent(this, comp3350.termsetter.Presentation.AccountUpdateEmail.class);
        startActivity(intent);
    }

    public void openChangePassword(View view) {
        Intent intent = new Intent(this, comp3350.termsetter.Presentation.AccountChangePassword.class);
        startActivity(intent);
    }

    public void accountBackToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}