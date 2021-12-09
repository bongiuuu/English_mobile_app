package tdtu.final_mobile.home.quiz;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
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
import tdtu.final_mobile.data.Quiz;
import tdtu.final_mobile.data.QuizCate;
import tdtu.final_mobile.databinding.ActivityQuizBinding;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.presentation.click_control.OnClickQuiz;
import tdtu.final_mobile.utils.Constants;

public class QuizActivity extends BaseActivity implements OnClickQuiz {

    ActivityQuizBinding binding;
    private int userId = 1;
    @Override
    protected void doBusiness() {
        SharedPreferences prefs = getSharedPreferences(Constants.KEY_USER_NAME, MODE_PRIVATE);
        userId = prefs.getInt(Constants.KEY_USER_ID, 1);
        Call<ArrayList<QuizCate>> call = apiInterface.getQuizCates(userId);
        call.enqueue(new Callback<ArrayList<QuizCate>>() {
            @Override
            public void onResponse(Call<ArrayList<QuizCate>> call, Response<ArrayList<QuizCate>> response) {

                Log.d("TAG",response.code()+"");
                ArrayList<QuizCate> quizzes = response.body();
                QuizAdapter quizAdapter = new QuizAdapter(quizzes,QuizActivity.this);
                quizAdapter.setOnQuizTopicClickAction(QuizActivity.this);
                binding.rvQuiz.setAdapter(quizAdapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QuizActivity.this, LinearLayoutManager.VERTICAL, false);
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
    public void OnQuizTopicClick(int quizCateId) {
        Intent doQuizIntent = new Intent(QuizActivity.this, QuizzesActivity.class);
        doQuizIntent.putExtra("id", quizCateId);
        startActivity(doQuizIntent);
    }
}