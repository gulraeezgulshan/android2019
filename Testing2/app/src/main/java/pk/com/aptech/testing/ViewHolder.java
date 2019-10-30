package pk.com.aptech.testing;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class ViewHolder {
    ImageView icon = null;
    TextView size = null;

    ViewHolder(View row)
    {
        this.icon = (ImageView) row.findViewById(R.id.icon);
        this.size = (TextView) row.findViewById(R.id.size);
    }
}
