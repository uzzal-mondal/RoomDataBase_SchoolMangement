package com.example.roomdatawithstudentinfo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatawithstudentinfo.Database.StudentDatabase;
import com.example.roomdatawithstudentinfo.Model.ClassCountModel;
import com.example.roomdatawithstudentinfo.Model.Student;
import com.example.roomdatawithstudentinfo.R;

import java.util.List;
import java.util.zip.Inflater;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    // initialize to context , and ArrayList...
    private Context context;
    private List<ClassCountModel> classCountModelList;
    private StudentItemClickListner studentItemClickListner;
    private ItemClickListner itemClickListner;

    public StudentAdapter(Context context, List<ClassCountModel> classCountModelList) {
        this.context = context;
        this.classCountModelList = classCountModelList;
        studentItemClickListner = (StudentItemClickListner) context;
        itemClickListner = (ItemClickListner) context;

    }

    public void clearAll() {
        classCountModelList.clear();
        notifyDataSetChanged();
    }

    public void setListener(ItemClickListner listener) {
        this.itemClickListner = listener;
    }

    public void addItems(List<ClassCountModel> modelList) {
        this.classCountModelList = modelList;

        // what is the work this method.. !
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_recycler_row,
                parent, false);
        StudentViewHolder holder = new StudentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, final int position) {

        // adapter data set the list

        final ClassCountModel classCountModel = classCountModelList.get(position);

        // showing the error...
        if (classCountModel != null) {

            holder.classTv.setText(String.valueOf(classCountModel.classCount));
            holder.nameTv.setText(classCountModel.className);
            holder.img1.setImageResource(R.drawable.student164);
            holder.img2.setImageResource(R.drawable.student264);
            holder.img3.setImageResource(R.drawable.student464);
        }



        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemClickListner.clickListener(classCountModel, position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return classCountModelList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv, classTv;
        ImageView img1, img2, img3;
        ConstraintLayout constraintLayout;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.textView_row_id);
            classTv = itemView.findViewById(R.id.textView2_row_id);

            img1 = itemView.findViewById(R.id.image1_row_id);
            img2 = itemView.findViewById(R.id.image2_row_id);
            img3 = itemView.findViewById(R.id.image3_row_id);
            constraintLayout = itemView.findViewById(R.id.cosntrint_layout_id);


        }
    }

    public interface StudentItemClickListner {

        void studentItemClicked(ClassCountModel classCountModel);
    }

    public interface ItemClickListner {

        void clickListener(ClassCountModel model, int position);

    }
}
