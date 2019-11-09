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

        Intent uri = new Intent(this, WebActivity.class);
        uri.putExtra(WebActivity.URI_EXTRA, url.getText().toString());

        startActivity(new Intent(uri));
    }
}
