package tdtu.final_mobile.home.quiz;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

import tdtu.final_mobile.R;
import tdtu.final_mobile.data.Quiz;
import tdtu.final_mobile.data.QuizCate;
import tdtu.final_mobile.home.progress.average_score.ScoredTopic;
import tdtu.final_mobile.presentation.click_control.OnClickQuiz;

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    private final List<QuizCate> quizzes;
    private final Context context;
    private final LayoutInflater layoutInflater;
    OnClickQuiz onClickQuiz;

    public QuizAdapter(List<QuizCate> quizzes, Context context) {
        this.quizzes = quizzes;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recyclerViewItem = layoutInflater.inflate(R.layout.custom_topic_layout, parent, false);

        recyclerViewItem.setOnClickListener(v -> handleRecyclerItemClick((RecyclerView)parent, v));
        return new QuizViewHolder(recyclerViewItem, onClickQuiz);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
//        int greenLetter = Color.rgb(0, 135, 73);
//        int greenCircle = Color.rgb(35, 254, 163);
//        int orangeLetter = Color.rgb(225, 84, 35);
//        int orangeCircle = Color.rgb(255, 226, 149);
//        int pinkLetter = Color.rgb(146, 116, 237);
//        int pinkCircle = Color.rgb(236, 183, 233);
//        int blueLetter = Color.rgb(72, 169, 226);
//        int blueCircle = Color.rgb(164, 194, 227);
//        int yellowLetter = Color.rgb(185, 145, 48);
//        int yellowCircle = Color.rgb(255, 253, 84);
//
//        int[] letterColors = {greenLetter, orangeLetter, pinkLetter, blueLetter, yellowLetter};
//        int[] circleColors = {greenCircle, orangeCircle, pinkCircle, blueCircle, yellowCircle};
//        int loop = 0;
//
//        for (int i = 0; i < quizzes.size(); i++) {
//            if (i < 5) {
//                holder.cvCircle.setCardBackgroundColor(circleColors[i]);
//                holder.tvQuizTopic.setTextColor(letterColors[i]);
//            } else {
//                if (i % 5 == 0) {
//                    loop++;
//                }
//                holder.cvCircle.setCardBackgroundColor(circleColors[i - 5 * loop]);
//                holder.tvQuizTopic.setTextColor(letterColors[i - 5 * loop]);
//            }
//        }

        holder.tvNumber.setText(position + 1 +"");
        holder.tvQuizTopic.setText(quizzes.get(position).getTitle());
        holder.tvVietnameseName.setText(quizzes.get(position).getSubTitle());
        holder.cvQuiz.setOnClickListener(v -> {
            onClickQuiz.OnQuizTopicClick(quizzes.get(position).getId());
        });
    }

    @Override
    public int getItemCount() {
        return this.quizzes.size();
    }

    private void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        QuizCate quiz  = this.quizzes.get(itemPosition);

//        Toast.makeText(this.context, quiz.getQuizTopic(), Toast.LENGTH_SHORT).show();
    }

    public void setOnQuizTopicClickAction(OnClickQuiz onClickQuiz){
        this.onClickQuiz = onClickQuiz;
    }
}
