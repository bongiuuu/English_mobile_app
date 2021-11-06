package tdtu.final_mobile.home.progress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import tdtu.final_mobile.R;
import tdtu.final_mobile.home.progress.already_known.AlreadyKnownActivity;
import tdtu.final_mobile.home.progress.average_score.AverageScoreActivity;

public class ProgressActivity extends AppCompatActivity {
    CardView cvAlreadyKnown;
    CardView cvAverageScore;
    ImageButton iBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_progress);

        iBtnBack = findViewById(R.id.iBtnBack);
        iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        cvAlreadyKnown = findViewById(R.id.cvAlreadyKnown);
        cvAverageScore = findViewById(R.id.cvAverageScore);

        cvAlreadyKnown.setOnClickListener(this::OnClick);
        cvAverageScore.setOnClickListener(this::OnClick);
    }

    @SuppressLint("NonConstantResourceId")
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.cvAlreadyKnown:
                Intent alreadyKnownIntent = new Intent(view.getContext(), AlreadyKnownActivity.class);
                startActivity(alreadyKnownIntent);
                break;
            case R.id.cvAverageScore:
                Intent averageScoreIntent = new Intent(view.getContext(), AverageScoreActivity.class);
                startActivity(averageScoreIntent);
                break;
        }
    }

}