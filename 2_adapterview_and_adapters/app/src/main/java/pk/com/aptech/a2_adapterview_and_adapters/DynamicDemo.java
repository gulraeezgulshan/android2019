package pk.com.aptech.a2_adapterview_and_adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DynamicDemo extends ListActivity {

    private static final String[] items = {"lorem", "ipsum", "dolor",
            "sit", "amet",
            "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue", "purus"};

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setListAdapter(new IconicAdapter());
    }

    class IconicAdapter extends ArrayAdapter<String> {
        IconicAdapter() {
            super(DynamicDemo.this, R.layout.row, R.id.label, items);
        }

        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            View row = super.getView(position, convertView, parent);
            ImageView icon = (ImageView) row.findViewById(R.id.icon);

            if (items[position].length() > 4) {
                icon.setImageResource(R.drawable.delete);
            } else {
                icon.setImageResource(R.drawable.ok);
            }

            TextView size = (TextView) row.findViewById(R.id.size);

            size.setText(String.format(getString(R.string.size_template), items[position].length()));

            return (row);
        }
    }
}
