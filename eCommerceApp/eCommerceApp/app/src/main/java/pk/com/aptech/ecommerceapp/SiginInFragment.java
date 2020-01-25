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

import static pk.com.aptech.ecommerceapp.RegisterActivity.onResetPasswordFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SiginInFragment extends Fragment {


    public SiginInFragment() {
        // Required empty public constructor
    }

    private TextView dontHaveAnAccount;
    private FrameLayout parentFrameLayout;

    private EditText email;
    private EditText password;

    private ImageButton closeBtn;
    private Button signinBtn;

    private TextView forgotPassword;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"; //regex

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sigin_in, container, false);

        dontHaveAnAccount = view.findViewById(R.id.signin_tvDontHaveAccount);
        parentFrameLayout = getActivity().findViewById(R.id.register_frameLayout);

        email = view.findViewById(R.id.signin_etEmail);
        password = view.findViewById(R.id.signin_etPassword);
        closeBtn = view.findViewById(R.id.signin_ibtnClose);
        signinBtn = view.findViewById(R.id.signin_btnSignIn);
        forgotPassword = view.findViewById(R.id.signin_tvForgotPassword);
        progressBar = view.findViewById(R.id.signin_progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               setTargetFragment(new SignUpFragment());
           }
       });

       closeBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mainIntent();
           }
       });

       email.addTextChangedListener(new TextWatcher() {
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

       signinBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               checkEmailAndPassword();
           }
       });

       forgotPassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               onResetPasswordFragment = true;
               setTargetFragment(new ResetPasswordFragment());
           }
       });
    }

    private void checkEmailAndPassword() {
        if (email.getText().toString().matches(emailPattern))
        {
            if (password.length() >= 8)
            {
                signinBtn.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful())
                                {
                                    mainIntent();
                                }
                                else
                                {
                                    signinBtn.setEnabled(true);
                                    progressBar.setVisibility(View.GONE);
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
            else
            {
                Toast.makeText(getActivity(), "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getActivity(), "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
        }
    }

    private void mainIntent() {
        Intent mainIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(mainIntent);
        getActivity().finish();
    }

    private void checkInputs() {

        if (!TextUtils.isEmpty(email.getText()))
        {
            if(!TextUtils.isEmpty(password.getText()))
            {
                signinBtn.setEnabled(true);

            }
            else
            {
                signinBtn.setEnabled(false);
            }

        }
        else
        {
            signinBtn.setEnabled(false);
        }
    }

    private void setTargetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}
