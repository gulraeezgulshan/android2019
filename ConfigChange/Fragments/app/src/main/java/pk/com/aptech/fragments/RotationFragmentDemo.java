package pk.com.aptech.fragments;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class RotationFragmentDemo extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportFragmentManager().findFragmentById(android.R.id.content)==null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content,
                            new RotationFragment()).commit();
        }
    }
}
