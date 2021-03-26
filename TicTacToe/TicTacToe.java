// Tim Meek II
// COSC 2430
// 3/22/2021
// Description: This program allows two users to play Tic Tac Toe using one keyboard.

import java.util.Scanner;

public class TicTacToe {
    
    static private String divider = "------------------";
    static private char[][] board = new char[3][3]; // changing the values here will allow for a majority of the program to run the same way
                                                    // even with a larger or smaller board apart from the winning conditions of the winner() method
    static private Scanner keyboard = new Scanner(System.in);
    static private int moveCount = 0;
    static private char turn = ' ';


    public static void writeBoard(){
        // this method draws the board for the user

        System.out.println(divider);

        // prints the header showing how many columns there are
        // doing it this way allows for the same lines to be used on different sized boards
        System.out.print("|R/C| ");
        for (int i = 0; i<board[0].length; i++)
            System.out.print(i+1 + " | ");
        System.out.println();
        System.out.println(divider);

        // Prints all the rows from the board
        for (int i = 0; i<board.length; i++){
            System.out.print("| "+ (i+1) +" | ");
            for (int j = 0; j < board[0].length; j++)
                System.out.print(board[i][j] +" | ");
            System.out.println();
            System.out.println(divider);
        }
        System.out.println();
    }

    public static void getMove(){
        // this method is called to inform the user whos turn it is and retrieves their move from them

        boolean invalidMove = true;
        int row = 0;
        int col= 0;
        if (moveCount % 2 == 0)
            turn = 'X';
        
        else
            turn = 'O';

        do{
            System.out.println(turn + "'s turn.");

            System.out.println("Where do you want your " + turn + " placed?");
            System.out.println("Please enter row number and column number separated by a space.\n");

            String move = keyboard.nextLine();
            while(invalidMove){
                try{
                    row = Integer.parseInt(move.split(" ")[0]);
                    col = Integer.parseInt(move.split(" ")[1]);
                    if (row > board.length || row < 1 || col > board[0].length || col < 1)
                        throw new ArrayIndexOutOfBoundsException();
                    invalidMove = false;
                }
                catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("This is not a valid position. Please try again.");
                    move = keyboard.nextLine();
                    continue;
                }
                catch(NumberFormatException f){
                    System.out.println("No values or invalid values were entered. Please try again.");
                    move = keyboard.nextLine();
                    continue;
                }
            }

            System.out.println("You have entered row #" + row + " and column #" + col);

            if (board[row -1][col -1] == 'X' || board[row -1][col -1] == 'O'){
                System.out.println("That cell is already taken.\nPlease make another selection.");
                invalidMove = true;
            }

            else{
                System.out.println("Thank you for your selection.");
                invalidMove = false;
            }
            
            System.out.println();
        } while(invalidMove);

        // if statement to check whos turn it is
        if(turn == 'X')
            board[row -1][col -1] = 'X';
        else if(turn == 'O')
            board[row -1][col -1] = 'O';
        
        moveCount++;
    }

    public static boolean winner(){
        // scans through the board array and determines if there is a winner or a draw, if not the method returns false

        // row check for X's and O's
        for (int x = 0; x < board.length; x++) {
            if (board[x][0] == 'X' &&
                board[x][1] == 'X' &&
                board[x][2] == 'X')
                {
                    System.out.println("X IS THE WINNER!!!\n");
                    writeBoard();
                    return true;
                }

            else if (board[x][0] == 'O' &&
                board[x][1] == 'O' &&
                board[x][2] == 'O')
                {
                    System.out.println("O IS THE WINNER!!!\n");
                    writeBoard();
                    return true;
                }
        }   // for loop

        // col check for X's and O's
        for (int y = 0; y < board[0].length; y++) {
            if (board[0][y] == 'X' &&
                board[1][y] == 'X' &&
                board[2][y] == 'X')
                {
                    System.out.println("X IS THE WINNER!!!\n");
                    writeBoard();
                    return true;
                }
            
            else if (board[0][y] == 'O' &&
                board[1][y] == 'O' &&
                board[2][y] == 'O')
                {
                    System.out.println("O IS THE WINNER!!!\n");
                    writeBoard();
                    return true;
                }
            else
                continue;
        }   // for loop

        // diagonal check

        // tests top left to bottom right for X's
        if (board[0][0] == 'X' &&
            board[1][1] == 'X' &&
            board[2][2] == 'X')
            {
                System.out.println("X IS THE WINNER!!!\n");
                writeBoard();
                return true;
            }
        
        // tests top right to bottom left for X's
        else if (board[2][0] == 'X' &&
            board[1][1] == 'X' &&
            board[0][2] == 'X')
            {
                System.out.println("X IS THE WINNER!!!\n");
                writeBoard();
                return true;
            }

        // tests top left to bottom right for O's
        else if (board[0][0] == 'O' &&
            board[1][1] == 'O' &&
            board[2][2] == 'O')
            {
                System.out.println("O IS THE WINNER!!!\n");
                writeBoard();
                return true;
            }

        // tests top right to bottom left for O's
        else if (board[0][2] == 'O' &&
            board[1][1] == 'O' &&
            board[2][0] == 'O')
            {
                System.out.println("O IS THE WINNER!!!\n");
                writeBoard();
                return true;
            }

        // determines when there is a draw
        if (moveCount == board.length * board[0].length)
        {
            System.out.println("Draw!");
            System.out.println("There was no winner.\n");
            writeBoard();
            return true;
        }
        
        else
            return false;
        
    }   // winner()

    public static void newGame(){
        // creates a new game board (these loops allow for the size of the desired board to be changed at the top of the code)

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                board[x][y] = ' ';
            }
        }
        moveCount = 0;
        System.out.println("New Game: X goes first.\n");

    }   // newGame()
}
