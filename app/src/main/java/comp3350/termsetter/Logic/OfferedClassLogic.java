package comp3350.termsetter.Logic;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import comp3350.termsetter.Persistence.CourseCategoryDriver;
import comp3350.termsetter.Persistence.CourseCategoryPersistence;
import comp3350.termsetter.Persistence.CourseCategorySQLDriver;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.CourseAccess;
import comp3350.termsetter.Persistence.Faculty;
import comp3350.termsetter.Persistence.Main;
import comp3350.termsetter.R;

public class OfferedClassLogic{

    Context context;
    CourseCategoryPersistence instance;

    public OfferedClassLogic(boolean useSQL, Context context){
        this.context = context;
        if(useSQL){
            loadFromSQL();
        }
        else{
            loadFromDriver();
        }
    }

    public List<Faculty> getCourseData(){
        List<Faculty> courseData = instance.getFaculties();
        return courseData;
    }

    private void loadFromSQL(){
        try{
            String path = Main.getDBPathName();
            CourseAccess courseAccess = new CourseAccess(path);
            CourseCategorySQLDriver courseDatabase = new CourseCategorySQLDriver(courseAccess);
            instance = courseDatabase;

        }
        catch(SQLException e){
            System.out.println("CourseCategorySQLDriver failed loading the course data.");
            e.printStackTrace();
        }
    }

    private void loadFromDriver(){
        try{
            InputStream is = context.getResources().openRawResource(R.raw.classdatabase);
            CourseCategoryDriver courseDatabase = new CourseCategoryDriver(is);
            instance = courseDatabase;
        }
        catch(IOException e){
            System.out.println("Database source file 'classdatabase.txt' is missing from res/raw.");
            e.printStackTrace();
        }
    }

}