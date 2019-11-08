package pk.com.aptech.a2_adapterview_and_adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewDemo1 extends ListActivity {

    //ListView without layout file
    private static final String[] items={"lorem", "ipsum", "dolor",
            "sit", "amet",
            "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue", "purus"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      /*  ArrayAdapter<String> aa1 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                items);
        setListAdapter(aa1);*/

        // For multiple choice options
        ArrayAdapter<String> aa2 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                items);
        setListAdapter(aa2);

        //Setting choice mode to multiple i.e checkbox will be appeared
        final ListView lv = getListView();
        lv.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(
                this,
                items[position],
                Toast.LENGTH_SHORT).show();
    }
}
