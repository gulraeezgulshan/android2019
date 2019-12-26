package pk.com.aptech.childeventlistener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import pk.com.aptech.childeventlistener.model.User;
import pk.com.aptech.childeventlistener.model.UserAdapter;

public class DetailsActivity extends AppCompatActivity {

    private TextView mShowText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mShowText = (TextView) findViewById(R.id.txtShowText);

        String uid = getIntent().getStringExtra(UserAdapter.USER_KEY);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                mShowText.setText(user.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
