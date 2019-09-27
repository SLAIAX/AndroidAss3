package com.example.assignment3;

public enum BlockNames {
    SpinMotor,
    PowerMotor,
    LightSmartHub,
    SoundsBlock,
    DisplayBlock;

    public static BlockNames getBlock(int i){
        switch (i){
            case 1:
                return SpinMotor;
            case 2:
                return PowerMotor;
            case 3:
                return LightSmartHub;
            case 4:
                return SoundsBlock;
            case 5:
                return DisplayBlock;
            default:
                return SpinMotor;
        }
    }
}
