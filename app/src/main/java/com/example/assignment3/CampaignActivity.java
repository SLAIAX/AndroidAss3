package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.ui.main.ProfileFragment;

public class CampaignActivity extends AppCompatActivity {
    private TextView section1;
    private TextView section2;
    private TextView section3;
    private Button complete;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campaign_screen);

        section1 = findViewById(R.id.section1);
        section2 = findViewById(R.id.section2);
        section3 = findViewById(R.id.section3);
        complete = findViewById(R.id.completeChallenge);
        complete.setOnClickListener(v -> completedChallenge());
        Intent intent = getIntent();
        level = intent.getIntExtra("Level", 0);

        switch (level){
            case 0:
                section1.setText("CHALLENGE 1: Spin the motor with a power of 8 for 2 seconds.");
                section2.setText("CHALLENGE 2: Spin the motor with the power of 1 for 1 second, power of 2 " +
                        "for 2 seconds, then a power of 3 for 3 seconds.");
                section3.setText("CHALLENGE 3: Write a program to spin the motor " +
                        "with a random power for 2 seconds.");
                break;
            case 1:
                section1.setText("CHALLENGE 1: Spin the motor clockwise for 2 " +
                        "seconds and then anti-clockwise for 3 seconds.");
                section2.setText("CHALLENGE 2: Modify the first program to flash " +
                        "the smarthub orange before it " +
                        "starts moving, green when it " +
                        "changes direction and red when it stops.");
                section3.setText("CHALLENGE 3: Modify the program further to " +
                        "change speed every time the " +
                        "light flashes. Make it spin with " +
                        "a speed of 5 after the orange " +
                        "flash and then a speed of 10 " +
                        "after the green flash.");
                break;
            case 2:
                section1.setText("CHALLENGE 1: Write a program that plays " +
                        "a random sound every time the " +
                        "play key is pushed.");
                section2.setText("CHALLENGE 2: Write a program that makes a " +
                        "display appear with picture on " +
                        "it.");
                section3.setText("CHALLENGE 3: Write a program to make the display " +
                        "count from 1 to 10");
                break;
            case 3:
                section1.setText("CHALLENGE 1: Write a program that spins the " +
                        "motor to the right when the 'R' " +
                        "key is pushed, and left when the " +
                        "'L' key is pushed.");
                section2.setText("CHALLENGE 2: Write a program to repeatedly play " +
                        "the cheering sound (12) when the " +
                        "'C' key is pushed.");
                section3.setText("CHALLENGE 3: Write a program to add 1 to a " +
                        "display 10 times when the 'A' " +
                        "key is pushed");
                break;
            case 4:
                section1.setText("CHALLENGE 1: Write a program to send the signal " +
                        "'start', receive the signal and " +
                        "then make the motor turn right.");
                section2.setText("CHALLENGE 2: Modify the above program so that " +
                        "the 'start' signal is received " +
                        "by another block and on this one, " +
                        "make a sound of your choice play.");
                section3.setText("CHALLENGE 3: Make a new program that sends the " +
                        "signal start, waits three seconds, " +
                        "and then sends the signal end. " +
                        "When the start signal is received, " +
                        "make the motor start spinning. " +
                        "When the Stop signal is received, " +
                        "make the motor stop spinning.");
                break;
            case 5:
                section1.setText("CHALLENGE 1: Write a program that loops around " +
                        "a numerical display with the " +
                        "input being the sound sensor " +
                        "block. Experiment with how " +
                        "talking at different volumes " +
                        "affects the number shown.");
                section2.setText("CHALLENGE 2: Recall the message blocks from last " +
                        "week: Make a program that loops " +
                        "around the message send block, " +
                        "sending whatever value the sound " +
                        "sensor gives it. Receive the " +
                        "signal of number 7, and when " +
                        "received, play sound number 12 " +
                        "(cheering).");
                section3.setText("CHALLENGE 3: Modify your program to also receive " +
                        "signals 4 and 6. When received, " +
                        "do actions of your choice. Be " +
                        "creative!");
                break;
            case 6:
                section1.setText("CHALLENGE 1: Recall the sound sensor we dealt " +
                        "with last week. Write a program " +
                        "that continually changes the " +
                        "power of the motor dependant on " +
                        "the volume being detected.");
                section2.setText("CHALLENGE 2: Make a program where every detected " +
                        "value above 7 spins the motor " +
                        "clockwise, and any value beneath " +
                        "spins it anti-clockwise.");
                section3.setText("CHALLENGE 3: Get creative! Make your model voice " +
                        "controlled! Receive at least 4 " +
                        "different levels of noise and " +
                        "have different actions accordingly.");
                break;
            case 7:
                section1.setText("CHALLENGE 1: Create a program that plays every " +
                        "sound once.");
                section2.setText("CHALLENGE 2: Modify the program to display every " +
                        "second picture.");
                section3.setText("CHALLENGE 3: Modify the program further to play " +
                        "every second sound and show every " +
                        "second picture.");
                break;
        }
    }

    public void completedChallenge(){
        MainActivity.updateCoin((level+1) * 50);
        ProfileFragment.CoinCount.setText("You currently have " + MainActivity.getCoin() + " coins.");
    }
}
