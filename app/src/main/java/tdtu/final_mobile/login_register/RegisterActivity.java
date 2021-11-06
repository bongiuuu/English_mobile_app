package tdtu.final_mobile.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import tdtu.final_mobile.R;

public class RegisterActivity extends AppCompatActivity {
    private ImageButton iBtnBack, iBtnGoogleLogin, iBtnFacebookLogin;
    private EditText edtUsername, edtPassword, edtReEnterPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_register);

        iBtnBack = findViewById(R.id.iBtnBack);
        iBtnGoogleLogin = findViewById(R.id.iBtnGoogleLogin);
        iBtnFacebookLogin = findViewById(R.id.iBtnFacebookLogin);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtReEnterPassword = findViewById(R.id.edtReEnterPassword);
        btnRegister = findViewById(R.id.btnRegister);

        iBtnBack = findViewById(R.id.iBtnBack);
        iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

    }
}