/* 
 * @author Chris Fortier
 *
 * CSCI e-50b Term Project
 *
 * This is the main user interface the program. It is a custom frame the extends JFrame. It holds a boardPanel that handles the actual gameboard and an output textarea.  It also holds the buttons to interact with the game.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class GameBoard extends JFrame
{
	//global variables
	
	//set debug level for output
	boolean debugGameBoard = true;
	
	//declare buttons
	JButton btnRevealMurder;
	JButton btnExit;
	JButton btnResetBoard;
	JButton btnRollDice;
	
	//track if it is a newRoll
	private boolean newRoll = false;
	
	//declare the boardPanel
	boardPanel leftPanel;
	
	//track murder and rumors
	private String murderString;
	private int[] murderInt;
	rumorFrame rumor;
	boolean gbStartRumor;

	/*
	 * @param debug - main constructor. takes a boolean value for dubgging
	 */
	public GameBoard(boolean debug) throws InterruptedException
	{
		//initial constructor
		super("Clue by Chris Fortier");
		debugGameBoard = debug;
	
		//initialize a rumorPanel
		rumor = new rumorFrame(debug);
		rumor.validate();

		//layout board
		int frameWidth = 1025;
		int frameHeight = 1000;
		this.setSize(frameWidth, frameHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font frameFont = new Font("Arial", Font.BOLD,24);
		//setBackground(Color.black);
		this.setLocationRelativeTo(null); //centers on screen		
		this.setVisible(true);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.insets = new Insets(0,0,0,0);

		//leftPanel - contains main board
		leftPanel = new boardPanel(debugGameBoard);
		cons.gridx = 0;
		cons.gridy = 0;
		leftPanel.setVisible(true);
		//leftPanel.setBackground(Color.black);
		this.add(leftPanel, cons);
		validate();

		//bottomPanel - displays output
		JPanel bottomPanel = new JPanel();
		cons.gridx = 0;
		cons.gridy = 1;
		bottomPanel.setVisible(true);
		bottomPanel.setBackground(Color.black);
		this.add(bottomPanel, cons);
		validate();

			//add button to display murder
			btnRevealMurder = new JButton("Reveal Murder");
			bottomPanel.add(btnRevealMurder);
			btnRevealMurder.addActionListener(new myActionListener());
			btnRevealMurder.setVisible(false); //only used while debugging
			validate();

			//add rollDice button to bottomPanel
			btnRollDice = new JButton("Roll the Dice");
			bottomPanel.add(btnRollDice);
			btnRollDice.addActionListener(new myActionListener());
			validate();

			//add resetBoard button to bottomPanel
			btnResetBoard = new JButton("Reset Board");
			btnResetBoard.setVisible(false);
			bottomPanel.add(btnResetBoard);
			btnResetBoard.addActionListener(new myActionListener());
			validate();

			//add exit button to bottomPanel
			btnExit = new JButton("Exit");
			bottomPanel.add(btnExit);
			btnExit.addActionListener(new myActionListener());
			validate();	
	}	

	/*
	 * @return value of newRoll
	 */
	public boolean isNewRoll()
	{	
		return newRoll;
	}

	/*
	 * @param roll - sets newRoll
	 */
	public void setNewRoll(boolean roll)
	{
		newRoll = roll;
	}

	/*
	 * listener for all button clicks
	 */
	class myActionListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//exit code
			if (e.getSource() == btnExit) {
				System.exit(10);
			}
			
			//reset board to default
			if (e.getSource() == btnResetBoard) {
				leftPanel.resetBoard();
			}

			//roll dice button
			if (e.getSource() == btnRollDice) {
				newRoll = true;
				//System.out.println("gameBoard: newRoll: " + newRoll);
			}

			//reveal murder
			if (e.getSource() == btnRevealMurder) {
				sendOutput(murderString);
			}

		}
	}

	/*
	 * redraws the gameBoard
	 */
	public void redraw(char[][] board)
	{
		if (debugGameBoard) { System.out.println("[117] GameBoard.redraw(): Started."); }
		
		//redraw objects
		leftPanel.redrawBoard(board);
		leftPanel.validate();
		
		if (debugGameBoard) { System.out.println("[123] GameBoard.redraw(): Completed."); }
	}

	/*
	 * calls leftPanel.resetBoard() to reset the display between turns
	 */
	public void resetBoard()
	{
		leftPanel.resetBoard();
	}

	/* 
	 * send output to window in leftPanel
	 */
	public void sendOutput(String s)
	{
		leftPanel.setOutput(s);
	}

	/*
	 * @param status - accept game status as int and pass to boardPanel
	 */
	public void setStatus(int status)
	{
		leftPanel.setStatus(status);
	}

	/*
	 * @param i - takes an int representing the currentPiece 
	 * @return - new positions from leftPanel
	 */
	public int[] getNewPosition(int i) throws InterruptedException
	{
		//if (debugGameBoard) { System.out.println("[141] GameBoard.getNewPosition(): Called.") ; }
		
		int[] tempPos = leftPanel.getNewPosition(i);
		//System.out.println(tempPos[0]);
		Thread.sleep(100);

		//if (debugGameBoard) { System.out.println("[145] GameBoard.getNewPosition().tempPos = " + tempPos[0] + ", " + tempPos[1]); }

		return tempPos;
	}

	/* 
	 * reset leftPanel for nextRoll
	 */
	public void nextRoll(int i)
	{
		//i is 0 for human player. 
		if (i == 0) {
			this.setNewRoll(false);
		} else {
			this.setNewRoll(true);
		}

		leftPanel.nextRoll();
		this.sendOutput("Next Roll...");
		rumor.setVisible(false);
		rumor.reset();
	}

	/* 
	 * @param s - String representing the murder
	 */
	public void setMurder(String s)
	{
		murderString = s;
	}

	/* 
	 * @return murderString
	 */
	public String getMurderString()
	{
		return murderString;
	}

	/*
	 * @return status of rumor
	 */
	public boolean getRumorStatus()
	{
		gbStartRumor = leftPanel.getStartRumor();
		return leftPanel.getStartRumor();
	}

	/*
	 * start a rumor by making rumorFrame visible
	 */
	public void startRumor() 
	{
		rumor.setVisible(true);
	}

	/* 
	 * start rumor for computer player
	 * @param iPerson person as integer
     * @param sPerson person name as string
     */	 
	public void startRumorPerson(int iPerson, String sPerson)
	{
		rumor.selectPerson(iPerson, sPerson);
	}

	/* 
	 * start rumor for computer player
	 * @param iRoom room as integer
     * @param sRoom room name as string
     */	 
	public void startRumorRoom(int iRoom, String sRoom)
	{
		rumor.selectRoom(iRoom, sRoom);
	}
	
	/* 
	 * start rumor for computer player
	 * @param iWeapon weapon as integer
     * @param sweapon weapon name as string
     */	 
	public void startRumorWeapon(int iWeapon, String sWeapon)
	{
		rumor.selectWeapon(iWeapon, sWeapon);
	}
	
	/*
	 * @return boolean is rumor formed
	 */
	public boolean rumorFormed()
	{
		return rumor.isRumorFormed();
	
	}

	/*
	 * @return getRumor as int array
	 */
	public int[] getRumorAsInt()
	{
		return rumor.getRumorInt();
	}

	/*
	 * @return getRumor as string
	 */
	public String getRumorAsStr()
	{
		return rumor.getRumorStr();
	}

	/*
	 * @param s - send message to rumor output
	 */
	public void sendRumorOut(String s)
	{
		rumor.sendOut(s);
	}

	/*
	 * @return isVisible of rumorFrame
	 */
	public boolean isRumorFrameShowing()
	{
		return rumor.isShowing();
	}

}
