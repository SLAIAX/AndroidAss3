package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {
    public EditText name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //Set fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_name);
        name = findViewById(R.id.nameEntered);
        Button continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(v -> openApp());
    }
    //Called when a the button is pressed
    public void openApp(){
        //Get's the name entered
        String nameTemp = name.getText().toString();
        //Checks it's not empty
        if(nameTemp.length() == 0){
            Toast toast = Toast.makeText(this,"Please enter a name", Toast.LENGTH_LONG);
            toast.show();
        } else {
            //Starts the tutorial
            Intent intent = new Intent(this, TutorialActivity.class);
            intent.putExtra("NameEntered",nameTemp);
            intent.putExtra("isFirst",true);
            startActivity(intent);
            this.finish();
        }
    }
}
