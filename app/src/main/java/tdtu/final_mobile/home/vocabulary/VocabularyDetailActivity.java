package tdtu.final_mobile.home.vocabulary;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.JsonElement;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.R;
import tdtu.final_mobile.data.Vocab;
import tdtu.final_mobile.databinding.ActivityHomeActionVocabularyDetailBinding;
import tdtu.final_mobile.home.favorite_vocab.FavoriteVocabActivity;
import tdtu.final_mobile.home.favorite_vocab.Word;
import tdtu.final_mobile.home.favorite_vocab.WordAdapter;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.utils.Constants;
import tdtu.final_mobile.utils.ViewAnimation;

public class VocabularyDetailActivity extends BaseActivity {
    private ActivityHomeActionVocabularyDetailBinding binding;
    private ArrayList<Vocab> vocabs = new ArrayList<>();
    private int currentPosition = 0;
    private int userId = 0;
    private Vocab currentVocab = null;

    ArrayList<Word> words = new ArrayList<>();

    @Override
    public View layoutId() {
        binding = ActivityHomeActionVocabularyDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void doBusiness() {
        getFavorVocab();
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

        if (vocabs.get(currentPosition).isFavor()) {
            binding.iBtnFavorite.setImageResource(R.drawable.full_star);
        } else {
            binding.iBtnFavorite.setImageResource(R.drawable.empty_star);
        }

//        binding.pbVocabulary.setProgress(currentPosition * (100 / (vocabs.size() - 1)));
    }

    private void fetchData() {
        int id = getIntent().getIntExtra("id", 1);
        binding.loading.setVisibility(View.VISIBLE);
        Call<ArrayList<Vocab>> call = apiInterface.getVocabularyFromCateId(id);
        call.enqueue(new Callback<ArrayList<Vocab>>() {
            @Override
            public void onResponse(Call<ArrayList<Vocab>> call, Response<ArrayList<Vocab>> response) {
                binding.loading.setVisibility(View.INVISIBLE);
                Log.d("TAG", response.code() + "");
                vocabs = response.body();
                for (int i = 0; i < vocabs.size(); i++) {
                    for (int i1 = 0; i1 < words.size(); i1++) {
                        if (words.get(i1).getEnglishWord().trim().equalsIgnoreCase(vocabs.get(i).getEnglish().trim())) {
                            vocabs.get(i).setFavor(true);
                        }
                    }
                }

                setupLogic();
            }

            @Override
            public void onFailure(Call<ArrayList<Vocab>> call, Throwable t) {
                binding.loading.setVisibility(View.INVISIBLE);
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

        if (vocabs.isEmpty()) return;
        binding.tvEnglishWord.setText(vocabs.get(0).getEnglish());
        binding.tvVietnameseWord.setText(vocabs.get(0).getVietnamese());

        binding.iBtnFavorite.setOnClickListener(view -> {
            currentVocab = vocabs.get(currentPosition);
            currentVocab.setUserId(userId);
            if (!currentVocab.isFavor()) {
                addFavorVocab();
                binding.iBtnFavorite.setImageResource(R.drawable.full_star);
            } else {
                removeFavorVocab();
                binding.iBtnFavorite.setImageResource(R.drawable.empty_star);
            }

            currentVocab.setFavor(!currentVocab.isFavor());
        });

        if (vocabs.get(0).isFavor()) {
            addFavorVocab();
            binding.iBtnFavorite.setImageResource(R.drawable.full_star);
        } else {
            removeFavorVocab();
            binding.iBtnFavorite.setImageResource(R.drawable.empty_star);
        }

    }

    private void getFavorVocab() {
        SharedPreferences prefs = getSharedPreferences(Constants.KEY_USER_ID, MODE_PRIVATE);
        userId = prefs.getInt(Constants.KEY_USER_ID, 1);
        Call<ArrayList<Word>> call = apiInterface.getFavorVocabs(userId);
        call.enqueue(new Callback<ArrayList<Word>>() {
            @Override
            public void onResponse(Call<ArrayList<Word>> call, Response<ArrayList<Word>> response) {
                Log.d("TAG", response.code() + "");
                words = response.body();
                fetchData();
            }

            @Override
            public void onFailure(Call<ArrayList<Word>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void addFavorVocab() {
        // Call Api to update favor
        Call<JsonElement> call = apiInterface.addFavorVocab(currentVocab);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.d("TAG", response.code() + "");
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void removeFavorVocab() {
        // Call Api to update favor
        Call<JsonElement> call = apiInterface.removeFavorVocab(currentVocab);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.d("TAG", response.code() + "");
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                call.cancel();
            }
        });
    }
}