package com.example.assignment3;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.ui.main.ProfileFragment;

public class SensorActivity extends AppCompatActivity {

    private TextView challenge1;
    private TextView challenge2;
    private TextView challenge3;
    private Button complete;
    private ChallengeBundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sensor);

        challenge1 = findViewById(R.id.sensor1);
        challenge2 = findViewById(R.id.sensor2);
        challenge3 = findViewById(R.id.sensor3);
        complete = findViewById(R.id.completeSensorChallenge);
        complete.setOnClickListener(v -> completeChallenge());

        bundle = GenerateChallenges.generateLevelTwoChallenges();

        challenge1.setText(bundle.challenge1);
        challenge2.setText(bundle.challenge2);
        challenge3.setText(bundle.challenge3);
    }

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

    public void completeChallenge(){
        //UpdateChallenges
        bundle = GenerateChallenges.generateLevelTwoChallenges();

        challenge1.setText(bundle.challenge1);
        challenge2.setText(bundle.challenge2);
        challenge3.setText(bundle.challenge3);


        MainActivity.updateCoin(80);
        ProfileFragment.CoinCount.setText("You currently have " + MainActivity.getCoin() + " coins.");
        Log.i("COINUPDATE", "Updated"+MainActivity.getCoin());
    }
}
