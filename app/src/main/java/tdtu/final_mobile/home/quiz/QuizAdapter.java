package tdtu.final_mobile.home.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tdtu.final_mobile.R;
import tdtu.final_mobile.presentation.click_control.OnClickQuiz;

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    private final List<Quiz> quizzes;
    private final Context context;
    private final LayoutInflater layoutInflater;
    OnClickQuiz onClickQuiz;

    public QuizAdapter(List<Quiz> quizzes, Context context) {
        this.quizzes = quizzes;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recyclerViewItem = layoutInflater.inflate(R.layout.custom_quiz_layout, parent, false);

        recyclerViewItem.setOnClickListener(v -> handleRecyclerItemClick((RecyclerView)parent, v));
        return new QuizViewHolder(recyclerViewItem, onClickQuiz);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.tvNumber.setText(quizzes.get(position).getNumber() + "");
        holder.tvNumber.setTextColor(quizzes.get(position).getLetterColor());
        holder.ivCircle.setBackgroundColor(quizzes.get(position).getCircleColor());
        holder.tvQuizTopic.setText(quizzes.get(position).getQuizTopic());
        holder.tvQuizTopic.setTextColor(quizzes.get(position).getLetterColor());
        holder.tvVietnameseName.setText(quizzes.get(position).getVietnameseName());
        holder.cvQuiz.setOnClickListener(v -> {
            onClickQuiz.OnQuizTopicClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return this.quizzes.size();
    }

    private void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Quiz quiz  = this.quizzes.get(itemPosition);

        Toast.makeText(this.context, quiz.getQuizTopic(), Toast.LENGTH_SHORT).show();
    }

    void setOnQuizTopicClickAction(OnClickQuiz onClickQuiz){
        this.onClickQuiz = onClickQuiz;
    }
}
