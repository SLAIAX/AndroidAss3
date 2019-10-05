package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.assignment3.ui.main.SectionsPagerAdapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    public FileOutputStream playerDataOut;
    public FileInputStream playerDataIn;
    private Boolean newUser;
    public static String name;
    public static long coin;
    private static ViewPager viewPager;
    private int currentTab = 1;

    public static boolean MediumLock;
    public static boolean HardLock;
    public static boolean SensorLock;
    public static int campaignStage;
    private ImageButton info;
    private static String colourString;
    private static int selectedColour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Intent nameSet = getIntent();
        String nameReceived = nameSet.getStringExtra("NameEntered");
        newUser = false;

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        try {
            playerDataIn = openFileInput("playerData");
            InputStreamReader InputStream= new InputStreamReader(playerDataIn);

            BufferedReader buff = new BufferedReader (InputStream);
            String Line = buff.readLine();

            String t[] = Line.split(";");
            name = t[0];
            coin = Long.parseLong(t[1]);
            MediumLock = Boolean.parseBoolean(t[2]);
            HardLock = Boolean.parseBoolean(t[3]);
            SensorLock = Boolean.parseBoolean(t[4]);
            campaignStage = Integer.parseInt(t[5]);
            colourString = t[6];
            selectedColour = Integer.parseInt(t[7]);
            viewPager.setBackgroundColor(selectedColour);
            InputStream.close();

        } catch (Exception e){
            Log.wtf("Reading File", "Failed Reading Data");
            newUser = true;
        }

        if(newUser && (nameReceived == null)){
            Intent intent = new Intent(this, NameActivity.class);
            startActivity(intent);
            this.finish();
        }

        if(nameReceived != null){
            name = nameReceived;
            coin = 500;
            MediumLock = true;
            HardLock = true;
            SensorLock = true;
            campaignStage = 1;
            colourString = "000001";
            selectedColour = Color.WHITE;
            saveDetails();
        }


        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(currentTab);        //Select home tab
        info = findViewById(R.id.infoButton);
        info.setOnClickListener(v -> {
            Intent intent = new Intent(this, TutorialActivity.class);
            startActivity(intent);
        });
    }

    public static void setName(String n){ name = n;}

    public static String getName(){
        return name;
    }

    public static void updateCoin(int amount){
        coin += amount;
    }

    public static long getCoin(){
        return coin;
    }

    public static int getLevel(){ return campaignStage;}

    public static void increaseLevel(){
        campaignStage++;
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveDetails();
        currentTab = viewPager.getCurrentItem();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.setCurrentItem(currentTab);
    }

    public static void unlockMedium(){
        MediumLock = false;
    }

    public static void unlockHard(){
        HardLock = false;
    }

    public static void unlockSensor(){
        SensorLock = false;
    }

    public static boolean getMediumLock(){
        return MediumLock;
    }

    public static boolean getHardLock(){
        return HardLock;
    }

    public static boolean getSensorLock(){
        return SensorLock;
    }

    public static void changeColour(int colour){
        selectedColour = colour;
        viewPager.setBackgroundColor(colour);
    }

    public static Boolean checkColour(int i){
        if(colourString.charAt(i) == '1'){
            return true;
        }
        return false;
    }

    public static void unlockColour(int i){
        char[] colours = colourString.toCharArray();
        colours[i] = '1';
        colourString = String.valueOf(colours);
    }

    public static int getColour(){
        return selectedColour;
    }

    public void saveDetails(){
        try{
            playerDataOut = openFileOutput("playerData", Context.MODE_PRIVATE);
            OutputStreamWriter outputStream = new OutputStreamWriter(playerDataOut);
            outputStream.write(name + ";" + coin + ";" + MediumLock + ";" + HardLock + ";" + SensorLock + ";" + campaignStage + ";" + colourString + ";" + selectedColour);
            outputStream.close();

        } catch (Exception e){
            Log.wtf("Writing File", "Failed Saving Data");
        }
    }

}