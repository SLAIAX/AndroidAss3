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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends Fragment {

    private String _name = MainActivity.getName();

    private static final String ARG_SECTION_NUMBER = "section_number";
    private Button freeplayEasy;
    private Button freeplayMedium;
    private Button freeplayHard;

    private PageViewModel pageViewModel;
    public static TextView Welcome;

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
        Welcome = root.findViewById(R.id.welcomeMessage);
        Welcome.setText("Welcome " + _name + "!");
        freeplayEasy = root.findViewById(R.id.freeplayEasy);
        freeplayMedium = root.findViewById(R.id.freeplayMedium);
        if(MainActivity.getMediumLock()) {
            freeplayMedium.setBackgroundColor(Color.RED);
        }
        freeplayHard = root.findViewById(R.id.freeplayHard);
        if(MainActivity.getHardLock()) {
            freeplayHard.setBackgroundColor(Color.RED);
        }
        freeplayEasy.setOnClickListener(v -> openFreePlayActivity(1));
        freeplayMedium.setOnClickListener(v -> openFreePlayMedium());
        freeplayHard.setOnClickListener(v -> openFreePlayHard());

        final Button challengeOfTheDay = root.findViewById(R.id.freeplaySensors);
        challengeOfTheDay.setOnClickListener(v->openSensorChallenges());

        return root;
    }


    public void openFreePlayActivity(int level){
        Intent intent = new Intent(getActivity(), FreePlayActivity.class);
        intent.putExtra("Level",level);
        startActivity(intent);
    }

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
                            freeplayMedium.setBackgroundColor(Color.WHITE);
                        }
                    })
                    .show();
        } else {
            Toast toast = Toast.makeText(getContext(),"You don't have enough coins to unlock (1000).", Toast.LENGTH_LONG);
            toast.show();
        }

    }

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
                            freeplayHard.setBackgroundColor(Color.WHITE);
                        }
                    })
                    .show();
        } else {
            Toast toast = Toast.makeText(getContext(),"You don't have enough coins to unlock (2000).", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void openSensorChallenges(){

    }
}
