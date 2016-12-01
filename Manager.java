
/* Author: Shannon TJ 10101385 Tutorial 1
   Version: Feb 6, 2015

   Features: Creates instances of an attacker and defender. Prompts the user to enter a number of combat rounds and percentages of each attack type. Sets the number of rounds to a default value of 10 if 
   a value outside of 1-100 is entered. Sets the percentage of each attack type to 33 if the percentages do not add up to 100. Communicates with the Attacker file to generate attack types. Implements a 
   learning period of 15 rounds for the intelligent defense. Communicates with the Defender file to generate defense types. For each round of combat, tracks the attack type and defense type, determines 
   if the attack hit the defender or was blocked, and prints corresponding output. At the end of the combat, prints total statistics of the battle: the total number of hits and blocks, and the percentage 
   of each attack type and defense type that was used. 

   Limitations: This program does not error correct for strings or any form of input besides integer values. The user cannot control the defender's actions or change the intelligent defender's learning 
   period. The user cannot adjust the attacker's actions in between rounds of combat. The final statistics are rounded to two decimal places. This program only runs once per combat. This program needs the
   Attacker and Defender files in order to run.

*/

import java.util.Scanner;

public class Manager
{

    final static float DEFAULTROUND = 10;
    final static float DEFAULTATTACK = 33;

    public static void main(String [] args)
    {

	//Creates instances of the attacker and defender

	Attacker attack1 = new Attacker();
	Defender defend1 = new Defender();

	//Initializes variables

	float roundNum = 0;

	float highAttack = 0;
	float medAttack = 0;
        float lowAttack = 0;

	float perHighAttack = 1;
	float perMedAttack = 1;
	float perLowAttack = 1;

	int i = 1;

	int attackResult = 0;
	int defendResult = 0;

	//Prompts the user to enter the number of attack rounds.

	Scanner in = new Scanner (System.in);
	System.out.print("Enter the number of rounds: ");
        roundNum = in.nextFloat ();

        //If the value is outside 1-100, a default number of 10 rounds is executed

	if (roundNum < 1 || roundNum > 100)
	    {
		roundNum = DEFAULTROUND;
	    }

	//Prompts the user to enter the percentages of high, medium, and low attacks

	System.out.println("Enter the percentages of high, medium, and low attacks.") ;
	System.out.println("The three percentages must add up to 100%.");
	System.out.println();

	System.out.print("Enter the percentage of high attacks: ");
	highAttack = in.nextFloat();

	System.out.print("Enter the percentage of medium attacks: ");
	medAttack = in.nextFloat();

	System.out.print("Enter the percentage of low attacks: ");
	lowAttack = in.nextFloat();

	//If the percentages do not add up to 100%, the default percentages are used

	if (highAttack + medAttack + lowAttack != 100)
	    {
		highAttack = DEFAULTATTACK;
		medAttack =  DEFAULTATTACK;
		lowAttack = DEFAULTATTACK;
	    }

	//Prints a header for the combat

	System.out.println();
	System.out.println("The Fight Begins!!!");
        System.out.println("----------------------------------");

	//This loop generates attack and defense types for each round of combat

	for (i = 1; i < roundNum + 1; i++)
	    {
		//Prints the combat's round number

		System.out.println();
		String roundHeader = "Round "+i+"...";
		System.out.printf("%-15s", roundHeader);

		//Sends attack percentages to the Attacker file and returns the attack type

	    	attackResult = attack1.getAttack(highAttack, medAttack, lowAttack);

		//For each round, tracks the number of each attack type

		attack1.attackTracker(attackResult);

		if (i <= 15)
		    {
			//Randomly generates a defense type during the defender's learning period (first 15 rounds)

			defendResult = defend1.randomDefense();
		    }

		else if (i == 16)
		    {
			//Calculates the percentage of each attack type based on the attacks used in the learning period

			Float [] attackPercentages = attack1.percentAttack(i);

			perHighAttack = attackPercentages[0];
			perMedAttack = attackPercentages[1];
			perLowAttack = attackPercentages[2];

			//Generates a defense type based on the probability of each attack type

			defendResult = defend1.getDefense(perHighAttack, perMedAttack, perLowAttack);
	       	    }

		else
		    {
		        //Generates a defense type based on the probability of each attack type

			defendResult = defend1.getDefense(perHighAttack, perMedAttack, perLowAttack);
    		    }

		//For each round, tracks the number of each defense type

		defend1.defenseTracker(defendResult);

		//For each round, prints the attack type

		System.out.printf("Attacker: ");
		String attackOutput = defend1.convertValue(attackResult);
		System.out.printf("%-10s", attackOutput);

		//For each round, prints the defense type

		System.out.printf("Defender: ");
		String defenseOutput = defend1.convertValue(defendResult);
		System.out.printf("%-10s", defenseOutput);

		//For each round, prints whether the attack was blocked or hit
		//For each round, tracks the number of blocks and hits

		System.out.printf(defend1.compareValue(attackResult, defendResult));
	    }

	//Returns the total number of hits and blocks in the battle

	int hitStats[] = defend1.hitReport();

	//Calculates the percentage of each attack type used in the entire battle

	Float [] attackStats = attack1.percentAttack(roundNum);

	//Calculates the percentage of each defense type used in the entire battle

	Float defendStats[] = defend1.percentDefense(roundNum);

	System.out.println();
	System.out.println();

//Prints the final results/statistics of the battle

	System.out.println("Battle Summary");
        System.out.println("----------------------------------");

	//Prints the total number of hits and blocks in the battle

	System.out.printf("Total Hits: "+hitStats[0]+"%19s","Total Blocks: "+hitStats[1]);
	
	System.out.println();
	System.out.println();

	//Prints the Attacker's statistics

	System.out.printf("Attacker Stats:  "+ "High: ");
	System.out.printf("%-8.2f", attackStats[0]);
	System.out.printf("Medium: ");
	System.out.printf("%-8.2f", attackStats[1]);
	System.out.printf("Low: ");
	System.out.printf("%-8.2f", attackStats[2]);
	  
	System.out.println();

	//Prints the Defender's statistics

	System.out.printf("Defender Stats:  "+"High: ");
	System.out.printf("%-8.2f", defendStats[0]);
	System.out.printf("Medium: ");
	System.out.printf("%-8.2f", defendStats[1]);
	System.out.printf("Low: ");
	System.out.printf("%-8.2f", defendStats[2]);

	System.out.println();
	System.out.println();	    
    }
}
