package com.example.assignment3;
import java.util.Random;

public abstract class Block {
    private static int _totalBlocks = 5;    //< The total number of action blocks
    protected String zName;                 //< The name of the block
    protected boolean zLooped = false;      //< Whether the generated code includes a loop or not
    private static Random _random = new Random();

    public Block(){
        zLooped = false;
    }

    public String getzName() {
        return zName;
    }

    public void setzName(String zName) {
        this.zName = zName;
    }

    public boolean iszLooped() {
        return zLooped;
    }

    public void setzLooped(boolean zLooped) {
        this.zLooped = zLooped;
    }

    public static int get_totalBlocks() {
        return _totalBlocks;
    }

    /*
     * Function to generate a random number given a range. Inclusive of the
     * min number but not of the max
     * @param[In] min - The minimum number that can be generated
     * @param[In] max - The maximum number that can be generated
     * @return int - the generated number
     */
    public static int randomNumber(int min, int max)
    {
        max -= min;
        return ((_random.nextInt(max)) + min);
    }

    /*
     *Function to generate a section of a challenge which in itself can exist on it's own
     * @return string - the generated challenge string
     */
    public abstract String generateSegment();
}
