package tdtu.final_mobile.home.contribute;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tdtu.final_mobile.R;
import tdtu.final_mobile.presentation.click_control.OnClickContribute;

public class TopicAdapter extends RecyclerView.Adapter<TopicViewHolder> {
    final List<Topic> topics;
    final Context context;
    final LayoutInflater layoutInflater;
    OnClickContribute onClickContribute;

    public TopicAdapter(List<Topic> topics, Context context){
        this.topics = topics;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recyclerViewItem = layoutInflater.inflate(R.layout.custom_topic_layout, parent, false);

        recyclerViewItem.setOnClickListener(v -> handleRecyclerItemClick((RecyclerView)parent, v));
        return new TopicViewHolder(recyclerViewItem, onClickContribute);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        holder.tvNumber.setText(topics.get(position).getNumber() + "");
        holder.tvNumber.setTextColor(topics.get(position).getLetterColor());
        holder.cvCircle.setBackgroundColor(topics.get(position).getCircleColor());
        holder.tvTopicName.setText(topics.get(position).getTopicName());
        holder.tvTopicName.setTextColor(topics.get(position).getLetterColor());
        holder.tvVietnameseName.setText(topics.get(position).getVietnameseName());
        holder.cvTopic.setOnClickListener(v -> {
            onClickContribute.OnContributeTopicClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Topic topic = this.topics.get(itemPosition);

        Toast.makeText(this.context, topic.getTopicName(), Toast.LENGTH_SHORT).show();
    }

    void setTopicContributeClickAction(OnClickContribute onClickContribute){
        this.onClickContribute = onClickContribute;
    }
}
