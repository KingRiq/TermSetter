package comp3350.termsetter;

import comp3350.termsetter.AcceptanceTests.ChangePasswordTest;
import comp3350.termsetter.AcceptanceTests.CreateAccountTest;
import comp3350.termsetter.AcceptanceTests.EnrollTest;
import comp3350.termsetter.AcceptanceTests.LoginTest;
import comp3350.termsetter.AcceptanceTests.UnenrollTest;
import comp3350.termsetter.AcceptanceTests.UpdateEmailTest;
import comp3350.termsetter.AcceptanceTests.ViewClassesTest;
import comp3350.termsetter.AcceptanceTests.ViewScheduleTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        CreateAccountTest.class,
        UpdateEmailTest.class,
        ChangePasswordTest.class,
        ViewClassesTest.class,
        EnrollTest.class,
        ViewScheduleTest.class,
        UnenrollTest.class
})


public class AcceptanceTestSuite {

}