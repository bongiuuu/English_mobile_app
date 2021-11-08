package tdtu.final_mobile.home.vocabulary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import java.util.ArrayList;

import tdtu.final_mobile.data.FakeDataVocab;
import tdtu.final_mobile.data.Vocab;
import tdtu.final_mobile.databinding.ActivityVocabulariesBinding;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.utils.ViewAnimation;

public class VocabulariesActivity extends BaseActivity {
    private ActivityVocabulariesBinding binding;
    private final ArrayList<Vocab> vocabs = FakeDataVocab.getData();
    private int currentPosition = 0;

    @Override
    public View layoutId() {
        binding = ActivityVocabulariesBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void doBusiness() {

        binding.iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.btnPrevious.setOnClickListener(view -> {
            if (currentPosition > 0) {
                currentPosition--;
                binding.tvEnglishWord.setText(vocabs.get(currentPosition).getEnglish());
                binding.tvVietnameseWord.setText(vocabs.get(currentPosition).getVietnamese());
            }

            checkVisibleBackAndNextButton(currentPosition);
            ViewAnimation.doViewAnimation(binding.cvVocab);
        });

        binding.btnNext.setOnClickListener(view -> {
            if (currentPosition < vocabs.size() - 1) {
                currentPosition++;
                binding.tvEnglishWord.setText(vocabs.get(currentPosition).getEnglish());
                binding.tvVietnameseWord.setText(vocabs.get(currentPosition).getVietnamese());
            }
            checkVisibleBackAndNextButton(currentPosition);
            ViewAnimation.doViewAnimation(binding.cvVocab);
        });

        binding.tvEnglishWord.setText(vocabs.get(0).getEnglish());
        binding.tvVietnameseWord.setText(vocabs.get(0).getVietnamese());
    }

    private void checkVisibleBackAndNextButton(int position) {
        if (position == 0) {
            binding.btnPrevious.setVisibility(View.INVISIBLE);
        } else {
            binding.btnPrevious.setVisibility(View.VISIBLE);
        }

        if (position == vocabs.size() - 1) {
            binding.btnNext.setVisibility(View.INVISIBLE);
        } else {
            binding.btnNext.setVisibility(View.VISIBLE);
        }

        binding.pbVocabulary.setProgress(currentPosition*5);
    }
}