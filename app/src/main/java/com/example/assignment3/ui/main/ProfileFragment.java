package com.example.assignment3.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.MainActivity;
import com.example.assignment3.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ProfileFragment extends Fragment {

    private Button buttonGreen;
    private Button buttonYellow;
    private Button buttonPurple;
    private Button buttonBlue;
    private Button buttonOrange;
    private Button buttonWhite;

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static TextView CoinCount;
    private PageViewModel pageViewModel;
    private long _coins;
    public static ProfileFragment newInstance(int index) {
        ProfileFragment fragment = new ProfileFragment();
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
        _coins = MainActivity.getCoin();
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        CoinCount = root.findViewById(R.id.coinCount);
        CoinCount.setText("You currently have " + _coins + " coins.");


        final Button changeName = root.findViewById(R.id.changeName);
        changeName.setOnClickListener(v->changeNameFunc());
        buttonGreen = root.findViewById(R.id.colourGreen);
        buttonGreen.setOnClickListener(v -> unlockColour(0, Color.GREEN));
        buttonYellow = root.findViewById(R.id.colourYellow);
        buttonYellow.setOnClickListener(v -> unlockColour(1, Color.YELLOW));
        buttonPurple = root.findViewById(R.id.colourPurple);
        buttonPurple.setOnClickListener(v -> unlockColour(2, Color.rgb(128,0,128)));
        buttonBlue = root.findViewById(R.id.colourBlue);
        buttonBlue.setOnClickListener(v -> unlockColour(3, Color.BLUE));
        buttonOrange = root.findViewById(R.id.colourOrange);
        buttonOrange.setOnClickListener(v -> unlockColour(4, Color.rgb(255,165,0)));
        buttonWhite = root.findViewById(R.id.colourWhite);
        buttonWhite.setOnClickListener(v -> unlockColour(5, Color.WHITE));
        return root;
    }

    public void changeNameFunc(){
        final EditText textEntered = new EditText(getContext());

        textEntered.setHint("Name");
        new AlertDialog.Builder(getContext())
                .setTitle("Please enter your name:")
                .setView(textEntered)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(_coins >= 50) {
                            String name = textEntered.getText().toString();
                            MainActivity.setName(name);
                            HomeFragment.Welcome.setText("Welcome " + name + "!");
                            MainActivity.updateCoin(-50);
                            _coins = MainActivity.getCoin();
                            CoinCount.setText("You currently have " + _coins + " coins.");
                        } else {
                            Toast toast = Toast.makeText(getContext(),"You don't have enough coins to change your name (50).", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                })
                .show();
    }

    private void unlockColour(int i, int colour){
        if(!MainActivity.checkColour(i)){
            new AlertDialog.Builder(getContext())
                    .setTitle("Would you like to unlock this colour for 50 coins?")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            if(_coins >= 50) {
                                MainActivity.changeColour(colour);
                                MainActivity.unlockColour(i);
                                MainActivity.updateCoin(-50);
                                _coins = MainActivity.getCoin();
                                CoinCount.setText("You currently have " + _coins + " coins.");
                            } else {
                                Toast toast = Toast.makeText(getContext(),"You don't have enough coins to unlock this colour (50).", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }
                    })
                    .show();
        } else {
            MainActivity.changeColour(colour);
        }
    }

}
