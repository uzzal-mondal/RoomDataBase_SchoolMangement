package com.example.roomdatawithstudentinfo.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(tableName = "tble_student",
        indices = {@Index(value = {"std_roll"},
        unique = false)})

//@Entity(tableName = "tble_student")
public class Student implements Serializable {


    @ColumnInfo(name = "std_name")
    private String name;

    @PrimaryKey(autoGenerate = true)
    private long studentID;



    @ColumnInfo(name = "std_roll")
    private int roll;

    @ColumnInfo(name = "std_address")
    private String adress;

    @ColumnInfo(name = "std_spinner")
    private String spinner;



    @Ignore
    private int fimage;
    @Ignore
    private int simage;
    @Ignore
    private int timage;

    @Ignore
    public Student(String name, long studentID, int roll, String adress, String spinner, int fimage, int simage, int timage) {
        this.name = name;
        this.studentID = studentID;
        this.roll = roll;
        this.adress = adress;
        this.spinner = spinner;
        this.fimage = fimage;
        this.simage = simage;
        this.timage = timage;
    }

    public Student(String name, int roll, String adress, String spinner) {
        this.name = name;
        this.roll = roll;
        this.adress = adress;
        this.spinner = spinner;
    }

    @Ignore
    public Student(int fimage, int simage, int timage) {
        this.fimage = fimage;
        this.simage = simage;
        this.timage = timage;

    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }

    public int getFimage() {
        return fimage;
    }

    public void setFimage(int fimage) {
        this.fimage = fimage;
    }

    public int getSimage() {
        return simage;
    }

    public void setSimage(int simage) {
        this.simage = simage;
    }

    public int getTimage() {
        return timage;
    }

    public void setTimage(int timage) {
        this.timage = timage;
    }



    // static method declare..
    public static List<Student> getStudentList(){

        // create a arrayList with return arrayList value...
        List<Student> studentList = new ArrayList<>();

        return studentList;

    }




}
