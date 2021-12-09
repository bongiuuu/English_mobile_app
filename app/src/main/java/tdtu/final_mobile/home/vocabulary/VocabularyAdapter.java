package tdtu.final_mobile.home.vocabulary;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tdtu.final_mobile.R;
import tdtu.final_mobile.presentation.click_control.OnClickVocabulary;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyViewHolder> {
    final List<Vocabulary> vocabularies;
    final Context context;
    final LayoutInflater layoutInflater;
    OnClickVocabulary onClickVocabulary;

    public VocabularyAdapter(List<Vocabulary> vocabularies, Context context){
        this.vocabularies = vocabularies;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public VocabularyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View recyclerViewItem = layoutInflater.inflate(R.layout.custom_topic_layout, parent, false);

        recyclerViewItem.setOnClickListener(v -> handleRecyclerItemClick((RecyclerView)parent, v));
        return new VocabularyViewHolder(recyclerViewItem, onClickVocabulary);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyViewHolder holder, int position) {
        int greenLetter = Color.rgb(0, 135, 73);
        int greenCircle = Color.rgb(35, 254, 163);
        int orangeLetter = Color.rgb(225, 84, 35);
        int orangeCircle = Color.rgb(255, 226, 149);
        int pinkLetter = Color.rgb(146, 116, 237);
        int pinkCircle = Color.rgb(236, 183, 233);
        int blueLetter = Color.rgb(72, 169, 226);
        int blueCircle = Color.rgb(164, 194, 227);
        int yellowLetter = Color.rgb(185, 145, 48);
        int yellowCircle = Color.rgb(255, 253, 84);

        int[] letterColors = {greenLetter, orangeLetter, pinkLetter, blueLetter, yellowLetter};
        int[] circleColors = {greenCircle, orangeCircle, pinkCircle, blueCircle, yellowCircle};
        int loop = 0;

        for (int i = 0; i < vocabularies.size(); i++) {
            if (i < 5) {
                holder.cvCircle.setCardBackgroundColor(circleColors[i]);
                holder.tvVocabularyName.setTextColor(letterColors[i]);
            } else {
                if (i % 5 == 0) {
                    loop++;
                }
                holder.cvCircle.setCardBackgroundColor(circleColors[i - 5 * loop]);
                holder.tvVocabularyName.setTextColor(letterColors[i - 5 * loop]);
            }
        }

        holder.tvNumber.setText(vocabularies.get(position).getNumber() + "");
        holder.tvNumber.setTextColor(vocabularies.get(position).getLetterColor());
//        holder.cvCircle.setCardBackgroundColor(vocabularies.get(position).getCircleColor());
        holder.tvVocabularyName.setText(vocabularies.get(position).getVocabularyName());
//        holder.tvVocabularyName.setTextColor(vocabularies.get(position).getLetterColor());
        holder.tvVietnameseName.setText(vocabularies.get(position).getVietnameseName());
        holder.cvVocabulary.setOnClickListener(v -> {
            onClickVocabulary.OnVocabularyTopicClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return this.vocabularies.size();
    }

    // Click on RecyclerView Item.
    public void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Vocabulary vocabulary = this.vocabularies.get(itemPosition);

        Toast.makeText(this.context, vocabulary.getVocabularyName(), Toast.LENGTH_SHORT).show();
    }

    public void setTopicVocabularyClickAction(OnClickVocabulary onClickVocabulary){
        this.onClickVocabulary = onClickVocabulary;
    }
}
