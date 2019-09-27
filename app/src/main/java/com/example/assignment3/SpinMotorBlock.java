package com.example.assignment3;

/*
 * class SpinMotor
 * Contains all methods relevant for direction block related challenge segment generation
 */
public class SpinMotorBlock extends Block {
    /*
     * Constructor to initialize name
     */
    public SpinMotorBlock()
    {
        zName = "Spin";
    }

    /*
     * Overriden generateSegment function to generate a motor direction related challenge segment
     * @return string - generated segment
     */
    @Override
    public String generateSegment() {
        String toReturn = "spin the motor ";
        int num = randomNumber(1, 3);           // 50/50 chance for it to specify clockwise or anti-clockwise
        switch (num)
        {
            case 1:
                toReturn += "Clockwise for ";
                break;
            case 2:
                toReturn += "Anti-Clockwise for ";
                break;
        }
        num = randomNumber(1, 11);
        toReturn += num + " seconds";
        return toReturn;
    }
}
