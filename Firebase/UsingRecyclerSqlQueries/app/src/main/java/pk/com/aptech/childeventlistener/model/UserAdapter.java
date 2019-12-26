package pk.com.aptech.childeventlistener.model;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import pk.com.aptech.childeventlistener.DetailsActivity;
import pk.com.aptech.childeventlistener.R;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context mContext;
    private List<User> mDataList;
    public static final String USER_KEY = "user_key";

    public UserAdapter(Context context, List<User> mDataList) {
        this.mContext = context;
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final User user = mDataList.get(position);
        holder.textView.setText(user.getName()+ " | "+ user.getAge());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = user.getUid();
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra(USER_KEY,uid);
                mContext.startActivity(intent);
            }
        });

        holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                String uid = user.getUid();

                Task<Void> task;
                task = FirebaseDatabase.getInstance().getReference().child(uid).setValue(null);
                task.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                });

                return  true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class  MyViewHolder extends  RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

}
