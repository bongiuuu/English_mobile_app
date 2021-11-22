package tdtu.final_mobile.login_register;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.data.request.CheckIn;
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
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void doBusiness() {


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.iBtnGoogleLogin);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT);

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

        binding.iBtnGoogleLogin.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            someActivityResultLauncher.launch(signInIntent);
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

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            Intent loginIntent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(loginIntent);
        }
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
            Log.d("hai", account.getDisplayName());
            Log.d("hai", account.getEmail());
            User user = new User(account.getDisplayName(), account.getEmail());
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
                    User res = response.body();
                    Intent loginIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(loginIntent);
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

    public void doCheckIn() {

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String currentDay = "";
        switch (day) {
            case Calendar.SUNDAY:
                currentDay = "Sun";
                break;
            case Calendar.MONDAY:
                currentDay = "Mon";
                break;
            case Calendar.TUESDAY:
                currentDay = "Tue";
                break;
            case Calendar.WEDNESDAY:
                currentDay = "Wed";
                break;
            case Calendar.THURSDAY:
                currentDay = "Thur";
                break;
            case Calendar.FRIDAY:
                currentDay = "Fri";
                break;
            case Calendar.SATURDAY:
                currentDay = "Sat";
                break;
        }

        // Call the api to update check in
        // If Today is monday, remove all CheckIn and Create a new one
        CheckIn checkIn = new CheckIn();
        checkIn.setDay(currentDay);
        checkIn.setUserId(1);
        Call<CheckIn> call = apiInterface.checkIn(checkIn);
        call.enqueue(new Callback<CheckIn>() {
            @Override
            public void onResponse(Call<CheckIn> call, Response<CheckIn> response) {

            }

            @Override
            public void onFailure(Call<CheckIn> call, Throwable t) {
                call.cancel();
            }
        });
    }
}

