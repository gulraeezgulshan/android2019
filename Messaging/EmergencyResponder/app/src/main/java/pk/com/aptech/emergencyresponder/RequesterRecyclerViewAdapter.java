package pk.com.aptech.emergencyresponder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class RequesterRecyclerViewAdapter  extends RecyclerView.Adapter<RequesterRecyclerViewAdapter.ViewHolder> {

    private List<String> mNumbers;

    public RequesterRecyclerViewAdapter(List<String> numbers ) {
        mNumbers = numbers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_requester,
                        parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.number = mNumbers.get(position);
        holder.numberView.setText(mNumbers.get(position));
    }

    @Override
    public int getItemCount() {
        if (mNumbers != null)
            return mNumbers.size();

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView numberView;
        public String number;

        public ViewHolder(View view) {
            super(view);
            numberView = view.findViewById(R.id.list_item_requester);
        }

        @Override
        public String toString() {
            return number;
        }
    }
}
