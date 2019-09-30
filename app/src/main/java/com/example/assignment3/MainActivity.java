package com.example.assignment3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.assignment3.ui.main.HomeFragment;
import com.example.assignment3.ui.main.ProfileFragment;
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

import java.io.BufferedReader;
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
    private ViewPager viewPager;
    private int currentTab = 1;

    public static boolean MediumLock;
    public static boolean HardLock;
    public static int campaignStage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            campaignStage = Integer.parseInt(t[4]);
            Log.i("UPDATINGCOIN", "Updated1" + coin);
            InputStream.close();

        } catch (Exception e){
            Log.wtf("Reading File", "Failed Reading Data");
            newUser = true;
        }

        if(newUser){
            Intent intent = new Intent(this, NameActivity.class);
            startActivity(intent);
            this.finish();

            final EditText textEntered = new EditText(this);

            textEntered.setHint("Name");

            new AlertDialog.Builder(this)
                    .setTitle("Please enter your name:")
                    .setView(textEntered)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            name = textEntered.getText().toString();
                            coin = 500;
                            MediumLock = true;
                            HardLock = true;
                            campaignStage = 1;
                            saveDetails();
                            HomeFragment.Welcome.setText("Welcome " + name + "!");
                            ProfileFragment.CoinCount.setText("You currently have " + coin + " coins.");
                        }
                    })
                    .show();
        }

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(currentTab);        //Select home tab

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

    public static boolean getMediumLock(){
        return MediumLock;
    }

    public static boolean getHardLock(){
        return HardLock;
    }

    public void saveDetails(){
        try{
            playerDataOut = openFileOutput("playerData", Context.MODE_PRIVATE);
            OutputStreamWriter outputStream = new OutputStreamWriter(playerDataOut);
            outputStream.write(name + ";" + coin + ";" + MediumLock + ";" + HardLock + ";" + campaignStage);
            outputStream.close();

        } catch (Exception e){
            Log.wtf("Writing File", "Failed Saving Data");
        }
    }
}