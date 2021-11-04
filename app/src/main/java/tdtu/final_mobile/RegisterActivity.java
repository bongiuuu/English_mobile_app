package tdtu.final_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

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

        iBtnBack.setOnClickListener(view -> {
            Intent backIntent = new Intent(view.getContext(), MainActivity.class);
            startActivity(backIntent);
        });

    }
}