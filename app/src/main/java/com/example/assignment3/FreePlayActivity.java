package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.ui.main.ProfileFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Timer;
import java.util.TimerTask;

public class FreePlayActivity extends AppCompatActivity {

    private TextView mChallenge;                //< The challenge Textview
    private int level;                          //< The level challenge to generate
    private boolean mChallengeSubmit = false;   //< Bool for if it's been over 10 seconds
    private Timer mTimer;                       //< Timer
    @Override
        protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Set fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_freeplay);
        ConstraintLayout layout = findViewById(R.id.freeplayLayout);
        layout.setBackgroundColor(MainActivity.getColour());
        mTimer = new Timer();
        mTimer.schedule(createTimerTask(), 10000);
        mChallenge = findViewById(R.id.challenge1);
        Button completed = findViewById(R.id.submit);
        completed.setOnClickListener(v -> newChallenge());
        Intent intent = getIntent();
        level = intent.getIntExtra("Level", 0);
        mChallenge.setText(GenerateChallenges.generateChallenge(level));
    }
    //Called when the complete button is clicked
    public void newChallenge(){
        if(mChallengeSubmit) {
            //Set a new timer
            mTimer.schedule(createTimerTask(), 10000);
            mChallengeSubmit = false;
            mChallenge.setText(GenerateChallenges.generateChallenge(level));
            //Give corresponding reward
            int reward = 0;
            switch (level) {
                case 1:
                    reward = 10;
                    break;
                case 2:
                    reward = 20;
                    break;
                case 3:
                    reward = 30;
                    break;
            }
            MainActivity.updateCoin(reward);
            ProfileFragment.CoinCount.setText("You currently have " + MainActivity.getCoin() + " coins.");
        } else {
            //If it hasn't been over 10 seconds
            Toast toast = Toast.makeText(this,"Please wait at least 10 seconds before submitting", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    //changes the bool to true when it's over 10 seconds
    public TimerTask createTimerTask(){
        return  new TimerTask() {
            @Override
            public void run() {
                mChallengeSubmit = true;
            }
        };
    }
}
