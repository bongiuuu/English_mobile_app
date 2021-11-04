package tdtu.final_mobile;

public class Vocabulary {
    String vocabularyName;
    String vietnameseName;
    int number;
    int numberColor;
    int color;

    public Vocabulary(String vocabularyName, String vietnameseName, int number, int numberColor, int color) {
        this.vocabularyName = vocabularyName;
        this.vietnameseName = vietnameseName;
        this.number = number;
        this.numberColor = numberColor;
        this.color = color;
    }

    public int getNumberColor() {
        return numberColor;
    }

    public void setNumberColor(int numberColor) {
        this.numberColor = numberColor;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
