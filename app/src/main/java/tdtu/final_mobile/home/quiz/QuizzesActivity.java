package tdtu.final_mobile.home.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;

import tdtu.final_mobile.R;
import tdtu.final_mobile.data.FakeQuizData;
import tdtu.final_mobile.data.Quiz;
import tdtu.final_mobile.databinding.ActivityQuizzesBinding;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.utils.ViewAnimation;

public class QuizzesActivity extends BaseActivity {
    private ActivityQuizzesBinding binding;
    private final ArrayList<Quiz> quizzes = FakeQuizData.getQuiz();
    private int currentPosition = 0;

    @Override
    protected View layoutId() {
        binding = ActivityQuizzesBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void doBusiness() {
        binding.iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.iBtnPrevious.setOnClickListener(view -> {
            if (currentPosition > 0) {
                currentPosition--;
                binding.tvQuestion.setText(quizzes.get(currentPosition).getQuestion());
                binding.tvAnswerA.setText(quizzes.get(currentPosition).getAnswerA());
                binding.tvAnswerB.setText(quizzes.get(currentPosition).getAnswerB());
                binding.tvAnswerC.setText(quizzes.get(currentPosition).getAnswerC());
                binding.tvAnswerD.setText(quizzes.get(currentPosition).getAnswerD());
            }

            checkVisibleBackAndNextButton(currentPosition);
//            ViewAnimation.doViewAnimation(binding.cvQuestion);
//            ViewAnimation.doViewAnimation(binding.cvAnswerA);
//            ViewAnimation.doViewAnimation(binding.cvAnswerB);
//            ViewAnimation.doViewAnimation(binding.cvAnswerC);
//            ViewAnimation.doViewAnimation(binding.cvAnswerD);
        });

        binding.iBtnNext.setOnClickListener(view -> {
            if (currentPosition < quizzes.size() - 1) {
                currentPosition++;
                binding.tvQuestion.setText(quizzes.get(currentPosition).getQuestion());
                binding.tvAnswerA.setText(quizzes.get(currentPosition).getAnswerA());
                binding.tvAnswerB.setText(quizzes.get(currentPosition).getAnswerB());
                binding.tvAnswerC.setText(quizzes.get(currentPosition).getAnswerC());
                binding.tvAnswerD.setText(quizzes.get(currentPosition).getAnswerD());
            }

            checkVisibleBackAndNextButton(currentPosition);
//            ViewAnimation.doViewAnimation(binding.cvQuestion);
//            ViewAnimation.doViewAnimation(binding.cvAnswerA);
//            ViewAnimation.doViewAnimation(binding.cvAnswerB);
//            ViewAnimation.doViewAnimation(binding.cvAnswerC);
//            ViewAnimation.doViewAnimation(binding.cvAnswerD);
        });

        binding.tvQuestion.setText(quizzes.get(0).getQuestion());
        binding.tvAnswerA.setText(quizzes.get(0).getAnswerA());
        binding.tvAnswerB.setText(quizzes.get(0).getAnswerB());
        binding.tvAnswerC.setText(quizzes.get(0).getAnswerC());
        binding.tvAnswerD.setText(quizzes.get(0).getAnswerD());
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

        binding.pbQuiz.setProgress(currentPosition*5);
    }
}