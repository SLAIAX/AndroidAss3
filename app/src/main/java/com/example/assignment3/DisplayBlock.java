package com.example.assignment3;

/*
 * class DisplayBlock
 * Contains all methods relevant for Display Block related challenge segment generation
 */
public class DisplayBlock extends Block {
    /*
     * Constructor to initialize name
     */
    public DisplayBlock()
    {
        zName = "Display";
    }

    /*
     * Overriden generateSegment function to generate a display related challenge segment
     * @return string - generated segment
     */
    @Override
    public String generateSegment() {
        String toReturn = "";
        int num = randomNumber(1, 3);
        switch (num)
        {
            case 1:
                toReturn = timedPic();
                break;
            case 2:
                toReturn = counter();
                break;
        }
        return toReturn;
    }

    /*
     * Function to generate a picture related challenge segment
     * @param[in] toReturn - A string reference, the generated challenge segment is assigned to it
     */
    public String timedPic()
    {
        String toReturn;
        int num = randomNumber(1, 30);          //Choose a picture
        if (num == 29)                          //If 30, ask for a random picture
        {
            toReturn = "display a random picture for ";
        } else
        {
            toReturn = "display picture number " + num + " for ";
        }
        num = randomNumber(2, 6);               //Choose number of seconds
        toReturn += num + " seconds";
        return toReturn;
    }

    /*
     * Function to generate a counter related challenge segment
     * @param[in] toReturn - A string reference, the generated challenge segment is assigned to it
     */
    public String counter()
    {
        String toReturn;
        int num = randomNumber(0, 10);          //Choose starting number
        toReturn = "create a display that counts from " + num + " to ";
        num = randomNumber((num + 5), 31);      //Choose final number
        toReturn += num;
        zLooped = true;
        return toReturn;
    }
}
