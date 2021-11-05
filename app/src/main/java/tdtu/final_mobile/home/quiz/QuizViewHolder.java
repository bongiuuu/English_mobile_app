package tdtu.final_mobile.home.quiz;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import tdtu.final_mobile.R;
import tdtu.final_mobile.presentation.quiz.OnItemClickAction;

public class QuizViewHolder extends RecyclerView.ViewHolder {
    ImageView ivCircle;
    TextView tvNumber;
    TextView tvQuizTopic;
    TextView tvVietnameseName;
    CardView cvQuiz;

    public QuizViewHolder(@NonNull View itemView, OnItemClickAction onClickAction) {
        super(itemView);
        this.ivCircle = itemView.findViewById(R.id.ivCircle);
        this.tvNumber = itemView.findViewById(R.id.tvNumber);
        this.tvQuizTopic = itemView.findViewById(R.id.tvQuizTopic);
        this.tvVietnameseName = itemView.findViewById(R.id.tvVietnameseName);
        this.cvQuiz = itemView.findViewById(R.id.cvQuiz);
    }
}
