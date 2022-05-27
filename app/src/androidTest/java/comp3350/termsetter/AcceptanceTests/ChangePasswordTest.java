package comp3350.termsetter.AcceptanceTests;
import comp3350.termsetter.R;
import comp3350.termsetter.Presentation.LoginPage;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChangePasswordTest {

    private String studentID = "new";
    private String password = "new123";
    private String newPassword = "newpass123";

    @Rule
    public ActivityScenarioRule<LoginPage> loginRule = new ActivityScenarioRule<>(LoginPage.class);

    @Test
    public void performChangePassword() {
        System.out.println("\nStarting ChangePasswordTest: password change successful\n");

        // Log in
        onView(withId(R.id.editTextUserID)).perform(typeText(studentID), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());

        // Navigate to account change password activity
        onView(withId(R.id.homeManageAccountBtn)).perform(click());
        onView(withId(R.id.buttonChangePassword)).perform(click());

        // Change password
        onView(withId(R.id.editTextCurrentPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.editTextNewPassword)).perform(typeText(newPassword), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText(newPassword), closeSoftKeyboard());
        onView(withId(R.id.editTextCurrentPassword)).check(matches(withText(password)));
        onView(withId(R.id.editTextNewPassword)).check(matches(withText(newPassword)));
        onView(withId(R.id.editTextConfirmPassword)).check(matches(withText(newPassword)));
        onView(withId(R.id.buttonConfirmPassword)).perform(click());

        // Log out
        onView(withId(R.id.buttonReturn)).perform(click());

        onView(withId(R.id.homeLogoutBtn)).perform(click());

        // Log in with new password
        onView(withId(R.id.editTextUserID)).perform(typeText(studentID), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText(newPassword), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());

        System.out.println("\nEnd ChangePasswordTest: password change successful\n");
        System.out.println("\nReverting to old password...\n");

        // Revert password
        onView(withId(R.id.homeManageAccountBtn)).perform(click());
        onView(withId(R.id.buttonChangePassword)).perform(click());
        onView(withId(R.id.editTextCurrentPassword)).perform(typeText(newPassword), closeSoftKeyboard());
        onView(withId(R.id.editTextNewPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.buttonConfirmPassword)).perform(click());

        System.out.println("\nRevert finished\n");
    }

}
