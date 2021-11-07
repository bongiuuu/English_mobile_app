package tdtu.final_mobile.home.progress.average_score;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tdtu.final_mobile.R;

public class ScoredTopicAdapter extends RecyclerView.Adapter<ScoreTopicViewHolder> {
    private final List<ScoredTopic> scoredTopics;
    private final Context context;
    private final LayoutInflater layoutInflater;

    public ScoredTopicAdapter(List<ScoredTopic> scoredTopics, Context context) {
        this.scoredTopics = scoredTopics;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ScoreTopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recyclerViewItem = layoutInflater.inflate(R.layout.custom_scored_topic_layout, parent, false);

        recyclerViewItem.setOnClickListener(v -> handleRecyclerItemClick((RecyclerView)parent, v));
        return new ScoreTopicViewHolder(recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreTopicViewHolder holder, int position) {
        ScoredTopic scoredTopic = this.scoredTopics.get(position);

        holder.tvNumber.setText(scoredTopic.getNumber() + "");
        holder.tvNumber.setTextColor(scoredTopic.getLetterColor());
        holder.ivCircle.setBackgroundColor(scoredTopic.getCircleColor());
        holder.tvTopicName.setText(scoredTopic.getTopicName());
        holder.tvTopicName.setTextColor(scoredTopic.getLetterColor());
        holder.tvVietnameseName.setText(scoredTopic.getVietnameseName());
        holder.tvScored.setText(scoredTopic.getScored() + "");
    }

    @Override
    public int getItemCount() {
        return this.scoredTopics.size();
    }

    private void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        ScoredTopic scoredTopic  = this.scoredTopics.get(itemPosition);

//        Toast.makeText(this.context, scoredTopic.getTopicName(), Toast.LENGTH_SHORT).show();
    }
}
