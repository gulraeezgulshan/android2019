package pk.com.aptech.adapterviewsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewDemo2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_demo);

        //Sample Data Model
        String[] items = {"Pakistan", "India", "Nepal", "Cow", "Oman",
                "china", "Shamraiz", "Jupiter", "PTI", "Laptop",
                "Aptech", "Jabbo","Aptech", "Jabbo","Aptech", "Jabbo",
                "Aptech", "Jabbo","Aptech", "Jabbo"};

        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, items);

        ListView lst = (ListView)findViewById(R.id.myListView);
        lst.setAdapter(aa);

    }


}
