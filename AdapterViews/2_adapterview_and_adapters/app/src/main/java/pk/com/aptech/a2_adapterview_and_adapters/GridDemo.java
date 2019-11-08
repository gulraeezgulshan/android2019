package pk.com.aptech.a2_adapterview_and_adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class GridDemo extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String[] items;
    private TextView selection;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_demo);

        selection=(TextView)findViewById(R.id.selection);

        final Resources resources = getResources();
        items = resources.getStringArray(R.array.items);

        GridView g=(GridView) findViewById(R.id.grid);
        g.setAdapter(new ArrayAdapter<String>(this, R.layout.cell,items));
        g.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selection.setText(items[position]);
    }
}
