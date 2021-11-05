package tdtu.final_mobile.home.action;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import tdtu.final_mobile.R;

public class ActionViewHolder extends RecyclerView.ViewHolder {
    ImageView ivActionPic;
    TextView tvActionName;
    CardView cvAction;

    public ActionViewHolder(@NonNull View itemView){
        super(itemView);
        this.ivActionPic = itemView.findViewById(R.id.ivActionPic);
        this.tvActionName = itemView.findViewById(R.id.tvActionName);
        this.cvAction = itemView.findViewById(R.id.cvAction);
    }
}