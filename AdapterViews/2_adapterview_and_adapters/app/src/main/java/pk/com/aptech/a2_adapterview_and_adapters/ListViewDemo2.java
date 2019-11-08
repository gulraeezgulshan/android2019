package pk.com.aptech.a2_adapterview_and_adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewDemo2 extends ListActivity {

    private TextView selection;

    String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_demo2);

        final Resources resources = getResources();
        items = resources.getStringArray(R.array.items);

        /*setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                items));*/

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice,
                items));
        selection=(TextView)findViewById(R.id.selection);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        selection.setText(items[position]);
    }
}
