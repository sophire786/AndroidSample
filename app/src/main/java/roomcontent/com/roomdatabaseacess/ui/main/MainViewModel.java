package roomcontent.com.roomdatabaseacess.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import roomcontent.com.roomdatabaseacess.model.Word;

public class MainViewModel extends AndroidViewModel {
    private static final String TAG = "MainViewModel";
    // TODO: Implement the ViewModel
    private LiveData<List<Word>> mAllWords;

    public MainViewModel(@NonNull Application application) {
        super(application);

    }

    LiveData<List<Word>> getAllWords() {

//        Uri kUri = Uri.parse("content://roomcontent.com.roomdbsample.com.provider.StubProvider.");
//        Cursor cursor = getApplication().getContentResolver().query(kUri, null, null, null, null);
//
//        if(cursor!=null)
//        Log.d(TAG, "Count: " + cursor.getCount());

        return mAllWords;
    }
}
