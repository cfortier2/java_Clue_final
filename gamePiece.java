/*Chris Fortier
 * csci e-50b
 *
 * term project
 *
 * This file will create an abstract class that controls the game pieces and movements. Two children classes of computer player and human player will be called. My original thought was that the logic to move the computer pieces would come from the child class of gamePieceComputer... however while coding i deteremined it was a lot simpler to include it in the other classes. Therefore gamePieceHuman and gamePieceComputer have the exact same functionality.`
 *
 */


import java.util.*;

public abstract class gamePiece
{
	//instance variables
	private String name;
	private char pieceMarker;
	private int pieceColorR;
	private int pieceColorG;
	private int pieceColorB;


	//create variables to track if the item is still in play (potential person/weapon/room). follows indexing from cardTracker
	private boolean[] weapon = new boolean[9];
	private boolean[] room = new boolean[9];
	private boolean[] person = new boolean[6];
	
	
	/*
	 * initial constructor
	 * @param n - String name of piece //ended up not using
	 * @param m - char m to use as the piece marker //ended up not using
	 * @param colorR - int for red value
	 * @param colorG - int for green value
	 * @param colorB - int for blue value
	 */
	gamePiece(String n, char m, int colorR, int colorG, int colorB)
	{
		//instance variables
		name = n;
		pieceMarker = m;
		pieceColorR = colorR;
		pieceColorG = colorG;
		pieceColorB = colorB;

		//set all trackers to true indicating they are still in play
		for (int i = 0; i < 9; i++) {
			weapon[i] = true;
			room[i] = true;
		}

		for (int i = 0; i < 6; i++) {
			person[i] = true;
		}
		
	}

	/*
	 * default constructor - not used in program
	 */
	gamePiece()
	{
		super();
	}

	/* 
	 * @return pieceMarker
	 */
	public char getPieceMarker()
	{
		return pieceMarker;
	}

	/*
	 * @return name
	 */
	public String getName()
	{
		return name;
	}

	/*
	 * @param i - int value for which weapon was viewed/not in play
	 */
	public void setWeaponViewed(int i)
	{
		weapon[i] = false;
	}

	/*
	 * @param i - int value for which person was viewed/not in play
	 */
	public void setPersonViewed(int i)
	{
		person[i] = false;
	}

	/*
	 * @param i - int value for which room was viewed/not in play
	 */
	public void setRoomViewed(int i)
	{
		room[i] = false;
	}

	/*
	 * @return cardsRemaining - get all cards not viewed (still in play)
	 */
	public String[] getCardsRemaining()
	{
		String[] output = new String[24];
		int outRow = 0;

		//get people
		for (int i = 0; i < 6; i++) {
			if (person[i] == true) {
				output[outRow] = "people[" + i + "] == true";
				outRow++;
			}
		}

		//get rooms
		for (int i = 0; i < 9; i++) {
			if (room[i] == true) {
				output[outRow] = "room[" + i + "] == true";
				outRow++;
			}
		}

		//get weapons
		for (int i = 0; i < 9; i++) {
			if (weapon[i] == true) {
				output[outRow] = "weapon[" + i + "] == true";
				outRow++;
			}
		}
		return output;
	}

	/*
	 * get all cards
	 */
	public String[] getAllCards()
	{
		String[] output = new String[24];
		int outRow = 0;

		//get people
		for (int i = 0; i < 6; i++) {
			if (person[i] == true) {
				output[outRow] = "people[" + i + "] == true";
			} else {
				output[outRow] = "people[" + i + "] == false";
			}	
			outRow++;
		}

		//get rooms
		for (int i = 0; i < 9; i++) {
			if (room[i] == true) {
				output[outRow] = "room[" + i + "] == true";
			} else {
				output[outRow] = "room[" + i + "] == false";
			}
			outRow++;
		}

		//get weapons
		for (int i = 0; i < 9; i++) {
			if (weapon[i] == true) {
				output[outRow] = "weapon[" + i + "] == true";
			} else {
				output[outRow] = "weapon[" + i + "] ==false";
			}

			outRow++;
		}


		return output;
	}

	/*
	 * @return rumor - randomly choose a remaining person, room, and weapon as rumor
	 */
	public int[] makeRumor()
	{
		//temp variables
		int tempPerson = -1;
		int tempRoom = -1;
		int tempWeapon = -1;
		int [] out = {-1, -1, -1};

		//generate random number
		Random gen = new Random();
		
		//randomly choose a person remaining
		do {
			int num = gen.nextInt(6);
			if (person[num] == true) {
				tempPerson = num;
			}
		} while (tempPerson == -1);		
		
		//randomly choose a room remaining
		do {
			int num = gen.nextInt(9);
			if (room[num] == true) {
				tempRoom = num;
			}
		} while (tempRoom == -1);		
		
		//randomly choose a weapon remaining
		do {
			int num = gen.nextInt(6);
			if (weapon[num] == true) {
				tempWeapon = num;
			}
		} while (tempWeapon == -1);		

		//assign to out
		out[0] = tempPerson;
		out[1] = tempRoom;
		out[2] = tempWeapon;

		//return
		return out;
		
	}
}
