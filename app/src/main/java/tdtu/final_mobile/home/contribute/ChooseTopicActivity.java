package tdtu.final_mobile.home.contribute;

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
import tdtu.final_mobile.databinding.ActivityChooseTopicBinding;
import tdtu.final_mobile.home.quiz.QuizActivity;
import tdtu.final_mobile.home.quiz.QuizAdapter;
import tdtu.final_mobile.home.quiz.QuizzesActivity;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.presentation.click_control.OnClickContribute;
import tdtu.final_mobile.presentation.click_control.OnClickQuiz;

public class ChooseTopicActivity extends BaseActivity implements OnClickQuiz {


    ActivityChooseTopicBinding binding;
    @Override
    protected void doBusiness() {
        Call<ArrayList<QuizCate>> call = apiInterface.getQuizCates(1);
        call.enqueue(new Callback<ArrayList<QuizCate>>() {
            @Override
            public void onResponse(Call<ArrayList<QuizCate>> call, Response<ArrayList<QuizCate>> response) {

                Log.d("TAG",response.code()+"");
                ArrayList<QuizCate> quizzes = response.body();
                QuizAdapter quizAdapter = new QuizAdapter(quizzes, ChooseTopicActivity.this);
                quizAdapter.setOnQuizTopicClickAction(ChooseTopicActivity.this);
                binding.rvTopic.setAdapter(quizAdapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChooseTopicActivity.this, LinearLayoutManager.VERTICAL, false);
                binding.rvTopic.setLayoutManager(linearLayoutManager);
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
        binding = ActivityChooseTopicBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void OnQuizTopicClick(int position) {
        Intent doQuizIntent = new Intent(ChooseTopicActivity.this, ContributeActivity.class);
        doQuizIntent.putExtra("id",position);
        startActivity(doQuizIntent);
    }
}