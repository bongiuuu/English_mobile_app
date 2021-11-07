package tdtu.final_mobile.home.contribute;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import tdtu.final_mobile.R;
import tdtu.final_mobile.presentation.click_control.OnClickContribute;

public class TopicViewHolder extends RecyclerView.ViewHolder {
    ImageView ivCircle;
    TextView tvNumber;
    TextView tvTopicName;
    TextView tvVietnameseName;
    CardView cvTopic;

    public TopicViewHolder(@NonNull View itemView, OnClickContribute onClickContribute){
        super(itemView);
        this.ivCircle = itemView.findViewById(R.id.ivCircle);
        this.tvNumber = itemView.findViewById(R.id.tvNumber);
        this.tvTopicName = itemView.findViewById(R.id.tvTopicName);
        this.tvVietnameseName = itemView.findViewById(R.id.tvVietnameseName);
        this.cvTopic = itemView.findViewById(R.id.cvTopic);
    }
}
