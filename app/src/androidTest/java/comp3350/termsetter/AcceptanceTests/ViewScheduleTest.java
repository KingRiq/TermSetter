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
public class ViewScheduleTest {

    private String studentID = "new";
    private String password = "new123";
    private String courseCode = "MATH1010"; // Enrolled A01 scheduled on Tuesday and Thursday (TR)

    @Rule
    public ActivityScenarioRule<LoginPage> loginRule = new ActivityScenarioRule<>(LoginPage.class);

    @Test
    public void performScheduleView() {
        System.out.println("\nStarting ViewScheduleTest: student able to view classes per day\n");

        // Log in and navigate to Timetable
        onView(withId(R.id.editTextUserID)).perform(typeText(studentID), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withId(R.id.homeScheduleBtn)).perform(click());

        // Iterate spinner options
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for(int i = 0; i < days.length; i++){
            // Select day
            onView(withId(R.id.daySpinner)).perform(click());
            onView(withText(days[i])).perform(click());
        }

        System.out.println("\nEnd ViewScheduleTest: student able to view classes per day\n");
    }

}
