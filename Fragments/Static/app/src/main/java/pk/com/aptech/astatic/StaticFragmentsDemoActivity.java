package pk.com.aptech.astatic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StaticFragmentsDemoActivity extends
        LifecycleLoggingActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void showOther(View v) {
        Intent other=new Intent(this, OtherActivity.class);
        other.putExtra(OtherActivity.EXTRA_MESSAGE,
                getString(R.string.other));
        startActivity(other);
    }
}
