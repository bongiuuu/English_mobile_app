package tdtu.final_mobile.home.favorite_vocab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import tdtu.final_mobile.R;

public class FavoriteVocabActivity extends AppCompatActivity {
    private WordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_home_activity_favorite_vocab);

        ImageButton iBtnBack = findViewById(R.id.iBtnBack);
        iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        List<Word> words = getWord();
        RecyclerView recyclerView = this.findViewById(R.id.rvAlreadyKnown);
        WordAdapter wordAdapter = new WordAdapter(words, this);
        recyclerView.setAdapter(wordAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_find_word, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                adapter.getFilter().filter(s);
//                return false;
//            }
//        });
        return true;
    }
}