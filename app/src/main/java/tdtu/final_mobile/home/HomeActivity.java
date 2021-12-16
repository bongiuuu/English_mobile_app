package tdtu.final_mobile.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tdtu.final_mobile.home.action.Action;
import tdtu.final_mobile.home.action.ActionAdapter;
import tdtu.final_mobile.home.activity.Activity;
import tdtu.final_mobile.home.activity.ActivityAdapter;
import tdtu.final_mobile.R;
import tdtu.final_mobile.home.checkin.CheckInActivity;
import tdtu.final_mobile.home.contribute.ContributeActivity;
import tdtu.final_mobile.home.extra.ExtraActivity;
import tdtu.final_mobile.home.notification.NotificationActivity;
import tdtu.final_mobile.home.favorite_vocab.FavoriteVocabActivity;
import tdtu.final_mobile.home.quiz.QuizActivity;
import tdtu.final_mobile.home.vocabulary.VocabularyActivity;
import tdtu.final_mobile.presentation.click_control.OnClickActivity;
import tdtu.final_mobile.presentation.click_control.OnClickAction;

public class HomeActivity extends AppCompatActivity implements OnClickAction, OnClickActivity {
    public static final String LOG_TAG = "icon1";
    public static final String LOG_TAG_1 = "action_pic_1";
    List<Activity> activities = new ArrayList<>();
    List<Action> actions = new ArrayList<>();
    ActivityAdapter activityAdapter;
    ActionAdapter actionAdapter;
    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_home);
        FirebaseApp.initializeApp(this);

        activities = getListData();
        actions = getActions();

        ImageButton iBtnExtra = findViewById(R.id.iBtnExtra);
        ImageButton iBtnNotification = findViewById(R.id.iBtnNotification);
        tvTitle = findViewById(R.id.tvActivities);

        iBtnExtra.setOnClickListener(this::OnClick);
        iBtnNotification.setOnClickListener(this::OnClick);

        RecyclerView activityRecyclerView = this.findViewById(R.id.rvActivities);
        activityAdapter = new ActivityAdapter(activities, this);
        activityAdapter.setActivityAction(this);
        activityRecyclerView.setAdapter(activityAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityRecyclerView.setLayoutManager(linearLayoutManager);


        RecyclerView actionRecyclerView = this.findViewById(R.id.rvActions);
        actionAdapter = new ActionAdapter(actions, this);
        actionAdapter.setOnClickAction(this);
        actionRecyclerView.setAdapter(actionAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        actionRecyclerView.setLayoutManager(linearLayoutManager1);

        getDataFireBase();
    }

    private List<Activity> getListData() {
        int[] colors = {Color.rgb(35, 254, 163), Color.rgb(255, 84, 35), Color.rgb(255, 176, 241)};
        activities.add(new Activity("Quizz", "icon1", colors[0]));
        activities.add(new Activity("Favorite Vocab", "icon2", colors[1]));
        activities.add(new Activity("Contribute Multiple choices Q&A", "icon3", colors[2]));
        return activities;
    }

    private List<Action> getActions() {
        List<Action> actions = new ArrayList<>();
        int[] colors = {Color.rgb(18, 88, 241), Color.rgb(36, 39, 52)};
        actions.add(new Action("Vocabulary", "action_pic_1", colors[0]));
        actions.add(new Action("Checkin", "action_pic_2", colors[1]));
        return actions;
    }


    @Override
    public void OnActionClick(int position) {
        if (position == 0) {
            Intent vocabularyIntent = new Intent(HomeActivity.this, VocabularyActivity.class);
            startActivity(vocabularyIntent);
            overridePendingTransition(R.anim.enter_trans, R.anim.exit_trans);
        } else {
            Intent checkinIntent = new Intent(HomeActivity.this, CheckInActivity.class);
            startActivity(checkinIntent);
            overridePendingTransition(R.anim.enter_trans, R.anim.exit_trans);
        }
    }

    @Override
    public void onActivityClick(int position) {
        if (position == 0) {
            Intent quizIntent = new Intent(HomeActivity.this, QuizActivity.class);
            startActivity(quizIntent);
            overridePendingTransition(R.anim.enter_trans, R.anim.exit_trans);
        } else if (position == 1) {
            Intent favoriteVocabIntent = new Intent(HomeActivity.this, FavoriteVocabActivity.class);
            startActivity(favoriteVocabIntent);
            overridePendingTransition(R.anim.enter_trans, R.anim.exit_trans);
        } else {
            Intent contributeIntent = new Intent(HomeActivity.this, ContributeActivity.class);
            startActivity(contributeIntent);
            overridePendingTransition(R.anim.enter_trans, R.anim.exit_trans);
        }
    }

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.iBtnExtra:
                Intent extraIntent = new Intent(view.getContext(), ExtraActivity.class);
                startActivity(extraIntent);
                break;
            case R.id.iBtnNotification:
                Intent notificationIntent = new Intent(view.getContext(), NotificationActivity.class);
                startActivity(notificationIntent);
                break;
        }
    }

    public void getDataFireBase() {

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference quiz = rootRef.child("menu").child("quiz");
        DatabaseReference favor = rootRef.child("menu").child("favor");
        DatabaseReference contribute = rootRef.child("menu").child("contribute");
        DatabaseReference tvActivities = rootRef.child("menu").child("title");

        quiz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                activities.get(0).setActivityName(name);
                activityAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        favor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                activities.get(1).setActivityName(name);
                activityAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        contribute.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                activities.get(2).setActivityName(name);
                activityAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        tvActivities.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                tvTitle.setText(name);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}