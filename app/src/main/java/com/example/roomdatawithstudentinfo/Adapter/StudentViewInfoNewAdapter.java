package com.example.roomdatawithstudentinfo.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatawithstudentinfo.Database.StudentDatabase;
import com.example.roomdatawithstudentinfo.Model.Student;
import com.example.roomdatawithstudentinfo.R;

import java.util.List;

// this is for extends Recycler view adapter..

public class StudentViewInfoNewAdapter extends
        RecyclerView.Adapter<StudentViewInfoNewAdapter.StudentInfoViewHolder> {


    private Context context;
    private List<Student> studentViewInfoList;
    private StudentEditItemClickListner editItemClickListner;
    // private StudentInfoItemClcikListner listner;


    public StudentViewInfoNewAdapter(Context context, List<Student> studentViewInfoList) {
        this.context = context;
        this.studentViewInfoList = studentViewInfoList;
        this.editItemClickListner = (StudentEditItemClickListner) context;
    }

    @NonNull
    @Override
    public StudentInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.student_info_details_recycler_row,
                        parent, false);
        StudentInfoViewHolder holder = new StudentInfoViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentInfoViewHolder holder, final int position) {

        final Student studentViewInfoNewModel = studentViewInfoList.get(position);

        if (studentViewInfoNewModel != null) {

            holder.stName.setText("Name: " + studentViewInfoNewModel.getName());
            holder.stAddress.setText("Address: " + studentViewInfoNewModel.getAdress());
            holder.stRoll.setText("Roll: " + String.valueOf(studentViewInfoNewModel.getRoll()));
            holder.stSpinnerClassShow.setText(String.valueOf(studentViewInfoNewModel.getSpinner()));

        }


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context, view);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.show();


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {

                            case R.id.row_itemDelete_id:

                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("Delete Student ID");
                                builder.setMessage("Do you want to Delete this Student Info ?");
                                builder.setIcon(R.drawable.question);

                                builder.setPositiveButton("Delete",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {


                                                int deletedRow = StudentDatabase.getInstance(context)
                                                        .getStudentDao()
                                                        .deleteStudent(studentViewInfoNewModel);
                                                if (deletedRow > 0) {
                                                    Toast.makeText(context, " deleted",
                                                            Toast.LENGTH_SHORT).show();
                                                    // collection theke data del korte hobe.
                                                    studentViewInfoList.remove(studentViewInfoNewModel);
                                                    notifyDataSetChanged();
                                                } else {

                                                    Toast.makeText(context, "Couldn't Deleted",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                                builder.setNegativeButton("Cancel", null);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                                break;


                            case R.id.row_itemEdit_id:

                                long id = studentViewInfoNewModel.getStudentID();
                                editItemClickListner.onStudentEditItemClicked(id);
                                Toast.makeText(context, "Student Update Info",
                                        Toast.LENGTH_SHORT).show();

                                break;
                        }

                        return false;
                    }
                });


                return false;
            }
        });


    }


    // studentInfoViewHolder this is class extends to Recycler view..*/


    @Override
    public int getItemCount() {
        return studentViewInfoList.size();
    }

    public class StudentInfoViewHolder extends RecyclerView.ViewHolder {

        TextView stName, stRoll, stSpinnerClassShow, stAddress;
        ConstraintLayout constraintLayout;

        public StudentInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            stName = itemView.findViewById(R.id.textName_id);
            stRoll = itemView.findViewById(R.id.textRoll_id);
            stSpinnerClassShow = itemView.findViewById(R.id.textSpinnerClass_id);
            stAddress = itemView.findViewById(R.id.textAdress_id);
            constraintLayout = itemView.findViewById(R.id.cosntrint_layout_id);

        }
    }

    public interface StudentEditItemClickListner {

        void onStudentEditItemClicked(long id);


    }


}
