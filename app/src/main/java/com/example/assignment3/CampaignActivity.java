package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.assignment3.ui.main.ChallengehubFragment;
import com.example.assignment3.ui.main.ProfileFragment;

import java.util.Timer;
import java.util.TimerTask;

public class CampaignActivity extends AppCompatActivity {
    //Textviews for each challenge
    private TextView mSection1;
    private TextView mSection2;
    private TextView mSection3;
    private Button complete;                    //< Button to complete the challenge
    private int level;                          //< Current level
    private boolean challengeSubmit = false;    //< If the challenge has been set for more than the specified time, allow submission
    private Timer timer;                        //< Timer

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Set fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.campaign_screen);
        ConstraintLayout layout = findViewById(R.id.campaignLayout);
        layout.setBackgroundColor(MainActivity.getColour());
        //Set timer for 30 seconds
        timer = new Timer();
        timer.schedule(createTimerTask(), 30000);
        mSection1 = findViewById(R.id.section1);
        mSection2 = findViewById(R.id.section2);
        mSection3 = findViewById(R.id.section3);
        complete = findViewById(R.id.completeChallenge);
        complete.setOnClickListener(v -> completedChallenge());
        Intent intent = getIntent();
        level = intent.getIntExtra("Level", 0);
        //Sets challenges dependant on level
        switch (level){
            case 0:
                mSection1.setText("Challenge 1: Spin the motor with a power of 8 for 2 seconds.");
                mSection2.setText("Challenge 2: Spin the motor with the power of 1 for 1 second, power of 2 " +
                        "for 2 seconds, then a power of 3 for 3 seconds.");
                mSection3.setText("Challenge 3: Write a program to spin the motor " +
                        "with a random power for 2 seconds.");
                break;
            case 1:
                mSection1.setText("Challenge 1: Spin the motor clockwise for 2 " +
                        "seconds and then anti-clockwise for 3 seconds.");
                mSection2.setText("Challenge 2: Modify the first program to flash " +
                        "the smarthub orange before it " +
                        "starts moving, green when it " +
                        "changes direction and red when it stops.");
                mSection3.setText("Challenge 3: Modify the program further to " +
                        "change speed every time the " +
                        "light flashes. Make it spin with " +
                        "a speed of 5 after the orange " +
                        "flash and then a speed of 10 " +
                        "after the green flash.");
                break;
            case 2:
                mSection1.setText("Challenge 1: Write a program that plays " +
                        "a random sound every time the " +
                        "play key is pushed.");
                mSection2.setText("Challenge 2: Write a program that makes a " +
                        "display appear with picture on " +
                        "it.");
                mSection3.setText("Challenge 3: Write a program to make the display " +
                        "count from 1 to 10");
                break;
            case 3:
                mSection1.setText("Challenge 1: Write a program that spins the " +
                        "motor to the right when the 'R' " +
                        "key is pushed, and left when the " +
                        "'L' key is pushed.");
                mSection2.setText("Challenge 2: Write a program to repeatedly play " +
                        "the cheering sound (12) when the " +
                        "'C' key is pushed.");
                mSection3.setText("Challenge 3: Write a program to add 1 to a " +
                        "display 10 times when the 'A' " +
                        "key is pushed");
                break;
            case 4:
                mSection1.setText("Challenge 1: Write a program to send the signal " +
                        "'start', receive the signal and " +
                        "then make the motor turn right.");
                mSection2.setText("Challenge 2: Modify the above program so that " +
                        "the 'start' signal is received " +
                        "by another block and on this one, " +
                        "make a sound of your choice play.");
                mSection3.setText("Challenge 3: Make a new program that sends the " +
                        "signal start, waits three seconds, " +
                        "and then sends the signal end. " +
                        "When the start signal is received, " +
                        "make the motor start spinning. " +
                        "When the Stop signal is received, " +
                        "make the motor stop spinning.");
                break;
            case 5:
                mSection1.setText("Challenge 1: Write a program that loops around " +
                        "a numerical display with the " +
                        "input being the sound sensor " +
                        "block. Experiment with how " +
                        "talking at different volumes " +
                        "affects the number shown.");
                mSection2.setText("Challenge 2: Recall the message blocks from last " +
                        "week: Make a program that loops " +
                        "around the message send block, " +
                        "sending whatever value the sound " +
                        "sensor gives it. Receive the " +
                        "signal of number 7, and when " +
                        "received, play sound number 12 " +
                        "(cheering).");
                mSection3.setText("Challenge 3: Modify your program to also receive " +
                        "signals 4 and 6. When received, " +
                        "do actions of your choice. Be " +
                        "creative!");
                break;
            case 6:
                mSection1.setText("Challenge 1: Recall the sound sensor we dealt " +
                        "with last week. Write a program " +
                        "that continually changes the " +
                        "power of the motor dependant on " +
                        "the volume being detected.");
                mSection2.setText("Challenge 2: Make a program where every detected " +
                        "value above 7 spins the motor " +
                        "clockwise, and any value beneath " +
                        "spins it anti-clockwise.");
                mSection3.setText("Challenge 3: Get creative! Make your model voice " +
                        "controlled! Receive at least 4 " +
                        "different levels of noise and " +
                        "have different actions accordingly.");
                break;
            case 7:
                mSection1.setText("Challenge 1: Create a program that plays every " +
                        "sound once.");
                mSection2.setText("Challenge 2: Modify the program to display every " +
                        "second picture.");
                mSection3.setText("Challenge 3: Modify the program further to play " +
                        "every second sound and show every " +
                        "second picture.");
                break;
        }
    }
    //Called when complete button clicked
    public void completedChallenge(){
        //Checks if challenge can be submitted
        if(challengeSubmit) {
            if (MainActivity.getLevel() == (level + 1)) {
                //Adjusts coin count displayed
                MainActivity.updateCoin((level + 1) * 50);
                ProfileFragment.CoinCount.setText("You currently have " + MainActivity.getCoin() + " coins.");
                //Increase level if it needs to be
                MainActivity.increaseLevel();
                ChallengehubFragment.Categories.invalidateViews();
            }
            this.finish();
        } else {
            //If challenge can't be submitted, notify user
            Toast toast = Toast.makeText(this,"Please wait at least 30 seconds before submitting", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    //Allow challenge submission after 30 seconds
    public TimerTask createTimerTask(){
        return  new TimerTask() {
            @Override
            public void run() {
                challengeSubmit = true;
            }
        };
    }
}
