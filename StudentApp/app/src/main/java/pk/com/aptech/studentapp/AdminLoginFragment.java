package pk.com.aptech.studentapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class AdminLoginFragment extends Fragment {



    public AdminLoginFragment() {

    }

    private EditText username;
    private EditText password;
    private Button signin;
    private ImageButton close;

    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    private String emailPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_login, container, false);

        username = view.findViewById(R.id.etUserName_admin);
        password = view.findViewById(R.id.etPassword_admin);
        signin = view.findViewById(R.id.btnLoginIn_admin);
        close = view.findViewById(R.id.ibClose_admin);

        progressBar = view.findViewById(R.id.progressBar_admin);

        mAuth = FirebaseAuth.getInstance();

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                checkEmailAndPassword();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getActivity(), HomeActivity.class);
                startActivity(home);
                getActivity().finish();
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void checkEmailAndPassword() {

        if(username.getText().toString().matches(emailPattern))
        {
            if(password.length()>=8)
            {
                progressBar.setVisibility(View.VISIBLE);
                signin.setEnabled(false);

                mAuth.signInWithEmailAndPassword(username.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Intent main = new Intent(getActivity(),MainActivity.class);
                            startActivity(main);
                            getActivity().finish();
                        }
                        else
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            signin.setEnabled(true);
                            String error = task.getException().getMessage();
                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else
            {
                Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkInput() {
        if (!TextUtils.isEmpty(username.getText()))
        {
            if (!TextUtils.isEmpty(password.getText()))
            {
                signin.setEnabled(true);
            }
            else
            {
                signin.setEnabled(false);
            }
        }
        else
        {
            signin.setEnabled(false);
        }
    }
}
