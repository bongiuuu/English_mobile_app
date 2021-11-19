package tdtu.final_mobile.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizCate {

    private int id;
    private String title;

    public int getId() {
        return id;
    }

    @SerializedName("sub_title")
    @Expose
    private String subTitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
