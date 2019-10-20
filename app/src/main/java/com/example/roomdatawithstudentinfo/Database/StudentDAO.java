package com.example.roomdatawithstudentinfo.Database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatawithstudentinfo.Model.Student;

import java.util.List;

@Dao
public interface StudentDAO {


    // first of all insert and it's success.
    @Insert
    long insertNewStudent(Student student);


    //second, all studentList Query.
    @Query("select * from tble_student")
    List<Student> getAllStudents();


    // all student roll, unique query.
    @Query("select * from tble_student")
    List<Student> getStudentByRoll();


    //search Query..
    @Query("select * from tble_student where std_roll like:roll")
    Student getStudentByRoll(long roll);




    // Edit
    @Query("select * from tble_student where studentID like:id")
    Student getStudentById(long id);

    // Delete
    @Delete
    int deleteStudent (Student student);


    // update data
    @Update
    int updateStudent(Student student);



}
