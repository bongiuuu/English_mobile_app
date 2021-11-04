package tdtu.final_mobile;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityViewHolder extends RecyclerView.ViewHolder {
    ImageView ivActivityIcon;
    TextView tvActivityName;

    public ActivityViewHolder(@NonNull View itemView){
        super(itemView);
        this.ivActivityIcon = itemView.findViewById(R.id.ivActivityIcon);
        this.tvActivityName = itemView.findViewById(R.id.tvActivityName);
    }
}
