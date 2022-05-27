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
public class EnrollTest {

    private String studentID = "new";
    private String password = "new123";
    private String courseCategory = "Mathematics";
    private String courseCode = "MATH1010";
    private String course = "MATH1010 - Applied Finite Mathematics";

    @Rule
    public ActivityScenarioRule<LoginPage> loginRule = new ActivityScenarioRule<>(LoginPage.class);

    @Test
    public void performEnroll() {
        System.out.println("\nStarting EnrollTest: student is able to enroll in a class\n");

        // Log in
        onView(withId(R.id.editTextUserID)).perform(typeText(studentID), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());

        // Select and enroll in a class
        onView(withId(R.id.homeOfferedClassesBtn)).perform(click());
        onView(withText(courseCategory)).perform(click());
        onView(withText(courseCode)).perform(click());
        onView(withId(R.id.buttonEnroll)).perform(click());

        // Navigate to view my classes
        onView(withId(R.id.buttonBackToMenu)).perform(click());
        onView(withId(R.id.homeEnrolledClassesBtn)).perform(click());

        // Verify enrolled class is present
        onView(withId(R.id.course_id)).check(matches(withText(course)));

        System.out.println("\nEnd EnrollTest: student is able to enroll in a class\n");
    }

}
