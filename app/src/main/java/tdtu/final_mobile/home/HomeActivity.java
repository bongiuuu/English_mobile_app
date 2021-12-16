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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_home);

        ImageButton iBtnExtra = findViewById(R.id.iBtnExtra);
        ImageButton iBtnNotification = findViewById(R.id.iBtnNotification);

        iBtnExtra.setOnClickListener(this::OnClick);
        iBtnNotification.setOnClickListener(this::OnClick);

        List<Activity> activities = getListData();
        RecyclerView activityRecyclerView = this.findViewById(R.id.rvActivities);
        ActivityAdapter activityAdapter = new ActivityAdapter(activities, this);
        activityAdapter.setActivityAction(this);
        activityRecyclerView.setAdapter(activityAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityRecyclerView.setLayoutManager(linearLayoutManager);

        List<Action> actions = getActions();
        RecyclerView actionRecyclerView = this.findViewById(R.id.rvActions);
        ActionAdapter actionAdapter = new ActionAdapter(actions, this);
        actionAdapter.setOnClickAction(this);
        actionRecyclerView.setAdapter(actionAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        actionRecyclerView.setLayoutManager(linearLayoutManager1);

    }

    private List<Activity> getListData(){
        List<Activity> activities = new ArrayList<>();
        int[] colors = {Color.rgb(35, 254, 163), Color.rgb(255, 84, 35), Color.rgb(255, 176, 241)};
        activities.add(new Activity("Quizz", "icon1", colors[0]));
        activities.add(new Activity("Favorite Vocab", "icon2", colors[1]));
        activities.add(new Activity("Contribute Multiple choices Q&A", "icon3", colors[2]));
        return activities;
    }
    private List<Action> getActions(){
        List<Action> actions = new ArrayList<>();
        int[] colors = {Color.rgb(18, 88, 241), Color.rgb(36, 39, 52)};
        actions.add(new Action( "Vocabulary", "action_pic_1", colors[0]));
        actions.add(new Action( "Checkin", "action_pic_2", colors[1]));
        return actions;
    }


    @Override
    public void OnActionClick(int position) {
        if (position == 0){
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
        if (position == 0){
            Intent quizIntent = new Intent(HomeActivity.this, QuizActivity.class);
            startActivity(quizIntent);
            overridePendingTransition(R.anim.enter_trans, R.anim.exit_trans);
        } else if (position == 1){
            Intent favoriteVocabIntent = new Intent(HomeActivity.this, FavoriteVocabActivity.class);
            startActivity(favoriteVocabIntent);
            overridePendingTransition(R.anim.enter_trans, R.anim.exit_trans);
        } else {
            Intent contributeIntent = new Intent(HomeActivity.this, ContributeActivity.class);
            startActivity(contributeIntent);
            overridePendingTransition(R.anim.enter_trans, R.anim.exit_trans);
        }
    }

    public void OnClick(View view){
        switch (view.getId()){
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
}