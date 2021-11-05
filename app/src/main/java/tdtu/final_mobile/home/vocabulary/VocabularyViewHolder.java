package tdtu.final_mobile.home.vocabulary;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import tdtu.final_mobile.R;
import tdtu.final_mobile.presentation.vocabulary.OnItemClickAction;

public class VocabularyViewHolder extends RecyclerView.ViewHolder {
    ImageView ivCircle;
    TextView tvNumber;
    TextView tvVocabularyName;
    TextView tvVietnameseName;
    CardView cvVocabulary;

    public VocabularyViewHolder(@NonNull View itemView, OnItemClickAction onClickAction){
        super(itemView);
        this.ivCircle = itemView.findViewById(R.id.ivCircle);
        this.tvNumber = itemView.findViewById(R.id.tvNumber);
        this.tvVocabularyName = itemView.findViewById(R.id.tvVocabularyName);
        this.tvVietnameseName = itemView.findViewById(R.id.tvVietnameseName);
        this.cvVocabulary = itemView.findViewById(R.id.cvVocabulary);
    }
}