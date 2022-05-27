package comp3350.termsetter.UIAdapters;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import comp3350.termsetter.Persistence.CourseOffering;
import comp3350.termsetter.R;

public class RecyclerCourseDataAdapter extends RecyclerView.Adapter<RecyclerCourseDataAdapter.ViewHolder> {

    private List<CourseOffering> viewItems;
    private int color;

    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public RecyclerCourseDataAdapter(List<CourseOffering> viewItems, int color) {
        this.viewItems = viewItems;
        this.color = color;
    }

    public interface btnClickListener {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView textView;

        public ViewHolder(View v){
            super(v);
            cardView = (CardView) v.findViewById(R.id.recycler_card);
            textView = (TextView)v.findViewById(R.id.recycler_card_txt);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get LayoutInflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the RecyclerView item layout xml.
        View itemView = layoutInflater.inflate(R.layout.widget_course_recycler_card, parent, false);

        // Create and return our customRecycler View Holder object.
        ViewHolder ret = new ViewHolder(itemView);
        return ret;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(viewItems!=null) {
            CourseOffering viewItem = viewItems.get(position);

            if(viewItem != null) {
                holder.textView.setText(viewItem.getCourseCode());
                holder.cardView.setCardBackgroundColor(color);
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClick.onItemClick(position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if(viewItems!=null) {
            ret = viewItems.size();
        }
        return ret;
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick=onClick;
    }

}
