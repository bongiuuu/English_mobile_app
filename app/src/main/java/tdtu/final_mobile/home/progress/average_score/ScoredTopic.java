package tdtu.final_mobile.home.progress.average_score;

public class ScoredTopic {
    String topicName;
    String vietnameseName;
    int scored;
    int number;
    int letterColor;
    int circleColor;

    public ScoredTopic(String topicName, String vietnameseName, int scored, int number, int letterColor, int circleColor) {
        this.topicName = topicName;
        this.vietnameseName = vietnameseName;
        this.scored = scored;
        this.number = number;
        this.letterColor = letterColor;
        this.circleColor = circleColor;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getVietnameseName() {
        return vietnameseName;
    }

    public void setVietnameseName(String vietnameseName) {
        this.vietnameseName = vietnameseName;
    }

    public int getScored() {
        return scored;
    }

    public void setScored(int scored) {
        this.scored = scored;
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
