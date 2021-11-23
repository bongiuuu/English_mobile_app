package tdtu.final_mobile.home.vocabulary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.R;
import tdtu.final_mobile.data.QuizCate;
import tdtu.final_mobile.databinding.ActivityQuizBinding;
import tdtu.final_mobile.home.quiz.QuizActivity;
import tdtu.final_mobile.home.quiz.QuizAdapter;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.presentation.click_control.OnClickQuiz;
import tdtu.final_mobile.presentation.click_control.OnClickVocabulary;

public class VocabularyActivity extends BaseActivity implements OnClickQuiz {


    ActivityQuizBinding binding;
    @Override
    protected void doBusiness() {
        Call<ArrayList<QuizCate>> call = apiInterface.getAllVocabularyCates();
        call.enqueue(new Callback<ArrayList<QuizCate>>() {
            @Override
            public void onResponse(Call<ArrayList<QuizCate>> call, Response<ArrayList<QuizCate>> response) {

                Log.d("TAG",response.code()+"");
                ArrayList<QuizCate> quizzes = response.body();
                QuizAdapter quizAdapter = new QuizAdapter(quizzes, VocabularyActivity.this);
                quizAdapter.setOnQuizTopicClickAction(VocabularyActivity.this);
                binding.rvQuiz.setAdapter(quizAdapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VocabularyActivity.this, LinearLayoutManager.VERTICAL, false);
                binding.rvQuiz.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<ArrayList<QuizCate>> call, Throwable t) {
                call.cancel();
            }
        });

        binding.iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    @Override
    protected View layoutId() {
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void OnQuizTopicClick(int id) {
        Intent learnVocabularyIntent = new Intent(VocabularyActivity.this, VocabulariesActivity.class);
        learnVocabularyIntent.putExtra("id",id);
        startActivity(learnVocabularyIntent);
    }
}