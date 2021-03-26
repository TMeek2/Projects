// Tim Meek II
// COSC 3420
// March 18, 2021
// Description: This program is a simple file edittor that runs in Java
//              and allows the user to use simple actions in the Java terminal

import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NullPointerException;
import java.util.Scanner;
import java.util.*;


public class FileEdit {

    private static ArrayList<String> fileLines = new ArrayList<String>();
    private static boolean fileOK = false;
    private static String fileName = "";
    private static String[] actionArray;
    private static Scanner importedFile = null;
    private static boolean hasNumbers = false;
    private static boolean invalidNumber = false;
    private static int numberCount = 0;
    private static Scanner keyboard;
    private static String lineInput = "";

	public static void main(String[] args){
		
        keyboard = new Scanner(System.in);
        
        // until a valid file name is entered the user will be able to have multiple attempts
        while(fileOK == false){
            System.out.print("Please enter the name of your text file: ");
            fileName = keyboard.nextLine();
            System.out.println();
            importFile(fileName);
        }

        System.out.println("EDIT "+ fileName);
        appendLines();
        printLines();

        String action = "";

        while(!(action.equals("E"))) {
            if (lineInput == ""){
                System.out.print(fileLines.size()+1 + "> ");
                action = keyboard.nextLine();
            }
            else
                action = lineInput;

            if (action.isBlank()){
                continue;
            }
            
            // the users input is put into arrays for easier breakdown of the characters
            String[] actionArray = action.split(" ");
            Integer[] actionNumbers = new Integer[actionArray.length-1];
        
            // for loop is used to assign the numbers entered by the user to an array
            int index = 0;
            for(int i = 0;i < actionArray.length;i++)
            {
                try
                {
                    actionNumbers[index] = Integer.parseInt(actionArray[i]);
                    index++;
                }
                catch (NumberFormatException e)
                {
                    // when letters are entered instead of numbers
                    continue;
                }
                catch (ArrayIndexOutOfBoundsException f)
                {
                    continue;
                }
            }

            // determine if there are any numbers in the users input
            numbersCheck(actionNumbers);

            if (actionArray[0].equals("I")){
                lineInput = I(actionNumbers);
            }

            else if (actionArray[0].equals("D")){
                try {
                    D(actionNumbers);
                } catch (NullPointerException e) {
                    System.out.println("Sorry, this command for \"D\" does not exist.");
                    System.out.println("Type \"H\" for the list of possible commands.");
                }
                lineInput = "";
            }

            else if (actionArray[0].equals("L")){
                L();
                lineInput = "";
            }

            else if (actionArray[0].equals("A")){
                lineInput = A();
            }

            else if (actionArray[0].equals("H")){
                H();
                lineInput = "";
            }

            else if (actionArray[0].equals("E")){
                continue;
            }

            else{
                System.out.println("An invalid command was entered. Please try again.");
                System.out.println("Type \"H\" for the list of possible commands.");
            }
        }

        // once "E" is entered the "E" method will be called and the lines will be saved to the text file
        // and all PrintWriter and Scanner objects are closed
        E();

    }

    private static void importFile(String fileName){
        // creates the importedFile Scanner object that is used to read all of the lines from the text file
        try{
            if(fileName.equalsIgnoreCase("exit")) // allows user to type in "Exit" to terminate the program
                System.exit(0);
            importedFile = new Scanner(new File(fileName)); // Reads the file
            fileOK = true;
            }
        catch(FileNotFoundException e){
            System.out.println("This file was not found. Please try again.");
        }

    }
    private static void appendLines(){
        // puts all of the text file lines into a LinkedList
        while (importedFile.hasNext()){
            fileLines.add(importedFile.nextLine());
        }
    }

    private static void printLines(){
        // prints all the file lines from the LinkedList
        for(int i = 1; i <= fileLines.size(); i++)
            System.out.println(i + "> " + fileLines.get(i-1).toString());

    }

    private static void numbersCheck(Integer[] arrayPassed){
        // method tests if there were any numbers entered by the user and keep track of how many there were
        numberCount = 0;
        hasNumbers = false;
        invalidNumber = false;
        
        for (int i = 0; i < arrayPassed.length; i++){
            try{
                if (arrayPassed[i] > 0 && arrayPassed[i] != null){
                    hasNumbers = true;
                    numberCount += 1;
                }
                else if (arrayPassed[i] < 1)
                    invalidNumber = true;
            }
            catch(NullPointerException c){
            }
        }
    }

