package tdtu.final_mobile.home.extra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import tdtu.final_mobile.R;
import tdtu.final_mobile.utils.Constants;

public class SettingActivity extends AppCompatActivity {
    boolean quizSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_setting);

        SharedPreferences prefs = getSharedPreferences(Constants.KEY_QUIZ_SOUND, MODE_PRIVATE);
        quizSound = prefs.getBoolean(Constants.KEY_QUIZ_SOUND, true);

        ImageButton iBtnBack = findViewById(R.id.iBtnBack);
        Switch sQuizSound = findViewById(R.id.sQuizSound);
        sQuizSound.setChecked(quizSound);

        sQuizSound.setOnCheckedChangeListener((compoundButton, b) -> setQuizSound(b));
        iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    public void setQuizSound(boolean sound) {
        SharedPreferences.Editor editor = getSharedPreferences(Constants.KEY_QUIZ_SOUND, MODE_PRIVATE).edit();
        editor.putBoolean(Constants.KEY_QUIZ_SOUND, sound);
        editor.apply();
    }
}