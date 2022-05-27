package comp3350.termsetter.AcceptanceTests;
import android.widget.Spinner;

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
public class ViewClassesTest {

    private String studentID = "new";
    private String password = "new123";
    private String courseCategory = "Mathematics";
    private String courseCode = "MATH1010";

    @Rule
    public ActivityScenarioRule<LoginPage> loginRule = new ActivityScenarioRule<>(LoginPage.class);

    @Test
    public void performScheduleView() {
        System.out.println("\nStarting ViewScheduleTest: student able to view classes per day\n");

        // Log in
        onView(withId(R.id.editTextUserID)).perform(typeText(studentID), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());

        // Explore the views inside the offered classes activities
        onView(withId(R.id.homeOfferedClassesBtn)).perform(click());
        onView(withText(courseCategory)).perform(click());
        onView(withText(courseCode)).perform(click());

        System.out.println("\nEnd ViewScheduleTest: student able to view classes per day\n");
    }

}
