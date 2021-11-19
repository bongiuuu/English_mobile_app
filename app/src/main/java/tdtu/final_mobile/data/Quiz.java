package tdtu.final_mobile.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quiz {

    @SerializedName("question")
    @Expose
    String question;
    @SerializedName("answer_1")
    @Expose
    String answerA;
    @SerializedName("answer_2")
    @Expose
    String answerB;
    @SerializedName("answer_3")
    @Expose
    String answerC;
    @SerializedName("answer_4")
    @Expose
    String answerD;
    @SerializedName("correct_answer")
    @Expose
    int correctAnswer;

    public Quiz(String question, String answerA, String answerB, String answerC, String answerD, int correctAnswer) {
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }
}
