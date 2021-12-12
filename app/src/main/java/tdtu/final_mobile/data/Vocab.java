package tdtu.final_mobile.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vocab {
    private String english;
    private String vietnamese;
    private boolean isFavor = false;
    @SerializedName("user_id")
    @Expose
    private int userId;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isFavor() {
        return isFavor;
    }

    public void setFavor(boolean favor) {
        isFavor = favor;
    }

    public Vocab(String english, String vietnamese) {
        this.english = english;
        this.vietnamese = vietnamese;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }
}
