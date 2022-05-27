package comp3350.termsetter.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import comp3350.termsetter.Logic.OfferedClassLogic;
import comp3350.termsetter.Persistence.Faculty;
import comp3350.termsetter.R;
import comp3350.termsetter.UIAdapters.RecyclerFacultyDataAdapter;

public class OfferedClassesCategories extends AppCompatActivity {

    List<Faculty> courseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_classes_categories);
        initData();
        initWidgets();
    }

    private void initData(){
        try{
            OfferedClassLogic dataRequester = new OfferedClassLogic(true, this);
            courseData = dataRequester.getCourseData();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void initWidgets(){
        RecyclerView faculties = (RecyclerView)findViewById(R.id.recycleViewFaculties);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        faculties.setLayoutManager(layoutManager);
        RecyclerFacultyDataAdapter recyclerAdapter= new RecyclerFacultyDataAdapter(courseData);
        faculties.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnClick(this::onItemClick);
    }

    public void onItemClick(int position) {
        Intent intent = new Intent(this, OfferedClassesView.class);
        intent.putExtra("faculty", courseData.get(position));
        startActivity(intent);
    }

    public void categoriesBackToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
