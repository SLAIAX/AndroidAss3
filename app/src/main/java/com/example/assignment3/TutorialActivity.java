package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TutorialActivity extends AppCompatActivity {

    private Button forward;
    private Button back;
    private TextView message;
    private int stage;
    private boolean firstIntro;
    private String nameTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        firstIntro = intent.getBooleanExtra("isFirst", false);
        nameTemp = intent.getStringExtra("NameEntered");
        if(nameTemp == null){
            nameTemp = MainActivity.getName();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        stage = 0;

        forward = findViewById(R.id.forwardButton);
        back = findViewById(R.id.backButton);
        message = findViewById(R.id.tutorialContent);
        setMessage();

        back.setOnClickListener(v -> {
            stage--;
            setMessage();
        });

        forward.setOnClickListener(v -> {
            stage++;
            setMessage();
        });
        back.setEnabled(false);
    }

    public void setMessage(){
        switch (stage){
            case 0:
                message.setText("Hi " + nameTemp + ", and welcome to WeDo Buddy!" + System.lineSeparator() + System.lineSeparator() +
                        "This app was designed to help extend your programming ability using the WeDo 2.0 programming software, so be sure you have it installed! " +
                        "Within this app you will find MANY different programming challenges, and by the time you unlock everything you'll be a programming expert!");
                back.setEnabled(false);
                break;
            case 1:
                message.setText("Home" + System.lineSeparator() + System.lineSeparator() + "On the home screen you will find a multitude of Free-Play options. Here you can test your skills, " +
                        "complete challenges and get more coins! These coins can be used to unlock harder Free-Play levels which in-turn " +
                        "will reward you with a higher amount of coins!");
                back.setEnabled(true);
                break;
            case 2:
                message.setText("Levels" + System.lineSeparator() + System.lineSeparator() + "On the Levels screen you will be presented with a multitude of levels to select from. Each level has a block" +
                        " focus and and they increasingly build in difficulty. To unlock a Level, all levels prior to it must too be completed. When you complete these" +
                        " challenges, you will be awarded with a suitable amount of coins reflecting the difficulty.");
                break;
            case 3:
                message.setText("Store" + System.lineSeparator() + System.lineSeparator() + "On the Store screen you are presented with various other in-app purchase options such as resetting your name, changing the theme" +
                        " colour amongst many others.");
                forward.setText("Finish");
                break;
            case 4:
                if(firstIntro){
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("NameEntered",nameTemp);
                    startActivity(intent);
                    this.finish();
                } else {
                    finish();
                }
                break;
        }
    }
}
