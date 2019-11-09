package pk.com.aptech.launchweb;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    public static final String URI_EXTRA = "";
    WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);

        browser=(WebView)findViewById(R.id.webkit);

        String uri = getIntent().getStringExtra(URI_EXTRA);
        //final Uri parsedURI = Uri.parse(uri);
        browser.loadUrl(uri);

        //browser.loadUrl(String.valueOf(Uri.parse(getIntent().getStringExtra(URI_EXTRA))));
    }
}
