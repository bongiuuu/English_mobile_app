package tdtu.final_mobile.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import tdtu.final_mobile.home.action.Action;
import tdtu.final_mobile.home.action.ActionAdapter;
import tdtu.final_mobile.home.activity.Activity;
import tdtu.final_mobile.home.activity.ActivityAdapter;
import tdtu.final_mobile.R;
import tdtu.final_mobile.home.checkin.CheckinActivity;
import tdtu.final_mobile.home.vocabulary.VocabularyActivity;
import tdtu.final_mobile.presentation.vocabulary.OnItemClickAction;

public class HomeActivity extends AppCompatActivity implements OnItemClickAction {
    public static final String LOG_TAG = "icon1";
    public static final String LOG_TAG_1 = "action_pic_1";
    private RecyclerView recyclerView;
    private RecyclerView actionRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_home);

        List<Activity> activities = getListData();
        this.recyclerView = this.findViewById(R.id.rvActivities);
        recyclerView.setAdapter(new ActivityAdapter(activities, this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<Action> actions = getActions();
        this.actionRecyclerView = this.findViewById(R.id.rvActions);
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
        activities.add(new Activity("Progress", "icon2", colors[1]));
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
    public void onClick(int position) {
        if (position == 0){
            Intent vocabularyIntent = new Intent(HomeActivity.this, VocabularyActivity.class);
            startActivity(vocabularyIntent);
        } else {
            Intent checkinIntent = new Intent(HomeActivity.this, CheckinActivity.class);
            startActivity(checkinIntent);
        }

    }
}