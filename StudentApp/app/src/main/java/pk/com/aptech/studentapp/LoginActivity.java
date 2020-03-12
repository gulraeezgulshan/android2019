package pk.com.aptech.studentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

public class LoginActivity extends AppCompatActivity {


    private FrameLayout frameLayout ;
    public static boolean isAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        frameLayout = findViewById(R.id.login_framelayout);

        if (isAdmin)
        {
            setFragment(new AdminLoginFragment());
            isAdmin = false;
        }
        else
        {
            setFragment(new UserLoginFragment());
        }

    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //
        }
        return super.onKeyDown(keyCode, event);
    }
}
