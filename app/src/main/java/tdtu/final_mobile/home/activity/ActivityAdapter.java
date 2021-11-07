package tdtu.final_mobile.home.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tdtu.final_mobile.R;
import tdtu.final_mobile.home.HomeActivity;
import tdtu.final_mobile.presentation.click_control.OnClickActivity;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityViewHolder> {
    private final List<Activity> activities;
    private final Context context;
    private final LayoutInflater layoutInflater;
    public OnClickActivity onClickActivity;

    public ActivityAdapter(List<Activity> activities, Context context){
        this.activities = activities;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // Inflate view from recyclerview_item_layout.xml
        View recyclerViewItem = layoutInflater.inflate(R.layout.custom_activity_layout, parent, false);

        recyclerViewItem.setOnClickListener(v -> handleRecyclerItemClick((RecyclerView)parent, v));
        return new ActivityViewHolder(recyclerViewItem);
    }


    @Override
    public void onBindViewHolder(ActivityViewHolder holder, int position) {
        Activity activity = this.activities.get(position);

        int imageResId = this.getDrawableResIdByName(activity.getActivityIcon());
        holder.ivActivityIcon.setImageResource(imageResId);
        holder.tvActivityName.setText(activity.getActivityName());
        holder.tvActivityName.setTextColor(activity.getTextColor());
    }

    @Override
    public int getItemCount() {
        return this.activities.size();
    }

    public int getDrawableResIdByName(String resName){
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i(HomeActivity.LOG_TAG, "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

    // Click on RecyclerView Item.
    private void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Activity activity  = this.activities.get(itemPosition);

        Toast.makeText(this.context, activity.getActivityName(), Toast.LENGTH_SHORT).show();
        onClickActivity.onActivityClick(itemPosition);
    }

    public void setActivityAction(OnClickActivity onClickActivity){
        this.onClickActivity = onClickActivity;
    }
}
