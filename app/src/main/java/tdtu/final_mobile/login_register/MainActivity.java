package tdtu.final_mobile.login_register;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.onesignal.OneSignal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.R;
import tdtu.final_mobile.data.response.BaseResponse;
import tdtu.final_mobile.data.response.User;
import tdtu.final_mobile.databinding.ActivityMainBinding;
import tdtu.final_mobile.home.HomeActivity;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.utils.Constants;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void doBusiness() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        binding.btnLogin.setOnClickListener(v -> {

            if (binding.edtUsername.getText().toString().trim().isEmpty()) {
                showToast("Validation Username");
                return;
            }

            if (binding.edtPassword.getText().toString().trim().isEmpty()) {
                showToast("Validation Password");
                return;
            }

            User user = new User(binding.edtUsername.getText().toString(), binding.edtUsername.getText().toString(), binding.edtPassword.getText().toString());
            login(user);

        });

        binding.iBtnGoogleLogin.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            someActivityResultLauncher.launch(signInIntent);
        });

        binding.btnRegister.setOnClickListener(v -> {
//            Intent registerIntent = new Intent(this, RegisterActivity.class);
            Intent registerIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerIntent);
        });
    }

    @Override
    protected View layoutId() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    private void login(User user) {
        Call<BaseResponse<User>> call = apiInterface.login(user);
        call.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {

                if (response.isSuccessful()) {
                    BaseResponse<User> res = response.body();
                    if (res != null && res.getStatus() == 200) {
                        saveUserId(res.getData().getId());
                        Intent loginIntent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(loginIntent);
                        finish();
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

    @Override
    public void onStart() {
        super.onStart();
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        if (account != null) {
//            Intent loginIntent = new Intent(MainActivity.this, HomeActivity.class);
//            startActivity(loginIntent);
//        }
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                handleSignInResult(task);
            });

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            User user = new User(account.getDisplayName(), account.getEmail());
            user.setPassword(Constants.DEFAULT_PASSWORD);
            loginWithGoogle(user);
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void loginWithGoogle(User user) {
        Call<User> call = apiInterface.loginWithGoogle(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    login(user);
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.response_fail), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void saveUserId(int userId) {
        SharedPreferences.Editor editor = getSharedPreferences(Constants.KEY_USER_ID, MODE_PRIVATE).edit();
        editor.putInt(Constants.KEY_USER_ID, userId);
        editor.apply();
//        OneSignal.setEmail("test@domain.com");
        OneSignal.sendTag("user_id", userId+"");
    }

}

