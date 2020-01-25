package pk.com.aptech.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null)
        {
            final Intent registerIntent = new Intent(SplashActivity.this, RegisterActivity.class);
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    startActivity(registerIntent);
                    finish();
                }
            }, 3000);
        }
        else
        {
            final Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    startActivity(mainIntent);
                    finish();
                }
            }, 3000);
        }
    }
}
