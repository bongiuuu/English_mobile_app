package tdtu.final_mobile.home.favorite_vocab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.R;
import tdtu.final_mobile.data.QuizCate;
import tdtu.final_mobile.databinding.ActivityHomeActivityFavoriteVocabBinding;
import tdtu.final_mobile.databinding.ActivityHomeActivityQuizBinding;
import tdtu.final_mobile.home.quiz.QuizActivity;
import tdtu.final_mobile.home.quiz.QuizAdapter;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.utils.Constants;

public class FavoriteVocabActivity extends BaseActivity {
    private WordAdapter adapter;
    ActivityHomeActivityFavoriteVocabBinding binding;
    private int userId = 1;
    @Override
    protected void doBusiness() {
        binding.iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        fetchData();
    }

    @Override
    protected View layoutId() {
        binding = ActivityHomeActivityFavoriteVocabBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    private void fetchData() {
        SharedPreferences prefs = getSharedPreferences(Constants.KEY_USER_ID, MODE_PRIVATE);
        userId = prefs.getInt(Constants.KEY_USER_ID, 1);
        Call<ArrayList<Word>> call = apiInterface.getFavorVocabs(userId);
        call.enqueue(new Callback<ArrayList<Word>>() {
            @Override
            public void onResponse(Call<ArrayList<Word>> call, Response<ArrayList<Word>> response) {

                Log.d("TAG",response.code()+"");
                ArrayList<Word> words = response.body();
                WordAdapter wordAdapter = new WordAdapter(words, FavoriteVocabActivity.this);
                binding.rvAlreadyKnown.setAdapter(wordAdapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FavoriteVocabActivity.this, LinearLayoutManager.VERTICAL, false);
                binding.rvAlreadyKnown.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<ArrayList<Word>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private List<Word> getWord(){
        List<Word> words = new ArrayList<>();

        String[] englishList = {"School", "Examination", "School Stationary", "School Subjects", "Classroom", "Music", "Clothes", "Family", "School", "Examination", "School Stationary", "School Subjects", "School", "Examination", "School Stationary", "School Subjects"};
        String[] vietnameseList = {"Trường học", "Kỳ thi", "Dụng cụ học tập", "Các môn học", "Lớp học", "Âm nhạc", "Quần áo", "Gia đình", "Trường học", "Kỳ thi", "Dụng cụ học tập", "Các môn học", "Trường học", "Kỳ thi", "Dụng cụ học tập", "Các môn học"};

        for (int i = 0; i < englishList.length; i++){
            words.add(new Word(englishList[i], vietnameseList[i]));
        }
        return words;
    }
}