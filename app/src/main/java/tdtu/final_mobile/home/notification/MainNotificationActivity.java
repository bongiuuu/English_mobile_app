package tdtu.final_mobile.home.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import tdtu.final_mobile.R;

public class MainNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main_notification);

        ImageButton iBtnBack = findViewById(R.id.iBtnBack);
        iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        List<Notification> notifications = getNotificationList();
        RecyclerView recyclerView = this.findViewById(R.id.rvNotification);
        recyclerView.setAdapter(new NotificationAdapter(notifications, this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private List<Notification> getNotificationList(){
        List<Notification> notifications = new ArrayList<>();

        String[] notificationTitleList = {"Notification 1", "Notification 2", "Notification 3", "Notification 4", "Notification 5", "Notification 6", "Notification 7"};
        String[] notificationContentList = {"Content 1", "Content 2", "Content 3", "Content 4", "Content 5", "Content 6", "Content 7"};
        for (int i = notificationTitleList.length - 1; i >= 0; i--){
            notifications.add(new Notification(notificationTitleList[i], notificationContentList[i]));
        }
        return notifications;
    }
}