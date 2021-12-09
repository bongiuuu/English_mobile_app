package tdtu.final_mobile.home.progress.average_score;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import tdtu.final_mobile.R;

public class ScoreTopicViewHolder extends RecyclerView.ViewHolder {
    CardView cvCircle;
    TextView tvNumber;
    TextView tvScored;
    TextView tvTopicName;
    TextView tvVietnameseName;
    CardView cvTopic;

    public ScoreTopicViewHolder(@NonNull View itemView) {
        super(itemView);
        this.cvCircle = itemView.findViewById(R.id.cvCircle);
        this.tvNumber = itemView.findViewById(R.id.tvNumber);
        this.tvTopicName = itemView.findViewById(R.id.tvTopicName);
        this.tvVietnameseName = itemView.findViewById(R.id.tvVietnameseName);
        this.tvScored = itemView.findViewById(R.id.tvScored);
        this.cvTopic = itemView.findViewById(R.id.cvTopic);
    }
}
