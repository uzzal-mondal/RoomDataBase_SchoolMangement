package com.example.roomdatawithstudentinfo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {




    //new
    ImageView imageViewSplash;
    Thread splashThread;
    ConstraintLayout constraintLayout;

    private static int SPLASH_COUNT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ActionBar myActionBar = getSupportActionBar();

        //For hiding android actionbar
       // myActionBar.hide();



        imageViewSplash = (ImageView) findViewById(R.id.imageview_splash_id);
        constraintLayout = findViewById(R.id.constraint);


       imageViewSplash.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               switch (v.getId()){
                   case R.id.imageview_splash_id:
                       imageViewSplash.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));
                       break;
               }
           }
       });


        Thread myThread = new Thread(){

            @Override
            public void run() {

                try{
                    sleep(1000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }


                SplashActivity.this.finish();

            }
        };

        myThread.start();


    }





    }

