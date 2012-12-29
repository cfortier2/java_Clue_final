#!/bin/bash

echo "javac rumorFrame.java"
javac rumorFrame.java

echo "javac cardTracker.java"
javac cardTracker.java

echo "javac positionTracker.java"
javac positionTracker.java

echo "javac boardPanel.java"
javac boardPanel.java

echo "javac gamePieceHuman.java"
javac gamePieceHuman.java

echo "javac gamePiece.java"
javac gamePiece.java

echo "javac GameBoard.java"
javac GameBoard.java

echo "javac Clue.java"
javac Clue.java

echo "run java Clue?"

read keyboard

if [ "$keyboard" == "y" ] 
then	java Clue
else	exit 1
fi
