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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.userRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserAdapter = new UserAdapter(this, mDataList);
        mRecyclerView.setAdapter(mUserAdapter);


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
                mRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        //Map<String,Object> data = (Map<String, Object>) dataSnapshot.getValue();
                        //Log.d("myTag", "onChildAdded: Name: "+data.get("name"));
                        //Log.d("myTag", "onChildAdded: Age: "+data.get("age"));

                        User user = dataSnapshot.getValue(User.class);
                        mDataList.add(user);
                        mUserAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
