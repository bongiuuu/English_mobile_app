package tdtu.final_mobile.home.extra;

import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApiClient;

import tdtu.final_mobile.R;
import tdtu.final_mobile.databinding.ActivityHomeExtraBinding;
import tdtu.final_mobile.presentation.BaseActivity;

public class ExtraActivity extends BaseActivity {
    private ActivityHomeExtraBinding binding;

    private GoogleSignInClient mGoogleSignInClient;
    GoogleApiClient mGoogleApiClient;
    boolean mSignInClicked;

    @Override
    protected void doBusiness() {
        binding.iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.cvMe.setOnClickListener(this::onClick);
        binding.cvSettings.setOnClickListener(this::onClick);
        binding.cvAboutUs.setOnClickListener(this::onClick);
    }

    @Override
    protected View layoutId() {
        binding = ActivityHomeExtraBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                status -> {

                });
    }

    @SuppressLint("NonConstantResourceId")
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
            case R.id.cvLogout:
                break;
        }
    }
}