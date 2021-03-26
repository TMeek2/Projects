// Tim Meek II
// COSC 2430
// Last updated: 3/22/2021
// Description: This program will use methods from the TicTacToe class to start a game of Tic Tac Toe

public class TicTacToeDemo extends TicTacToe {
    
    public static void main(String[] args) {
        
        newGame();

        while(!winner())
        {
            writeBoard();
            getMove();
        }
    }
}