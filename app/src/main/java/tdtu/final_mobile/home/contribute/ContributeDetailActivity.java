package tdtu.final_mobile.home.contribute;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.R;
import tdtu.final_mobile.data.Quiz;
import tdtu.final_mobile.databinding.ActivityHomeActivityContributeDetailBinding;
import tdtu.final_mobile.presentation.BaseActivity;

public class ContributeDetailActivity extends BaseActivity {
    private ActivityHomeActivityContributeDetailBinding binding;
    private final Quiz quiz = new Quiz();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void doBusiness() {
        quiz.setCorrectAnswer(1);

        binding.iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.btnApprove.setOnClickListener(v -> {

            int id = getIntent().getIntExtra("id", 1);

            if (binding.edtAnswerA.getText().toString().trim().equals("") || binding.edtAnswerB.getText().toString().trim().equals("") || binding.edtAnswerC.getText().toString().trim().equals("") || binding.edtAnswerD.getText().toString().trim().equals("")) {
                Toast.makeText(ContributeDetailActivity.this, getString(R.string.not_enough_infor), Toast.LENGTH_SHORT).show();
                return;
            }

            quiz.setQuestion(binding.edtQuestion.getText().toString());
            quiz.setAnswerA(binding.edtAnswerA.getText().toString());
            quiz.setAnswerB(binding.edtAnswerB.getText().toString());
            quiz.setAnswerC(binding.edtAnswerC.getText().toString());
            quiz.setAnswerD(binding.edtAnswerD.getText().toString());
            quiz.setQuizCatesId(id);
            createANewQuiz(quiz);

        });

        binding.rdAnswer.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioButtonA:
                    quiz.setCorrectAnswer(1);
                    break;
                case R.id.radioButtonB:
                    quiz.setCorrectAnswer(2);
                    break;
                case R.id.radioButtonC:
                    quiz.setCorrectAnswer(3);
                    break;
                case R.id.radioButtonD:
                    quiz.setCorrectAnswer(4);
                    break;
            }
        });
    }

    @Override
    protected View layoutId() {
        binding = ActivityHomeActivityContributeDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    public void createANewQuiz(Quiz quiz) {
        Call<Quiz> call = apiInterface.createQuiz(quiz);
        call.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                if (response.isSuccessful()) {
                    onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                call.cancel();
            }
        });
    }
}