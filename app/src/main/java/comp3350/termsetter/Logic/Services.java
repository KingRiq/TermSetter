package comp3350.termsetter.Logic;

import android.content.Context;

import comp3350.termsetter.Persistence.DomainSpecific.CoursePersistence;
import comp3350.termsetter.Persistence.DomainSpecific.EnrollPersistence;
import comp3350.termsetter.Persistence.DomainSpecific.StubDatabase;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.CourseAccess;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.EnrollAccess;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.StudentAccess;
import comp3350.termsetter.Persistence.Main;
import comp3350.termsetter.Persistence.StudentPersistence;

public class Services
{
    private static StudentPersistence studentAccess = null;
    private static CoursePersistence coursePersistence = null;
    private static EnrollPersistence enrollPersistence = null;
    private static Context mContext;

    public static synchronized StudentPersistence getRealStudentAccess()
    {
        if (studentAccess == null)
        {
            studentAccess = new StudentAccess(Main.getDBPathName());
        }

        return studentAccess;
    }
    public static synchronized StudentPersistence getFakeStudentAccess(Context context)
    {
        if (studentAccess == null)
        {
            studentAccess = new StubDatabase(context,"test.db");

        }

        return studentAccess;
    }

    public static synchronized CoursePersistence getCoursePersistence()
    {
        if (coursePersistence == null)
        {
            // stub here?
            coursePersistence = new CourseAccess(Main.getDBPathName()) {
            };
        }

        return coursePersistence;
    }

    public static synchronized EnrollPersistence getEnrollPersistence() {
        if (enrollPersistence == null) {

            // stub here?
            enrollPersistence = new EnrollAccess(Main.getDBPathName());
        }

        return enrollPersistence;
    }

}

