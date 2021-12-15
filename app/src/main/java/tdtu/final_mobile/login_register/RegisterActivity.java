package tdtu.final_mobile.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.R;
import tdtu.final_mobile.SplashActivity;
import tdtu.final_mobile.data.response.BaseResponse;
import tdtu.final_mobile.data.response.MultipleResource;
import tdtu.final_mobile.data.response.User;
import tdtu.final_mobile.databinding.ActivityPracticeBinding;
import tdtu.final_mobile.databinding.ActivityRegisterBinding;
import tdtu.final_mobile.home.HomeActivity;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.presentation.PracticeActivity;
import tdtu.final_mobile.utils.Constants;

public class RegisterActivity extends BaseActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void doBusiness() {
        binding.iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.btnRegister.setOnClickListener(v -> {

            if (binding.edtUsername.getText().toString().trim().isEmpty()) {
                showToast("Validation Username");
                return;
            }

            if (binding.edtPassword.getText().toString().trim().isEmpty() || binding.edtReEnterPassword.getText().toString().trim().isEmpty() ) {
                showToast("Validation Password");
                return;
            }

            if (!binding.edtPassword.getText().toString().trim().equals(binding.edtReEnterPassword.getText().toString().trim())) {
                showToast("Password not equal");
                return;
            }

            if (binding.edtPassword.getText().toString().trim().length() < 6) {
                showToast("Password length");
            }

            registerAccount();
        });

    }

    @Override
    protected View layoutId() {
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    private void validate() {

        if (isValid(binding.edtUsername.getText().toString().trim())){
            showToast("Email is invalid");
            return;
        }

        if (binding.edtUsername.getText().toString().trim().isEmpty()) {
            showToast("Validation Username");
            return;
        }

        if (binding.edtPassword.getText().toString().trim().isEmpty() || binding.edtReEnterPassword.getText().toString().trim().isEmpty() ) {
            showToast("Validation Password");
            return;
        }

        if (!binding.edtPassword.getText().toString().trim().equals(binding.edtReEnterPassword.getText().toString().trim())) {
            showToast("Password not equal");
            return;
        }

        if (binding.edtPassword.getText().toString().trim().length() < 6) {
            showToast("Password length");
        }
    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private void registerAccount() {
        User user = new User(binding.edtUsername.getText().toString(), binding.edtUsername.getText().toString(), binding.edtPassword.getText().toString());
        Call<BaseResponse<User>> call = apiInterface.createUser(user);
        call.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {

                if (response.isSuccessful()) {
                    BaseResponse<User> res = response.body();
                    if (res != null && res.getStatus() == 200) {
                        Intent goToMainActivity = new Intent(RegisterActivity.this, HomeActivity.class);
                        goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(goToMainActivity);
                        saveUserId(res.getData().getId());
                    }
                } else {
                    try {
                        assert response.errorBody() != null;
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(RegisterActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void saveUserId(int userId) {
        SharedPreferences.Editor editor = getSharedPreferences(Constants.KEY_USER_ID, MODE_PRIVATE).edit();
        editor.putInt(Constants.KEY_USER_ID, userId);
        editor.apply();
        OneSignal.setEmail("test@domain.com");
        OneSignal.sendTag("user_id", userId+"");
    }
}