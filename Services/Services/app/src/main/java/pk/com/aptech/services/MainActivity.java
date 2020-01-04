package pk.com.aptech.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view) {

        startService(new Intent(getBaseContext(), MyService.class));
        //If you are calling this service from an external application, then the call to the startService() method looks like this:
        //startService(new Intent("pk.com.aptech.MyService"));

        //startService(new Intent(getBaseContext(), MyIntentService.class));
    }
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));

       // stopService(new Intent(MainActivity.this, MyIntentService.class));
    }
}
