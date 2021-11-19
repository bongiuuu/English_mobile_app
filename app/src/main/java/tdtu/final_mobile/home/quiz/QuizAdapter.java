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
        View recyclerViewItem = layoutInflater.inflate(R.layout.custom_quiz_layout, parent, false);

        recyclerViewItem.setOnClickListener(v -> handleRecyclerItemClick((RecyclerView)parent, v));
        return new QuizViewHolder(recyclerViewItem, onClickQuiz);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.ivCircle.setBackgroundColor(color);

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
