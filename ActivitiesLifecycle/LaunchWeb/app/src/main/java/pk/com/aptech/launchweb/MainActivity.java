package pk.com.aptech.launchweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMe(View v) {
        EditText url=(EditText)findViewById(R.id.url);

        Intent i = new Intent(this, getClass());

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url.getText().toString())));
    }
}
