
/* Author: Shannon TJ 10101385 Tutorial 1
   Version: Feb 6, 2015

   Features: Tracks the number of defense types that are used in each round. Randomly generates a defense type during the Defender AI's learning period. Generates defense types, based on 
   the percentages of attack types observed in the learning period (first 15 rounds). Converts attack and defense values from integers to strings. Compares attack and defense types: generates a 
   "blocked" message if the types are the same, or a "hit" message if the types are different. Returns the number of blocks and hits that have occurred in the entire combat. Generates the percentage 
   of each type of defense that has occurred in the entire combat.

   Limitations: This program does not track the number of attack types that are used in each round. This program does not calculate the percentages of the attack types. This program cannot communicate
   directly with the attacker. This program needs the Manager file in order to run.

*/

import java.util.Random;

public class Defender
{
    final static int HIGHDEFENSE = 1;
    final static int MEDDEFENSE = 2;
    final static int LOWDEFENSE = 3;

    private int trackDefenseHigh = 0;
    private int trackDefenseMed = 0;
    private int trackDefenseLow = 0;

    private int hitTrack = 0;
    private int blockTrack = 0;
  
    public Defender()
    {
    }

    /*defenseTracker: Tracks the number of defense types used in each round 
      Parameter: An integer that corresponds to a defense type (i.e. 1 for high defense, 2 for medium defense, 3 for low defense)
      Returns: N/A
    */

    public void defenseTracker(int defense)
    {   
	if (defense == 1)
	    {
		trackDefenseHigh = trackDefenseHigh + 1;
	    }
	else if (defense == 2)
	    {
		trackDefenseMed = trackDefenseMed + 1;
	    }	    
	else 
	    {
		trackDefenseLow = trackDefenseLow + 1;
	    }
    }   

    /*randomDefense: Randomly generates a defense type during the learning period 
      Parameters: N/A
      Returns: A randomly generated integer (either 1, 2, or 3) that corresponds to a defense type.
    */

    public int randomDefense()
    {
	Random gen2 = new Random();
	int randomDef = gen2.nextInt(3) + 1;
	return(randomDef);
    }

    /*getDefense: Generates intelligent defense, based on the percentages of attack types in the learning period
      Parameters: The percentage of high attacks that occurred in the learning period, the percentage of medium attacks in the learning period, the percentage of low attacks in the learning period
      Returns: An integer that corresponds to a defense type
    */

    public int getDefense(float highValue, float medValue, float lowValue)
    {

	//Generates a random number between 1 and 100

	float newMedValue = highValue + medValue;
	Random gen = new Random();
	int result = gen.nextInt(100) + 1;

	//Generates a high defense

	if (result <= highValue)
	    {
		return(HIGHDEFENSE);
	    }

	//Generates a medium defense

	else if(result > highValue && result <= newMedValue)
	    {
		return(MEDDEFENSE);
	    }

	//Generates a low defense

	else  
	    {
		return(LOWDEFENSE);
	    }		
    } 

    /*convertValue: Converts attack and defense values from integers to strings
      Parameter: An integer corresponding to an attack type or an integer corresponding to a defense type
      Returns: A string indicating whether an attack or defense was "High", "Medium", or "Low"
    */

    public String convertValue(int value)
    {
	if (value == 1)
	    {
		return("High");
	    }
	else if (value == 2)
	    {
		return("Medium");
	    }
	else 
	    {
		return("Low");
	    }
    }

    /*compareValue: Compares attack and defense types. Generates a block if the types are the same, or a hit if the types are different. Tracks the number of blocks and hits.
      Parameters: An integer corresponding to an attack type, an integer corresponding to a defense type
      Returns: A string indicating whether an attack was "BLOCKED" or whether it "HIT" the defender
*/

    public String compareValue(int attack, int defense)
    {
	if (attack == defense)
	    {
		blockTrack = blockTrack + 1;
		return("BLOCKED");
	    }
	else
	    {
		hitTrack = hitTrack + 1;
		return("HIT");
	    }
    }

    /*hitReport: Returns the number of blocks and hits that have occurred in the entire combat
      Parameters: N/A
      Returns: An array containing the total number of blocks and hits in the combat
     */

    public int[] hitReport()
    {
	int ar2 [] = new int [2];

	ar2 [0] = hitTrack;
	ar2 [1] = blockTrack;

	return(ar2);
    }

    /*percentDefense: Calculates the percentage of each defense type that has occurred in the combat
      Parameters: Number of combat rounds
      Returns: An array containing the percentage of high defenses, medium defenses, and low defenses
     */

    public Float[] percentDefense(float number)
    {	
        float reportDefenseHigh = trackDefenseHigh/number * 100;
	float reportDefenseMed = trackDefenseMed/number * 100;
	float reportDefenseLow = trackDefenseLow/number * 100;

	Float ar3 [] = new Float[3];

	ar3 [0] = reportDefenseHigh;
	ar3 [1] = reportDefenseMed;
	ar3 [2] = reportDefenseLow;

	return(ar3);
    }
}

