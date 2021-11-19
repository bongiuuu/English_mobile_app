package tdtu.final_mobile.home.quiz;

public class QuizCircle {
    String quizTopic;
    String vietnameseName;
    int number;
    int letterColor;
    int circleColor;

    public QuizCircle(String quizTopic, String vietnameseName, int number, int letterColor, int circleColor) {
        this.quizTopic = quizTopic;
        this.vietnameseName = vietnameseName;
        this.number = number;
        this.letterColor = letterColor;
        this.circleColor = circleColor;
    }

    public String getQuizTopic() {
        return quizTopic;
    }

    public void setQuizTopic(String quizTopic) {
        this.quizTopic = quizTopic;
    }

    public String getVietnameseName() {
        return vietnameseName;
    }

    public void setVietnameseName(String vietnameseName) {
        this.vietnameseName = vietnameseName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLetterColor() {
        return letterColor;
    }

    public void setLetterColor(int letterColor) {
        this.letterColor = letterColor;
    }

    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }
}
