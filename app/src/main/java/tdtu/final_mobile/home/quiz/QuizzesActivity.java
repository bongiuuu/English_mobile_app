package tdtu.final_mobile.home.quiz;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.R;
import tdtu.final_mobile.data.Quiz;
import tdtu.final_mobile.databinding.ActivityHomeActivityQuizDetailBinding;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.utils.Constants;
import tdtu.final_mobile.utils.ViewAnimation;

public class QuizzesActivity extends BaseActivity {
    private ActivityHomeActivityQuizDetailBinding binding;
    private ArrayList<Quiz> quizzes = new ArrayList<>();
    private int currentPosition = 0;
    private int correctAnswerPosition = 1;
    private int currentScore = 0;
    private boolean quizSound = true;

    @Override
    protected View layoutId() {
        binding = ActivityHomeActivityQuizDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void doBusiness() {
        fetchData();

        setOnClickAnswerButton();
    }

    private void checkVisibleBackAndNextButton(int position) {
        if (position == 0) {
            binding.iBtnPrevious.setVisibility(View.INVISIBLE);
        } else {
            binding.iBtnPrevious.setVisibility(View.VISIBLE);
        }

        if (position == quizzes.size() - 1) {
            binding.iBtnNext.setVisibility(View.INVISIBLE);
        } else {
            binding.iBtnNext.setVisibility(View.VISIBLE);
        }

        binding.pbQuiz.setProgress(currentPosition * (100 / (quizzes.size() - 1)));
        binding.tvCurrentScore.setText(currentPosition + 1 +"/" + quizzes.size());
    }

    private void fetchData() {
        int id = getIntent().getIntExtra("id",1);
        Call<ArrayList<Quiz>> call = apiInterface.getQuizs(id);
        call.enqueue(new Callback<ArrayList<Quiz>>() {
            @Override
            public void onResponse(Call<ArrayList<Quiz>> call, Response<ArrayList<Quiz>> response) {
                if (response.isSuccessful()) {
                    quizzes = response.body();
                    assert quizzes != null;
                    if(quizzes.isEmpty()) return;
                    binding.tvQuestion.setText(quizzes.get(0).getQuestion());
                    binding.tvAnswerA.setText(quizzes.get(0).getAnswerA());
                    binding.tvAnswerB.setText(quizzes.get(0).getAnswerB());
                    binding.tvAnswerC.setText(quizzes.get(0).getAnswerC());
                    binding.tvAnswerD.setText(quizzes.get(0).getAnswerD());
                    binding.tvCurrentScore.setText(1 +"/" + quizzes.size());
                    correctAnswerPosition = quizzes.get(0).getCorrectAnswer();
                }
                Log.d("TAG", response.code() + "");
            }

            @Override
            public void onFailure(Call<ArrayList<Quiz>> call, Throwable t) {
                call.cancel();
            }
        });

        SharedPreferences prefs = getSharedPreferences(Constants.KEY_QUIZ_SOUND, MODE_PRIVATE);
        quizSound = prefs.getBoolean(Constants.KEY_QUIZ_SOUND, true);
    }

    @SuppressLint("SetTextI18n")
    private void autoGoToTheNextQuestion() {
        if (currentPosition < quizzes.size() - 1) {
            currentPosition++;
            correctAnswerPosition = quizzes.get(currentPosition).getCorrectAnswer();
            binding.tvQuestion.setText(quizzes.get(currentPosition).getQuestion());
            binding.tvAnswerA.setText(quizzes.get(currentPosition).getAnswerA());
            binding.tvAnswerB.setText(quizzes.get(currentPosition).getAnswerB());
            binding.tvAnswerC.setText(quizzes.get(currentPosition).getAnswerC());
            binding.tvAnswerD.setText(quizzes.get(currentPosition).getAnswerD());
            binding.tvCurrentScore.setText(currentPosition + 1 +"/" + quizzes.size());
            binding.pbQuiz.setProgress(currentPosition * (100 / (quizzes.size() - 1)));
            ViewAnimation.doFlipAnimation(binding.cvQuestion);
        } else {
            showDoneDialog();
        }
//        checkVisibleBackAndNextButton(currentPosition);
    }

    private void setOnClickAnswerButton() {

        binding.iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

//        binding.iBtnPrevious.setOnClickListener(view -> {
//            if (currentPosition > 0) {
//                currentPosition--;
//                correctAnswerPosition = quizzes.get(currentPosition).getCorrectAnswer();
//                binding.tvQuestion.setText(quizzes.get(currentPosition).getQuestion());
//                binding.tvAnswerA.setText(quizzes.get(currentPosition).getAnswerA());
//                binding.tvAnswerB.setText(quizzes.get(currentPosition).getAnswerB());
//                binding.tvAnswerC.setText(quizzes.get(currentPosition).getAnswerC());
//                binding.tvAnswerD.setText(quizzes.get(currentPosition).getAnswerD());
//            }
//            checkVisibleBackAndNextButton(currentPosition);
//        });
//
//        binding.iBtnNext.setOnClickListener(view -> {
//            if (currentPosition < quizzes.size() - 1) {
//                currentPosition++;
//                correctAnswerPosition = quizzes.get(currentPosition).getCorrectAnswer();
//                binding.tvQuestion.setText(quizzes.get(currentPosition).getQuestion());
//                binding.tvAnswerA.setText(quizzes.get(currentPosition).getAnswerA());
//                binding.tvAnswerB.setText(quizzes.get(currentPosition).getAnswerB());
//                binding.tvAnswerC.setText(quizzes.get(currentPosition).getAnswerC());
//                binding.tvAnswerD.setText(quizzes.get(currentPosition).getAnswerD());
//            }
//            checkVisibleBackAndNextButton(currentPosition);
//        });

        binding.cvAnswerA.setOnClickListener(v -> {
            checkTheAnswer(binding.cvAnswerA, 1);
        });

        binding.cvAnswerB.setOnClickListener(v -> {
            checkTheAnswer(binding.cvAnswerB, 2);
        });

        binding.cvAnswerC.setOnClickListener(v -> {
            checkTheAnswer(binding.cvAnswerC, 3);
        });

        binding.cvAnswerD.setOnClickListener(v -> {
            checkTheAnswer(binding.cvAnswerC, 4);
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkTheAnswer(CardView cardView, int answerPosition) {

        if (answerPosition == correctAnswerPosition) {
            currentScore = currentScore + 20;
            binding.tvScore.setText(getString(R.string.plus)+currentScore);
            if (quizSound){
                MediaPlayer correctRing = MediaPlayer.create(this, R.raw.correct);
                correctRing.start();
            }
        }
        else {
            if (quizSound){
                MediaPlayer incorrectRing = MediaPlayer.create(this, R.raw.incorrect);
                incorrectRing.start();
            }
        }
        ViewAnimation.doBlinkAnimation(cardView);
        final Handler handler = new Handler();
        handler.postDelayed(this::autoGoToTheNextQuestion, 1600);
    }

    private void showDoneDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.you_have_finished)+ ". Score: "+ currentScore).setPositiveButton(R.string.yes, dialogClickListener).setCancelable(false).show();
    }
}