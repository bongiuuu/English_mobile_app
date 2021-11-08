package tdtu.final_mobile.data;

import java.util.ArrayList;

public class FakeQuizData {

    public static ArrayList<Quiz> getQuiz(){
        ArrayList<Quiz> quizzes = new ArrayList<>();
        for (int i = 1; i < 22; i ++){
            quizzes.add(new Quiz(i + ". Question " + i, "Answer A_" + i, "Answer B_" + i, "Answer C_" + i, "Answer D_" + i));
        }
        return quizzes;
    }
}
