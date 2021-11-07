package tdtu.final_mobile.home.vocabulary;

import android.content.Context;
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
        View recyclerViewItem = layoutInflater.inflate(R.layout.custom_vocabulary_layout, parent, false);

        recyclerViewItem.setOnClickListener(v -> handleRecyclerItemClick((RecyclerView)parent, v));
        return new VocabularyViewHolder(recyclerViewItem, onClickVocabulary);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyViewHolder holder, int position) {
        holder.tvNumber.setText(vocabularies.get(position).getNumber() + "");
        holder.tvNumber.setTextColor(vocabularies.get(position).getLetterColor());
        holder.ivCircle.setBackgroundColor(vocabularies.get(position).getCircleColor());
        holder.tvVocabularyName.setText(vocabularies.get(position).getVocabularyName());
        holder.tvVocabularyName.setTextColor(vocabularies.get(position).getLetterColor());
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

    void setTopicVocabularyClickAction(OnClickVocabulary onClickVocabulary){
        this.onClickVocabulary = onClickVocabulary;
    }
}
