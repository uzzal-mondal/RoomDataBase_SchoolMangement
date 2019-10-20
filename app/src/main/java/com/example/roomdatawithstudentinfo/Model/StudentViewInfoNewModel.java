package com.example.roomdatawithstudentinfo.Model;

import java.io.Serializable;

public class StudentViewInfoNewModel implements Serializable {

    // model class create..

    private String name;
    private String address;
    private String classShow;  // spinner data show..!
    private int roll;



    public StudentViewInfoNewModel(String name, String address, String classShow, int roll) {
        this.name = name;
        this.address = address;
        this.classShow = classShow;
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassShow() {
        return classShow;
    }

    public void setClassShow(String classShow) {
        this.classShow = classShow;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }


}
