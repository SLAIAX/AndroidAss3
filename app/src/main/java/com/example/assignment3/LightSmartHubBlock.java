package com.example.assignment3;

/*
 * class LightSmartHub
 * Contains all methods relevant for Light Block related challenge segment generation
 */
public class LightSmartHubBlock extends Block {
    /*
     * Constructor to initialize the name
     */
    public LightSmartHubBlock()
    {
        zName = "Colour";
    }

    /*
     * Overriden generateSegment function to generate a light related challenge segment
     * @return string - generated segment
     */
    @Override
    public String generateSegment() {
        String toReturn = "change the colour on the Smart-Hub to ";
        int num = randomNumber(1, 12);
        //Possible colours: pink, purple, blue, sky blue, teal, green, yellow, orange, red, and white.
        switch (num)
        {
            case 1:
                toReturn += "colour number 1";
                break;
            case 2:
                toReturn += "colour number 2";
                break;
            case 3:
                toReturn += "colour number 3";
                break;
            case 4:
                toReturn += "colour number 4";
                break;
            case 5:
                toReturn += "colour number 5";
                break;
            case 6:
                toReturn += "colour number 6";
                break;
            case 7:
                toReturn += "colour number 7";
                break;
            case 8:
                toReturn += "colour number 8";
                break;
            case 9:
                toReturn += "colour number 9";
                break;
            case 10:
                toReturn += "colour number 10";
                break;
            case 11:
                toReturn += "a random colour";
                break;
        }
        return toReturn;
    }
}
