package tdtu.final_mobile.home.quiz;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import tdtu.final_mobile.R;
import tdtu.final_mobile.presentation.click_control.OnClickQuiz;

public class QuizViewHolder extends RecyclerView.ViewHolder {
    CardView cvCircle;
    TextView tvNumber;
    TextView tvQuizTopic;
    TextView tvVietnameseName;
    CardView cvQuiz;

    public QuizViewHolder(@NonNull View itemView, OnClickQuiz onClickQuiz) {
        super(itemView);
        this.cvCircle = itemView.findViewById(R.id.cvCircle);
        this.tvNumber = itemView.findViewById(R.id.tvNumber);
        this.tvQuizTopic = itemView.findViewById(R.id.tvTopicName);
        this.tvVietnameseName = itemView.findViewById(R.id.tvVietnameseName);
        this.cvQuiz = itemView.findViewById(R.id.cvTopic);
    }
}
