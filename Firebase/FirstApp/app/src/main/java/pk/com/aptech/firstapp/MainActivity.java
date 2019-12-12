package pk.com.aptech.firstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private static final Object TAG = "myTag";
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    Button btnSave;
    Button btnFetch;
    EditText txtName;
    TextView lblStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnFetch = (Button) findViewById(R.id.btnFetch);
        lblStatus = (TextView)findViewById(R.id.lblStatus);
        txtName = (EditText) findViewById(R.id.txtValue);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = txtName.getText().toString();
                myRef.setValue(value).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,"Data Inserted !", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });



            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        lblStatus.setText(value);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w((String) TAG, "Failed to read value.", databaseError.toException());
                    }
                });
            }
        });


    }


}
