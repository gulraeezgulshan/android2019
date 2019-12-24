package pk.com.aptech.childeventlistener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.HashMap;
import java.util.Map;

import pk.com.aptech.childeventlistener.model.Person;

public class MainActivity extends AppCompatActivity {

    private Button mReadData, mRunCode;
    private EditText mInputName, mInputAge;

    private FirebaseDatabase mDataBase;
    private DatabaseReference mRef;
    private ChildEventListener mChildListener; //1

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRef.removeEventListener(mChildListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataBase = FirebaseDatabase.getInstance();
        mRef = mDataBase.getReference();

        mReadData = (Button) findViewById(R.id.btnReadData);
        mRunCode = (Button) findViewById(R.id.btnRunCode);

        mInputName = (EditText) findViewById(R.id.txtName);
        mInputAge = (EditText) findViewById(R.id.txtAge);

        mChildListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Person person = dataSnapshot.getValue(Person.class);

                Log.d("mTag", "onChildAdded: name: "+ person.getName());
                Log.d("mTag", "onChildAdded: age: "+ person.getAge());

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
        };

        mRef.addChildEventListener(mChildListener);

        this.mRunCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mInputName.getText().toString();
                int age = Integer.parseInt(mInputAge.getText().toString());

                // Map<String, Object> insertValue = new HashMap<>();

                 //insertValue.put("name", name);
                 //insertValue.put("age", age);

                Person person = new Person(name,age);

                String key = mRef.push().getKey();
                mRef.child(key).setValue(person);

            }
        });

        this.mReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
