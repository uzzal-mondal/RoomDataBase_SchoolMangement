package com.example.roomdatawithstudentinfo;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.roomdatawithstudentinfo.Adapter.StudentAdapter;
import com.example.roomdatawithstudentinfo.Adapter.StudentViewInfoNewAdapter;
import com.example.roomdatawithstudentinfo.Fragment.StudentDetailsFragment;
import com.example.roomdatawithstudentinfo.Fragment.StudentRecordAddFragment;
import com.example.roomdatawithstudentinfo.Fragment.StudentRecordListFragment;
import com.example.roomdatawithstudentinfo.Model.ClassCountModel;
import com.example.roomdatawithstudentinfo.Model.Student;
import com.example.roomdatawithstudentinfo.Search.SearchActivity;


public class MainActivity extends AppCompatActivity implements
        StudentAdapter.StudentItemClickListner,
        StudentRecordListFragment.StudentNewAddClickListner,
        StudentRecordAddFragment.StudentAddRecordCompleteListenr,
        StudentAdapter.ItemClickListner,
        StudentViewInfoNewAdapter.StudentEditItemClickListner,
        StudentRecordAddFragment.StudentUpdateCompleteListner {

    FragmentManager fragmentManager;
    public androidx.appcompat.widget.Toolbar toolbar;
    Fragment prevFragment, currentFragment;
    boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar_id);
        //  toolbar.setTitle("Student Database");
        toolbar.setTitleTextColor(getResources().getColor(R.color.smsp_white_color));
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

       /* prevFragment = new StudentDetailsFragment();
        currentFragment = new StudentDetailsFragment();
        flag = true;  flag = false;*/


        // Student Record List Fragment - 1st.
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        StudentRecordListFragment studentRecordListFragment = new StudentRecordListFragment();
        ft.add(R.id.fragment_container_id, studentRecordListFragment);
        ft.commit();

    }


   /* public void fragmenttransection(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment str = fragment;
        ft.replace(R.id.fragment_container_id, str);
        ft.commitAllowingStateLoss();
    }*/


    // This is student Details Fragment  --- 3
    public void fragmenttransection(Fragment fragment, ClassCountModel model) {

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        // That is very important.

        prevFragment = new StudentRecordListFragment();
        currentFragment = new StudentDetailsFragment();
        flag = false;


        StudentDetailsFragment studentDetailsFragment = new StudentDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("student", model.className);
        studentDetailsFragment.setArguments(bundle);
        ft.replace(R.id.fragment_container_id, studentDetailsFragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    // ClassCount model student Details a comment kore.
    @Override
    public void studentItemClicked(ClassCountModel countModel) {

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        StudentDetailsFragment studentDetailsFragment = new StudentDetailsFragment();
        ft.replace(R.id.fragment_container_id, studentDetailsFragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();

    }


    // This is Student Record Add Fragment  ----   2
    @Override
    public void stdNewAddClick() {

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        // important back arrow cll in fragment..
        prevFragment = new StudentRecordListFragment();
        currentFragment = new StudentRecordAddFragment();
        flag = false;


        StudentRecordAddFragment studentRecordAddFragment = new StudentRecordAddFragment();
        ft.replace(R.id.fragment_container_id, studentRecordAddFragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();


    }


    @Override
    public void clickListener(ClassCountModel model, int position) {

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        StudentDetailsFragment studentDetailsFragment = new StudentDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("student", position);
        studentDetailsFragment.setArguments(bundle);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();

    }


    // Sathe kore Data niye jassi - Record Add Fragmnet. a..
    @Override
    public void onStudentEditItemClicked(long id) {

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        StudentRecordAddFragment studentRecordAddFragment = new StudentRecordAddFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        studentRecordAddFragment.setArguments(bundle);
        ft.replace(R.id.fragment_container_id, studentRecordAddFragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();

    }

    // data add hoye data guli --   RecordListFragment a niye ashbe.
    @Override
    public void onAddStudentComplete() {

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        StudentRecordListFragment studentRecordListFragment = new StudentRecordListFragment();
        ft.replace(R.id.fragment_container_id, studentRecordListFragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();

    }


    // data update hoye data guli -- RecordListFragment a niye ashbe..
    @Override
    public void onUpdateStudentComplete() {

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        StudentRecordListFragment studentRecordListFragment = new StudentRecordListFragment();
        ft.replace(R.id.fragment_container_id, studentRecordListFragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    // onOptions item selected menu item, this is back button default..
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case android.R.id.home:
                backPressed(prevFragment, currentFragment);
                break;

            case R.id.action_search:
                searchPressed();
                break;



        }


        return super.onOptionsItemSelected(item);
    }


    //  This is for important backPressed method Create,
    public void backPressed(Fragment prevFragment, Fragment currentFragment) {

        // back button clickable currentFragment or prevFragment container loaded.

        if (!flag) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            prevFragment = new StudentDetailsFragment();
            currentFragment = new StudentRecordListFragment();
            flag = true;
            ft.replace(R.id.fragment_container_id, currentFragment);
            ft.addToBackStack(null);
            ft.commitAllowingStateLoss();
        } else{

            finish();
        }


    }

    public void searchPressed(){




    }
}



