package tdtu.final_mobile.data.request;

import com.google.gson.annotations.SerializedName;

public class CheckIn {

    private String day;

    @SerializedName("user_id")
    private int userId;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
