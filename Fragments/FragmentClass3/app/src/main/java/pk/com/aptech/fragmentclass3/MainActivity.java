package pk.com.aptech.fragmentclass3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager manager = getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.actionadd1:
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.fragmentcontainer1, new MyListFragment());
                transaction.commit();
                break;
            case R.id.actionadd2:
                transaction = manager.beginTransaction();
                transaction.add(R.id.fragmentcontainer2, new DetailFragment());
                transaction.commit();
                break;

            default:
                break;
        }
        return true;
    }
}
