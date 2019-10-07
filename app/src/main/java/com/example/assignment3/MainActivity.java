package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

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

    private FileOutputStream playerDataOut;     //< Save file
    private FileInputStream playerDataIn;       //< Read file
    private Boolean mNewUser;                   //< Used to check it's a new user
    private static String name;                 //< Players name
    private static long coin;                   //< Players balance
    private static ViewPager viewPager;         //< For tabbed layout
    private int mCurrentTab = 1;                //< Records the current tab
    private static boolean mMediumLock;         //< Whether Medium challenges are locked
    private static boolean mHardLock;           //< Whether Hard challenges are locked
    private static boolean mSensorLock;         //< Whether Sensor challenges are locked
    private static int mCampaignStage;          //< What stage of the campaign the user is on
    private ImageButton info;                   //< Button in the toolbar to show the tutorial again
    private static String mColourString;        //< Binary representation of what colours are unlocked
    private static int mSelectedColour;         //< Current selected colour


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //Get name from intent
        Intent nameSet = getIntent();
        String nameReceived = nameSet.getStringExtra("NameEntered");
        mNewUser = false;
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        try {
            //Open file
            playerDataIn = openFileInput("playerData");
            InputStreamReader InputStream= new InputStreamReader(playerDataIn);
            //Get all information out and assign it to necessary variables
            BufferedReader buff = new BufferedReader (InputStream);
            String Line = buff.readLine();
            String t[] = Line.split(";");
            name = t[0];
            coin = Long.parseLong(t[1]);
            mMediumLock = Boolean.parseBoolean(t[2]);
            mHardLock = Boolean.parseBoolean(t[3]);
            mSensorLock = Boolean.parseBoolean(t[4]);
            mCampaignStage = Integer.parseInt(t[5]);
            mColourString = t[6];
            mSelectedColour = Integer.parseInt(t[7]);
            viewPager.setBackgroundColor(mSelectedColour);
            InputStream.close();

        } catch (Exception e){
            //If file reading failed, notify new user
            mNewUser = true;
        }
        //If new user, start name activity and tutorial process
        if(mNewUser && (nameReceived == null)){
            Intent intent = new Intent(this, NameActivity.class);
            startActivity(intent);
            this.finish();
        }
        //If setting name activity has finished, set default values
        if(nameReceived != null){
            name = nameReceived;
            coin = 500;
            mMediumLock = true;
            mHardLock = true;
            mSensorLock = true;
            mCampaignStage = 1;
            mColourString = "000001";
            mSelectedColour = Color.WHITE;
            saveDetails();
        }
        //Setup fragments
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(mCurrentTab);        //Select home tab
        info = findViewById(R.id.infoButton);
        info.setOnClickListener(v -> {
            Intent intent = new Intent(this, TutorialActivity.class);
            startActivity(intent);
        });
    }
    //Changes name
    public static void setName(String n){ name = n;}
    //Returns name
    public static String getName(){
        return name;
    }
    //Updates the coin balance
    public static void updateCoin(long amount){
        coin += amount;
    }
    //Returns current balance
    public static long getCoin(){
        return coin;
    }
    //Returns current level
    public static int getLevel(){ return mCampaignStage;}
    //Increases level
    public static void increaseLevel(){
        mCampaignStage++;
    }
    //Unlocks the medium challenges
    public static void unlockMedium(){
        mMediumLock = false;
    }
    //Unlocks the hard challenges
    public static void unlockHard(){
        mHardLock = false;
    }
    //Unlocks the sensor challenges
    public static void unlockSensor(){
        mSensorLock = false;
    }
    //Returns unlock state of medium challenges
    public static boolean getMediumLock(){
        return mMediumLock;
    }
    //Returns unlock state of hard challenges
    public static boolean getHardLock(){
        return mHardLock;
    }
    //Returns unlock state of sensor challenges
    public static boolean getSensorLock(){
        return mSensorLock;
    }
    //Changes the colour
    public static void changeColour(int colour){
        mSelectedColour = colour;
        viewPager.setBackgroundColor(colour);
    }
    //Checks if the specified colour is unlocked
    public static Boolean checkColour(int i){
        if(mColourString.charAt(i) == '1'){
            return true;
        }
        return false;
    }
    //Unlocks the specified colour
    public static void unlockColour(int i){
        char[] colours = mColourString.toCharArray();
        colours[i] = '1';
        mColourString = String.valueOf(colours);
    }
    //Returns the current colour
    public static int getColour(){
        return mSelectedColour;
    }
    //onPause saves details
    @Override
    protected void onPause() {
        super.onPause();
        saveDetails();
        mCurrentTab = viewPager.getCurrentItem();
    }
    //onResume sets current tab
    @Override
    protected void onResume() {
        super.onResume();
        viewPager.setCurrentItem(mCurrentTab);
    }
    //Saves all player details to the file
    public void saveDetails(){
        try{
            playerDataOut = openFileOutput("playerData", Context.MODE_PRIVATE);
            OutputStreamWriter outputStream = new OutputStreamWriter(playerDataOut);
            outputStream.write(name + ";" + coin + ";" + mMediumLock + ";" + mHardLock + ";" + mSensorLock + ";" + mCampaignStage + ";" + mColourString + ";" + mSelectedColour);
            outputStream.close();

        } catch (Exception e){
            Log.wtf("Writing File", "Failed Saving Data");
        }
    }

}