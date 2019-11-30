package pk.com.aptech.orientationchanges;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.d("StateInfo", "Landscape");
        }else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.d("StateInfo", "Portrait");
        }

        Log.d("StateInfo", "onCreate");
    }

    @Override
    public void onStart() {
        Log.d("StateInfo", "onStart");
        super.onStart();
    }
    @Override
    public void onResume() {
        Log.d("StateInfo", "onResume");
        super.onResume();
    }
    @Override
    public void onPause() {
        Log.d("StateInfo", "onPause");
        super.onPause();
    }
    @Override
    public void onStop() {
        Log.d("StateInfo", "onStop");
        super.onStop();
    }
    @Override
    public void onDestroy() {
        Log.d("StateInfo", "onDestroy");
        super.onDestroy();
    }
    @Override
    public void onRestart() {
        Log.d("StateInfo", "onRestart");
        super.onRestart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("ID", "1234567890");
        Log.d("StateInfo", "SaveInstance");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String ID = savedInstanceState.getString("ID");
        Log.d("StateInfo", ID.toString());
    }
}
