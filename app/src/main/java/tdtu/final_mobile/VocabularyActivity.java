package tdtu.final_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class VocabularyActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_vocabulary);

        List<Vocabulary> vocabularies = getVocabulary();
        this.recyclerView = this.findViewById(R.id.rvVocabulary);
        recyclerView.setAdapter(new VocabularyAdapter(vocabularies, this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private List<Vocabulary> getVocabulary(){
        List<Vocabulary> vocabularies = new ArrayList<>();
        int[] colors = {Color.rgb(35, 254, 163),
                        Color.rgb(255, 226, 149)};
        int[] numColors = {Color.rgb(0, 135, 73),
                            Color.rgb(225, 84, 35)};
        vocabularies.add(new Vocabulary( "School", "Trường học", 1, numColors[0], colors[0]));
        vocabularies.add(new Vocabulary( "Examination", "Kỳ thi", 2, numColors[1], colors[1]));
        vocabularies.add(new Vocabulary( "Extracurricular Activities", "Hoạt động ngoại khoá", 3, numColors[1], colors[1]));
        vocabularies.add(new Vocabulary( "School Stationary", "Dụng cụ học tập", 4, numColors[1], colors[1]));
        vocabularies.add(new Vocabulary( "School Subjects", "Các môn học", 5, numColors[1], colors[1]));
        vocabularies.add(new Vocabulary( "Classroom", "Lớp học", 6, numColors[1], colors[1]));
        vocabularies.add(new Vocabulary( "Music", "Âm nhạc", 7, numColors[1], colors[1]));
        vocabularies.add(new Vocabulary( "Clothes", "Quần áo", 8, numColors[1], colors[1]));
        vocabularies.add(new Vocabulary( "Family", "Gia đình", 9, numColors[1], colors[1]));
        return vocabularies;
    }
}