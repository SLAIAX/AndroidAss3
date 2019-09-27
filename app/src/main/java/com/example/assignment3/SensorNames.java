package com.example.assignment3;

public enum SensorNames {
    Motion,
    Distance,
    Sound;

    public static SensorNames getBlock(int i){
        switch (i){
            case 1:
                return Motion;
            case 2:
                return Distance;
            case 3:
                return Sound;
            default:
                return Motion;
        }
    }
}
