package pk.com.aptech.childeventlistener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import pk.com.aptech.childeventlistener.model.User;
import pk.com.aptech.childeventlistener.model.UserAdapter;

public class MainActivity extends AppCompatActivity {

    private Button mReadData, mRunCode;
    private EditText mInputName, mInputAge;

    private FirebaseDatabase mDataBase;
    private DatabaseReference mRef;

    private RecyclerView mRecyclerView;
    private UserAdapter mUserAdapter;

    private List<User> mDataList;
    private ValueEventListener mQueryListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.userRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserAdapter = new UserAdapter(this, mDataList);
        mRecyclerView.setAdapter(mUserAdapter);

        mQueryListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    User user = snapshot.getValue(User.class);
                    mDataList.add(user);
                }
                mUserAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        mDataBase = FirebaseDatabase.getInstance();
        mRef = mDataBase.getReference();

        mReadData = (Button) findViewById(R.id.btnReadData);
        mRunCode = (Button) findViewById(R.id.btnRunCode);

        mInputName = (EditText) findViewById(R.id.txtName);
        mInputAge = (EditText) findViewById(R.id.txtAge);

        this.mRunCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mInputName.getText().toString();
                int age = Integer.parseInt(mInputAge.getText().toString());

                Map<String, Object> insertValue = new HashMap<>();
                insertValue.put("name", name);
                insertValue.put("age", age);

                String key = mRef.push().getKey();
                mRef.child(key).setValue(insertValue);
            }
        });

        this.mReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //select * from users order by name
                Query q1 = mRef.orderByChild("name");


                //select * from user where age>30
                Query q2 = mRef.orderByChild("age").endAt(30);

                //select * from user where age<30
                Query q3 = mRef.orderByChild("age").startAt(30);
                q1.addValueEventListener(mQueryListener);
            }
        });

    }
}
