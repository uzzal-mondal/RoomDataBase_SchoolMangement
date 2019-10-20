package com.example.roomdatawithstudentinfo.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.roomdatawithstudentinfo.Database.StudentDatabase;
import com.example.roomdatawithstudentinfo.MainActivity;
import com.example.roomdatawithstudentinfo.Model.Student;
import com.example.roomdatawithstudentinfo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentRecordAddFragment extends Fragment {


    private StudentAddRecordCompleteListenr listenr;
    private StudentUpdateCompleteListner updateListner;
    private EditText nameEt, rollEt, addressEt;
    private String showSpinner;
    private String showSpinnerText;
    private Spinner spinner;
    private FloatingActionButton fabAddButton, fabUpButton;
    private Context context;
    // private ArrayList<ClassCalculateModel> classCalculateModels = new ArrayList<>();
    // private List<Student> studentList = new ArrayList<>();
    private long id = 0;
    private int count = 0;
    androidx.appcompat.widget.Toolbar toolbar;
    private Student student;


    public StudentRecordAddFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        listenr = (StudentAddRecordCompleteListenr) context;
        updateListner = (StudentUpdateCompleteListner) context;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_record_add,
                container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        if (!studentList.isEmpty()) {
//            studentList.clear();
//        }


        nameEt = (EditText) view.findViewById(R.id.edit_name_id);
        rollEt = (EditText) view.findViewById(R.id.edit_roll_id);
        addressEt = (EditText) view.findViewById(R.id.edit_address_id);


        fabAddButton = view.findViewById(R.id.fabAddButton_id);
        fabUpButton = view.findViewById(R.id.fabUpButton_id);
        spinner = view.findViewById(R.id.spinner_id);
        nameEt.requestFocus();


        final Bundle bundle = getArguments();
        if (bundle != null) {

            /*fabAddButton.setVisibility(View.GONE);
            fabUpButton.setVisibility(View.VISIBLE);*/

            fabAddButton.hide();
            fabUpButton.show();

            this.id = bundle.getLong("id");


            student = StudentDatabase.getInstance(context)
                    .getStudentDao()
                    .getStudentById(id);



            if (student != null) {
                nameEt.setText(student.getName());
                rollEt.setText(String.valueOf(student.getRoll()));
                addressEt.setText(student.getAdress());


            }

        }

        // spinner to adding to list..
        final List<String> categories = new ArrayList<>();
        categories.add(0, "Select Your Class");
        categories.add("Class: 1");
        categories.add("Class: 2");
        categories.add("Class: 3");
        categories.add("Class: 4");
        categories.add("Class: 5");


        // spinner to categories size with automatically set in data to spinner.
        for (int i = 1; i < categories.size(); i++) {

            if (student != null) {

                if (student.getSpinner() != null) {

                    if (student.getSpinner().equals(categories.get(i))) {
                        categories.remove(i);
                    }
                }
            }
        }



        if (student != null) {
            categories.add(0, student.getSpinner());
        }


        // style spinner
        final ArrayAdapter<String> adapter = new
                ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item,
                categories);
        // Dropdown layout style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // ataching data adapter to spinner
        spinner.setAdapter(adapter);


        spinner.setSelection(((ArrayAdapter) spinner.getAdapter())
                .getPosition(spinner.getGravity()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long l) {

                if (parent.getItemAtPosition(position).equals("Your Class")) {
                    spinner.setSelection(position);

                    // do nothing
                } else {

                    showSpinner = parent.getItemAtPosition(position).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        spinner.setAdapter(adapter);


        // click to add button,,!!
        fabAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //there is no exception try case with is == call.. just follow the below..:)
                // Learning Quotation error  by - s a :)
                /*int roll = 0;
                if (rollEt.equals("")) {
                     roll = Integer.parseInt(rollEt.getText().toString());
                }*/

                if (validate()) {
                    count++;
                    Log.d("debug", "validate is true");
                    String rolls = rollEt.getText().toString();
                    Log.d("roll", rolls);


                    try {


                        String name = nameEt.getText().toString();
                        int roll = Integer.parseInt(rollEt.getText().toString().trim());
                        String address = addressEt.getText().toString();
                        //this is student name is database access name...
                        Student student = new Student(name, roll, address, showSpinner);


                        // this is call inserted row.. insert value return to long value.
                        long insertedRowId = StudentDatabase.getInstance(context)
                                .getStudentDao()
                                .insertNewStudent(student);

                        // listenr.onAddStudentComplete();


                        if (insertedRowId > 0) {

                            // inserted data Sucessfuly to save..
                            listenr.onAddStudentComplete();
                            // which in the class in data show the Toast..
                            Toast.makeText(context, "Student Info Save - " + student.getSpinner(),
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(context, "couldn't inserted ! ",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {

                        Toast.makeText(context, "Roll is Unique, please another roll include",
                                Toast.LENGTH_SHORT).show();

                    }


                }


            }
        });


        fabUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(context, "Updated Clicked ",
                        Toast.LENGTH_SHORT).show();


                if (validate()) {

                    count++;


                    try {


                        String name = nameEt.getText().toString();
                        int roll = Integer.parseInt(rollEt.getText().toString());
                        String address = addressEt.getText().toString();

                        Student student = new Student(name, roll, address, showSpinner);

                        // this is done to update id
                        student.setStudentID(id);


                        int updateRow = StudentDatabase.getInstance(context)
                                .getStudentDao()
                                .updateStudent(student);

                        updateListner.onUpdateStudentComplete();


                        if (updateRow > 0) {

                            updateListner.onUpdateStudentComplete();
                            Toast.makeText(context, "Student Info Updated - "
                                            + student.getSpinner(),
                                    Toast.LENGTH_SHORT).show();

                        }

                    } catch (Exception e) {

                        Toast.makeText(context, e.toString(),
                                Toast.LENGTH_SHORT).show();

                    }

                }


            }


        });


    }



    // check validity to in your record info added.
    public boolean validate() {


        String rolls = rollEt.getText().toString();

        int roll = -1;

        try {
            roll = Integer.parseInt(rolls.trim());

        } catch (NumberFormatException e) {
            Toast.makeText(context, "Roll cann't be empty Button ",
                    Toast.LENGTH_SHORT).show();
        }

        Log.d("roll", rolls);

        if (roll > -1) {


            Toast.makeText(context, "Submit Button ",
                    Toast.LENGTH_SHORT).show();
            return true;


        } else {

            Toast.makeText(context, "Hello Students, write fields you're required .. ",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    // this is toolbar name changeable..
    @Override
    public void onResume() {
        super.onResume();
        //title bar set name. with cust..
        ((MainActivity) getActivity()).toolbar.setTitle("Student Record Add");
    }


    public interface StudentAddRecordCompleteListenr {

        void onAddStudentComplete();

    }

    public interface StudentUpdateCompleteListner {

        void onUpdateStudentComplete();

    }


}
