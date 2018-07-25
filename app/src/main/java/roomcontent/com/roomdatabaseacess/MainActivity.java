package roomcontent.com.roomdatabaseacess;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import roomcontent.com.roomdatabaseacess.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {
    private static final int LOADER_CHEESES = 1;
    private static final int REQUEST_WRITE_PERMISSION = 786;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        requestPermission();


    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                            "com.example.android.contentprovidersample.provider.READ_DATABASE,com"
                                    + ".example.android.contentprovidersample.provider"
                                    + ".WRITE_DATABASE"},
                    REQUEST_WRITE_PERMISSION);
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
            int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }

}
