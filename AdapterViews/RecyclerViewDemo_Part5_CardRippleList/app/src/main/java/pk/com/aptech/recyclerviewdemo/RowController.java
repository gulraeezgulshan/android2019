package pk.com.aptech.recyclerviewdemo;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RowController extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView label=null;
    TextView size=null;
    ImageView icon=null;
    String template=null;

    RowController(View row) {
        super(row);

        label=(TextView)row.findViewById(R.id.label);
        size=(TextView)row.findViewById(R.id.size);
        icon=(ImageView)row.findViewById(R.id.icon);

        template=size.getContext().getString(R.string.size_template);

        row.setOnClickListener(this);

     /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            row.setOnTouchListener(new View.OnTouchListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    v
                            .findViewById(R.id.row_content)
                            .getBackground()
                            .setHotspot(event.getX(), event.getY());
                    return(false);
                }
            });
        }*/

   }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(),
                String.format("Clicked on position %d", getAdapterPosition()),
                Toast.LENGTH_SHORT).show();
    }

    void bindModel(String item) {
        label.setText(item);
        size.setText(String.format(template, item.length()));

        if (item.length()>4) {
            icon.setImageResource(R.drawable.delete);
        }
        else {
            icon.setImageResource(R.drawable.ok);
        }
    }
}
