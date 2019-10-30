package pk.com.aptech.testing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class DynamicDemo extends ListActivity {

    private static final String[] items = {"Pakista","Pakista","Pakista","Pakista","Pakista","Pakista"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new IconicAdapter());
    }

    private class IconicAdapter extends ArrayAdapter<String> {
        public IconicAdapter() {
            super(DynamicDemo.this, R.layout.row, R.id.label, items);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = super.getView(position, convertView, parent);

            ViewHolder holder = (ViewHolder) row.getTag();
            if (holder==null)
            {
                holder = new ViewHolder(row);
                row.setTag(holder);
            }

            get
           // ImageView icon = (ImageView) row.findViewById(R.id.icon);
            if (getModel(position).length()>4)
            {
                icon.setImageResource(R.drawable.ok);
            }
            {
                icon.setImageResource(R.drawable.delete);
            }

            TextView size = (TextView) row.findViewById(R.id.size);
            size.setText("Size: "+items[position].length());
            return  row;

        }
    }
}
