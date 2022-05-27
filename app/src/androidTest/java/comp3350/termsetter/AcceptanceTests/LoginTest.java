package comp3350.termsetter.AcceptanceTests;
import comp3350.termsetter.R;
import comp3350.termsetter.Presentation.LoginPage;
import org.junit.Before;
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
public class LoginTest {

    private String testStudentID = "test1";
    private String testPassword = "test11";

    @Rule
    public ActivityScenarioRule<LoginPage> activityRule = new ActivityScenarioRule<>(LoginPage.class);

    @Test
    public void performLogin() {
        System.out.println("\nStarting LoginTest: test user is able to log in\n");

        // Verify login with preset credentials work
        onView(withId(R.id.editTextUserID)).perform(typeText(testStudentID), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText(testPassword), closeSoftKeyboard());
        onView(withId(R.id.editTextUserID)).check(matches(withText(testStudentID)));
        onView(withId(R.id.editTextPassword)).check(matches(withText(testPassword)));
        onView(withId(R.id.buttonLogin)).perform(click());

        System.out.println("\nEnd LoginTest: test user is able to log in\n");
    }
}
