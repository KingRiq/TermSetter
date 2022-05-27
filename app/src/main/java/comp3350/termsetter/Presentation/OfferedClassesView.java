package comp3350.termsetter.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;
import comp3350.termsetter.Persistence.CourseOffering;
import comp3350.termsetter.Persistence.Faculty;
import comp3350.termsetter.R;

public class OfferedClassesView extends AppCompatActivity {

    Faculty faculty;
    List<CourseOffering> firstYearCourses;
    List<CourseOffering> secondYearCourses;
    List<CourseOffering> thirdYearCourses;
    List<CourseOffering> fourthYearCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_classes_view);
        initData();
        initWidgets();
    }

    private void initData(){
        faculty = (Faculty) getIntent().getSerializableExtra("faculty");
        TextView selectedCategory = findViewById(R.id.headerDepartment);
        selectedCategory.setText(faculty.getName());
        firstYearCourses = faculty.getCoursesByLevel(1);
        secondYearCourses = faculty.getCoursesByLevel(2);
        thirdYearCourses = faculty.getCoursesByLevel(3);
        fourthYearCourses = faculty.getCoursesByLevel(4);
    }

    private void initWidgets() {
        RecyclerView firstYearLayout = (RecyclerView)findViewById(R.id.recycleViewYear1);
        RecyclerView secondYearLayout = (RecyclerView)findViewById(R.id.recyleViewYear2);
        RecyclerView thirdYearLayout = (RecyclerView)findViewById(R.id.recyleViewYear3);
        RecyclerView fourthYearLayout = (RecyclerView)findViewById(R.id.recycleViewYear4);

        GridLayoutManager firstYearLayoutManager = new GridLayoutManager(this,1);
        firstYearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        firstYearLayout.setLayoutManager(firstYearLayoutManager);

        GridLayoutManager secondYearLayoutManager = new GridLayoutManager(this,1);
        secondYearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        secondYearLayout.setLayoutManager(secondYearLayoutManager);

        GridLayoutManager thirdYearLayoutManager = new GridLayoutManager(this,1);
        thirdYearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        thirdYearLayout.setLayoutManager(thirdYearLayoutManager);

        GridLayoutManager fourthYearLayoutManager = new GridLayoutManager(this,1);
        fourthYearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fourthYearLayout.setLayoutManager(fourthYearLayoutManager);

        comp3350.termsetter.UIAdapters.RecyclerCourseDataAdapter firstYearAdapter= new comp3350.termsetter.UIAdapters.RecyclerCourseDataAdapter(firstYearCourses, getResources().getColor(R.color.cardBackground1));
        firstYearLayout.setAdapter(firstYearAdapter);
        firstYearAdapter.setOnClick(this::firstYearClicked);

        comp3350.termsetter.UIAdapters.RecyclerCourseDataAdapter secondYearAdapter= new comp3350.termsetter.UIAdapters.RecyclerCourseDataAdapter(secondYearCourses, getResources().getColor(R.color.cardBackground2));
        secondYearLayout.setAdapter(secondYearAdapter);
        secondYearAdapter.setOnClick(this::secondYearClicked);
        comp3350.termsetter.UIAdapters.RecyclerCourseDataAdapter thirdYearAdapter= new comp3350.termsetter.UIAdapters.RecyclerCourseDataAdapter(thirdYearCourses, getResources().getColor(R.color.cardBackground3));
        thirdYearLayout.setAdapter(thirdYearAdapter);
        thirdYearAdapter.setOnClick(this::thirdYearClicked);
        comp3350.termsetter.UIAdapters.RecyclerCourseDataAdapter fourthYearAdapter= new comp3350.termsetter.UIAdapters.RecyclerCourseDataAdapter(fourthYearCourses, getResources().getColor(R.color.cardBackground4));
        fourthYearLayout.setAdapter(fourthYearAdapter);
        fourthYearAdapter.setOnClick(this::fourthYearClicked);

    }

    public void firstYearClicked(int position) {
        Intent intent = new Intent(this, OfferedClassesDetail.class);
        intent.putExtra("course", firstYearCourses.get(position));
        intent.putExtra("faculty", faculty);
        startActivity(intent);
    }

    public void secondYearClicked(int position) {
        Intent intent = new Intent(this, OfferedClassesDetail.class);
        intent.putExtra("course", secondYearCourses.get(position));
        intent.putExtra("faculty", faculty);
        startActivity(intent);
    }

    public void thirdYearClicked(int position) {
        Intent intent = new Intent(this, OfferedClassesDetail.class);
        intent.putExtra("course", thirdYearCourses.get(position));
        intent.putExtra("faculty", faculty);
        startActivity(intent);
    }

    public void fourthYearClicked(int position) {
        Intent intent = new Intent(this, OfferedClassesDetail.class);
        intent.putExtra("course", fourthYearCourses.get(position));
        intent.putExtra("faculty", faculty);
        startActivity(intent);
    }

    public void viewBackToCategories(View view){
        Intent intent = new Intent(this, OfferedClassesCategories.class);
        startActivity(intent);
    }

}