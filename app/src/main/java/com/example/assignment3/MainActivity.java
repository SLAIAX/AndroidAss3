package com.example.assignment3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.assignment3.ui.main.SectionsPagerAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    public FileOutputStream playerDataOut;
    public FileInputStream playerDataIn;
    private Boolean newUser = false;
    public static String name;
    public static long coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);

        try {
            playerDataIn = openFileInput("playerData");
            InputStreamReader InputStream= new InputStreamReader(playerDataIn);

            char[] inputBuffer= new char[80];
            String temp="";
            int charRead;

            while ((charRead=InputStream.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                temp += readstring;
            }
            name = temp;
            InputStream.close();

        } catch (Exception e){
            Log.wtf("Reading File", "Failed Reading Data");
            newUser = true;
        }

        if(newUser){
            try {
                playerDataOut = openFileOutput("playerData", Context.MODE_PRIVATE);
                OutputStreamWriter outputStream = new OutputStreamWriter(playerDataOut);

                final EditText textEntered = new EditText(this);

                // Set the default text to a link of the Queen
                textEntered.setHint("Name");

                new AlertDialog.Builder(this)
                        .setTitle("Please enter your name:")
                        .setView(textEntered)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                name = textEntered.getText().toString();
                            }
                        })
                        .show();

                outputStream.write(name);
                outputStream.close();
                name = "Default";
                coin = 500;     //Temporary
            } catch (Exception e){
                Log.wtf("Writing File", "Failed Saving Data");
            }
        }

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);        //Select home tab

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static String getName(){
        return name;
    }

    public static void updateCoin(int amount){
        coin += amount;
    }

    public static long getCoin(){
        return coin;
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}