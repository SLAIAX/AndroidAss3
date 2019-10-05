package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.ui.main.ProfileFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Timer;
import java.util.TimerTask;

public class FreePlayActivity extends AppCompatActivity {

    private TextView challenge1;
    private int level;
    public boolean challengeSubmit = false;
    private Timer timer;
    @Override
        protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeplay);
        ConstraintLayout layout = findViewById(R.id.freeplayLayout);
        layout.setBackgroundColor(MainActivity.getColour());
        timer = new Timer();
        timer.schedule(createTimerTask(), 10000);

        challenge1 = findViewById(R.id.challenge1);
        Button completed = findViewById(R.id.submit);
        completed.setOnClickListener(v -> newChallenge());
        Intent intent = getIntent();
        level = intent.getIntExtra("Level", 0);
        challenge1.setText(GenerateChallenges.generateChallenge(level));
    }

    public void newChallenge(){
        if(challengeSubmit) {
            timer.schedule(createTimerTask(), 10000);
            challengeSubmit = false;
            challenge1.setText(GenerateChallenges.generateChallenge(level));
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
            Log.i("COINUPDATE", "Updated" + MainActivity.getCoin());
        } else {
            Toast toast = Toast.makeText(this,"Please wait at least 10 seconds before submitting", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public TimerTask createTimerTask(){
        return  new TimerTask() {
            @Override
            public void run() {
                challengeSubmit = true;
            }
        };
    }
}
