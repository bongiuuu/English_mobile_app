package tdtu.final_mobile.presentation;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import tdtu.final_mobile.network.APIClient;
import tdtu.final_mobile.network.APIService;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void doBusiness();
    protected abstract View layoutId();
    public APIService apiInterface;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = APIClient.getClient().create(APIService.class);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(layoutId());
        doBusiness();
    }

    public void showToast(String content) {
        Toast.makeText(getBaseContext(), content, Toast.LENGTH_SHORT).show();
    }
}
