package tdtu.final_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import tdtu.final_mobile.R;
import tdtu.final_mobile.home.HomeActivity;
import tdtu.final_mobile.login_register.MainActivity;
import tdtu.final_mobile.presentation.PracticeActivity;
import tdtu.final_mobile.utils.Constants;

public class SplashActivity extends Activity {
    private int userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences prefs = getSharedPreferences(Constants.KEY_USER_ID, MODE_PRIVATE);
        userId = prefs.getInt(Constants.KEY_USER_ID, 0);
            int secondsDelayed = 1;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (userId != 0) {
                        Intent goToMainActivity = new Intent(SplashActivity.this, HomeActivity.class);
                        goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(goToMainActivity);
                        finish();
                    } else {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }


                }
            }, secondsDelayed * 500);
    }
}