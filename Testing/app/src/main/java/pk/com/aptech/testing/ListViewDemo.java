package pk.com.aptech.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ChoiceFormat;

public class ListViewDemo extends ListActivity  {


    ListView listView;
    private static final String[] items={"lorem", "ipsum", "dolor",
            "sit", "amet",
            "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue", "purus"};

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,items));
        listView = getListView();
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Toast.makeText(this, items[position], Toast.LENGTH_SHORT).show();
        listView.getCheckedItemPosition();
    }
}
