package pk.com.aptech.studentapp.ui.student;


import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Magnifier;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.security.Permission;
import java.util.Calendar;
import java.util.Collection;

import io.opencensus.internal.Utils;
import pk.com.aptech.studentapp.BuildConfig;
import pk.com.aptech.studentapp.MainActivity;
import pk.com.aptech.studentapp.R;
import pk.com.aptech.studentapp.model.Student;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentAddModifyFragment extends Fragment {

    private FirebaseFirestore db;
    private DocumentReference mRef;
    private Button save;
    private static EditText firstname,lastname,fathername,mothername,cnic,dob, placeofbirth, contact1, contact2, gcontact1, gcontact2, address, email;

    private ImageButton close,uploadphoto ;
    private ProgressBar progressBar;
    private static ImageView pickedPhoto;

    static  int reqCode = 1, REQCODE = 2;

    Uri pickedImgUri;

    private TextWatcher textWatcher;

    private StorageReference mStorageRef;

    public StudentAddModifyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_students_add_modify, container, false);

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        firstname = view.findViewById(R.id.etFirstName_students);
        firstname.addTextChangedListener(textWatcher);

        lastname = view.findViewById(R.id.etLastName_students);
        lastname.addTextChangedListener(textWatcher);

        fathername = view.findViewById(R.id.etFatherName_students);
        fathername.addTextChangedListener(textWatcher);

        cnic = view.findViewById(R.id.etCNIC_students);

        dob = view.findViewById(R.id.etDOB_students);
        dob.addTextChangedListener(textWatcher);

        placeofbirth = view.findViewById(R.id.etPlaceOfBirth_students);

        contact1 = view.findViewById(R.id.etContact1_students);
        contact1.addTextChangedListener(textWatcher);

        contact2 = view.findViewById(R.id.etContact2_students);

        gcontact1 = view.findViewById(R.id.etGContact1_students);
        gcontact1.addTextChangedListener(textWatcher);

        gcontact2 = view.findViewById(R.id.etGContact2_students);
        address = view.findViewById(R.id.etAddress_students);

        email = view.findViewById(R.id.etEmail_students);
        email.addTextChangedListener(textWatcher);

        pickedPhoto = view.findViewById(R.id.ivProfilePhoto_students);
        uploadphoto = view.findViewById(R.id.ibUploadPhoto_students);

        progressBar = view.findViewById(R.id.pb_students);

        close = view.findViewById(R.id.ibClose_student);

        db = FirebaseFirestore.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference().child("students_photos");
       // mRef = db.document("students");

        save = view.findViewById(R.id.btnSave_students);

        return  view;
    }

    private void checkInputs() {

        if(isEmpty())
        {
            save.setEnabled(false);
        }
        else
        {
            save.setEnabled(true);
        }

    }


    protected static boolean isEmpty()
    {

        Boolean isValid = (firstname.getText().toString().isEmpty()
                        || fathername.getText().toString().isEmpty()
                        || dob.getText().toString().isEmpty()
                        || contact1.getText().toString().isEmpty()
                        || gcontact1.getText().toString().isEmpty()
                        || email.getText().toString().isEmpty());

        return isValid;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getActivity(), MainActivity.class);
                startActivity(home);
                getActivity().finish();
            }
        });

        uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(getActivity(), "testing", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= 22)
                {
                    checkAndRequestPermission();
                }else
                {
                    openGallery();
                }

            }
        });

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "testing", Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();

                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dob.setText(day +"-" + (month+1) + "-" + year);
                    }
                },year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String _firstname = firstname.getText().toString().trim().toUpperCase();
                final String _lastname = lastname.getText().toString().trim().toUpperCase();
                final String _fathername = fathername.getText().toString().trim().toUpperCase();
                final String _mothername = mothername.getText().toString().trim().toUpperCase();
                final String _cnic = cnic.getText().toString();
                final String _dob = dob.getText().toString();
                final String _placeofbirth = placeofbirth.getText().toString();
                final String _contact1 = contact1.getText().toString();
                final String _contact2 = contact2.getText().toString();
                final String _gcontact1 = gcontact1.getText().toString();
                final String _gcontact2 = gcontact2.getText().toString();
                final String _address = address.getText().toString();
                final String _email = email.getText().toString();
                final Drawable _photdrawable = pickedPhoto.getDrawable();

                if (isEmpty() || _photdrawable == null )
                {
                    //save.setEnabled(false);
                    Toast.makeText( getContext(), "Please provide required fields !", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    progressBar.setVisibility(View.VISIBLE);
                    save.setEnabled(false);
                    final StorageReference imageFilePath  = mStorageRef.child(pickedImgUri.getLastPathSegment());

                    imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    Student student = new Student(_firstname, _lastname, _fathername, _mothername, _cnic, _dob, _placeofbirth, _contact1, _contact2, _gcontact1, _gcontact2, _address, _email, String.valueOf(uri));

                                    db.collection("students").document().set(student)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(getContext(), "Saved Succesfully", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {


                                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }) .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                }

            }
        });
    }


    private void checkAndRequestPermission() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                Toast.makeText(getContext(), "Please accept for required permission !", Toast.LENGTH_SHORT).show();

            }
            else
            {
                ActivityCompat.requestPermissions(
                        getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        reqCode);
            }
        }
        else
        {
            openGallery();
        }
    }

    private void openGallery() {

        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,REQCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQCODE && data !=null)
        {
            pickedImgUri = data.getData();
            pickedPhoto.setImageURI(pickedImgUri);
        }
    }
}
