
/* Author: Shannon TJ 10101385 Tutorial 1
   Version: Feb 6, 2015
   
   Features: Generates the attack types based on user input. Tracks the numbers of each attack type. Calculates the percentages of each attack type.

   Limitations: This program does not track any information associated with the defender. This program cannot communicate with the defender directly. This program needs the Manager file in order to run.

*/

import java.util.Random;

public class Attacker
{

//Creates attacker attributes

    final static int HIGHATTACK = 1;
    final static int MEDATTACK = 2;
    final static int LOWATTACK = 3;

    private int trackAttackHigh = 0;
    private int trackAttackMed = 0;
    private int trackAttackLow = 0;

    public Attacker()
    {
    }

    /*getAttack: Generates an attack type
      Parameters: The user-input percentage of high attack, user-input percentage of medium attack, user-input percentage of low attack
      Returns: An integer that corresponds to an attack type (i.e. 1 for high attack, 2 for medium attack, 3 for low attack)
    */

    public int getAttack(float highValue, float medValue, float lowValue)
    {

	//Generates a random number between 1 and 100

	float newMedValue = highValue + medValue;
	Random gen = new Random();
	int result = gen.nextInt(100) + 1;

	//Generates a high attack

	if (result <= highValue)
	    {
		return(HIGHATTACK);
	    }

	//Generates a medium attack

	else if (result > highValue && result <= newMedValue)
	    {
		return(MEDATTACK);
	    }

	//Generates a low attack

	else
	    {
		return(LOWATTACK);
	    }		
    }

    /*attackTracker: Tracks the number of attack types used in each round 
      Parameter: An integer that corresponds to an attack type
      Returns: N/A
    */

    public void attackTracker(int attack)
    {   
	if (attack == 1)
	    {
		trackAttackHigh = trackAttackHigh + 1;
	    }
	else if (attack == 2)
	    {
		trackAttackMed = trackAttackMed + 1;
	    }	    
	else 
	    {
		trackAttackLow = trackAttackLow + 1;
	    }
    }   

    /*percentAttack: Calculates the percentage of each attack type 
      Parameter: Number of combat rounds
      Returns: An array containing the percentage of each attack type, based on how often it appeared in combat 
    */

    public Float[] percentAttack(float number)
    {

	//Calculates the percentage of each attack type

	float percentAttackHigh = trackAttackHigh/number * 100;
	float percentAttackMed = trackAttackMed/number * 100;
	float percentAttackLow = trackAttackLow/number * 100;

	//Stores the attack types in an array

	Float ar [] = new Float [3];

	ar [0] = percentAttackHigh;
	ar [1] = percentAttackMed;
	ar [2] = percentAttackLow;

	return(ar);
    }
}



