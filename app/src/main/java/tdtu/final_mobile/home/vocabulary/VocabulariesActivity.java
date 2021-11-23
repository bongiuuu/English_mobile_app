package tdtu.final_mobile.home.vocabulary;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.data.FakeDataVocab;
import tdtu.final_mobile.data.Quiz;
import tdtu.final_mobile.data.QuizCate;
import tdtu.final_mobile.data.Vocab;
import tdtu.final_mobile.databinding.ActivityVocabulariesBinding;
import tdtu.final_mobile.home.contribute.ChooseTopicActivity;
import tdtu.final_mobile.home.quiz.QuizAdapter;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.utils.ViewAnimation;

public class VocabulariesActivity extends BaseActivity {
    private ActivityVocabulariesBinding binding;
    private ArrayList<Vocab> vocabs = new ArrayList<>();
    private int currentPosition = 0;

    @Override
    public View layoutId() {
        binding = ActivityVocabulariesBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void doBusiness() {
        fetchData();
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

        binding.pbVocabulary.setProgress(currentPosition * (100 / (vocabs.size() - 1)));
    }

    private void fetchData() {
        int id = getIntent().getIntExtra("id",1);
        Call<ArrayList<Vocab>> call = apiInterface.getVocabularyFromCateId(id);
        call.enqueue(new Callback<ArrayList<Vocab>>() {
            @Override
            public void onResponse(Call<ArrayList<Vocab>> call, Response<ArrayList<Vocab>> response) {

                Log.d("TAG",response.code()+"");
                vocabs = response.body();
                setupLogic();
            }

            @Override
            public void onFailure(Call<ArrayList<Vocab>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void setupLogic() {
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
            ViewAnimation.doFlipAnimation(binding.cvVocab);
        });

        binding.btnNext.setOnClickListener(view -> {
            if (currentPosition < vocabs.size() - 1) {
                currentPosition++;
                binding.tvEnglishWord.setText(vocabs.get(currentPosition).getEnglish());
                binding.tvVietnameseWord.setText(vocabs.get(currentPosition).getVietnamese());
            }
            checkVisibleBackAndNextButton(currentPosition);
            ViewAnimation.doFlipAnimation(binding.cvVocab);
        });

        if(vocabs.isEmpty()) return;
        binding.tvEnglishWord.setText(vocabs.get(0).getEnglish());
        binding.tvVietnameseWord.setText(vocabs.get(0).getVietnamese());
    }
}