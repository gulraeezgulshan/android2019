package pk.com.aptech.studentapp.ui.student;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import pk.com.aptech.studentapp.R;
import pk.com.aptech.studentapp.model.Student;

public class StudentListFragment extends Fragment {

    private FirebaseFirestore db;
    private StudentViewModel studentViewModel;
    private FloatingActionButton fabAdd;
    private RecyclerView studentList;
    ArrayList<Student> students;
    StudentListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_students_list, container, false);

       ///final TextView textView = root.findViewById(R.id.text_students);
        fabAdd = root.findViewById(R.id.fabAdd);

        studentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });

        db = FirebaseFirestore.getInstance();


        students = new ArrayList<Student>();
        studentList = (RecyclerView)root.findViewById(R.id.rvStudentList);
        studentList.setHasFixedSize(true);
        studentList.setLayoutManager(new LinearLayoutManager(getContext()));
        //  Drawable divider = getResources().getDrawable(R.drawable.item_divider);
        // studentList.addItemDecoration(new StudentListAdapter.HorizontalDividerItemDecoration(divider));

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Source source = Source.DEFAULT;

        db.collection("students").get(source).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        Student s = document.toObject(Student.class);
                        students.add(s);
                    }

                    adapter = new StudentListAdapter(getActivity(),students);
                    studentList.setAdapter(adapter);
                }
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(getId(), new StudentAddModifyFragment());
                ft.commit();
                ft.addToBackStack(null);
            }
        });
    }
}