package tdtu.final_mobile;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tdtu.final_mobile.presentation.vocabulary.OnItemClickAction;

public class ActionAdapter extends RecyclerView.Adapter<ActionViewHolder> {
    private final List<Action> actions;
    private final Context context;
    private final LayoutInflater layoutInflater;
    OnItemClickAction onClickAction;
    public ActionAdapter(List<Action> actions, Context context){
        this.actions = actions;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ActionViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // Inflate view from recyclerview_item_layout.xml
        View recyclerViewItem = layoutInflater.inflate(R.layout.custom_action_layout, parent, false);

        recyclerViewItem.setOnClickListener(v -> handleRecyclerItemClick((RecyclerView)parent, v));
        return new ActionViewHolder(recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionViewHolder holder, int position) {
        Action action = this.actions.get(position);

        int imageResId = this.getDrawableResIdByName(action.getActionPic());
        holder.ivActionPic.setImageResource(imageResId);
        holder.tvActionName.setText(actions.get(position).getActionName());
        holder.cvAction.setCardBackgroundColor(actions.get(position).getLayoutColor());
    }

    @Override
    public int getItemCount() {
        return this.actions.size();
    }

    public int getDrawableResIdByName(String resName){
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i(HomeActivity.LOG_TAG_1, "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

    // Click on RecyclerView Item.
    private void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Action action  = this.actions.get(itemPosition);
        Toast.makeText(this.context, action.getActionName(), Toast.LENGTH_SHORT).show();
        onClickAction.onClick(itemPosition);
    }

    void setOnClickAction(OnItemClickAction onClickAction){
        this.onClickAction = onClickAction;
    }
}
