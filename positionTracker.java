/*
 *
 * Chris Fortier
 * term project
 *
 * positionTracker.java
 *
 * class is instantiated and keeps track of every position in the game. 
 *
 */

import java.util.*;
import java.lang.Math.*;

public class positionTracker
{
	//instance variables
	private String output = "";
	private boolean positionDebug = false;
	private int diceRoll = -1;

	//create an array of chars that represent each of the available boxes on the gameboard
	char[][] gameBoardArray = new char[25][35];

	//create two dimension array to track position of each piece
	int[][] trackPieces = new int[6][2];

	//create simple array to track position number to position character
	char[] pieceChar = new char[6];

	/*
	 * main constructor
	 * @param debug - boolean for debug mode
	 */
	positionTracker(boolean debug)
	{
		//set dubug
		positionDebug = debug;

		setOutput("Hello from position tracker");
		
		//initialize board
		for (int row = 0; row < 25; row++) {
			for (int column = 0; column < 35; column++) {
				gameBoardArray[row][column] = ' ';
			}
		}

		//set pieceChar
		pieceChar[0] = 'M';
		pieceChar[1] = 'S';
		pieceChar[2] = 'W';
		pieceChar[3] = 'G';
		pieceChar[4] = 'B';
		pieceChar[5] = 'P';

		//set initial positions
		setInitialPositions();

		//if (positionDebug) printGameBoardArray();
	}


	/* 
	 * @param piece - int identifiying which piece is active - calculate available positions
	 */
	public void calcAvailablePositions(int piece)
	{
		//get row and column of current piece
		int currentRow = trackPieces[piece][0];
		int currentColumn = trackPieces[piece][1];

		//variables
		int differenceRow = -1;
		int differenceColumn = -1;
		int absDifference = -1;

		//computes distance availble from current position
		for (int row = 0; row < 25; row++) {
			for (int column = 0; column < 35; column++) {
				
				//get the differences in x and y values
				differenceColumn = Math.abs(currentColumn - column);
				differenceRow = Math.abs(currentRow - row);
				absDifference = differenceColumn + differenceRow;

				//if absDifference is less than diceRoll thne mark with letter A for available
				if (absDifference <= diceRoll) {

					//only update blank spaces
					if (gameBoardArray[row][column] == ' ') {
						gameBoardArray[row][column] = 'A';
					}
				}

				//debug
				/*
				if (positionDebug) {
					System.out.println("\ncurrentRow: " + currentRow + " - row[" + row + "] = " + differenceRow);
				      	System.out.println("currentColumn: " + currentColumn + " - column[" + column + "] = " + differenceColumn);
					System.out.println("absDifference: " + absDifference +" diceRoll: " + diceRoll);	
				} */
			}
		} //end for loops

		//debug - reprint gameBoardArray
		if (positionDebug) printGameBoardArray();
	}

	/*
	 * sets initial positions for each piece
	 */
	public void setInitialPositions()
	{
		//only called during initialization. Does not check to see if space is available. 
		//gameBoardArray[row][col] = piece;
		
		//debug
		if (positionDebug) { System.out.println("setInitialPostions(). Called"); }

		/*set each position manually:
		 * gameBoard[row][column] = (char)piece
		 * trackPieces: first index is the piece.
		 * 				second index [0] = row
		 * 				second index [1] = column
		 */
		
		//set Colonel Mustard
		gameBoardArray[5][7] = 'M';
		trackPieces[0][0] = 5;
	    trackPieces[0][1] = 7;

		//set Miss Scarlett
		gameBoardArray[5][17] = 'S';
		trackPieces[1][0] = 5;
		trackPieces[1][1] = 17;

		//set Mrs. White
		gameBoardArray[5][27] = 'W';
		trackPieces[2][0] = 5;
		trackPieces[2][1] = 27;	
		
		//set Green
		gameBoardArray[18][7] = 'G';
		trackPieces[3][0] = 18;
		trackPieces[3][1] = 7;	
		
		//set Mrs. Peacock
		gameBoardArray[18][17] = 'B';
		trackPieces[4][0] = 18;
		trackPieces[4][1] = 17;	
		
		//set Mrs. Purple
		gameBoardArray[18][27] = 'P';
		trackPieces[5][0] = 18;
		trackPieces[5][1] = 27;	
	}

	/*
	 * accepts two integers representing a location on the board. Checks to see if it is available and sets that position
	 * @param row - int row position
	 * @param column - int column position
	 * @param piece - int current piece
	 */
	public int setPosition(int row, int column, int piece)
	{
		//if the space is available, clear the old position and place it in the new one.
		if (gameBoardArray[row][column] == 'A') {
		
			//clear the prior position
			gameBoardArray[trackPieces[piece][0]][trackPieces[piece][1]] = ' ';		

			//set the position
			gameBoardArray[row][column] = pieceChar[piece];
			trackPieces[piece][0] = row;
			trackPieces[piece][1] = column;

			setOutput(pieceChar[piece] + " was successfully placed at row[" + row + "] / column[" + column + "]");
			
			//debug - reprint gameBoardArray
			if (positionDebug) printGameBoardArray();
			
			return 1; //1 for success
		} else {
			//position is blocked
			setOutput("row[" + row + "] / column[" + column + "] is not available");
			
			//debug - reprint gameBoardArray
			if (positionDebug) printGameBoardArray();
			
			return -1; //-1 for failure
		} 	
	}
	
	/*
	 * roll dice, set variable, and return integer for number of available spaces
	 * @return roll - int for number of spaces available
	 */
	public int rollDice()
	{
		//random number generater that returns a number from 1 to 12
		Random generator = new Random();
		int roll = generator.nextInt(11) + 2;

		//set instance variable
		diceRoll = roll;

		return roll;
	}

	/*
	 * @return diceRoll - value of roll
	 */
	public int getDiceRoll()
	{
		return diceRoll;
	}

	/*
	 * @param s - string to send to output
	 */
	public void setOutput(String s)
	{
		output = s;
	}

	/*
	 * @return output - String output
	 */
	public String getOutput()
	{	
		return output;
	}

	/*
	 * printGameBoardArray() to standard out
	 */
	public void printGameBoardArray()
	{
		for (int row = 0; row <= 24; row++){
			System.out.println("\n");
			for (int column = 0; column < 35; column++) {
				System.out.print("|" + gameBoardArray[row][column] + "|");
			}
		}

		System.out.println("\n");
	}

	/*
	 * resetBoard() - clears board leaving only current pieces in their position
	 */
	public void resetBoard()
	{
		for (int row = 0; row <= 24; row++){
			for (int column = 0; column < 35; column++) {
				//clear 'A' from cells. Leaving only the actual positions
				if (gameBoardArray[row][column] == 'A') {
					gameBoardArray[row][column] = ' ';
				}
			}
		}

		//debug
		if (positionDebug) printGameBoardArray();
		
	}

	/*
	 * @return gameBoardArray - returns the gameBoardArray
	 */
	public char[][] getGameBoardArray()
	{
		return gameBoardArray;
	}
}
