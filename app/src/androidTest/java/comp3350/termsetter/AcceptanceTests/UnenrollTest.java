package comp3350.termsetter.AcceptanceTests;
import comp3350.termsetter.Presentation.AccountManageProfile;
import comp3350.termsetter.Presentation.CreateAccount;
import comp3350.termsetter.Presentation.MainActivity;
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
public class UnenrollTest {

    private String studentID = "new";
    private String password = "new123";
    private String course = "MATH1010 - Applied Finite Mathematics";

    @Rule
    public ActivityScenarioRule<LoginPage> loginRule = new ActivityScenarioRule<>(LoginPage.class);

    @Test
    public void performUnenroll() {
        System.out.println("\nStarting UnenrollTest: student is able to unenroll in a class\n");

        // Log in and navigate to Unenrollment
        onView(withId(R.id.editTextUserID)).perform(typeText(studentID), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withId(R.id.homeUnenrollBtn)).perform(click());

        // Select class to unenroll from
        onView(withText(course)).perform(click());
        onView(withId(R.id.unenrollButton)).perform(click());

        // Verify unenrollment
        onView(withId(R.id.homeEnrolledClassesBtn)).perform(click());
        onView(withId(R.id.course_id)).check(doesNotExist());

        System.out.println("\nEnd UnenrollTest: student is able to unenroll in a class\n");
    }

}
