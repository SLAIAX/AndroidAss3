package com.example.assignment3;

public class StateSingleton {
    private static StateSingleton state;
    public static long Coin;
    public static String Name;
    private StateSingleton(){

    }

    public StateSingleton getState(){
        if(state == null){
            state = new StateSingleton();
        }
        return state;
    }
}
