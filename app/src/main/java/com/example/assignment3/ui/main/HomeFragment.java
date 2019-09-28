package com.example.assignment3.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment3.FreePlayActivity;
import com.example.assignment3.MainActivity;
import com.example.assignment3.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends Fragment {

    private String _name = MainActivity.getName();

    private static final String ARG_SECTION_NUMBER = "section_number";

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
        final Button freeplay = root.findViewById(R.id.freeplay);
        freeplay.setOnClickListener(v -> openFreePlayActivity());     //ADJUST

        final Button challengeOfTheDay = root.findViewById(R.id.challengeOfTheDay);
        //challengeOfTheDay.setOnClickListener();

        return root;
    }


    public void openFreePlayActivity(){
        Intent intent = new Intent(getActivity(), FreePlayActivity.class);
        startActivity(intent);
    }
}
