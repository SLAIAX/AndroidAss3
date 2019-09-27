package com.example.assignment3;

/*
 * class StartBlock
 * Contains all methods relevant for generating the start segment for challenges
 */
public class StartBlock extends Block {

    /*
     * Overriden generateSegment function to generate a start segment
     * @return string - generated segment
     */
    @Override
    public String generateSegment() {
        int num = randomNumber(1, 3);           // 50/50 chance as to whether it will be a Letter key or start block
        String toReturn = "";
        switch (num)
        {
            case 1:
                toReturn += "When the Start Block is pressed, ";
                break;
            case 2:
                num = randomNumber(0, 26);      // Generates a random letter
                num += 65;
                char letter = (char)num;
                toReturn += "When the '" + letter + "' key is pressed, ";
                break;
        }
        return toReturn;
    }
}
