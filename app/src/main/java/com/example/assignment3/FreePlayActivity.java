package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment3.ui.main.ProfileFragment;

import androidx.appcompat.app.AppCompatActivity;

public class FreePlayActivity extends AppCompatActivity {

    private TextView challenge1;
    private int level;
    @Override
        protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeplay);

        challenge1 = findViewById(R.id.challenge1);
        Button completed = findViewById(R.id.submit);
        completed.setOnClickListener(v -> newChallenge());
        Intent intent = getIntent();
        level = intent.getIntExtra("Level", 0);
        challenge1.setText(GenerateChallenges.generateChallenge(level));
    }

    public void newChallenge(){
        challenge1.setText(GenerateChallenges.generateChallenge(level));
        int reward = 0;
        switch (level){
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
        Log.i("COINUPDATE", "Updated");
    }
}
