package roomcontent.com.roomdbsample;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import roomcontent.com.roomdbsample.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ContentResolver cr = getContentResolver();


        try {
            Uri uri=Uri.parse("roomcontent.com.roomdbsample.com.provider.StubProvider.CONTENT_URI");
            Cursor cursor = cr.query(uri, null,
                    null, null, null);
            if (cursor != null) {
                Log.d("MainActivity", "Count is not zero  " + cursor.getCount());
            } else {
                Log.d("MainActivity", "Count is zero  ");
            }
        } catch (Exception e) { // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}
