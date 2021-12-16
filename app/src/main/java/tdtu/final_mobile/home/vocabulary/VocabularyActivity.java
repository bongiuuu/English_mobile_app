package tdtu.final_mobile.home.vocabulary;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.R;
import tdtu.final_mobile.data.QuizCate;
import tdtu.final_mobile.databinding.ActivityHomeActionVocabularyBinding;
import tdtu.final_mobile.databinding.CustomTopicLayoutBinding;
import tdtu.final_mobile.home.quiz.QuizAdapter;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.presentation.click_control.OnClickQuiz;

public class VocabularyActivity extends BaseActivity implements OnClickQuiz {
    ActivityHomeActionVocabularyBinding binding;

    @Override
    protected void doBusiness() {
        Call<ArrayList<QuizCate>> call = apiInterface.getAllVocabularyCates();
        binding.loading.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ArrayList<QuizCate>>() {
            @Override
            public void onResponse(Call<ArrayList<QuizCate>> call, Response<ArrayList<QuizCate>> response) {
                binding.loading.setVisibility(View.INVISIBLE);
                Log.d("TAG",response.code() + "");
                ArrayList<QuizCate> quizzes = response.body();
                QuizAdapter quizAdapter = new QuizAdapter(quizzes, VocabularyActivity.this);
                quizAdapter.setOnQuizTopicClickAction(VocabularyActivity.this);
                binding.rvVocabulary.setAdapter(quizAdapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VocabularyActivity.this, LinearLayoutManager.VERTICAL, false);
                binding.rvVocabulary.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<ArrayList<QuizCate>> call, Throwable t) {
                binding.loading.setVisibility(View.INVISIBLE);
                call.cancel();
            }
        });

        binding.iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    @Override
    protected View layoutId() {
        binding = ActivityHomeActionVocabularyBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void OnQuizTopicClick(int id) {
        Intent learnVocabularyIntent = new Intent(VocabularyActivity.this, VocabularyDetailActivity.class);
        learnVocabularyIntent.putExtra("id", id);
        startActivity(learnVocabularyIntent);
        overridePendingTransition(R.anim.enter_trans, R.anim.exit_trans);
    }
}