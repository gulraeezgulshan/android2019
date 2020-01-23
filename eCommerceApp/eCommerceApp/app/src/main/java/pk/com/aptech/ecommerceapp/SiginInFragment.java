package pk.com.aptech.ecommerceapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;


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

       forgotPassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               setTargetFragment(new ResetPasswordFragment());
           }
       });
    }

    private void setTargetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}
