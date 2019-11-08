package pk.com.aptech.a2_adapterview_and_adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class AutoCompleteDemo extends AppCompatActivity implements TextWatcher {

    private AutoCompleteTextView edit;
    private TextView selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocomplete_demo);

        selection=(TextView)findViewById(R.id.selection);
        edit=(AutoCompleteTextView)findViewById(R.id.edit);

        edit.addTextChangedListener(this);

        String[] items = getResources().getStringArray(R.array.items);

        edit.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                items));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        selection.setText(edit.getText());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
