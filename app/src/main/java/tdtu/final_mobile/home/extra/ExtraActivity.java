package tdtu.final_mobile.home.extra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import tdtu.final_mobile.R;
import tdtu.final_mobile.home.notification.MainNotificationActivity;

public class ExtraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_extra);

        ImageButton iBtnBack = findViewById(R.id.iBtnBack);
        iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        CardView cvMe = findViewById(R.id.cvMe);
        CardView cvSetting = findViewById(R.id.cvSettings);
        CardView cvAboutUs = findViewById(R.id.cvAboutUs);
        CardView cvLogout = findViewById(R.id.cvLogout);

        cvMe.setOnClickListener(this::onClick);
        cvSetting.setOnClickListener(this::onClick);
        cvAboutUs.setOnClickListener(this::onClick);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.cvMe:
                Intent meIntent = new Intent(view.getContext(), MeActivity.class);
                startActivity(meIntent);
                break;
            case R.id.cvSettings:
                Intent settingIntent = new Intent(view.getContext(), SettingActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.cvAboutUs:
                Intent aboutUsIntent = new Intent(view.getContext(), AboutUsActivity.class);
                startActivity(aboutUsIntent);
                break;
        }
    }
}