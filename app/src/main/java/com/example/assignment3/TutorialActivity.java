package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TutorialActivity extends AppCompatActivity {

    private Button mForward;         //< Forward Button
    private Button mBack;            //< Back Button
    private TextView message;        //< Tutorial page message
    private int mStage;              //< Stage of the tutorial
    private boolean mFirstIntro;     //< Bool for if this was accessed through the Name Activity
    private String mNameTemp;        //< The name set to be passed through if accessed through the Name Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        //If it's accessed through the Name Activity
        mFirstIntro = intent.getBooleanExtra("isFirst", false);
        mNameTemp = intent.getStringExtra("NameEntered");
        if(mNameTemp == null){
            mNameTemp = MainActivity.getName();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        LinearLayout layout = findViewById(R.id.tutorialLayout);
        layout.setBackgroundColor(MainActivity.getColour());
        mStage = 0;
        mForward = findViewById(R.id.forwardButton);
        mBack = findViewById(R.id.backButton);
        message = findViewById(R.id.tutorialContent);
        setMessage();
        mBack.setOnClickListener(v -> {
            mStage--;
            setMessage();
        });
        mForward.setOnClickListener(v -> {
            mStage++;
            setMessage();
        });
        mBack.setEnabled(false);
    }
    //Change the message and alter the buttons as needed
    public void setMessage(){
        switch (mStage){
            case 0:
                message.setText("Hi " + mNameTemp + ", and welcome to WeDo Buddy!" + System.lineSeparator() + System.lineSeparator() +
                        "This app was designed to help extend your programming ability using the WeDo 2.0 programming software, so be sure you have it installed! " +
                        "Within this app you will find MANY different programming challenges, and by the time you unlock everything you'll be a programming expert!");
                mBack.setEnabled(false);
                break;
            case 1:
                message.setText("Home" + System.lineSeparator() + System.lineSeparator() + "On the home screen you will find a multitude of Free-Play options. Here you can test your skills, " +
                        "complete challenges and get more coins! These coins can be used to unlock harder Free-Play levels which in-turn " +
                        "will reward you with a higher amount of coins!");
                mBack.setEnabled(true);
                break;
            case 2:
                message.setText("Levels" + System.lineSeparator() + System.lineSeparator() + "On the Levels screen you will be presented with a multitude of levels to select from. Each level has a block" +
                        " focus and and they increasingly build in difficulty. To unlock a Level, all levels prior to it must too be completed. When you complete these" +
                        " challenges, you will be awarded with a suitable amount of coins reflecting the difficulty.");
                break;
            case 3:
                message.setText("Store" + System.lineSeparator() + System.lineSeparator() + "On the Store screen you are presented with various other in-app purchase options such as resetting your name, changing the theme" +
                        " colour amongst many others.");
                mForward.setText("Finish");
                break;
            case 4:
                if(mFirstIntro){
                    //If accessed through the name activity, need to start the main activity
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("NameEntered", mNameTemp);
                    startActivity(intent);
                    this.finish();
                } else {
                    //If accessed through the main activity, just end.
                    finish();
                }
                break;
        }
    }
}
