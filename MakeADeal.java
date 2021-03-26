// Tim Meek II
// 
// Last updated: February 25, 2021
// Description: The purpose of this program is to simulate the "Let's Make A Deal" TV show to
// determine whether you have a higher chance of winning if you hold your original door or switch

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MakeADeal {

	public static void main(String[] args){
		
        // These are used to keep track of the number of runs and to keep score of which door wins
        double total = 0;
        double swap = 0;
        double stay = 0;
        int trials = 0;
        String result = "";
        Boolean errorCaught = false; // used for determining when a non-integer was entered
        
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter number of times you want to play: ");
        do{
            try{
                trials = keyboard.nextInt();
                }
            catch (InputMismatchException a){ // In the instance an integer is not entered
                keyboard.next();
                System.out.println("** Only positive integers are accepted. Please try again. **");
                System.out.print("Enter number of times you want to play: ");
                errorCaught = true;
                }
            if (trials < 1 && errorCaught == false){
                System.out.println("** Only positive integers are accepted. Please try again. **");
                System.out.print("Enter number of times you want to play: ");
                continue;
                }
            errorCaught = false;
            } while (trials < 1);
        

        System.out.println();
        System.out.println("Prize  Guess  View  New Guess");

        for (int i = 0; i < trials ; i++){
            total += 1;
            result = runOneTrial();
            if (result == "win")
                swap += 1;
            else if (result == "lose")
                stay += 1;
        }

        System.out.println();
        System.out.println("Probability of winning if you switch = " + (swap / total));
        System.out.println("Probability of winning if you do not switch = " + (stay / total));

        keyboard.close();

    } // main

    private static int roll() {

        Random randomInt = new Random();
        return randomInt.nextInt(3) + 1;
    } // roll method

    private static String runOneTrial() {

        int prize = roll();
        int guess = roll();
        int view;
        int newGuess;
        String outcome = "";

        do{
            view = roll();
        } while (view == prize || view == guess);

        do{
            newGuess = roll();
        } while (newGuess == guess || newGuess == view);

        
        String prizeStr = String.format("%3d", prize);
        String guessStr = String.format("%7d", guess);
        String viewStr = String.format("%7d", view);
        String newGuessStr = String.format("%8d", newGuess);

        System.out.println(prizeStr + guessStr + viewStr + newGuessStr);

        if (newGuess == prize)
            outcome = "win";
        else if (guess == prize)
            outcome = "lose";

        return outcome;

    }

} // MakeADeal class
