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
public class UpdateEmailTest {

    private String studentID = "new";
    private String password = "new123";
    private String newEmail = "imNew@myumanitoba.ca";

    @Rule
    public ActivityScenarioRule<LoginPage> loginRule = new ActivityScenarioRule<>(LoginPage.class);

    @Test
    public void performUpdateEmail() {
        System.out.println("\nStarting UpdateEmailTest: email change successful\n");

        // Log in
        onView(withId(R.id.editTextUserID)).perform(typeText(studentID), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());

        // Navigate to account change password activity
        onView(withId(R.id.homeManageAccountBtn)).perform(click());
        onView(withId(R.id.buttonUpdateEmail)).perform(click());

        // Change email
        onView(withId(R.id.editTextNewEmail)).perform(typeText(newEmail), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmEmail)).perform(typeText(newEmail), closeSoftKeyboard());
        onView(withId(R.id.editTextNewEmail)).check(matches(withText(newEmail)));
        onView(withId(R.id.editTextConfirmEmail)).check(matches(withText(newEmail)));
        onView(withId(R.id.buttonConfirmEmail)).perform(click());

        // Verify updated email
        onView(withId(R.id.buttonManageProfile)).perform(click());
        onView(withId(R.id.userInfoEmail)).check(matches(withText(newEmail)));

        System.out.println("\nEnd UpdateEmailTest: email change successful\n");
    }

}
