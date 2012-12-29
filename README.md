java_Clue_final
===============

This was a term project for a Java class. I implemented the classic boardgame Clue.

Files:

Clue.java: main entry for program. Initializes players and controls all game play.

GameBoard.java: Extends jframe and creates the frame for the graphical display. 

boardPanel.java: Constructs the actual game board using jbuttons as tiles. Handles all button actionlistener's.

cardTracker.java: Tracks each players cards, assigns cards to players.

gamePiece.java: An abstract class that handles all of the pieces for the player and computer.

gamePieceComputer.java & gamePieceHuman.java: Both extend gamePiece.java and details of each piece

positionTracker.java: Tracks position of every character in game, handles dice rolls, and space availability.

rumorFrame.java: Allows user to create a rumor.   

clue.sh: shell script to compile all files. 
