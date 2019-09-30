package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {
    public EditText name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        name = findViewById(R.id.nameEntered);


        Button continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(v -> openApp());
    }

    public void openApp(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("NameEntered",name.getText().toString());
        startActivity(intent);
        //setResult(1, intent);
        this.finish();
    }
}
