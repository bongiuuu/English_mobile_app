package tdtu.final_mobile.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.data.response.BaseResponse;
import tdtu.final_mobile.data.response.User;
import tdtu.final_mobile.databinding.ActivityMainBinding;
import tdtu.final_mobile.databinding.ActivityRegisterBinding;
import tdtu.final_mobile.home.HomeActivity;
import tdtu.final_mobile.home.contribute.ChooseTopicActivity;
import tdtu.final_mobile.home.contribute.ContributeActivity;
import tdtu.final_mobile.home.extra.MeActivity;
import tdtu.final_mobile.home.notification.MainNotificationActivity;
import tdtu.final_mobile.home.notification.NotificationDetailActivity;
import tdtu.final_mobile.home.progress.ProgressActivity;
import tdtu.final_mobile.home.progress.already_known.AlreadyKnownActivity;
import tdtu.final_mobile.home.progress.average_score.AverageScoreActivity;
import tdtu.final_mobile.home.quiz.QuizActivity;
import tdtu.final_mobile.R;
import tdtu.final_mobile.home.quiz.QuizzesActivity;
import tdtu.final_mobile.home.vocabulary.VocabulariesActivity;
import tdtu.final_mobile.presentation.BaseActivity;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    @Override
    protected void doBusiness() {
        binding.btnLogin.setOnClickListener(v -> {

            if (binding.edtUsername.getText().toString().trim().isEmpty()) {
                showToast("Validation Username");
                return;
            }

            if (binding.edtPassword.getText().toString().trim().isEmpty()) {
                showToast("Validation Password");
                return;
            }

            login();

        });

        binding.btnRegister.setOnClickListener(v -> {
            Intent registerIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerIntent);
        });
    }

    @Override
    protected View layoutId() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    private void login() {
        User user = new User(binding.edtUsername.getText().toString(), binding.edtUsername.getText().toString(), binding.edtPassword.getText().toString());
        Call<BaseResponse<User>> call = apiInterface.login(user);
        call.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {

                if (response.isSuccessful()) {
                    BaseResponse<User> res = response.body();
                    if (res != null && res.getStatus() == 200) {
                        Intent loginIntent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(loginIntent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.response_fail), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                call.cancel();
            }
        });
    }
}

