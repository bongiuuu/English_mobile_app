package tdtu.final_mobile.home.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.R;
import tdtu.final_mobile.data.QuizCate;
import tdtu.final_mobile.databinding.ActivityMainNotificationBinding;
import tdtu.final_mobile.databinding.ActivityQuizBinding;
import tdtu.final_mobile.home.quiz.QuizActivity;
import tdtu.final_mobile.home.quiz.QuizAdapter;
import tdtu.final_mobile.home.vocabulary.VocabularyAdapter;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.presentation.click_control.OnClickNotification;

public class MainNotificationActivity extends BaseActivity implements OnClickNotification {
    private ActivityMainNotificationBinding binding;

    @Override
    protected void doBusiness() {
        Call<ArrayList<Notification>> call = apiInterface.getNotifications();
        call.enqueue(new Callback<ArrayList<Notification>>() {
            @Override
            public void onResponse(Call<ArrayList<Notification>> call, Response<ArrayList<Notification>> response) {

                Log.d("TAG",response.code()+"");
                ArrayList<Notification> notifications = response.body();
                NotificationAdapter notificationAdapter = new NotificationAdapter(notifications, MainNotificationActivity.this);
                notificationAdapter.setNotificationClickAction(MainNotificationActivity.this);
                binding.rvNotification.setAdapter(notificationAdapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainNotificationActivity.this, LinearLayoutManager.VERTICAL, false);
                binding.rvNotification.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<ArrayList<Notification>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    @Override
    protected View layoutId() {
        binding = ActivityMainNotificationBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hide the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
//        setContentView(R.layout.activity_main_notification);
//
//        ImageButton iBtnBack = findViewById(R.id.iBtnBack);
//        iBtnBack.setOnClickListener(view -> {
//            onBackPressed();
//        });
//    }

    @Override
    public void OnNotificationClick(int position) {
        Intent notificationIntent = new Intent(MainNotificationActivity.this, NotificationDetailActivity.class);
        startActivity(notificationIntent);
    }
}