package tdtu.final_mobile.home.favorite_vocab;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Word {
    @SerializedName("english")
    @Expose
    String englishWord;
    @SerializedName("vietnamese")
    @Expose
    String vietnameseWord;
    @SerializedName("user_id")
    @Expose
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Word(String englishWord, String vietnameseWord) {
        this.englishWord = englishWord;
        this.vietnameseWord = vietnameseWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getVietnameseWord() {
        return vietnameseWord;
    }

    public void setVietnameseWord(String vietnameseWord) {
        this.vietnameseWord = vietnameseWord;
    }
}
