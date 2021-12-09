package tdtu.final_mobile.home.notification;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.databinding.ActivityHomeNotificationBinding;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.presentation.click_control.OnClickNotification;

public class NotificationActivity extends BaseActivity implements OnClickNotification {
    private ActivityHomeNotificationBinding binding;

    @Override
    protected void doBusiness() {
        Call<ArrayList<Notification>> call = apiInterface.getNotifications();
        call.enqueue(new Callback<ArrayList<Notification>>() {
            @Override
            public void onResponse(Call<ArrayList<Notification>> call, Response<ArrayList<Notification>> response) {

                Log.d("TAG",response.code()+"");
                ArrayList<Notification> notifications = response.body();
                NotificationAdapter notificationAdapter = new NotificationAdapter(notifications, NotificationActivity.this);
                notificationAdapter.setNotificationClickAction(NotificationActivity.this);
                binding.rvNotification.setAdapter(notificationAdapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NotificationActivity.this, LinearLayoutManager.VERTICAL, false);
                binding.rvNotification.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<ArrayList<Notification>> call, Throwable t) {
                call.cancel();
            }
        });

        binding.iBtnBack.setOnClickListener(view -> onBackPressed());
    }

    @Override
    protected View layoutId() {
        binding = ActivityHomeNotificationBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void OnNotificationClick(int position) {
        Intent notificationIntent = new Intent(NotificationActivity.this, NotificationDetailActivity.class);
        startActivity(notificationIntent);
    }
}