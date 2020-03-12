package pk.com.aptech.studentapp.ui.student;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pk.com.aptech.studentapp.R;
import pk.com.aptech.studentapp.model.Student;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.RowController> {


    Context context;
    ArrayList<Student> students;


    public StudentListAdapter(Context c, ArrayList<Student> s)
    {
        this.context = c;
        this.students = s;
    }
    @NonNull
    @Override
    public RowController onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RowController(LayoutInflater.from(context).inflate(R.layout.row_student,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull RowController holder, int position) {
        holder.firstName.setText(students.get(position).getFirstname());
        holder.mobile.setText(students.get(position).getContact1());;
        holder.email.setText(students.get(position).getEmail());


        if (!students.get(position).getPhotouri().isEmpty())
        {
            Picasso.with(context).load(students.get(position).getPhotouri()).into(holder.profilePhoto);
        }
        else
        {

        }

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class RowController extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView firstName = null, email =null, mobile=null;

        ImageView profilePhoto;

         RowController(@NonNull View row) {
            super(row);

            firstName = (TextView)row.findViewById(R.id.tvFirstName);
            mobile = (TextView)row.findViewById(R.id.tvMobile);
            email = (TextView)row.findViewById(R.id.tvEmail);
            profilePhoto = (ImageView)row.findViewById(R.id.ivProfilePhoto);
        }

        @Override
        public void onClick(View v) {

        }

    }

    public static class HorizontalDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable divider;

        public HorizontalDividerItemDecoration(Drawable divider) {
            this.divider=divider.mutate();
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left=parent.getPaddingLeft();
            int right=parent.getWidth()-parent.getPaddingRight();

            int childCount=parent.getChildCount();

            for (int i=0; i<childCount-1; i++) {
                View child=parent.getChildAt(i);
                RecyclerView.LayoutParams params=
                        (RecyclerView.LayoutParams)child.getLayoutParams();

                int top=child.getBottom()+params.bottomMargin;
                int bottom=top+divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }
}
