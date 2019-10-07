package com.example.assignment3.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
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

    private Button mButtonGreen;     //< Buttons for changing background colours and name
    private Button mButtonYellow;
    private Button mButtonPurple;
    private Button mButtonBlue;
    private Button mButtonOrange;
    private Button mButtonWhite;
    private Button mButtonChangeName;

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static TextView CoinCount;       //< Allows for alteration of the displayed coin sum from other classes/ Activities
    private PageViewModel pageViewModel;
    private long _coins;                    //< Sum of coins
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
        //Get current sum of coins
        _coins = MainActivity.getCoin();
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        CoinCount = root.findViewById(R.id.coinCount);
        CoinCount.setText("You currently have " + _coins + " coins.");

        //Add all onClickListeners
        mButtonChangeName = root.findViewById(R.id.changeName);
        mButtonChangeName.setOnClickListener(v->changeNameFunc());
        mButtonGreen = root.findViewById(R.id.colourGreen);
        mButtonGreen.setOnClickListener(v -> unlockColour(0, Color.GREEN, mButtonGreen));
        if(!MainActivity.checkColour(0)){
            mButtonGreen.setBackgroundColor(Color.argb(128, 255,0,0));
        }
        mButtonYellow = root.findViewById(R.id.colourYellow);
        mButtonYellow.setOnClickListener(v -> unlockColour(1, Color.YELLOW, mButtonYellow));
        if(!MainActivity.checkColour(1)){
            mButtonYellow.setBackgroundColor(Color.argb(128, 255,0,0));
        }
        mButtonPurple = root.findViewById(R.id.colourPurple);
        mButtonPurple.setOnClickListener(v -> unlockColour(2, Color.rgb(128,0,128), mButtonPurple));
        if(!MainActivity.checkColour(2)){
            mButtonPurple.setBackgroundColor(Color.argb(128, 255,0,0));
        }
        mButtonBlue = root.findViewById(R.id.colourBlue);
        mButtonBlue.setOnClickListener(v -> unlockColour(3, Color.BLUE, mButtonBlue));
        if(!MainActivity.checkColour(3)){
            mButtonBlue.setBackgroundColor(Color.argb(128, 255,0,0));
        }
        mButtonOrange = root.findViewById(R.id.colourOrange);
        mButtonOrange.setOnClickListener(v -> unlockColour(4, Color.rgb(255,165,0), mButtonOrange));
        if(!MainActivity.checkColour(4)){
            mButtonOrange.setBackgroundColor(Color.argb(128, 255,0,0));
        }
        mButtonWhite = root.findViewById(R.id.colourWhite);
        mButtonWhite.setOnClickListener(v -> unlockColour(5, Color.WHITE, mButtonWhite));
        return root;
    }
    //Create Dialog box to input a name change
    public void changeNameFunc(){
        final EditText textEntered = new EditText(getContext());
        textEntered.setHint("Name");
        new AlertDialog.Builder(getContext())
                .setTitle("Please enter your name:")
                .setView(textEntered)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String name = textEntered.getText().toString();
                        if(name.length() == 0){
                            //If nothing was entered
                            Toast toast = Toast.makeText(getContext(),"Please enter a name.", Toast.LENGTH_LONG);
                            toast.show();
                            return;
                        }
                        if(_coins >= 50) {
                            //Adjusts name and coin count accordingly
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
    //Unlock and change colour to selected colour
    private void unlockColour(int i, int colour, Button but){
        if(!MainActivity.checkColour(i)){
            new AlertDialog.Builder(getContext())
                    .setTitle("Would you like to unlock this colour for 50 coins?")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            if(_coins >= 50) {
                                //If not yet unlocked, confirm the purchase
                                MainActivity.changeColour(colour);
                                MainActivity.unlockColour(i);
                                MainActivity.updateCoin(-50);
                                _coins = MainActivity.getCoin();
                                CoinCount.setText("You currently have " + _coins + " coins.");
                                but.setBackgroundColor(getResources().getColor(R.color.colorAccent));;
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
