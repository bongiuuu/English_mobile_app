package tdtu.final_mobile.home.vocabulary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import tdtu.final_mobile.R;
import tdtu.final_mobile.presentation.click_control.OnClickVocabulary;

public class VocabularyActivity extends AppCompatActivity implements OnClickVocabulary {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_vocabulary);

        ImageButton iBtnBack = findViewById(R.id.iBtnBack);
        iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        List<Vocabulary> vocabularies = getVocabulary();
        RecyclerView recyclerView = this.findViewById(R.id.rvVocabulary);
        VocabularyAdapter vocabularyAdapter = new VocabularyAdapter(vocabularies, this);
        vocabularyAdapter.setTopicVocabularyClickAction(this);
        recyclerView.setAdapter(vocabularyAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public List<Vocabulary> getVocabulary(){
        int greenLetter = Color.rgb(0, 135, 73);
        int greenCircle = Color.rgb(35, 254, 163);
        int orangeLetter = Color.rgb(225, 84, 35);
        int orangeCircle = Color.rgb(255, 226, 149);
        int pinkLetter = Color.rgb(146, 116, 237);
        int pinkCircle = Color.rgb(236, 183, 233);
        int blueLetter = Color.rgb(72, 169, 226);
        int blueCircle = Color.rgb(164, 194, 227);
        int yellowLetter = Color.rgb(185, 145, 48);
        int yellowCircle = Color.rgb(255, 253, 84);

        List<Vocabulary> vocabularies = new ArrayList<>();

        String[] vocabularyList = {"School", "Examination", "Extracurricular Activities", "School Stationary", "School Subjects", "Classroom", "Music", "Clothes", "Family", "School", "Examination", "Extracurricular Activities", "School Stationary", "School Subjects"};
        String[] vietnameseList = {"Trường học", "Kỳ thi", "Hoạt động ngoại khoá", "Dụng cụ học tập", "Các môn học", "Lớp học", "Âm nhạc", "Quần áo", "Gia đình", "Trường học", "Kỳ thi", "Hoạt động ngoại khoá", "Dụng cụ học tập", "Các môn học"};
        int[] letterColors = {greenLetter, orangeLetter, pinkLetter, blueLetter, yellowLetter};
        int[] circleColors = {greenCircle, orangeCircle, pinkCircle, blueCircle, yellowCircle};
        int loop = 0;

        for (int i = 0; i < vocabularyList.length; i++){
            if (i < 5){
                vocabularies.add(new Vocabulary(vocabularyList[i], vietnameseList[i], i + 1, letterColors[i], circleColors[i]));
            } else {
                if (i % 5 == 0){
                    loop++;
                }
                vocabularies.add(new Vocabulary(vocabularyList[i], vietnameseList[i], i + 1, letterColors[i - 5*loop], circleColors[i - 5*loop]));
            }
        }
        return vocabularies;
    }

    @Override
    public void OnVocabularyTopicClick(int position) {
        Intent learnVocabularyIntent = new Intent(VocabularyActivity.this, VocabulariesActivity.class);
        startActivity(learnVocabularyIntent);
    }
}