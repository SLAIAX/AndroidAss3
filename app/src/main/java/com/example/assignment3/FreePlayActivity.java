package com.example.assignment3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FreePlayActivity extends AppCompatActivity {

    private TextView challenge1;
    private TextView challenge2;
    private TextView challenge3;

    @Override
        protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeplay);

        challenge1 = findViewById(R.id.challenge1);
        challenge2 = findViewById(R.id.challenge2);
        challenge3 = findViewById(R.id.challenge3);
        Button generate = findViewById(R.id.generate);

        challenge1.setText(GenerateChallenges.generateChallenge());


    }

}
