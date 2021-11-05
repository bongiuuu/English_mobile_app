package tdtu.final_mobile.home.vocabulary;

public class Vocabulary {
    String vocabularyName;
    String vietnameseName;
    int number;
    int letterColor;
    int circleColor;

    public Vocabulary(String vocabularyName, String vietnameseName, int number, int numberColor, int color) {
        this.vocabularyName = vocabularyName;
        this.vietnameseName = vietnameseName;
        this.number = number;
        this.letterColor = numberColor;
        this.circleColor = color;
    }

    public int getLetterColor() {
        return letterColor;
    }

    public void setLetterColor(int letterColor) {
        this.letterColor = letterColor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getVocabularyName() {
        return vocabularyName;
    }

    public void setVocabularyName(String vocabularyName) {
        this.vocabularyName = vocabularyName;
    }

    public String getVietnameseName() {
        return vietnameseName;
    }

    public void setVietnameseName(String vietnameseName) {
        this.vietnameseName = vietnameseName;
    }

    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }
}
