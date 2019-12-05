package pk.com.aptech.usingcontentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    final private int REQUEST_READ_CONTACTS = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_READ_CONTACTS);
        } else{
            ListContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode
            , String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_CONTACTS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ListContacts();
                } else {
                    Toast.makeText(MainActivity.this
                            , "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode
                        , permissions, grantResults);
        }
    }

    protected void ListContacts(){
         Uri allContacts = Uri.parse("content://contacts/people");
         //Uri allContacts = ContactsContract.Contacts.CONTENT_URI;


        String[] projection = new String[]
                {ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME};

        Cursor c;
        CursorLoader cursorLoader = new CursorLoader(
                this,
                allContacts,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'R%'",
                null,
                null); //ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        c = cursorLoader.loadInBackground();

        String[] columns = new String[]{
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts._ID};

        int[] views = new int[]{R.id.contactName, R.id.contactID};

        SimpleCursorAdapter adapter;

        adapter = new SimpleCursorAdapter(
                this, R.layout.activity_main, c, columns, views,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        PrintContacts(c);
        this.setListAdapter(adapter);
    }

    private void PrintContacts(Cursor c)
    {
        if (c.moveToFirst()) {
            do{
                String contactID = c.getString(c.getColumnIndex(
                        ContactsContract.Contacts._ID));

                String contactDisplayName =
                        c.getString(c.getColumnIndex(
                                ContactsContract.Contacts.DISPLAY_NAME));

                Log.v("Content Providers", contactID + ", " +
                        contactDisplayName);


                //---get phone number---
                Cursor phoneCursor =
                        getContentResolver().query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " +
                                        contactID, null, null);
                while (phoneCursor.moveToNext()) {
                    Log.v("Content Providers",
                            phoneCursor.getString(
                                    phoneCursor.getColumnIndex(
                                            ContactsContract.CommonDataKinds.Phone.NUMBER)));
                }
                phoneCursor.close();
            } while (c.moveToNext());
        }
    }
}
