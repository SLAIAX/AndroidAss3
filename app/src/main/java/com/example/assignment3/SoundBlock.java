package com.example.assignment3;

public class SoundBlock extends Block {

    public SoundBlock()
    {
        super();
        zName = "Sound";
    }


    @Override
    public String generateSegment() {
        String toReturn;
        int num = randomNumber(0, 30);
        if(num == 0)
        {
            toReturn = "play a custom sound";
        }
        else if(num == 29){
            toReturn = "play a random sound";
        }
        else
        {
            toReturn = "play sound number " + num;
        }
        return toReturn;
    }
}
