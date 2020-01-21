package pk.com.aptech.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Intent registerIntent = new Intent(SplashActivity.this, RegisterActivity.class);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(registerIntent);
                finish();
            }
        }, 3000);

    }
}
