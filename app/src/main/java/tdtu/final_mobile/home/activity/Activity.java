package tdtu.final_mobile.home.activity;

import android.content.res.ColorStateList;

public class Activity {
    String activityName;
    String activityIcon;
    int textColor;

    public Activity(String activityName, String activityIcon, int textColor) {
        this.activityName = activityName;
        this.activityIcon = activityIcon;
        this.textColor = textColor;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityIcon() {
        return activityIcon;
    }

    public void setActivityIcon(String activityIcon) {
        this.activityIcon = activityIcon;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
