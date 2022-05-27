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
public class CreateAccountTest {

    private String newStudentName = "New Student";
    private String newStudentID = "new";
    private String newPassword = "new123";
    private String newEmail = "mailme@myumanitoba.ca";
    private String newPhone = "2035563667";

    @Rule
    public ActivityScenarioRule<LoginPage> loginRule = new ActivityScenarioRule<>(LoginPage.class);

    @Test
    public void performCreateAccount() {
        System.out.println("\nStarting CreateAccountTest: student account creation successful\n");

        // Create new student account
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        onView(withId(R.id.editTextSetName)).perform(typeText(newStudentName), closeSoftKeyboard());
        onView(withId(R.id.editTextSetID)).perform(typeText(newStudentID), closeSoftKeyboard());
        onView(withId(R.id.editTextSetPassword)).perform(typeText(newPassword), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmAPassword)).perform(typeText(newPassword), closeSoftKeyboard());
        onView(withId(R.id.editTextEmail)).perform(typeText(newEmail), closeSoftKeyboard());
        onView(withId(R.id.editTextPhone)).perform(typeText(newPhone), closeSoftKeyboard());
        onView(withId(R.id.buttonConfirmAccount)).perform(click());

        // Enter new student credentials
        onView(withId(R.id.editTextUserID)).perform(typeText(newStudentID), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText(newPassword), closeSoftKeyboard());
        onView(withId(R.id.editTextUserID)).check(matches(withText(newStudentID)));
        onView(withId(R.id.editTextPassword)).check(matches(withText(newPassword)));
        onView(withId(R.id.buttonLogin)).perform(click());

        // Navigate to account profile
        onView(withId(R.id.homeManageAccountBtn)).perform(click());
        onView(withId(R.id.buttonManageProfile)).perform(click());

        // Verify student information is correct
        onView(withId(R.id.userInfoName)).check(matches(withText(newStudentName)));
        onView(withId(R.id.userInfoStudentID)).check(matches(withText(newStudentID)));
        onView(withId(R.id.userInfoEmail)).check(matches(withText(newEmail)));
        onView(withId(R.id.userInfoPhone)).check(matches(withText(newPhone)));

        System.out.println("\nEnd CreateAccountTest: student account creation successful\n");
    }

}
