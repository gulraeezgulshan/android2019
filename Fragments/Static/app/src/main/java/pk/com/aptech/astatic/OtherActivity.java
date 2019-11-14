package pk.com.aptech.astatic;

import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends LifecycleLoggingActivity {
    public static final String EXTRA_MESSAGE="msg";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        TextView tv=(TextView)findViewById(R.id.msg);
        tv.setText(getIntent().getStringExtra(EXTRA_MESSAGE));
    }
}
