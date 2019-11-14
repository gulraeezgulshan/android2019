package pk.com.aptech.astatic;

import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends LifecycleLoggingActivity {
    public static final String EXTRA_MESSAGE="msg";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content,
                            new OtherFragment()).commit();
        }
    }
}
