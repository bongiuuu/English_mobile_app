package tdtu.final_mobile.data;

import java.util.ArrayList;

public class FakeDataVocab {

    public static ArrayList<Vocab> getData() {
        ArrayList<Vocab> data = new ArrayList<>();
        for (int i = 1; i < 22; i++) {
            data.add(new Vocab("English "+i,"Vietnamese " + i));
        }
        return data;
    }
}
