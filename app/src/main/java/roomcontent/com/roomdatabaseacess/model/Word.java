package roomcontent.com.roomdatabaseacess.model;

import android.support.annotation.NonNull;

public class Word {
    private String mWord;
    public Word(@NonNull String word) {
        this.mWord = word;
    }

    public String getWord() {
        return this.mWord;
    }
}
