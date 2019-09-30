package com.example.assignment3;

public class GenerateChallenges {

    /*
     * Function to randomly choose an action block out of all possible action blocks
     * @return Block - the chosen block object
     */
    private static Block chooseBlock()
    {
        Block obj;                                                    //Utilizes polymorphism
        int num = Block.randomNumber(1, (Block.get_totalBlocks() + 1));     //Generates a random number between 1 and the maximum number of action Blocks
        BlockNames test = BlockNames.getBlock(num);
        switch (test)
        {
            case SpinMotor:
                obj = new SpinMotorBlock();
                break;
            case LightSmartHub:
                obj = new LightSmartHubBlock();
                break;
            case PowerMotor:
                obj = new PowerMotorBlock();
                break;
            case SoundsBlock:
                obj = new SoundBlock();
                break;
            case DisplayBlock:
                obj = new DisplayBlock();
                break;
            default:
                obj = new SpinMotorBlock();
                break;
        }
        return obj;
    }

    public static String generateChallenge(int level){
        String challenge = "";
        Block obj = new StartBlock();
        challenge += obj.generateSegment();

        switch (level){
            case 1:
                challenge += generateEasyChallenge();
                break;
            case 2:
                challenge += generateMediumChallenge();
                break;
            case 3:
                challenge += generateHardChallenge();
                break;
        }
        return challenge;
    }

    /*
     * Function to generate an easy challenge which will comprise of 1 blocks action
     * and the chance of repeating the whole thing
     * @return string - the generated challenge
     */
    public static String generateEasyChallenge()
    {
        boolean looped = false;
        String challenge;
        Block obj = chooseBlock();
        challenge = obj.generateSegment();
        looped = obj.iszLooped();

        challenge += "." + repeat(looped);
        return challenge;
    }

    /*
     * Function to generate a medium challenge which will comprise of 2 block segments
     * which will be of different block types and has the chance of repeating the whole thing
     * @return string - the generated challenge
     */
    public static String generateMediumChallenge()
    {
        boolean looped = false;
        String challenge;
        Block obj = chooseBlock();
        challenge = obj.generateSegment();
        looped = obj.zLooped;

        Block objNew;
        do
        {
            objNew = chooseBlock();
        } while (obj.zName == objNew.zName);

        challenge += " and then " + objNew.generateSegment();

        if (!looped)
        {
            looped = objNew.zLooped;
        }


        challenge += "." + repeat(looped);

        return challenge;
    }

    /*
     * Function to generate a medium challenge which will comprise of 3 block segments
     * which will be of at least 2 different block types with the chance of repeating the whole thing
     * @return string - the generated challenge
     */
    public static String generateHardChallenge()
    {
        boolean looped = false;
        String challenge;
        Block obj = chooseBlock();
        challenge = obj.generateSegment();
        looped = obj.zLooped;

        Block objNew;
        do
        {
            objNew = chooseBlock();
        } while (obj.zName == objNew.zName);

        challenge += " and then " + objNew.generateSegment();

        if(!looped)
        {
            looped = objNew.zLooped;
        }

        do
        {
            obj = chooseBlock();
        } while (obj.zName == objNew.zName);

        challenge += " and finally " + obj.generateSegment();
        if (!looped)
        {
            looped = obj.zLooped;
        }

        challenge += "." + repeat(looped);

        return challenge;
    }

    /*
     * Function to decide whether the challenge should be looped
     * 1 in 5 chance of it being looped
     * @return the generated string if any
     */
    private static String repeat(boolean looped)
    {
        String toReturn = "";
        if (looped == false)
        {
            int num = Block.randomNumber(1, 6);                             //Generate a number between 0 and 6
            if (num == 5)                                                   //If it's a 5, repeat the code segment
            {
                num = Block.randomNumber(2, 6);                             //Generate a new random number to specify how many times to repeat it
                toReturn = " Repeat this ";
                if (num == 5)                                               //If it's a 5 generated, it will be repeated an infinite amount of times
                {
                    toReturn += "an infinite amount of times.";
                }
                else                                                        //Else it will repeat the number of times according to the generated number
                {
                    toReturn += num + " times using the loop block.";
                }
            }
        }
        return toReturn;
    }

    /*
     * Function to generate Sensor specific challenges
     */
    public static SensorActivity.ChallengeBundle generateLevelTwoChallenges()
    {
        int randomNumber = Block.randomNumber(1, 4);
        SensorNames _sensor = SensorNames.getBlock(randomNumber);
        String ch1 = "";
        String ch2 = "";
        String ch3 = "";

        Block obj;
        int num;
        switch (_sensor)
        {
            case Motion:
                ch1 = "Challenge 1: When the model is tilted forward, " + generateEasyChallenge();

                obj = chooseBlock();
                ch2 = "Challenge 2: When the model is tilted backward, " + generateMediumChallenge();

                num = Block.randomNumber(1, 3);
                obj = chooseBlock();
                if (num == 1)
                {
                    ch3 = "Challenge 3: When the model is tilted left, " + generateHardChallenge();
                }
                else
                {
                    ch3 = "Challenge 3: When the model is tilted right, " + generateHardChallenge();
                }
                break;
            case Distance:
                int dist1, dist2, dist3;
                dist1 = Block.randomNumber(1, 16);                              //Generate a random distance between 1 and 15 (the maximum detectable distance)
                ch1 = "Challenge 1: When the distance is " + dist1 + " centimenters away, " + generateEasyChallenge();

                do
                {
                    dist2 = Block.randomNumber(1, 16);
                } while (dist2 == dist1);
                ch2 = "Challenge 2: When the distance is " + dist2 + " centimenters away, " + generateMediumChallenge();

                do
                {
                    dist3 = Block.randomNumber(1, 16);
                } while (dist3 == dist1 || dist3 == dist2);
                ch3 = "Challenge 3: When the distance is " + dist3 + " centimenters away, " + generateHardChallenge();

                break;
            case Sound:
                int vol1, vol2, vol3;
                vol1 = Block.randomNumber(1, 16);                              //Generate a random distance between 1 and 15 (the maximum detectable distance)
                ch1 = "Challenge 1: When the volume is " + vol1 + ", " + generateEasyChallenge();

                do
                {
                    vol2 = Block.randomNumber(1, 16);
                } while (vol2 == vol1);
                ch2 = "Challenge 2: When the volume is " + vol2 + ", " + generateMediumChallenge();

                do
                {
                    vol3 = Block.randomNumber(1, 16);
                } while (vol3 == vol1 || vol3 == vol2);
                ch3 = "Challenge 3: When the volume is " + vol3 + ", " + generateHardChallenge();
                break;
        }
        return new SensorActivity.ChallengeBundle(ch1,ch2,ch3);
    }
}
