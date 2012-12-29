/*  @author Chris Fortier
 *
 * CSCI e-50b Term Project
 *
 * rumorFrame extends JFrame and presents a custom dialog allowing the player to select a rumor/accusation.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class rumorFrame extends JFrame 
{
	//debug variable
	boolean debugPanel = false;

	//create panels to hold buttons for user selection
	JPanel characterPanel = new JPanel(new GridLayout(0,1));
	JPanel weaponPanel = new JPanel(new GridLayout(0,1));
	JPanel roomPanel = new JPanel(new GridLayout(0,1));

	//create continue button
	JButton btnContinue = new JButton("Continue");

	//player selections
	int personSelected = -1;
	int weaponSelected = -1;
	int roomSelected = -1;
	String personSelectedStr = "";
	String weaponSelectedStr = "";
	String roomSelectedStr = "";

	/*instantiate buttons*/

	//player buttons
	JButton btnGreen = new JButton("Green");
	JButton btnWhite = new JButton("White");
	JButton btnScarlet = new JButton("Scarlet");
	JButton btnPlum = new JButton("Plum");
	JButton btnMustard = new JButton("Mustard");
	JButton btnPeacock = new JButton("Peacock");
	
	//weapon buttons
	JButton btnTrophy = new JButton("Trophy");
	JButton btnRope = new JButton("Rope");
	JButton btnPistol = new JButton("Pistol");
	JButton btnPoison = new JButton("Poison");
	JButton btnKnife = new JButton("Knife");
	JButton btnDumbbell = new JButton("Dumbbell");
	JButton btnCandlestick = new JButton("Candlestick");
	JButton btnBat = new JButton("Bat");
	JButton btnAxe = new JButton("Axe");
	
	//room buttons
	JButton btnRoomGuestHouse = new JButton("Guest House");
	JButton btnRoomDiningRoom = new JButton("Dining Room");
	JButton btnRoomHall = new JButton("Hall");
	JButton btnRoomPatio = new JButton("Patio");
	JButton btnRoomObservatory = new JButton("Observatory");
	JButton btnRoomLivingRoom = new JButton("LivingRoom");
	JButton btnRoomTheater = new JButton("Theater");
	JButton btnRoomSpa = new JButton("Spa");
	JButton btnRoomKitchen = new JButton("Kitchen");

	//create text area for output
	JTextArea text = new JTextArea("I think...", 5 ,1);
	JScrollPane jsp = new JScrollPane(text);

	/*
	 * @param debug - boolean for debug mode
	 */	 
	public rumorFrame(boolean debug)
	{
		super("Start a Rumor!");
		
		//initial constructor
		debugPanel = debug;
		
		//set default values for display
		int frameWidth = 500;
		int frameHeight = 500;
		this.setSize(frameWidth, frameHeight);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font frameFont = new Font("Arial", Font.BOLD,24);
		//setBackground(Color.black);
		this.setLocationRelativeTo(null); //centers on screen		
		this.setVisible(false);
		this.setLayout(new GridBagLayout());
		
		//create gridBagConstraints for layout
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.insets = new Insets(0,0,0,0);

		//text area for output to user
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 3;
		cons.fill = GridBagConstraints.BOTH;
		this.add(jsp, cons);

		//characterPanel - contains buttons for user selection
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.fill = GridBagConstraints.BOTH;
		characterPanel.setVisible(true);
		this.add(characterPanel, cons);
		validate();
			
			/*add characters */

			//green
			btnGreen.setBackground(Color.green);
			btnGreen.setOpaque(true);
			characterPanel.add(btnGreen);
			btnGreen.addActionListener(new myActionListener());
			validate();

			//Mustard
			btnMustard.setBackground(Color.yellow);
			btnMustard.setOpaque(true);
			characterPanel.add(btnMustard);
			btnMustard.addActionListener(new myActionListener());
			validate();

			//Peacock
			btnPeacock.setBackground(Color.blue);
			btnPeacock.setOpaque(true);
			characterPanel.add(btnPeacock);
			btnPeacock.addActionListener(new myActionListener());
			validate();

			//Plum
			btnPlum.setBackground(Color.magenta);
			btnPlum.setOpaque(true);
			characterPanel.add(btnPlum);
			btnPlum.addActionListener(new myActionListener());
			validate();
			
			//Scarlet
			btnScarlet.setBackground(Color.red);
			btnScarlet.setOpaque(true);
			characterPanel.add(btnScarlet);
			btnScarlet.addActionListener(new myActionListener());
			validate();

			//White
			btnWhite.setBackground(Color.white);
			btnWhite.setOpaque(true);
			characterPanel.add(btnWhite);
			btnWhite.addActionListener(new myActionListener());
			validate();

		//add weaponPanel
		cons.gridx = 2;
		cons.gridy = 0;
		cons.gridwidth = 1;
		characterPanel.setVisible(true);
		cons.fill = GridBagConstraints.BOTH;
		this.add(weaponPanel, cons);
		validate();

			/*buttons for each weapon*/

			//axe
			btnAxe.setOpaque(true);
			weaponPanel.add(btnAxe);
			btnAxe.addActionListener(new myActionListener());
			validate();

			//bat
			weaponPanel.add(btnBat);
			btnBat.setOpaque(true);
			btnBat.addActionListener(new myActionListener());
			validate();

			//candlestick
			weaponPanel.add(btnCandlestick);
			btnCandlestick.setOpaque(true);
			btnCandlestick.addActionListener(new myActionListener());
			validate();

			//dumbbell
			weaponPanel.add(btnDumbbell);
			btnDumbbell.setOpaque(true);
			btnDumbbell.addActionListener(new myActionListener());
			validate();

			//knife
			weaponPanel.add(btnKnife);
			btnKnife.setOpaque(true);
			btnKnife.addActionListener(new myActionListener());
			validate();

			//pistol
			weaponPanel.add(btnPistol);
			btnPistol.setOpaque(true);
			btnPistol.addActionListener(new myActionListener());
			validate();

			//posion
			weaponPanel.add(btnPoison);
			btnPoison.setOpaque(true);
			btnPoison.addActionListener(new myActionListener());
			validate();

			//rope
			weaponPanel.add(btnRope);
			btnRope.setOpaque(true);
			btnRope.addActionListener(new myActionListener());
			validate();

			//trophy
			weaponPanel.add(btnTrophy);
			btnTrophy.setOpaque(true);
			btnTrophy.addActionListener(new myActionListener());
			validate();

		//add roomPanel
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.fill = GridBagConstraints.BOTH;
		characterPanel.setVisible(true);
		this.add(roomPanel, cons);
		validate();

			/*buttons for each room*/

			//spa
			//btnRoomSpa.setBackground(roomPanelColor);
			btnRoomSpa.setOpaque(true);
			btnRoomSpa.addActionListener(new myActionListener());
			roomPanel.add(btnRoomSpa);
			validate();

			//theater
			//btnRoomTheater.setBackground(roomPanelColor);
			btnRoomTheater.setOpaque(true);
			btnRoomTheater.addActionListener(new myActionListener());
			roomPanel.add(btnRoomTheater);
			validate();

			//livingRoom
			//btnRoomLivingRoom.setBackground(roomPanelColor);
			btnRoomLivingRoom.setOpaque(true);
			btnRoomLivingRoom.addActionListener(new myActionListener());
			roomPanel.add(btnRoomLivingRoom);
			validate();

			//observatory
			//btnRoomObservatory.setBackground(roomPanelColor);
			btnRoomObservatory.setOpaque(true);
			btnRoomObservatory.addActionListener(new myActionListener());
			roomPanel.add(btnRoomObservatory);
			validate();

			//patio
			//btnRoomPatio.setBackground(roomPanelColor);
			btnRoomPatio.setOpaque(true);
			btnRoomPatio.addActionListener(new myActionListener());
			roomPanel.add(btnRoomPatio);
			validate();

			//hall
			//btnRoomHall.setBackground(roomPanelColor);
			btnRoomHall.setOpaque(true);
			btnRoomHall.addActionListener(new myActionListener());
			roomPanel.add(btnRoomHall);
			validate();

			//kitchen
			//btnRoomKitchen.setBackground(roomPanelColor);
			btnRoomKitchen.setOpaque(true);
			btnRoomKitchen.addActionListener(new myActionListener());
			roomPanel.add(btnRoomKitchen);
			validate();

			//diningRoom
			//btnRoomDiningRoom.setBackground(roomPanelColor);
			btnRoomDiningRoom.setOpaque(true);
			btnRoomDiningRoom.addActionListener(new myActionListener());
			roomPanel.add(btnRoomDiningRoom);
			validate();

			//guestHouse
			//btnRoomGuestHouse.setBackground(roomPanelColor);
			btnRoomGuestHouse.setOpaque(true);
			btnRoomGuestHouse.addActionListener(new myActionListener());
			roomPanel.add(btnRoomGuestHouse);
			validate();

			//add btnContinue
			cons.gridx = 1;
			cons.gridy = 2;
			cons.gridwidth = 1;
			btnContinue.setVisible(true);
			cons.fill = GridBagConstraints.BOTH;
			btnContinue.addActionListener(new myActionListener());
			this.add(btnContinue, cons);
			validate();


				
	}	


	/*
	 * listener for all button presses
	 */
	class myActionListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//btnContinue
			if (e.getSource() == btnContinue) {
				setVisible(false);						       	
			}
			
			//people
			if (e.getSource() == btnGreen) {
				selectPerson(3, "Green");						       	
			}
			
			if (e.getSource() == btnMustard) {
				selectPerson(0, "Mustard");						       	
			}
		
			if (e.getSource() == btnPeacock) {
				selectPerson(4, "Peacock");						       	
			}
		
			if (e.getSource() == btnPlum) {
				selectPerson(4, "Plum");						       	
			}
		
			if (e.getSource() == btnScarlet) {
				selectPerson(1, "Scarlet");						       	
			}
		
			if (e.getSource() == btnWhite) {
				selectPerson(2, "White");						       	
			}
		
		//weapons
			if (e.getSource() == btnAxe) {
				selectWeapon(0, "Axe");						       	
			}
		
			if (e.getSource() == btnBat) {
				selectWeapon(1, "Bat");						       	
			}
		
			if (e.getSource() == btnCandlestick) {
				selectWeapon(2, "Candlestick");						       	
			}
		
			if (e.getSource() == btnDumbbell) {
				selectWeapon(3, "Dumbbell");						       	
			}
		
			if (e.getSource() == btnKnife) {
				selectWeapon(4, "Knife");						       	
			}
		
			if (e.getSource() == btnPistol) {
				selectWeapon(5, "Pistol");						       	
			}
		
			if (e.getSource() == btnPoison) {
				selectWeapon(6, "Poison");						       	
			}
		
			if (e.getSource() == btnRope) {
				selectWeapon(7, "Rope");						       	
			}
		
			if (e.getSource() == btnTrophy) {
				selectWeapon(8, "Trophy");						       	
			}
	
		//rooms	
			if (e.getSource() == btnRoomDiningRoom) {
				selectRoom(0, "Dining Room");						       	
			}
		
			if (e.getSource() == btnRoomGuestHouse) {
				selectRoom(1, "Guest House");						       	
			}
		
			if (e.getSource() == btnRoomHall) {
				selectRoom(2, "Hall");						       	
			}
		
			if (e.getSource() == btnRoomKitchen) {
				selectRoom(3, "Kitchen");						       	
			}
		
			if (e.getSource() == btnRoomLivingRoom) {
				selectRoom(4, "Living Room");						       	
			}
		
			if (e.getSource() == btnRoomObservatory) {
				selectRoom(5, "Observatory");						       	
			}
		
			if (e.getSource() == btnRoomPatio) {
				selectRoom(6, "Patio");						       	
			}
		
			if (e.getSource() == btnRoomSpa) {
				selectRoom(7, "Spa");						       	
			}
		
			if (e.getSource() == btnRoomTheater) {
				selectRoom(8, "Theater");						       	
			}
		}
	}

	/* 
	 *set selections
	 * @param i - int of person
	 * @param person - String name of person
	 */
	public void selectPerson(int i, String person)
	{
		if (personSelected == -1) {
			personSelected = i;
			personSelectedStr = ("It was " + person);
			sendOut(personSelectedStr);
		} else if (personSelected > -1) {
			sendOut("You have already made a selection.");
		}
	}

	/* 
	 *set selections
	 * @param i - int of weapon
	 * @param weapon - String name of weapon
	 */
	public void selectWeapon(int i, String weapon)
	{
		if (weaponSelected == -1) {
			weaponSelected = i;
			weaponSelectedStr = ("with the " + weapon);
			sendOut(weaponSelectedStr);
		} else if (weaponSelected > -1) {
			sendOut("You have already made a selection.");
		}
	}

	/* 
	 *set selections
	 * @param i - int of room
	 * @param room - String name of room
	 */
	public void selectRoom(int i, String room)
	{
		if (roomSelected == -1) {
			roomSelected = i;
			roomSelectedStr = ("in the " + room);
			sendOut(roomSelectedStr);
		} else if (roomSelected > -1) {
			sendOut("You have already made a selection.");
		}
	}

	/* 
	 * update textarea
	 * @param s - String to send to textArea
	 */
	public void sendOut(String s)
	{
		//append s to bottom of textArea and scroll to the bottom
		text.append("\n" + s);
		text.setCaretPosition(text.getDocument().getLength());
	}

	/* 
	 * @return rumor as person, room, weapon
	 */
	public int[] getRumorInt()
	{
		int[] rumor = {personSelected, roomSelected, weaponSelected};
		return rumor;
	}

	/*
	 * @return rumor as string
	 */
	public String getRumorStr()
	{
		String out = "I think it was " + personSelectedStr + " " + roomSelectedStr + " " + weaponSelectedStr;

		return out;
	}

	/*
	 * @return isRumorFormed - boolean if rumor is complete. ie all three options are selected
	 */
	public boolean isRumorFormed()
	{
		//check all three and return true if all three are not == -1
		if (personSelected != -1) {
			if (roomSelected != -1) {
				if (weaponSelected != -1) {
					return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}

	/* 
	 * reset rumorFrame
	 */
	public void reset()
	{
		personSelected = -1;
		weaponSelected = -1;
		roomSelected = -1;
		personSelectedStr = "";
		weaponSelectedStr = "";
		roomSelectedStr = "";
		text.setText("");
	}



}
