package pk.com.aptech.adapterviewsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class GridViewDemo extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_demo);

        items = getResources().getStringArray(R.array.items);

        GridView g = (GridView)findViewById(R.id.grid);
        g.setAdapter(new ArrayAdapter<String>(
                        this,
                        R.layout.grid_cell,
                        items));

        g.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(
                view.getContext(),
                items[position].toString(),
                Toast.LENGTH_SHORT).show();
    }
}
