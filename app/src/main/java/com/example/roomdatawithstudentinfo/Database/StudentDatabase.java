package com.example.roomdatawithstudentinfo.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatawithstudentinfo.Model.Student;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    // database class are method must be declare to abstract.
    private static StudentDatabase db;
    // this is for database method
    public abstract StudentDAO getStudentDao();


    public static StudentDatabase getInstance(Context context){

        if (db!=null){

            return db;
        }

        // but DataBase BackButtonThread Queries hobe...
        db = Room.databaseBuilder(context,StudentDatabase.class,"student_db")
                .allowMainThreadQueries()
                .build();

        return db;

    }


}
