package com.example.assignment3;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.ui.main.ProfileFragment;

import java.util.Timer;
import java.util.TimerTask;

public class SensorActivity extends AppCompatActivity {

    private TextView challenge1;                //< Challenges
    private TextView challenge2;
    private TextView challenge3;
    private Button complete;                    //< Complete button
    private ChallengeBundle bundle;             //< Custom structure to hold 3 generated challenges
    public boolean challengeSubmit = false;     //< Bool for if it's been over 30 seconds
    private Timer timer;                        //< Timer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timer = new Timer();
        timer.schedule(createTimerTask(), 30000);
        setContentView(R.layout.activity_sensor);
        challenge1 = findViewById(R.id.sensor1);
        challenge2 = findViewById(R.id.sensor2);
        challenge3 = findViewById(R.id.sensor3);
        complete = findViewById(R.id.completeSensorChallenge);
        complete.setOnClickListener(v -> completeChallenge());
        //Get challenges
        bundle = GenerateChallenges.generateLevelTwoChallenges();
        challenge1.setText(bundle.challenge1);
        challenge2.setText(bundle.challenge2);
        challenge3.setText(bundle.challenge3);
    }
    //ChallengeBundle structure
    public static class ChallengeBundle{
        public ChallengeBundle(String ch1, String ch2, String ch3){
            challenge1 = ch1;
            challenge2 = ch2;
            challenge3 = ch3;
        }
        public String challenge1;
        public String challenge2;
        public String challenge3;
    }
    //Called when the complete button is pressed
    public void completeChallenge(){
        //UpdateChallenges
        if(challengeSubmit) {
            //If it's been over 30 seconds, reset timer
            timer.schedule(createTimerTask(), 30000);
            challengeSubmit = false;
            //Get more challenges
            bundle = GenerateChallenges.generateLevelTwoChallenges();
            challenge1.setText(bundle.challenge1);
            challenge2.setText(bundle.challenge2);
            challenge3.setText(bundle.challenge3);
            MainActivity.updateCoin(80);
            ProfileFragment.CoinCount.setText("You currently have " + MainActivity.getCoin() + " coins.");
        } else {
            Toast toast = Toast.makeText(this,"Please wait at least 30 seconds before submitting", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    //After 30 seconds enable the submission
    public TimerTask createTimerTask(){
        return  new TimerTask() {
            @Override
            public void run() {
                challengeSubmit = true;
            }
        };
    }
}
