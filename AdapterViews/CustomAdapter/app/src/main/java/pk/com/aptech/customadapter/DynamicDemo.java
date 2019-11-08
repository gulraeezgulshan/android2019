package pk.com.aptech.customadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class DynamicDemo extends ListActivity {


    private static final String[] items = {"lorem", "ipsum", "dolor",
            "sit", "amet",
            "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue", "purus"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setListAdapter(new AptechAdapter());
    }

    private class AptechAdapter extends ArrayAdapter<String> {
        public AptechAdapter() {
            super(DynamicDemo.this, R.layout.row, R.id.lblStatus, items);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row =  super.getView(position, convertView, parent);

            ImageView icon = (ImageView) row.findViewById(R.id.icon);

            if (items[position].length()>4)
            {
                icon.setImageResource(R.drawable.ok);
            }
            else
            {
                icon.setImageResource(R.drawable.delete);
            }

            TextView size = (TextView) row.findViewById(R.id.lblSize);
            //size.setText("Size : " + getString(items[position].length()) );

            String sizeText = String.format("Size: %s", items[position].length());
            size.setText(sizeText);
            return row;
        }
    }
}
