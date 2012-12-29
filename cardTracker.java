/*  @author Chris Fortier
 *
 * CSCI e-50b Term Project
 *
 * cardTracker tracks eavh card in the game and which cards each player has.
 */

import java.util.*;
import java.lang.Math.*;

public class cardTracker
{
	//debug boolean
	boolean debug = false;

	//weapon cards
	String[] weaponCard = new String[9];
	int murderWeapon = -1;
	boolean[] weaponTaken = new boolean[9];
	
	//room cards
	String[] roomCard = new String[9];
	private int murderRoom = -1;
	boolean[] roomTaken = new boolean[9];
	
	//guest cards
	String[] guestCard = new String[6];
	private int murderGuest = -1;
	boolean[] guestTaken = new boolean[6];
	int guestsAvailable = 5;
	
	//player cards - keep track of which cards each player has. first index is player num, second index is weapon, room, guest
	int[][] playerCards = new int[6][3];

	//pool cards
	final int[] poolWeapon = new int[2];
	final int[] poolRoom = new int[2];


	/*
	 * default constructor
	 * @param debugValue - set debug mode
	 */
	public cardTracker(boolean debugValue)
	{
		//debug
		debug = debugValue;
	
		//weaponCards
		weaponCard[0] = "Ax";
		weaponCard[1] = "Bat";
		weaponCard[2] = "Candlestick";
		weaponCard[3] = "Dumbbell";
		weaponCard[4] = "Knife";
		weaponCard[5] = "Pistol";
		weaponCard[6] = "Poison";
		weaponCard[7] = "Rope";
		weaponCard[8] = "Trophy";

		//room cards
		roomCard[0] = "Dining Room";
		roomCard[1] = "Guest House";
		roomCard[2] = "Hall";
		roomCard[3] = "Kitchen";
		roomCard[4] = "Living Room";
		roomCard[5] = "Observatory";
		roomCard[6] = "Patio";
		roomCard[7] = "Spa";
		roomCard[8] = "Theater";
	
		//guest cards
		guestCard[0] = "Mustard";
		guestCard[1] = "Scarlet";
		guestCard[2] = "White";
		guestCard[3] = "Green";
		guestCard[4] = "Peacock";
		guestCard[5] = "Plum";
	
		
		//set playerCards to -1
		for (int i = 0; i < 6; i++) {
			for (int c = 0; c < 3; c++) {
				playerCards[i][c] = -1;
			}
		}

		//call setMurder to determine what happened
		setMurder();

		//assign remaining cards
		assignRemainingCards();
		System.out.println("assignRemainingCards() done");
	}

	/* 
	 * determine the murderer, room, and weapon
	 */
	public void setMurder()
	{
		//create random number generator
		Random generator = new Random();

		//generate muder weapon, person, room
		murderWeapon = generator.nextInt(9);
		murderRoom = generator.nextInt(9);
		murderGuest = generator.nextInt(5);

		//mark as taken
		weaponTaken[murderWeapon] = true;
		roomTaken[murderRoom] = true;
		guestTaken[murderGuest] = true;

		//debug
		if (debug) {
			System.out.println("murderWeapon: " + weaponCard[murderWeapon]);
			System.out.println("murderRoom: " + roomCard[murderRoom]);
			System.out.println("murderGuest: " + guestCard[murderGuest]);

		}
	}

	/*
	 * @return murderString - get murder as string
	 */
	public String getMurder()
	{
		String murderString = "It was " + guestCard[murderGuest] + ", in the " + roomCard[murderRoom] + ", with the " + weaponCard[murderWeapon];
		return murderString;
	}

	/*
	 * @return murder as int
	 */
	public int[] getMurderInt()
	{
		int[] output = new int[3];
		output[0] = murderGuest;
		output[1] = murderRoom;
		output[2] = murderWeapon;

		return output;
	}

	/* 
	 * assign remaining cards to players
	 */
	public void assignRemainingCards()
	{
		//generate random numbers
		Random generator = new Random();

		//assign remaining cards to players
		for (int i = 0; i < 6; i++) {
			
			System.out.println("guestsAvailable: " + guestsAvailable);	
			
			//guest cards
			if (guestsAvailable > 0) {

				//make sure there are still guests available
				while (playerCards[i][0] == -1) {
				
					int randomNum = generator.nextInt(6);
					System.out.println(randomNum);
				
					//make sure randomNum is not already taken 
					if (guestTaken[randomNum] != true) {
						System.out.println("false called");
						playerCards[i][0] = randomNum;
						guestTaken[randomNum] = true;
						guestsAvailable--;

						System.out.println(i + ": guest: " + guestCard[randomNum]); 
					} else {
						System.out.println("true called");
					}
				}
			} //end if
			
			//room cards
			while (playerCards[i][1] == -1) {
				int randomNum = generator.nextInt(8);
				
				//make sure randomNum is not already taken 
				if (roomTaken[randomNum] != true) {
					playerCards[i][1] = randomNum;
					roomTaken[randomNum] = true;
					
					System.out.println(i + ": room: " + roomCard[randomNum]); 
				}
			}
			
			//weapon cards
			while (playerCards[i][2] == -1) {
				int randomNum = generator.nextInt(8);
				
				System.out.println("\nplayer " + i +" randomNum: " + randomNum);
				
				//make sure randomNum is not already taken 
				if (weaponTaken[randomNum] != true) {
					playerCards[i][2] = randomNum;
					weaponTaken[randomNum] = true;

					System.out.println(i +": weapon: " + weaponCard[randomNum]); 
				}
			}
		} //end for loop

		//assign remaining cards to pool
		for (int loop = 0; loop < 2; loop++) {
			for (int i = 0; i < weaponTaken.length; i++) {
				if (weaponTaken[i] == false) {
					poolWeapon[loop] = i;
					weaponTaken[i] = true;
					System.out.println("weapon " + i + ": " + weaponTaken[i]);
				}
			}

			for (int i =0; i < roomTaken.length; i++) {
				if (roomTaken[i] == false) {
					poolRoom[loop] = i;
					roomTaken[i] = true;
					System.out.println("room " + i + ": " + roomTaken[i]);
				}
			}
		}
	}

	/* 
	 * @param player - int - display player cards
	 */
	public String getPlayerCards(int player)
	{
		//variable for output
		String out;

		//assign each card from playerCards
		int weapon = playerCards[player][0];
		int room = playerCards[player][1];
		int guest = playerCards[player][2];

		//build output
		out = player + ": " +weaponCard[weapon] + ", " + roomCard[room];
	       	if (guest != -1) {
			out+= ", " + guestCard[guest];
		}	

		return out;
	}

	/*
	 * @return playerCardsAsInt - Used by rumor and accusations
	 */
	public int[] getPlayerCardsAsInt(int player)
	{
		//variable for output
		int[] out = new int[3];

		int weapon = -1;
		int room = -1;
		int guest = -1;


		try {
		//assign each card from playerCards
			weapon = playerCards[player][0];
			room = playerCards[player][1];
			guest = playerCards[player][2];
		} catch (Exception e) {
			guest = -1;
		}	

		//build output as guest, room, weapon
		out[0] = guest;
		out[1] = room;
		out[2] = weapon;

		return out;
		
	}
}

