package pk.com.aptech.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class RotationFragment extends Fragment implements
        View.OnClickListener {
    static final int PICK_REQUEST=1337;
    Uri contact=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        View result=inflater.inflate(R.layout.activity_main, parent, false);
        result.findViewById(R.id.pick).setOnClickListener(this);

        View v=result.findViewById(R.id.view);

        v.setOnClickListener(this);
        v.setEnabled(contact != null);

        return(result);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        if (requestCode == PICK_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                contact=data.getData();
                getView().findViewById(R.id.view).setEnabled(true);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.pick) {
            pickContact(v);
        }
        else {
            viewContact(v);
        }
    }

    public void pickContact(View v) {
        Intent i= new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(i, PICK_REQUEST);
    }

    public void viewContact(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, contact));
    }
}
