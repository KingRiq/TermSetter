package comp3350.termsetter.UIAdapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import comp3350.termsetter.Persistence.Faculty;
import comp3350.termsetter.R;

public class RecyclerFacultyDataAdapter extends RecyclerView.Adapter<RecyclerFacultyDataAdapter.ViewHolder> {

    private List<Faculty> viewItems;

    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public RecyclerFacultyDataAdapter(List<Faculty> viewItems) {
        this.viewItems = viewItems;
    }

    public interface btnClickListener {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public ViewHolder(View v){
            super(v);
            textView = (TextView)v.findViewById(R.id.recycler_card_txt);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get LayoutInflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the RecyclerView item layout xml.
        View itemView = layoutInflater.inflate(R.layout.widget_faculty_recycler_card, parent, false);

        // Create and return our customRecycler View Holder object.
        ViewHolder ret = new ViewHolder(itemView);
        return ret;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(viewItems!=null) {
            Faculty viewItem = viewItems.get(position);

            if(viewItem != null) {
                holder.textView.setText(viewItem.getName());

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
