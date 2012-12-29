/*Chris Fortier
 * csci e-50b
 *
 * term project
 *
 * This file will create an abstract class that controls the game pieces and movements. Two children classes of computer player and human player will be called.
 *
 */


import java.util.*;
import java.awt.*;
import javax.swing.*;

public class gamePieceComputer extends gamePiece
{
	/*
	 * initial constructor
	 * @param n - String name of piece //ended up not using
	 * @param m - char m to use as the piece marker //ended up not using
	 * @param colorR - int for red value
	 * @param colorG - int for green value
	 * @param colorB - int for blue value
	 */
	gamePieceComputer(String n, char m, int colorR, int colorG, int colorB)
	{
		//call super with current character
		super(n, m, colorR, colorG, colorB);	
	}

}
