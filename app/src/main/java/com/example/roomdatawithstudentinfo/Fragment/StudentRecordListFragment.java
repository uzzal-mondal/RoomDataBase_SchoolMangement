package com.example.roomdatawithstudentinfo.Fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.roomdatawithstudentinfo.Adapter.StudentAdapter;
import com.example.roomdatawithstudentinfo.Database.StudentDatabase;
import com.example.roomdatawithstudentinfo.MainActivity;
import com.example.roomdatawithstudentinfo.Model.ClassCountModel;
import com.example.roomdatawithstudentinfo.Model.Student;
import com.example.roomdatawithstudentinfo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentRecordListFragment extends Fragment implements
        StudentAdapter.ItemClickListner {

    private RecyclerView recyclerView;
    private Context context;
    private StudentAdapter adapter;
    private FloatingActionButton fab;
    private StudentNewAddClickListner listner;

    //
   // androidx.appcompat.widget.Toolbar toolbar;
    // colection declare.
    private List<Student> studentList = new ArrayList<>();
    private List<ClassCountModel> classCountList = new ArrayList<>();

    //uzzal codding
    //private StudentDetailsClickListner detailsClickListner;

    // counter declare.
    int class1Counter = 0, class2Counter = 0, class3Counter = 0,
            class4Counter = 0, class5Counter = 0, getClass5Counter = 0;
    //


    public StudentRecordListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        listner = (StudentNewAddClickListner) context;


        // dlsClickListner = (StudentDetailsClickListner) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_record_list,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerstudentfragment_id);
        fab = view.findViewById(R.id.fab_id);
        adapter = new StudentAdapter(getActivity(), new ArrayList<ClassCountModel>());
        adapter.setListener(this);


       /* toolbar = view.findViewById(R.id.toolBar_id);
        toolbar.setTitle("Student info");
       toolbar.setTitleTextColor(getResources().getColor(R.color.smsp_white_color));*/


       studentList = StudentDatabase.getInstance(context)
               .getStudentDao().getStudentByRoll();


        // this is bad, said me apu:-
        if (!classCountList.isEmpty()) {
            classCountList.clear();
        }
        // use to AsyncTask Runable..
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                studentList = StudentDatabase.getInstance(getActivity())
                        .getStudentDao().getAllStudents();
                ClassCountModel model = new ClassCountModel();
                ClassCountModel model2 = new ClassCountModel();
                ClassCountModel model3 = new ClassCountModel();
                ClassCountModel model4 = new ClassCountModel();
                ClassCountModel model5 = new ClassCountModel();

                // declare to for each loop countable student get to studentList by :- sapla apu.:)
                for (Student student : studentList) {

                    if (student.getSpinner() != null) {

                        if (student.getSpinner().equals("Class: 1")) {
                            model.className = "Class: 1";
                            class1Counter++;
                            model.classCount++;

                        } else if (student.getSpinner().equals("Class: 2")) {
                            model2.className = "Class: 2";
                            class2Counter++;
                            model2.classCount++;
                        } else if (student.getSpinner().equals("Class: 3")) {

                            model3.className = "Class: 3";
                            class3Counter++;
                            model3.classCount++;
                        } else if (student.getSpinner().equals("Class: 4")) {
                            model4.className = "Class: 4";
                            class4Counter++;
                            model4.classCount++;
                        } else if (student.getSpinner().equals("Class: 5")) {

                            model5.className = "Class: 5";
                            class5Counter++;
                            model5.classCount++;
                        }
                    }

                }

                // this is classCountList adding to model.
                classCountList.add(model);
                classCountList.add(model2);
                classCountList.add(model3);
                classCountList.add(model4);
                classCountList.add(model5);


                adapter.clearAll();
                adapter.addItems(classCountList);


                // collection declare to before the Qurey.
                studentList = StudentDatabase.getInstance(context)
                        .getStudentDao().getAllStudents();


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        LinearLayoutManager lm = new LinearLayoutManager(context,
                                LinearLayoutManager.VERTICAL, false);

                        recyclerView.setLayoutManager(lm);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });


        // fab button click to data adding ...!!
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listner.stdNewAddClick();

            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).toolbar.setTitle("Student Record List");
    }

    @Override
    public void clickListener(ClassCountModel model, int position) {

        // apu write coding.
        ((MainActivity) getActivity()).fragmenttransection(new StudentDetailsFragment(), model);
        //  Bundle bundle = new Bundle();

    }


    public interface StudentNewAddClickListner {

        void stdNewAddClick();
    }

}
