package pk.com.aptech.ecommerceapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private TextView alreadyHaveAnAccount;
    FrameLayout parentFrameLayout;

    private EditText email;
    private EditText fullName;
    private  EditText password;
    private  EditText confirmPassword;

    private ImageButton closeButton;
    private Button signupButton;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);
        alreadyHaveAnAccount = view.findViewById(R.id.signup_tvAlreadyHaveAccount);
        parentFrameLayout = getActivity().findViewById(R.id.register_frameLayout);

        email = view.findViewById(R.id.signup_etEmail);
        fullName = view.findViewById(R.id.signup_etFullName);
        password = view.findViewById(R.id.signup_etPassword);
        confirmPassword = view.findViewById(R.id.signup_etPasswordConfirm);

        closeButton = view.findViewById(R.id.signin_ibtnClose);
        signupButton = view.findViewById(R.id.signup_btnSignUp);

        progressBar = view.findViewById(R.id.signup_progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTargetFragment(new SiginInFragment());
            }
        });


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                checkInputs();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fullName.addTextChangedListener(new TextWatcher() {
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
        });
        password.addTextChangedListener(new TextWatcher() {
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
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
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
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkEmailAndPassword();
            }
        });
    }

    private void checkEmailAndPassword() {
        if(email.getText().toString().matches(emailPattern))
        {
            if(password.getText().toString().equals(confirmPassword.getText().toString()))
            {
                progressBar.setVisibility(View.VISIBLE);
                signupButton.setEnabled(false);
                firebaseAuth.
                        createUserWithEmailAndPassword(
                                email.getText().toString(),
                                password.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(mainIntent);
                                    getActivity().finish();
                                }
                                else
                                {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    signupButton.setEnabled(true);
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
            else
            {
                confirmPassword.setError("Password does not match");
            }
        }
        else
        {
            email.setError("Invalid Email");
        }
    }

    private void checkInputs() {

        if(!TextUtils.isEmpty(email.getText()))
        {
            if(!TextUtils.isEmpty(fullName.getText()))
            {
                if (!TextUtils.isEmpty(password.getText()) && password.length()>=8)
                {
                    if(!TextUtils.isEmpty(confirmPassword.getText()))
                    {
                        signupButton.setEnabled(true);
                    }
                    else
                    {
                        signupButton.setEnabled(false);
                    }

                }
                else
                {
                    signupButton.setEnabled(false);
                }
            }
            else
            {
                signupButton.setEnabled(false);
            }
        }
        else
        {
            signupButton.setEnabled(false);
        }
    }

    private void setTargetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}
