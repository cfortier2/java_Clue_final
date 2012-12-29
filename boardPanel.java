/*  @author Chris Fortier
 *
 * CSCI e-50b Term Project
 *
 * boardPanel.java 
 *
 * File is called from GameBoard and constructs a panel of 35 squares wide by 25 squares high to use for the board. It also contains a JTextArea for all output to user. Each of the spaces and rooms are actually buttons.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class boardPanel extends JPanel 
{
	//class variables
	myButton btn[] = new myButton[1000]; //instantiate enough myButtons for each square.
	int activeMyButtons = 0; //track the amount of myButtons in use.
	boolean debugBoardPanel = false; //track debug mode
	private int status = 1; //track status of game
	private boolean spaceSelected = false; //track if a VALID space was selected
	private int newPositionColumn = -1; //used to store newPositions for GameBoard to call
	private int newPositionRow = -1; //used to store newPositions for GameBoard to call
	private boolean startRumor = false; //track if a new rumor was started

	//create GridBagConstraints for objects in class
	GridBagConstraints cons = new GridBagConstraints();

	//create global button names AND store a reference to them in an array [allRooms]
	JButton[] allRooms = new JButton[10];
	JButton btnSpa;
	JButton btnTheater;
	JButton btnLivingRoom;
	JButton btnObservatory;
	JButton btnPatio;
	JButton btnPool;
	JButton btnHall;
	JButton btnKitchen;
	JButton btnDiningRoom;
	JButton btnGuestHouse;

	//create global textOut for bottom panel. Accessible to whole board
	String textOut = "Welcome to Clue!";
	JTextArea bottomText = new JTextArea(textOut, 5 , 1);
	JScrollPane jsp = new JScrollPane(bottomText);

	//default room color
	Color defaultRoomColor = new Color(220, 209, 193);
	Color defaultPoolColor = new Color(118, 184, 177);

	/*
	 * main constructor
	 * @param debug - boolean for debug mode
	 */
	public boardPanel(boolean debug)
	{
		//initial constructor
		super();
		debugBoardPanel = debug;
		
		//continue defining boardPanel
		this.setLayout(new GridBagLayout());
		cons.fill = GridBagConstraints.BOTH;
		cons.insets = new Insets(0,0,0,0);
		cons.anchor = GridBagConstraints.FIRST_LINE_START;
		this.setVisible(true);

		//button preferred size
		Dimension btnSize = new Dimension(25,25);


	// top row ------------------------------------------------------------------------------------------	
		//spa
		btnSpa = new JButton("Spa");
		allRooms[0] = btnSpa;
		btnSpa.setPreferredSize(btnSize);
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 5;
		cons.gridheight = 5;
		btnSpa.setBackground(defaultRoomColor);
		btnSpa.setOpaque(true);
		btnSpa.setBorderPainted(false);
		btnSpa.addActionListener(new myActionListener());
		this.add(btnSpa, cons);
	
		//space buttons
		for (int y = 0; y < 5; y++) {	
			for (int x = 5; x < 10; x++) {
				createBlankButtons(x, y, btnSize);		
			} 
		}
			
		//theater
		btnTheater = new JButton("Theater");
		allRooms[1] = btnTheater;
		btnTheater.setPreferredSize(btnSize);
		cons.gridx = 10;
		cons.gridy = 0;
		cons.gridwidth = 5;
		cons.gridheight = 5;
		btnTheater.setOpaque(true);
		btnTheater.setBorderPainted(false);
		btnTheater.setBackground(defaultRoomColor);
		btnTheater.addActionListener(new myActionListener());
		this.add(btnTheater, cons);
	
		//space buttons
		for (int y = 0; y < 5; y++) {	
			for (int x = 15; x < 20; x++) {
				createBlankButtons(x, y, btnSize);		
			} 
		}
			
		//Living Room
		btnLivingRoom = new JButton("Living Room");
		allRooms[2] = btnLivingRoom;
		btnLivingRoom.setPreferredSize(btnSize);
		cons.gridx = 20;
		cons.gridy = 0;
		cons.gridwidth = 5;
		cons.gridheight = 5;
		btnLivingRoom.setBackground(defaultRoomColor);
		btnLivingRoom.setOpaque(true);
		btnLivingRoom.setBorderPainted(false);
		btnLivingRoom.addActionListener(new myActionListener());
		this.add(btnLivingRoom, cons);
	
		//space buttons
		for (int y = 0; y < 5; y++) {	
			for (int x = 25; x < 30; x++) {
				createBlankButtons(x, y, btnSize);		
			} 
		}
			
		//observatory
		btnObservatory = new JButton("Observatory");
		allRooms[3] = btnObservatory;
		btnObservatory.setPreferredSize(btnSize);
		cons.gridx = 30;
		cons.gridy = 0;
		cons.gridwidth = 5;
		cons.gridheight = 5;
		btnObservatory.setBackground(defaultRoomColor);
		btnObservatory.setOpaque(true);
		btnObservatory.setBorderPainted(false);
		btnObservatory.addActionListener(new myActionListener());
		this.add(btnObservatory, cons);
	
	// top to middle spaces  ------------------------------------------------------------------------------------------	
	
		//space buttons
		for (int y = 5; y < 10; y++) {	
			for (int x = 0; x < 35; x++) {
				createBlankButtons(x, y, btnSize);		
			} 
		}
		
	// middle row ------------------------------------------------------------------------------------------	
		
		//Patio
		btnPatio = new JButton("Patio");
		allRooms[4] = btnPatio;
		btnPatio.setPreferredSize(btnSize);
		cons.gridx = 0;
		cons.gridy = 10;
		cons.gridwidth = 5;
		cons.gridheight = 5;
		btnPatio.setBackground(defaultRoomColor);
		btnPatio.setOpaque(true);
		btnPatio.setBorderPainted(false);
		btnPatio.addActionListener(new myActionListener());
		this.add(btnPatio, cons);
	
		//space buttons
		for (int y = 10; y < 15; y++) {	
			for (int x = 5; x < 10; x++) {
				createBlankButtons(x, y, btnSize);		
			} 
		}
			
		//pool
		btnPool = new JButton("Pool");
		allRooms[5] = btnPool;
		btnPool.setPreferredSize(btnSize);
		cons.gridx = 10;
		cons.gridy = 10;
		cons.gridwidth = 15;
		cons.gridheight = 5;
		btnPool.setBackground(defaultPoolColor);
		btnPool.setOpaque(true);
		btnPool.setBorderPainted(false);
		btnPool.addActionListener(new myActionListener());
		this.add(btnPool, cons);
	
		//space buttons
		for (int y = 10; y < 15; y++) {	
			for (int x = 25; x < 30; x++) {
				createBlankButtons(x, y, btnSize);		
			} 
		}
			
		//Hall
		btnHall = new JButton("Hall");
		allRooms[6] = btnHall;
		btnHall.setPreferredSize(btnSize);
		cons.gridx = 30;
		cons.gridy = 10;
		cons.gridwidth = 5;
		cons.gridheight = 5;
		btnHall.setBackground(defaultRoomColor);
		btnHall.setOpaque(true);
		btnHall.setBorderPainted(false);
		btnHall.addActionListener(new myActionListener());
		this.add(btnHall, cons);
	
	// middle to bottom spaces  ------------------------------------------------------------------------------------------	
	
		//space buttons
		for (int y = 15; y < 20; y++) {	
			for (int x = 0; x < 35; x++) {
				createBlankButtons(x, y, btnSize);		
			} 
		}
		
	
	// bottom row ------------------------------------------------------------------------------------------	
			
		//Kitchen
		btnKitchen = new JButton("Kitchen");
		allRooms[7] = btnKitchen;
		btnKitchen.setPreferredSize(btnSize);
		cons.gridx = 0;
		cons.gridy = 20;
		cons.gridwidth = 5;
		cons.gridheight = 5;
		btnKitchen.setBackground(defaultRoomColor);
		btnKitchen.setOpaque(true);
		btnKitchen.setBorderPainted(false);
		btnKitchen.addActionListener(new myActionListener());
		this.add(btnKitchen, cons);
	
		//space buttons
		for (int y = 20; y < 25; y++) {	
			for (int x = 5; x < 10; x++) {
				createBlankButtons(x, y, btnSize);		
			} 
		}
			
		//dining room
		btnDiningRoom = new JButton("Dining Room");
		allRooms[8] = btnDiningRoom;
		btnPool.setPreferredSize(btnSize);
		cons.gridx = 10;
		cons.gridy = 20;
		cons.gridwidth = 15;
		cons.gridheight = 5;
		btnDiningRoom.setBackground(defaultRoomColor);
		btnDiningRoom.setOpaque(true);
		btnDiningRoom.setBorderPainted(false);
		btnDiningRoom.addActionListener(new myActionListener());
		this.add(btnDiningRoom, cons);
	
		//space buttons
		for (int y = 20; y < 25; y++) {	
			for (int x = 25; x < 30; x++) {
				createBlankButtons(x, y, btnSize);		
			} 
		}
			
		//Guest House
		btnGuestHouse = new JButton("Guest House");
		allRooms[9] = btnGuestHouse;
		btnGuestHouse.setPreferredSize(btnSize);
		cons.gridx = 30;
		cons.gridy = 20;
		cons.gridwidth = 5;
		cons.gridheight = 5;
		btnGuestHouse.setBackground(defaultRoomColor);
		btnGuestHouse.setOpaque(true);
		btnGuestHouse.setBorderPainted(false);
		btnGuestHouse.addActionListener(new myActionListener());
		this.add(btnGuestHouse, cons);

	// textArea ------------------------------------------------------------------------------------------	
		
		//create textarea for output
		cons.gridx = 0;
		cons.gridy = 25;
		cons.gridwidth = 35;
		this.add(jsp, cons);
	}	

	//listener that listens to all buttons and outputs which button was pressed
	class myActionListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
 			//method variable for output and button pressed
			String output = "";
			String buttonPressed = "";
			int btnColumn = 0;
			int btnRow = 0;
		
			//if game status = 2 / dice has been rolled. Determine which button was pressed
			if (status == 2) {
				//iterates through every btn[i] and determines which one was pressed
				for (int i = 0; i <= activeMyButtons; i++) {
					if (e.getSource() == btn[i]) {
						buttonPressed = "btn[" + i +"]";
						btnColumn = btn[i].positionX;
						btnRow = btn[i].positionY;

						//check if button is blue, meaning it is available
						if ((btn[i].getBackground() == Color.blue) && (spaceSelected == false)) {
							btn[i].setBackground(Color.red);
							spaceSelected = true;
							setOutput("You selected: btn[" + i +"]");

							//set new positions so that control loop will move the position
							newPositionColumn = btnColumn;
							newPositionRow = btnRow;
							
							//send output for debug
							if (debugBoardPanel) { System.out.println("newPositionColumn: " + newPositionColumn + ", newPositionRow: " + newPositionRow); }

						} else if (spaceSelected == true) {
							setOutput("You have already made a selection");	
						}else {
							setOutput("btn[" + i + "]: That space is not available. Please select another.");
						}
					}
				}

				//iterate through each of the named buttons and assign to index tracker selectedRoom.
				int selectedRoom = -1;
				
				if (e.getSource() == btnSpa) { 
					selectedRoom = 0; 
					buttonPressed = "btnSpa";
					btnColumn = 4;
					btnRow = 4;
				}
				
				if (e.getSource() == btnTheater) { 
					selectedRoom = 1;
					buttonPressed = "btnTheater";
					btnColumn = 10;
					btnRow = 4;
				}

				if (e.getSource() == btnLivingRoom) { 
					selectedRoom = 2;
					buttonPressed = "btnLivingRoom";
					btnColumn = 10;
					btnRow = 4;
				}

				if (e.getSource() == btnObservatory) { 
					selectedRoom = 3; 
					buttonPressed = "btnObservatory";
					btnColumn = 30;
					btnRow = 4;
				}

				if (e.getSource() == btnPatio) { 
					selectedRoom = 4; 
					buttonPressed = "btnPatio";
					btnColumn = 4;
					btnRow = 12;
				}

				if (e.getSource() == btnPool) { 
					selectedRoom = 5;
					buttonPressed = "btnPool";
					btnColumn = 17;
					btnRow = 12;
				}

				if (e.getSource() == btnHall) { 	
					selectedRoom = 6; 
					buttonPressed = "btnHall";
					btnColumn = 30;
					btnRow = 12;
				}

				if (e.getSource() == btnKitchen) { 
					selectedRoom = 7; 
					buttonPressed = "btnKitchen";
					btnColumn = 4;
					btnRow = 20;
				}

				if (e.getSource() == btnDiningRoom) { 
					selectedRoom = 8; 
					buttonPressed = "btnDiningRoom";
					btnColumn = 17;
					btnRow = 20;
				}

				if (e.getSource() == btnGuestHouse) { 
					selectedRoom = 9; 
					buttonPressed = "btnGuestHouse";
					btnColumn = 30;
					btnRow = 20;
				}
				
				//if a room is selected, apply actions to that room			
				if (selectedRoom > -1)	{
					//if space is available
					if ((allRooms[selectedRoom].getBackground() == Color.cyan) && spaceSelected == false) {
						spaceSelected = true;
						setOutput("You selected: " + buttonPressed);
						newPositionColumn = btnColumn;
						newPositionRow = btnRow;
						startRumor = true;
					} else if (spaceSelected == true) {
						setOutput("You have already made a selection.");
					} else {
						setOutput(buttonPressed + " is not available. Please select another.");
					}
				}


				//create output string and pass to setOutput()
				//output = buttonPressed + " was pressed.";
				//output += " [positionColumn: " + btnColumn + "]" + "[positionRow: " + btnRow + "]";
				//setOutput(output);
			
				
			}
		}
	}

	/*
	 * custom class to extend JButton and add references to current position, button number, color, and toolTipText. Used extensively for the spaces.
	 */
	class myButton extends JButton
	{
		/*
		 * instance variables for each button
		 */
		int positionX;
		int positionY;
		int button;
		private Color defaultColor = Color.white;
		private String defaultToolTipText = "";

		/*
		 * default constructor
		 */
		public myButton()
		{
			super();
		}

		/*
		 * overloaded constructor accepting String as button text
		 */
		public myButton(String s)
		{
			super(s);
		}

		/*
		 * @return button number
		 */
		public int getButton()
		{
			return button;
		}

		/*
		 * reset background color to default
		 */
		public void resetColor()
		{
			this.setBackground(defaultColor);
		}

		/*
		 * @param c - set default color
		 */
		public void setDefaultColor(Color c)
		{
			defaultColor = c;
		}

		/*
		 * @return defaultColor 
		 */
		public Color getDefaultColor()
		{
			return defaultColor;
		}

		/*
		 * @param s - sets defaultToolTopText
		 */
		public void setDefaultToolTipText(String s)
		{
			defaultToolTipText = s;
		}

		/*
		 * @return defaultToolTipText
		 */
		public String getDefaultToolTipText()
		{
			return defaultToolTipText;
		}

		/*
		 * reset toolTipText to default
		 */
		public void resetToolTipText()
		{
			this.setToolTipText(defaultToolTipText);
		}

		/*
		 * reset text to null
		 */
		public void resetText()
		{
			this.setText("");
		}

		/*
		 * reset button to defaults
		 */
		public void reset()
		{
			this.resetColor();
			this.resetToolTipText();
			this.resetText();
		}

	}

	/* 
	 * helper class to initialize empty spaces
	 * @param int x - column of button
	 * @param int y - row of button
	 * @param Dimension btnSize - sets the size of the button
     */	
	public void createBlankButtons(int x, int y, Dimension btnSize)
	{
		String output = "";
		Font clueFont = new Font("Arial", Font.PLAIN, 8);

		//initialize next myButton
		btn[activeMyButtons] = new myButton();
		String toolTip = ("" + activeMyButtons);
		btn[activeMyButtons].setDefaultToolTipText(toolTip);
		btn[activeMyButtons].setToolTipText(toolTip);

		//get next available myButton and assign current position
		btn[activeMyButtons].positionX = x;
		btn[activeMyButtons].positionY = y;
		btn[activeMyButtons].button = activeMyButtons;
		btn[activeMyButtons].setPreferredSize(btnSize);
		btn[activeMyButtons].setFont(clueFont);

		//assign GridBagConstraints for //position
		cons.gridx = x;
		cons.gridy = y;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.ipadx = 0;
		cons.ipady = 0;
			
			//determine position and set alternating color
			if ( ((x % 2 == 0) && (y % 2 == 0)) || ((x % 2 != 0) && (y % 2 != 0)) ) {
				btn[activeMyButtons].setBackground(new Color(152,86,112));
				btn[activeMyButtons].setDefaultColor(new Color(152,86,112));
			} else {
				
				btn[activeMyButtons].setBackground(new Color(128,48,82));
				btn[activeMyButtons].setDefaultColor(new Color(128,48,82));
			}
			
			btn[activeMyButtons].setOpaque(true);
			btn[activeMyButtons].setBorderPainted(false);
			
		//add button to boardPanel
		this.add(btn[activeMyButtons], cons);
		btn[activeMyButtons].addActionListener(new myActionListener());
		validate();
	
		//pass output to setOutput()
		output = "activeMyButtons: " + activeMyButtons + " " + btn[activeMyButtons].getDefaultColor();
		//setOutput(output);
			
		activeMyButtons++;

	}

	/* 
	 * @param String s - send s to output textArea
	 */
	public void setOutput(String s)
	{	
		//if (debugBoardPanel) {
			bottomText.append("\n" + s);
			bottomText.setCaretPosition(bottomText.getDocument().getLength());
		//}
	}

	/*
	 * method to reset boardPanel to default
	 */
	public void resetBoard()
	{
		//iterates through every btn[i] and reset
		for (int i = 0; i < activeMyButtons; i++) {
			//reset button if it is not occupied
			if (btn[i].getText() == "") {
				btn[i].reset();
			}
		}

		//reset rooms
		for (int i = 0; i < allRooms.length - 1; i++) {
			allRooms[i].setBackground(defaultRoomColor);
		}

	}

	/* 
	 * @param char[][] gameBoard - iterate through each button and highlight if it is available
	 */
	public void redrawBoard(char[][] gameBoard)
	{
		if (debugBoardPanel) { System.out.println("[492] boardPanel.redrawBoard(): Started."); }
		
		//iterate through each button
		for (int i = 0; i < activeMyButtons; i++) {
			
			//switch case for value of btn
			switch (gameBoard[btn[i].positionY][btn[i].positionX]) {
				case 'A': btn[i].setBackground(Color.blue); //space is available
					break;
				case 'M': btn[i].setBackground(Color.yellow); //Mustard
					  btn[i].setText("M");
					  btn[i].setToolTipText("Colonel Mustard");
					break;	
				case 'S': btn[i].setBackground(Color.red); //Scarlett
				  	  btn[i].setText("S");
				          btn[i].setToolTipText("Miss Scarlett");
					  break;
				case 'W': btn[i].setBackground(Color.white); //White
					  btn[i].setText("W");
					  btn[i].setToolTipText("Mrs. White");	  
					  break;
				case 'B': btn[i].setBackground(Color.blue); //Peacock
					  btn[i].setText("B");
					  btn[i].setToolTipText("Peacock");	  
					  break;
				case 'G': btn[i].setBackground(Color.green); //Green
					  btn[i].setText("G");
					  btn[i].setToolTipText("Green");	  
					  break;
				case 'P': btn[i].setBackground(Color.magenta); //Purple
					  btn[i].setText("P");
					  btn[i].setToolTipText("Plum");	  
					  break;
				case ' ': btn[i].reset();	
					  break;
			}

			//if (debugBoardPanel) { System.out.println("boardPanel.redrawBoard(): btn[" + i +"]"); }
			/*
			if (gameBoard[btn[i].positionY][btn[i].positionX] == 'A') {
				btn[i].setBackground(Color.blue);
			}*/
		}

		//see if any of the rooms are available
		for (int row = 0; row < 25; row++) {
			for (int col = 0; col < 35; col++) {
				if (gameBoard[row][col] == 'A'){ 
				
					//spa
					if (row < 5 && col < 5) {
						btnSpa.setBackground(Color.cyan);
					}

					//theater
					if (row < 5 && (col > 9 && col < 15)) {
						btnTheater.setBackground(Color.cyan);
					}

					//livingRoom
					if (row < 5 && (col > 19 && col < 25)) {
						btnLivingRoom.setBackground(Color.cyan);
					}

					//observatory
					if (row < 5 && col > 29) {
						btnObservatory.setBackground(Color.cyan);
					}

					//patio
					if ((row > 9 && row < 15) && col < 5) {
						btnPatio.setBackground(Color.cyan);
					}

					//pool - don't code yet
					
					//hall
					if ((row > 9 && row < 15) && col > 29) {
						btnHall.setBackground(Color.cyan);
					}
					
					//kitchen
					if (row > 19 && col < 5) {
						btnKitchen.setBackground(Color.cyan);
					}

					//diningRoom
					if (row > 19 && (col > 10 && col < 26)) {
						btnDiningRoom.setBackground(Color.cyan);
					}
					
					//guestHouse
					if (row > 19 && col > 29) {
						btnGuestHouse.setBackground(Color.cyan);
					}
				}
			}
		}
		
		if (debugBoardPanel) { System.out.println("[514] boardPanel.redrawBoard(): Complete."); }
	}

	/* 
	 * @param boardStatus - set status of game
	 */
	public void setStatus(int boardStatus)
	{
		status = boardStatus;
	}

	/* 
	 * @param i - accepts int i for person number 
	 * @return - new positions
	 */
	public int[] getNewPosition(int i)
	{
		//logic to auto select for computer player:
		if (i != 0) 
		{
			//while space is not selected, randomly select rooms and spaces. 
			while (spaceSelected == false) {
				
				//generate random number
				Random generator = new Random();
				int room = generator.nextInt(10);

				//random select a room
				allRooms[room].doClick();

				//generate random space
				int space = generator.nextInt(524);
				
				//randomly select a space
				btn[space].doClick();

			}

		}

		//if (debugBoardPanel) { System.out.println("[526] boardPanel.getNewPosition(): Called.") ; }
		int[] newPositions = {newPositionColumn, newPositionRow};
		
		//if (debugBoardPanel) { System.out.println("[530] boardPanel.getNewPosition().newPositions: " + newPositions[0] + ", " + newPositions[1]); }
		
		return newPositions;
	}

	/* 
	 * reset everything for nextRoll
	 */
	public void nextRoll()
	{
		spaceSelected = false;
		newPositionColumn = -1;
		newPositionRow = -1;
		status = 1;
		startRumor = false;
	}

	/* 
	 * @return startRumor - status of rumor
	 */
	public boolean getStartRumor()
	{
		return startRumor;
	}

}	

