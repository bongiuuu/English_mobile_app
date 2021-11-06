package tdtu.final_mobile.home.contribute;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import tdtu.final_mobile.R;
import tdtu.final_mobile.home.vocabulary.Vocabulary;
import tdtu.final_mobile.home.vocabulary.VocabularyActivity;
import tdtu.final_mobile.home.vocabulary.VocabularyAdapter;

public class ChooseTopicActivity extends AppCompatActivity {
    private ImageButton iBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_choose_topic);

        iBtnBack = findViewById(R.id.iBtnBack);
        iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        VocabularyActivity vocabularyActivity = new VocabularyActivity();
        List<Vocabulary> vocabularies = vocabularyActivity.getVocabulary();
        RecyclerView recyclerView = this.findViewById(R.id.rvTopicList);
        VocabularyAdapter vocabularyAdapter = new VocabularyAdapter(vocabularies, this);
        recyclerView.setAdapter(vocabularyAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}