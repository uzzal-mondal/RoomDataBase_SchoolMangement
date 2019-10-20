package com.example.roomdatawithstudentinfo.Fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatawithstudentinfo.Adapter.StudentViewInfoNewAdapter;
import com.example.roomdatawithstudentinfo.Database.StudentDatabase;
import com.example.roomdatawithstudentinfo.MainActivity;
import com.example.roomdatawithstudentinfo.Model.ClassCountModel;
import com.example.roomdatawithstudentinfo.Model.Student;
import com.example.roomdatawithstudentinfo.Model.StudentViewInfoNewModel;
import com.example.roomdatawithstudentinfo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentDetailsFragment extends Fragment {



    private TextView stdName, stdAddress, stdRoll;
    private TextView stdSpineer;
    private Student student;
    private Context context;
    private RecyclerView recyclerDetails;
    private String className;
    private List<Student> studentList = new ArrayList<>();
    // list of arrayList.
    List<Student> studentNewList = new ArrayList<>();
    // adapter er object create.. :)
    private List<StudentViewInfoNewModel> studentViewInfoNewModels = new ArrayList<>();
    private StudentViewInfoNewAdapter adapter;
    androidx.appcompat.widget.Toolbar toolbar;


    public StudentDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_details,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        // list clear..
        if(!studentNewList.isEmpty()){
            studentNewList.clear();
        }

        recyclerDetails = view.findViewById(R.id.recycler_StudentDetails_id);
        recyclerDetails.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false));


        // this is important show the class details info.
        Bundle bundle = getArguments();
        if (bundle != null) {
            className = bundle.getString("student");

            //ClassCountModel student = (ClassCountModel) bundle.getSerializable("student");
            //this.className = student.className;
        }


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                studentList = StudentDatabase.getInstance(getActivity())
                        .getStudentDao().getAllStudents();

                for (int i = 0; i < studentList.size(); i++) {
                    // student list get position.
                    Student student = studentList.get(i);
                    //Excellent null point selection..
                    if (student.getSpinner() != null) {
                        if (student.getSpinner().equals(className)) {
                            studentNewList.add(student);
                        }
                    }
                }

                // ui thread Runable codding in java...
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new StudentViewInfoNewAdapter(context, studentNewList);
                        recyclerDetails.setAdapter(adapter);

                    }
                });

                }



        });

    }


    @Override
    public void onResume() {
        super.onResume();
        //title bar set name.
        ((MainActivity)getActivity()).toolbar.setTitle("Student Details");
    }


    // try to added search action, ........
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_search,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {



                return false;
            }
        });



    }
}

