package pk.com.aptech.actionbar1_nativedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends ListActivity {

    private static final String[] items= { "lorem", "ipsum", "dolor",
            "sit", "amet", "consectetuer", "adipiscing", "elit", "morbi",
            "vel", "ligula", "vitae", "arcu", "aliquet", "mollis", "etiam",
            "vel", "erat", "placerat", "ante", "porttitor", "sodales",
            "pellentesque", "augue", "purus" };

    private ArrayList<String> words=null;
    private ArrayAdapter<String> adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().setDisplayShowHomeEnabled(true);
        initAdapter(); //Refactoring
    }

    //Applying the Resource

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action, menu);
        return(super.onCreateOptionsMenu(menu));
    }

    //Responding to Events
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.add:
                addWord();
                return(true);

            case R.id.reset:
                initAdapter();
                return(true);

            case R.id.about:
                Toast.makeText(this, R.string.about_toast,
                        Toast.LENGTH_LONG)
                        .show();
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }

    private void initAdapter() {
        words=new ArrayList<String>();

        for (int i=0;i<5;i++) {
            words.add(items[i]);
        }

        //words.addAll(Arrays.asList(items).subList(0, 5));

        adapter=
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        words);

        setListAdapter(adapter);
    }


    private void addWord() {
        if (adapter.getCount()<items.length) {
            adapter.add(items[adapter.getCount()]);
        }
    }
}
