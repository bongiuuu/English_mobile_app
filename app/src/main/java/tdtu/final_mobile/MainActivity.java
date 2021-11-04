package tdtu.final_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageButton iBtnGoogleLogin, iBtnFacebookLogin;
    private EditText edtUsername, edtPassword;
    private TextView tvForgotPassword;
    private Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        iBtnGoogleLogin = findViewById(R.id.iBtnGoogleLogin);
        iBtnFacebookLogin = findViewById(R.id.iBtnFacebookLogin);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(this::OnClick);
        btnLogin.setOnClickListener(this::OnClick);
    }

    public void OnClick(View view){
        switch (view.getId()){
            case R.id.btnRegister:
                Intent registerIntent = new Intent(view.getContext(), VocabularyActivity.class);
                startActivity(registerIntent);
                break;
            case R.id.btnLogin:
                Intent loginIntent = new Intent(view.getContext(), HomeActivity.class);
                startActivity(loginIntent);
                break;
        }
    }
}