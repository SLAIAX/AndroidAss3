package com.example.assignment3.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.FreePlayActivity;
import com.example.assignment3.MainActivity;
import com.example.assignment3.R;
import com.example.assignment3.SensorActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends Fragment {

    private String mName = MainActivity.getName();  //< Chosen name

    private static final String ARG_SECTION_NUMBER = "section_number";
    private Button mFreeplayEasy;        //< Easy challenge button
    private Button mFreeplayMedium;      //< Medium challenge button
    private Button mFreeplayHard;        //< Hard challenge button
    private Button mFreeplaySensor;      //< Sensor challenge button
    private PageViewModel pageViewModel;
    public static TextView Welcome;      //< Welcome text to allow for updates from other Activities

    public static HomeFragment newInstance(int index) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //Set welcome message
        Welcome = root.findViewById(R.id.welcomeMessage);
        Welcome.setText("Welcome " + mName + "!");
        mFreeplayEasy = root.findViewById(R.id.freeplayEasy);
        mFreeplayMedium = root.findViewById(R.id.freeplayMedium);
        //Change colour of button depending on if it is locked or not
        if(MainActivity.getMediumLock()) {
            mFreeplayMedium.setBackgroundColor(Color.argb(128, 255,0,0));
        }
        mFreeplayHard = root.findViewById(R.id.freeplayHard);
        if(MainActivity.getHardLock()) {
            mFreeplayHard.setBackgroundColor(Color.argb(128, 255,0,0));
        }
        mFreeplaySensor = root.findViewById(R.id.freeplaySensors);
        if(MainActivity.getSensorLock()) {
            mFreeplaySensor.setBackgroundColor(Color.argb(128, 255,0,0));
        }
        //Set onClickListeners
        mFreeplayEasy.setOnClickListener(v -> openFreePlayActivity(1));
        mFreeplayMedium.setOnClickListener(v -> openFreePlayMedium());
        mFreeplayHard.setOnClickListener(v -> openFreePlayHard());
        mFreeplaySensor.setOnClickListener(v -> openFreePlaySensor());

        return root;
    }
    //Opens freeplay activity at specified level
    public void openFreePlayActivity(int level){
        Intent intent = new Intent(getActivity(), FreePlayActivity.class);
        intent.putExtra("Level",level);
        startActivity(intent);
    }
    //Checks if activity is unlocked, if not, ask to unlock it.
    //If already unlocked, start at medium level
    public void openFreePlayMedium(){
        if(!MainActivity.getMediumLock()){
            openFreePlayActivity(2);
        } else if(MainActivity.getCoin() >= 1000 && MainActivity.getMediumLock()){
            new AlertDialog.Builder(getContext())
                    .setTitle("Unlock medium challenges?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            MainActivity.unlockMedium();
                            MainActivity.updateCoin(-1000);
                            ProfileFragment.CoinCount.setText("You currently have " + MainActivity.getCoin() + " coins.");
                            mFreeplayMedium.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        }
                    })
                    .show();
        } else {
            Toast toast = Toast.makeText(getContext(),"You don't have enough coins to unlock (1000).", Toast.LENGTH_LONG);
            toast.show();
        }

    }
    //Checks if activity is unlocked, if not, ask to unlock it.
    //If already unlocked, start at hard level
    public void openFreePlayHard(){
        if(!MainActivity.getHardLock()){
            openFreePlayActivity(3);
        } else if(MainActivity.getCoin() >= 2000 && MainActivity.getHardLock()){
            new AlertDialog.Builder(getContext())
                    .setTitle("Unlock hard challenges?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            MainActivity.unlockHard();
                            MainActivity.updateCoin(-2000);
                            ProfileFragment.CoinCount.setText("You currently have " + MainActivity.getCoin() + " coins.");
                            mFreeplayHard.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                        }
                    })
                    .show();
        } else {
            Toast toast = Toast.makeText(getContext(),"You don't have enough coins to unlock (2000).", Toast.LENGTH_LONG);
            toast.show();
        }

    }
    //Checks if activity is unlocked, if not, ask to unlock it.
    //If already unlocked, start sensor challenges
    public void openFreePlaySensor(){
        if(!MainActivity.getSensorLock()){
            openSensorChallenges();
        } else if(MainActivity.getCoin() >= 3000 && MainActivity.getSensorLock()){
            new AlertDialog.Builder(getContext())
                    .setTitle("Unlock sensor challenges?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            MainActivity.unlockSensor();
                            MainActivity.updateCoin(-3000);
                            ProfileFragment.CoinCount.setText("You currently have " + MainActivity.getCoin() + " coins.");
                            mFreeplaySensor.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        }
                    })
                    .show();
        } else {
            Toast toast = Toast.makeText(getContext(),"You don't have enough coins to unlock (3000).", Toast.LENGTH_LONG);
            toast.show();
        }

    }
    //Start sensor challenge activity
    public void openSensorChallenges(){
        Intent intent = new Intent(getActivity(), SensorActivity.class);
        startActivity(intent);
    }
}
