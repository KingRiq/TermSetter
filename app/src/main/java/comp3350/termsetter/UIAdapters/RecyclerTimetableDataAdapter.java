package comp3350.termsetter.UIAdapters;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import comp3350.termsetter.Persistence.CourseOffering;
import comp3350.termsetter.Persistence.CourseSection;
import comp3350.termsetter.R;

public class RecyclerTimetableDataAdapter extends RecyclerView.Adapter<RecyclerTimetableDataAdapter.ViewHolder> {

    private List<CourseSection> sectionItems;
    private List<CourseOffering> courseItems;
    private int color;

    public RecyclerTimetableDataAdapter(List<CourseOffering> courseItems, List<CourseSection> sectionItems, int color) {
        this.courseItems = courseItems;
        this.sectionItems = sectionItems;
        this.color = color;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView course_id_txt;
        public TextView course_section_txt;

        public ViewHolder(View v){
            super(v);
            cardView = (CardView) v.findViewById(R.id.recycler_card);
            course_id_txt = (TextView)v.findViewById(R.id.course_id);
            course_section_txt = (TextView)v.findViewById(R.id.course_section);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.widget_timetable_recycler_card, parent, false);
        ViewHolder ret = new ViewHolder(itemView);
        return ret;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(courseItems != null && sectionItems != null) {
            CourseOffering courseItem = courseItems.get(position);
            CourseSection sectionItem = sectionItems.get(position);

            if(courseItem != null && sectionItem != null) {
                holder.course_id_txt.setText(courseItem.getCourseCode() + " - " + courseItem.getName());
                holder.course_section_txt.setText("Section " + sectionItem.getSection() + " [ " + sectionItem.getTimeSlot() + " ]");
                holder.cardView.setCardBackgroundColor(color);
            }
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if(courseItems !=null) {
            ret = courseItems.size();
        }
        return ret;
    }

}
