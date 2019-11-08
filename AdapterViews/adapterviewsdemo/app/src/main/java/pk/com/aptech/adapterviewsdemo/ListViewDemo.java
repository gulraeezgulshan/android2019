package pk.com.aptech.adapterviewsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static android.widget.AdapterView.*;



public class ListViewDemo extends ListActivity {

   /* String[] items = {"Pakistan", "India", "Nepal", "Cow", "Oman",
            "china", "Shamraiz", "Jupiter", "PTI", "Laptop",
            "Aptech", "Jabbo","Aptech", "Jabbo","Aptech", "Jabbo",
            "Aptech", "Jabbo","Aptech", "Jabbo"};*/

   String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Resources r = getResources();
        items = r.getStringArray(R.array.items);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                items);

        setListAdapter(aa);

        ListView lv = getListView();
        lv.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(v.getContext(), items[position].toString(),Toast.LENGTH_SHORT).show();
    }
}
