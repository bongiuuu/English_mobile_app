package tdtu.final_mobile.home.progress.already_known;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import tdtu.final_mobile.R;

public class AlreadyKnownActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_home_activity_progress_already_known);

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

        String[] englishList = {"School", "Examination", "School Stationary", "School Subjects", "Classroom", "Music", "Clothes", "Family", "School", "Examination", "School Stationary", "School Subjects"};
        String[] vietnameseList = {"Trường học", "Kỳ thi", "Dụng cụ học tập", "Các môn học", "Lớp học", "Âm nhạc", "Quần áo", "Gia đình", "Trường học", "Kỳ thi", "Dụng cụ học tập", "Các môn học"};

        for (int i = 0; i < englishList.length; i++){
            words.add(new Word(englishList[i], vietnameseList[i]));
        }
        return words;
    }
}