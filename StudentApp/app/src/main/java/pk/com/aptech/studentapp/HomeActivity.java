package pk.com.aptech.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button btnAdminLogin;
    private  Button btnUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnAdminLogin = findViewById(R.id.btnAdminLogin);
        btnUserLogin = findViewById(R.id.btnUserLogin);

        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                LoginActivity.isAdmin = true;
                startActivity(intent);
                finish();
            }
        });

        btnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                LoginActivity.isAdmin = false;
                startActivity(intent);
                finish();
            }
        });

    }
}
