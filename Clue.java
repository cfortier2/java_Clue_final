/*  @author Chris Fortier
 *
 * CSCI e-50b Term Project
 *
 * This project will create a game similar to the classic boardgame Clue by Parker Brothers. The essential elements of the game are recreated, however some of the more trivial elements have been ommitted.
 * Game play 
 * 
 * This is the main entry point for the program and does not take any parameters. 
 * 
 * I redirected standard output to a log file named 'clue_log.txt' to capture information helpful during debugging and also to view what's going on under the hood.
 */

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Clue
{
	//global flag to track status of game. Explained several lines below this.
	private static int gameStatus = 1;

	/* main program - does not accept any params or return any values*/	
	public static void main (String [] args) throws java.io.IOException, InterruptedException
	{
		//global flag for debugging purposes. If true it will display the processes running in the background
		Boolean debug = true;

		//redirect standard out to c:\log.txt
		System.setOut(new PrintStream(new FileOutputStream("clue_log.txt")));
		
		//call createGameBoard() to construct game. GameBoard is the main display frame for the game. This is the interface layer.
		GameBoard gb = new GameBoard(debug);
		
		//instantiate positionTracker - positionTracker is a text based tracker to keep track of all of the game pieces. This is part of the "engine" level
		positionTracker pt = new positionTracker(debug);

		//instantiate cardTracker - cardTracker keeps track of which players have which cards. This is part of the "engine" level
		cardTracker cards = new cardTracker(debug);
		
		//determine murderer, room, and weapon
		gb.setMurder(cards.getMurder());
		int[]  murder = cards.getMurderInt();
		
		/* hidden - originally for debug
		for (int i = 0; i < 6; i++) { 
			gb.sendOutput(cards.getPlayerCards(i));
		} */

		//instantiate gamepieces - This sets the name, character marker, and color as RGB values
		//Colonel Mustard = Human Player
		gamePieceHuman Mustard = new gamePieceHuman("Colonel Mustard", 'M', 255, 255, 0);
		gamePieceComputer Scarlet = new gamePieceComputer("Miss Scarlett", 'S', 255, 0 ,0);
		gamePieceComputer White = new gamePieceComputer("Mrs. White", 'W', 255, 255, 255);
		gamePieceComputer Green = new gamePieceComputer("Green", 'G', 255, 255, 255);
		gamePieceComputer Peacock = new gamePieceComputer("Peacock", 'B', 255, 255, 255);
		gamePieceComputer Plum = new gamePieceComputer("Plum", 'P', 255, 255, 255);

		//create array of gamePieces to make batch processing easier
		gamePiece [] allPieces = new gamePiece[6];
	    allPieces[0] = Mustard;
		allPieces[1] = Scarlet;
		allPieces[2] = White;
		allPieces[3] = Green;
		allPieces[4] = Peacock;
		allPieces[5] = Plum;
			
		//call cardTracker to send cards viewed to each piece
		for (int p = 0; p < allPieces.length; p++) {
			System.out.println("p: " + p);
			System.out.println(cards.playerCards[p][0]);
			if (cards.playerCards[p][0] > -1) {
				allPieces[p].setPersonViewed(cards.playerCards[p][0]);
			}
			allPieces[p].setRoomViewed(cards.playerCards[p][1]);
			allPieces[p].setWeaponViewed(cards.playerCards[p][2]);
		}
			

		//redraw board with all positions
		gb.redraw(pt.getGameBoardArray());
		
		//game engine
		
		/* gameStatus
		 * 1 = waiting/continue looping
		 * 2 = pause while running
		 * 3 = exit
		 * 4 = Game Won
		 */

		//start message
		gb.sendOutput("You are Colonel Mustard! Please press the 'roll dice' button to begin your turn.");

		//declare current player
		int currentPiece = 0;
		
		//begin loop for each play. This will cycle until the program ends.
		while (getGameStatus() == 1)  {
			//System.out.println("gameStatus: " + getGameStatus());
			//System.out.println(""); //for some reason there needs to be code here or the loop doesn't run.
			while (gb.isNewRoll() == false) {
				Thread.sleep(100);
			}

			//scan GameBoard to see if newRoll is true
			if (gb.isNewRoll() == true) {
				
				//debug
				//System.out.println("newRoll is true");
				
				//pause the loop
				setGameStatus(2);
				
				//set status of board to allow select
				gb.setStatus(getGameStatus());

				//reset gb.newRoll to false
				gb.setNewRoll(false);
				
				//roll the dice and display output to player
				pt.rollDice();
				gb.sendOutput("Dice roll was: " + pt.getDiceRoll());

				//call calculateAvailablePositions to update the available positions after dice was rolled
				if (debug) { System.out.println("[84] pt.calcAvailablePositions: Called.") ; }
				pt.calcAvailablePositions(currentPiece); 
				
				//call gameBoard.redraw with the available positions
				if (debug) {System.out.println("[88] gb.redraw(pt.getGameBoardArray()): Called.") ; } 
				gb.redraw(pt.getGameBoardArray());

				//look for new position from boardPanel
				int[] position = gb.getNewPosition(currentPiece); //pass currentPiece so gameBoard knows if it is human player
				while (position[0] == -1 && position[1] == -1) {
					position = gb.getNewPosition(currentPiece);
					//if (debug) { System.out.println("[93] position[]: " + position[1] + ", " + position[0]); }
				}

				//if position has been changed	
				if (position[0] != -1 && position[1] != -1) {
					
					//place piece in positionTracker
					if (debug) { System.out.println("[99] pt.setPosition: Called.") ; }
					pt.setPosition(position[1], position[0], currentPiece);
				
				}

				//reset and redraw board
				if (debug) { System.out.println("[110] gb.redraw(pt.getGameBoardArray()): Called.") ;
			       	             pt.printGameBoardArray();	}
				gb.redraw(pt.getGameBoardArray());
				gb.resetBoard();
				pt.resetBoard();

				//check to see if a rumor has been started
				if (gb.getRumorStatus() == true) {
				
					//variable to track if the rumor is fully formed
					boolean formed = false;

					//variable to store the rumor for comparison
					int[] rumorAsInt = new int[3];
				
					if (currentPiece == 0) {	
						//begin human rumor
						gb.startRumor();

					} else { //start computer rumor
						//gb.startRumor();
						//create array variable to hold the 3 indexes of the murder
						int[] rumorArray = allPieces[currentPiece].makeRumor();
						
						//create temp variables for each index
						int p = rumorArray[0];
						int r = rumorArray[1];
						int w = rumorArray[2];

						//call function
						gb.startRumorPerson(p, cards.guestCard[p]);
						gb.startRumorRoom(r, cards.roomCard[r] );
						gb.startRumorWeapon(w, cards.weaponCard[w] );

					}

					//check if rumor is formed
					while (formed == false) {
						Thread.sleep(100);
						formed = gb.rumorFormed();
						String tempStr = Boolean.toString(formed);;
						//gb.sendOutput(tempStr);
					

						//rumor is fully formed
						if (formed == true) {
							
							//store rumor
							rumorAsInt = gb.getRumorAsInt();

							//print rumorAsInt
							String rumorString = "rumor:" + rumorAsInt[0] + ", " + rumorAsInt[1] + ", " + rumorAsInt[2];
							//gb.sendOutput(rumorString);
							System.out.println(rumorString);

							//display rumor in main output
							gb.sendOutput(gb.getRumorAsStr());	
						}
					}

					//see if rumor is true
					boolean rumorIsTrue = false;
					if (rumorAsInt[0] == murder[0]) {
						if (rumorAsInt[1] == murder[1]) {
							if (rumorAsInt[2] == murder[2]) {
								//game is won
								rumorIsTrue = true;
								gameStatus = 4;

								//display message box
								String winnerMsg = "Congratulations! " + cards.guestCard[currentPiece] + " you won!";
								int exit = JOptionPane.showConfirmDialog(null,winnerMsg , "You Won!", JOptionPane.OK_OPTION);
								if (exit == JOptionPane.OK_OPTION) {
									System.exit(0);
								}
							}
						}			
					}


					//loop through all players looking to disprove rumor
					boolean rumorDisproven = false;
						System.out.println("rumorDisproven: " + rumorDisproven);
					
					//loops through all players
					for (int player = 0; player < 6; player++) {
						System.out.println("player: " + player);

						if (rumorDisproven == false) {
						System.out.println("in rumorDisproven == false loop");

						//get correct next piece
						int nextPiece = -1;
						if (player < allPieces.length - 1) {
							nextPiece = player + 1;	
						} else if (currentPiece == allPieces.length - 1) {
							nextPiece = 0;
						}	
							System.out.println("currentPiece: " + currentPiece + " nextPiece: " + nextPiece + " allPieces.length " + allPieces.length);

						
						//get cards for nextPiece
						int[] nextCards = cards.getPlayerCardsAsInt(nextPiece); //returns 3 index int of cards of next player
						System.out.println("nextCards[] " + nextCards[0] + ", " + nextCards[1] + ", " + nextCards[2]); 

						for (int i = 0; i < 3; i++) {
							if (nextCards[i] == rumorAsInt[i]) { //rumor is disproven
								System.out.println("rumorDisproven = true");
								rumorDisproven = true;
								String out = "nextCards[" + i+ "]: " + nextCards[i] + " == rumorAsInt[" + i + "]: " + rumorAsInt[i]; 
								System.out.println(out);
								//gb.sendOutput(out);
							}

							//pass person card to gamePiece to track that it has been viewed by that piece
							if (i == 0) {
								allPieces[currentPiece].setPersonViewed(rumorAsInt[i]);
								System.out.println("currentPiece: " + currentPiece + " i == 0");
								try {
									gb.sendRumorOut("Player was shown: " + cards.guestCard[nextCards[0]]);
								} catch (Exception e) {
								}
							}

							//pass room card to gamePiece to track that it has been viewed by that piece
							if (i == 1) {
								allPieces[currentPiece].setRoomViewed(rumorAsInt[i]);
								System.out.println("currentPiece: " + currentPiece + " i == 1");
								try {
									gb.sendRumorOut("Player was shown: " + cards.roomCard[nextCards[1]]);
								}catch (Exception e) {
								}
							}

							//pass weapon card to gamePiece to track that it has been viewed by that piece
							if (i == 2) {
								allPieces[currentPiece].setWeaponViewed(rumorAsInt[i]);
								System.out.println("currentPiece: " + currentPiece + " i == 2");
								try {
									gb.sendRumorOut("Player was shown: " + cards.weaponCard[nextCards[2]]);
								}catch (Exception e) {
								}
							}
							
						}

						//send cards remaining to output 
						String[] tempArray = allPieces[currentPiece].getAllCards();
						for (int x = 0; x < tempArray.length ; x++) {
							System.out.println(tempArray[x]);
							//gb.sendOutput(tempArray[x]);
						}
							
						}
					}

				}

				//loop through currentpiece to set the next piece
				//if (debug) { gb.sendOutput("currentPiece: " + currentPiece + " allPieces.length: " + allPieces.length); }
				if (currentPiece < allPieces.length - 1) {
					currentPiece++;
				} else if (currentPiece == allPieces.length - 1) {
					currentPiece = 0;
				}
				
				//reset gameStatus to 1 to continue next roll.
				while (gb.isRumorFrameShowing() == true) {
					Thread.sleep(100);
				}
				gameStatus = 1;
				gb.nextRoll(currentPiece);


				//output message
				gb.sendOutput("Current turn is: " + allPieces[currentPiece].getName());

			}
		} //end game loop

		
	} //end main

	/*
	 * @return the status of the game 
	 */
	public static int getGameStatus() 
	{
		return gameStatus;
	}

	/*
	 * @param i takes the status of the game as an int and sets the game status
	 */
	public static void setGameStatus(int i)
	{
		gameStatus = i;
	}
}
