package tdtu.final_mobile.data;

import java.util.ArrayList;

public class FakeDataVocab {

    public static ArrayList<Vocab> getData() {
        ArrayList<Vocab> datum = new ArrayList<>();
        for (int i = 1; i < 22; i++) {
            datum.add(new Vocab("English " + i,"Vietnamese " + i));
        }
        return datum;
    }
}
