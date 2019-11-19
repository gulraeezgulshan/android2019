package pk.com.aptech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class EditorFragment extends Fragment {
    private static final String KEY_POSITION="position";

    static EditorFragment newInstance(int position) {
        EditorFragment frag=new EditorFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.editor, container, false);
        EditText editor=result.findViewById(R.id.editor);
        int position=getArguments().getInt(KEY_POSITION, -1);

        editor.setHint(String.format(getString(R.string.hint), position + 1));

        return(result);
    }
}