    private static String I(Integer[] numbersPassed){
        // this method inserts lines entered by the user either into a specified line or at the end of the text file
        lineInput = "";
        int lineNumber;

        // if a number is included after "I"
        if (numberCount == 1){
            lineNumber = numbersPassed[0];
            do{
                System.out.print(lineNumber + "> ");
                lineInput = keyboard.nextLine();
                if ((lineInput.equals("D") || lineInput.equals("L") || lineInput.equals("E") || lineInput.equals("A") || lineInput.equals("I")))
                    break;
                try{
                    fileLines.add(lineNumber - 1, lineInput);
                    lineNumber++;
                }
                catch(IndexOutOfBoundsException e){
                    // accounts for the situation when the line number entered exceeds the size of the ArrayList
                    for (int i = fileLines.size()+1; i <lineNumber; i++)
                        fileLines.add("");
                    fileLines.add(lineNumber - 1, lineInput);
                    lineNumber++;
                }
            } while (!(lineInput.equals("D") || lineInput.equals("L") || lineInput.equals("E") || lineInput.equals("A") || lineInput.equals("I")));
        }

        // when no numbers are entered this function operates the same as the "A" function
        else if (hasNumbers == false){
            A();
        }

        // tells user this function only takes one number
        else if (numberCount > 1){
            System.out.println("Sorry, there is no command for \"I\" when there is more than 1 number entered.");
            System.out.println("Type \"H\" for the list of possible commands.");
        }

        // returning lineInput allows for when another command is entered for it to be directly called without entering it twice
        return lineInput;
    }

    private static void D(Integer[] numbersPassed){
        // this method is used to remove lines from the ArrayList

        // if two numbers are entered
        try{
            if(numberCount == 2)
                for (int i = numbersPassed[1] - 1; i >= numbersPassed[0]-1; i--)
                    fileLines.remove(i);

            // if one number is entered
            else if (numberCount == 1)
                fileLines.remove(numbersPassed[0]-1);
                
            // if no numbers are entered
            else if (numberCount > 2){
                System.out.println("Sorry, there is no command for \"D\" when there is more than 2 numbers entered.");
                System.out.println("Type \"H\" for the list of possible commands.");
            }

            else if (invalidNumber) // if a negative number is entered
                throw new IndexOutOfBoundsException();
                
            else if (hasNumbers == false) // default scenario of just "D" entered
                fileLines.remove(fileLines.size()-1);

        }
        catch(IndexOutOfBoundsException a){ // for the instance when a number larger than the amount of lines in the file is entered
            System.out.println("The number/numbers entered are not valid for the \"D\" command.\nPlease enter valid numbers next time.");
        }
    }

    private static void L(){
        // calls the printLines method to show all the lines in the text file

        if (hasNumbers == true || invalidNumber == true){ // if anything more than "L" is entered this message will be displayed
            System.out.println("Sorry, this command for \"L\" does not exist.");
            System.out.println("Type \"H\" for the list of possible commands.");
        }
        else
            printLines();
    }

    private static String A(){
        // this method appends the new lines entered to the end of the file

        do{
            if (hasNumbers == true){
                System.out.println("Sorry, this command for \"A\" does not exist.");
                System.out.println("Type \"H\" for the list of possible commands.");
                break;
            }
            else if (invalidNumber){
                System.out.println("The number/numbers entered are not valid for this command.\nPlease enter valid numbers next time.");
                break;
            }
            System.out.print(fileLines.size()+1 + "> ");
            lineInput = keyboard.nextLine();
            if ((lineInput.equals("D") || lineInput.equals("L") || lineInput.equals("E") || lineInput.equals("A") || lineInput.equals("I")))
                    break;
            fileLines.add(lineInput);

        } while (!(lineInput.equals("D") || lineInput.equals("L") || lineInput.equals("E") || lineInput.equals("A") || lineInput.equals("I")));

        return lineInput;
    }

    private static void H(){
        // tells the user what all of the functions are and how they work

        System.out.println("\nThe functional commands are \"A\", \"D\", \"I\", \"L\", and \"E\".");
        System.out.println("\"A\" will append the new lines entered to the end of the text file.");
        System.out.println("\"D\" entered with no numbers will delete the last line, if entered with 1 number will delete the line specified by the number,\nand if entered with 2 numbers will delete the lines starting from the first number and ending with the second number.");
        System.out.println("\"I\" entered with no numbers will append the new lines entered to the end of the text file, if entered with 1 number will insert\nthe new lines entered starting at the line specified by the number");
        System.out.println("\"L\" will list all of the lines within the text file.");
        System.out.println("\"E\" will save all of the changes that were made to the text file and end the program.\n");

    }

    private static void E(){
        // saves all of the lines from the ArrayList into a new file with the same name as the original, resulting in the old file to be overwritten

        PrintWriter outputFile = null;
        try{
            outputFile = new PrintWriter(fileName);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error creating the file " + fileName);
        }

        for(String newLines : fileLines)
            outputFile.println(newLines);

        outputFile.close();
        keyboard.close();
        importedFile.close();
    }

} // FileEdit class