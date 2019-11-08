package pk.com.aptech.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends RecyclerViewActivity {

    private static final String[] items={"lorem", "ipsum", "dolor",
            "sit", "amet",
            "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue", "purus"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutManager(new LinearLayoutManager(this));
        setAdapter(new IconicAdapter());
    }

    private class IconicAdapter extends RecyclerView.Adapter<RowController> {
        @NonNull
        @Override

        public RowController onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return(new RowController(getLayoutInflater()
                    .inflate(R.layout.row, parent, false)));
        }

        @Override
        public void onBindViewHolder(@NonNull RowController holder, int position) {
            holder.bindModel(items[position]);
        }

        @Override
        public int getItemCount() {
            return(items.length);
        }
    }

}
