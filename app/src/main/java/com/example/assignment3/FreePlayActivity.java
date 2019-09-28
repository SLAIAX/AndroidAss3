package com.example.assignment3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FreePlayActivity extends AppCompatActivity {

    private TextView challenge1;

    @Override
        protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeplay);

        challenge1 = findViewById(R.id.challenge1);
        Button completed = findViewById(R.id.submit);
        completed.setOnClickListener(v -> newChallenge());

        challenge1.setText(GenerateChallenges.generateChallenge());


    }

    public void newChallenge(){
        challenge1.setText(GenerateChallenges.generateChallenge());
        //ADD COINS
    }

}
