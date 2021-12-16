package Pleasework;


import javax.swing.*;
import java.awt.*;
import java.util.*;//this only imports three words program
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;


public class GUI extends JFrame {//JFrame is just a building function/ programs the window
	
	//randomizer for cards
	Random rand = new Random();
	
	//temporary integer used for used status
	int tempC;
	
	//boolean that indicates whether the dealer is thinking or not
	boolean dHitter = false;
	
	//list of cards
	ArrayList<Card> Cards = new ArrayList<Card>();
	
	//list of messages
	ArrayList<Message> Log = new ArrayList<Message>();
	
	//fonts used
	Font fontCard = new Font("Times New Roman", Font.PLAIN, 40);
	
	Font fontQuest = new Font("Times New Roman", Font.BOLD, 40);
	
	Font fontButton = new Font("Times New Roman", Font.PLAIN, 25);
	
	Font fontLog = new Font("Times New Roman", Font.ITALIC, 30);
	
	//Log message colors
	Color cDealer = Color.red;//this is just to make the font red to make diff from user
	
	Color cPlayer = new Color(25,55,255);
	
	//strings used
	String questHitStay = new String("Hit or Stay?");
	String questPlayMore = new String("Play more?");
	
	//colors used
	Color colorBackground = new Color(0,0,204); //Color NEEDS three number (RED, GREEN, BLUE)
	Color colorButton = new Color(204,204,0);
	//also can change to anything coder wants
	
	/*
	 * ALL COLORS
	very light red 255-102-102
	Light red 255- 51- 51
	Red 255- 0 - 0
	Dark red 204- 0 - 0
	Very dark red 153- 0 - 0
	Very light blue 51-204-255
	Light blue 51-153-255
	Blue 0 - 0 -255
	Dark blue 0 - 0 -204
	Very dark blue 0 - 0 -153
	Very light green 102-255-102
	Light green 0 -255- 51
	Green 0 -204- 0
	Dark green 0 -153- 0
	Very dark green 0 -102- 0
	Very light yellow 255-255-204
	Light yellow 255-255-153
	Yellow 255-255- 0
	Dark yellow 255-204- 0
	Light orange 255-153- 0
	Orange 255-102- 0
	Gold 255-204- 51
	Light grey 204-204-204
	Grey 153-153-153
	Dark grey 102-102-102
	Very dark grey 51- 51- 51
	Light brown 153-102- 0
	Brown 102- 51- 0
	Dark brown 51- 0 - 0
	Purple 102- 0 -153
	Black 0 - 0 - 0
	White 255-255-255
	 */
	
	
	//buttons used
	JButton bHit = new JButton();//needs to be JButtons not normal buttons
	JButton bStay = new JButton();
	JButton bYes = new JButton();
	JButton bNo = new JButton();
	
	//screen resolution
	int sW = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int sH = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	//window resolution
	int aW = 1300;
	int aH = 800;
	
	//card grid position and dimensions
	int gridX = 50;
	int gridY = 50;
	int gridW = 900;
	int gridH = 400;
	
	//card spacing and dimensions
	int spacing = 10;
	int rounding = 9;//this is for the round edges on the card
	int tCardW = (int) gridW/6;
	int tCardH = (int) gridH/2;
	int cardW = tCardW - spacing*2;
	int cardH = tCardH - spacing*2;
	
	//booleans about phases
	boolean hit_stay_q = true;//this is the first phase of the game
	boolean dealer_turn = false;//this is the second phase of the game
	boolean play_more_q = false;//this is the Third phase of the game
	
	//player and dealer card array
	ArrayList<Card> pCards = new ArrayList<Card>();
	ArrayList<Card> dCards = new ArrayList<Card>();
	
	//player and dealer totals
	int pMinTotal = 0;// this makes it where if some person got 21 it will pass that turn
	int pMaxTotal = 0;
	int dMinTotal = 0;
	int dMaxTotal = 0;
	
	//polygons for diamond shapes
	int[] polyX = new int[4];
	int[] polyY = new int[4];
	
	public GUI() {
		
		this.setTitle("Blackjack");
		this.setBounds((sW-aW-6)/2, (sH-aH-29)/2, aW+6, aH+29);//to fill the exact Base, Witdh, Hight of the window that shows
		this.setVisible(true);//if this wasn't here the program would run without a visible screen
		this.setResizable(false);//windows by default can be resized but this make it to where its a define size	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//this line makes sure when user exit, the code terminated
		
		Board board = new Board();
		this.setContentPane(board);
		board.setLayout(null);//needs to be NULL because normally this doesn't allow the buttons to be moved nor set right
		
		Move move = new Move();
		this.addMouseMotionListener(move);
		
		Click click = new Click();
		this.addMouseListener(click);
		
		//button stuff
		
		ActHit actHit = new ActHit();//addeds buttons
		bHit.addActionListener(actHit);//this links to the button
		bHit.setBounds(1000, 200, 100, 50); //these set the dimensions
		bHit.setBackground(colorButton);
		bHit.setFont(fontButton);//needs to be here if not the size of the font won't change
		bHit.setText("HIT");
		board.add(bHit);
		
		ActStay actStay = new ActStay();//adds buttons
		bStay.addActionListener(actStay);//this links to the button
		bStay.setBounds(1150, 200, 100, 50);//these set the dimensions
		bStay.setBackground(colorButton);
		bStay.setFont(fontButton);//needs to be here if not the size of the font won't change
		bStay.setText("STAY");
		board.add(bStay);
		
		ActYes actYes = new ActYes();//adds buttons
		bYes.addActionListener(actYes);//this links to the button
		bYes.setBounds(1000, 600, 100, 50);//these set the dimensions
		bYes.setBackground(colorButton);
		bYes.setFont(fontButton);//needs to be here if not the size of the font won't change
		bYes.setText("YES");
		board.add(bYes);
		
		ActNo actNo = new ActNo();//adds buttons
		bNo.addActionListener(actNo);//this links to the button
		bNo.setBounds(1150, 600, 100, 50);//these set the dimensions
		bNo.setBackground(colorButton);
		bNo.setFont(fontButton);//needs to be here if not the size of the font won't change
		bNo.setText("NO");
		board.add(bNo);
		
		//creating all cards
		
		String temp_str = "starting_temp_str_name";
		for (int i = 0; i < 52; i++) {
			if (i % 4 == 0) {
				temp_str = "Spades";
			} else if (i % 4 == 1) {
				temp_str = "Hearts";
			} else if (i % 4 == 2) {
				temp_str = "Diamonds";
			} else if (i % 4 == 3) {
				temp_str = "Clubs";
			}
			Cards.add(new Card((i/4) + 1, temp_str, i));
		}
		
		//randomly selecting initial cards for player and dealer
		
		tempC = rand.nextInt(52);
		pCards.add(Cards.get(tempC));
		Cards.get(tempC).setUsed();
	//	System.out.println("Card " + pCards.get(0).name + " of " + pCards.get(0).shape + " added to the player's cards.");
		
		tempC = rand.nextInt(52);
		while (Cards.get(tempC).used == true) {
			tempC = rand.nextInt(52);
		}
		dCards.add(Cards.get(tempC));
		Cards.get(tempC).setUsed();
	//	System.out.println("Card " + dCards.get(0).name + " of " + dCards.get(0).shape + " added to the dealer's cards.");
		
		tempC = rand.nextInt(52);
		while (Cards.get(tempC).used == true) {
			tempC = rand.nextInt(52);
		}
		pCards.add(Cards.get(tempC));
		Cards.get(tempC).setUsed();
	//	System.out.println("Card " + pCards.get(1).name + " of " + pCards.get(1).shape + " added to the player's cards.");
		
		tempC = rand.nextInt(52);
		while (Cards.get(tempC).used == true) {
			tempC = rand.nextInt(52);
		}
		dCards.add(Cards.get(tempC));
		Cards.get(tempC).setUsed();
	//	System.out.println("Card " + dCards.get(1).name + " of " + dCards.get(1).shape + " added to the dealer's cards.");
		
	}
	
	public void totalsChecker() {
		
		int acesCount;
		
		//calculation of player's totals
		pMinTotal = 0;
		pMaxTotal = 0;
		acesCount = 0;
		
		for (Card c : pCards) {
			pMinTotal += c.value;
			pMaxTotal += c.value;
			if (c.name == "Ace")
				acesCount++;
			
		}
		
		if (acesCount > 0)
			pMaxTotal += 10;
		
		dMinTotal = 0;
		dMaxTotal = 0;
		acesCount = 0;
		
		for (Card c : dCards) {
			dMinTotal += c.value;
			dMaxTotal += c.value;
			if (c.name == "Ace")
				acesCount++;
			
		}
		
		if (acesCount > 0)
			dMaxTotal += 10;
	}
	
	public void setWinner() {
		int pPoints = 0;
		int dPoints = 0;
		
		if (pMaxTotal > 21) {
			pPoints = pMinTotal;
		} else {
			pPoints = pMaxTotal;
		}
		
		if (dMaxTotal > 21) {
			dPoints = dMinTotal;
		} else {
			dPoints = dMaxTotal;
		}
		
		//all choices in blackjack
		if (pPoints > 21 && dPoints > 21) {
			Log.add(new Message("Nobody wins!", "Dealer"));
		} else if (dPoints > 21) {//dealer points
			Log.add(new Message("You win!", "Player"));
			Main.pWins++; //player wins
		} else if (pPoints > 21) {
			Log.add(new Message("Dealer wins!", "Dealer"));
			Main.dWins++;//dealer wins
		} else if (pPoints > dPoints) {
			Log.add(new Message("You win!", "Player"));
			Main.pWins++;// adds a win to the player
		} else {
			Log.add(new Message("Dealer wins!", "Dealer"));
			Main.dWins++;//adds a win to the dealer
		}
		
	}
	
	public void dealerHitStay() {//makes the dealer slow down, makes the dealer hit until he get 16 or higher
		dHitter = true;
		
		int dAvailable = 0;
		if (dMaxTotal > 21) {
			dAvailable = dMinTotal;
		} else {
			dAvailable = dMaxTotal;
		}
		
		int pAvailable = 0;
		if (pMaxTotal > 21) {
			pAvailable = pMinTotal;
		} else {
			pAvailable = pMaxTotal;
		}
		
		repaint();
		
		try {
			Thread.sleep(2000);//makes the dealer slow down
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ((dAvailable < pAvailable && pAvailable <= 21) || dAvailable < 16) {
			int tempMax = 0;
			if (dMaxTotal <= 21) {
				tempMax = dMaxTotal;
			} else {
				tempMax = dMinTotal;
			}
			String mess = ("Dealer decided to hit! (total: " + Integer.toString(tempMax) + ")");
			Log.add(new Message(mess, "Dealer"));
		//	System.out.println(mess);
			tempC = rand.nextInt(52);
			while (Cards.get(tempC).used == true) {
				tempC = rand.nextInt(52);
			}
			dCards.add(Cards.get(tempC));
			Cards.get(tempC).setUsed();
	//		System.out.println("Card " + dCards.get(dCards.size()-1).name + " of " + dCards.get(dCards.size()-1).shape + " added to the dealer's cards.");
		} else {
			int tempMax = 0;
			if (dMaxTotal <= 21) {
				tempMax = dMaxTotal;
			} else {
				tempMax = dMinTotal;
			}
			String mess = ("Dealer decided to stay! (total: " + Integer.toString(tempMax) + ")");
			Log.add(new Message(mess, "Dealer"));
			setWinner();
			dealer_turn = false;
			play_more_q = true;
		}
		dHitter = false;
	}
	
	public void refresher() {//check when and who turn is it
		
		//button visibilty checker
		if (hit_stay_q == true) {
			bHit.setVisible(true);//this makes it were diff phase show diff buttons
			bStay.setVisible(true);//this makes it were diff phase show diff buttons
		} else {
			bHit.setVisible(false);
			bStay.setVisible(false);
		}
		
		if (dealer_turn == true) {
			if (dHitter == false)
				dealerHitStay();
		}
		
		if (play_more_q == true) {
			bYes.setVisible(true);//this makes it were diff phase show diff buttons
			bNo.setVisible(true);//this makes it were diff phase show diff buttons
		} else {
			bYes.setVisible(false);
			bNo.setVisible(false);
		}
		
		totalsChecker();
		
		if ((pMaxTotal == 21 || pMinTotal >= 21) && hit_stay_q == true) {
			int tempMax = 0;
			if (pMaxTotal <= 21) {
				tempMax = pMaxTotal;
			} else {
				tempMax = pMinTotal;
			}
			String mess = ("Auto pass! (total: " + Integer.toString(tempMax) + ")");
			Log.add(new Message(mess, "Player"));
			hit_stay_q = false;
			dealer_turn = true;
		}
		
		if ((dMaxTotal == 21 || dMinTotal >= 21) && dealer_turn == true) {
			int tempMax = 0;
			if (dMaxTotal <= 21) {
				tempMax = dMaxTotal;
			} else {
				tempMax = dMinTotal;
			}
			String mess = ("Dealer auto pass! (total: " + Integer.toString(tempMax) + ")");
			Log.add(new Message(mess, "Dealer"));
			setWinner();
			dealer_turn = false;
			play_more_q = true;
		}
		
		repaint();
	}
	
	public class Board extends JPanel {//this is where the shapes are made
		
		public void paintComponent(Graphics g) {//method is for the color the window
			//background
			g.setColor(colorBackground);
			g.fillRect(0, 0, aW, aH);
			
			//questions
			if (hit_stay_q == true) {
				g.setColor(Color.black);
				g.setFont(fontQuest);
				g.drawString(questHitStay, gridX+gridW+60, gridY+90);
			//
				g.drawString("Total:", gridX+gridW+60, gridY+290);
				if (pMinTotal == pMaxTotal) {
					g.drawString(Integer.toString(pMaxTotal), gridX+gridW+60, gridY+350);
				} else if (pMaxTotal <= 21) {
					g.drawString(Integer.toString(pMinTotal) + " or " + Integer.toString(pMaxTotal), gridX+gridW+60, gridY+350);
				} else {
					g.drawString(Integer.toString(pMinTotal), gridX+gridW+60, gridY+350);
				}
			} else if (play_more_q == true) {
				g.setColor(Color.black);
				g.setFont(fontQuest);
				g.drawString(questPlayMore, gridX+gridW+70, gridY+490);
			}
		//this is the outline of the boards you could take it out
		
			g.setColor(Color.pink);
			g.drawRect(gridX, gridY, gridW, gridH);
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 2; j++) {
					g.drawRect(gridX+spacing+tCardW*i, gridY+spacing+tCardH*j, cardW, cardH);
				}
			}
			g.drawRect(gridX+gridW+50, gridY, 250, 400);
			g.drawRect(gridX, gridY+gridH+50, gridW, 250);
			
			
			
			
			g.setColor(Color.black);
			g.fillRect(gridX, gridY+gridH+50, gridW, 500);
			
			//Log
			g.setFont(fontLog);
			int logIndex = 0;
			for (Message L : Log) {
				if (L.getWho().equalsIgnoreCase("Dealer")) {
					g.setColor(cDealer);
				} else {
					g.setColor(cPlayer);
				}
				g.drawString(L.getMessage(), gridX+20, gridY+480+logIndex*35);
				logIndex++;
			}
			
			//score
			g.setColor(Color.BLACK);
			g.setFont(fontQuest);
			String score = ("Score: " + Integer.toString(Main.pWins) + " - " + Integer.toString(Main.dWins));
			g.drawString(score, gridX+gridW+70, gridY+gridH+300);
			
			//player cards
			int index = 0;
			for (Card c : pCards) {
				g.setColor(Color.white);
				g.fillRect(gridX+spacing+tCardW*index+rounding, gridY+spacing, cardW-rounding*2, cardH);
				g.fillRect(gridX+spacing+tCardW*index, gridY+spacing+rounding, cardW, cardH-rounding*2);
				g.fillOval(gridX+spacing+tCardW*index, gridY+spacing, rounding*2, rounding*2);
				g.fillOval(gridX+spacing+tCardW*index, gridY+spacing+cardH-rounding*2, rounding*2, rounding*2);
				g.fillOval(gridX+spacing+tCardW*index+cardW-rounding*2, gridY+spacing, rounding*2, rounding*2);
				g.fillOval(gridX+spacing+tCardW*index+cardW-rounding*2, gridY+spacing+cardH-rounding*2, rounding*2, rounding*2);
				
				g.setFont(fontCard);
				if (c.shape.equalsIgnoreCase("Hearts") || c.shape.equalsIgnoreCase("Diamonds")) {
					g.setColor(Color.red);
				} else {
					g.setColor(Color.black);
				}
				
				g.drawString(c.symbol, gridX+spacing+tCardW*index+rounding, gridY+spacing+cardH-rounding);
				
				if (c.shape.equalsIgnoreCase("Hearts")) {
					g.fillOval(gridX+tCardW*index+42, gridY+70, 35, 35);
					g.fillOval(gridX+tCardW*index+73, gridY+70, 35, 35);
					g.fillArc(gridX+tCardW*index+30, gridY+90, 90, 90, 51, 78);
				} else if (c.shape.equalsIgnoreCase("Diamonds")) {
					polyX[0] = gridX+tCardW*index+75;
					polyX[1] = gridX+tCardW*index+50;
					polyX[2] = gridX+tCardW*index+75;
					polyX[3] = gridX+tCardW*index+100;
					polyY[0] = gridY+60;
					polyY[1] = gridY+100;
					polyY[2] = gridY+140;
					polyY[3] = gridY+100;
					g.fillPolygon(polyX, polyY, 4);
				} else if (c.shape.equalsIgnoreCase("Spades")) {
					g.fillOval(gridX+tCardW*index+42, gridY+90, 35, 35);
					g.fillOval(gridX+tCardW*index+73, gridY+90, 35, 35);
					g.fillArc(gridX+tCardW*index+30, gridY+15, 90, 90, 51+180, 78);
					g.fillRect(gridX+tCardW*index+70, gridY+100, 10, 40);
				} else {
					g.fillOval(gridX+tCardW*index+40, gridY+90, 35, 35);
					g.fillOval(gridX+tCardW*index+75, gridY+90, 35, 35);
					g.fillOval(gridX+tCardW*index+58, gridY+62, 35, 35);
					g.fillRect(gridX+tCardW*index+70, gridY+75, 10, 70);
				}
				
				//-------------------------
				index++;
			}
			
			if (dealer_turn == true || play_more_q == true) {//this make the dealer cards no appear at first
				//dealer cards
				index = 0;
				for (Card c : dCards) {
					g.setColor(Color.white);
					g.fillRect(gridX+spacing+tCardW*index+rounding, gridY+spacing+200, cardW-rounding*2, cardH);
					g.fillRect(gridX+spacing+tCardW*index, gridY+spacing+rounding+200, cardW, cardH-rounding*2);
					g.fillOval(gridX+spacing+tCardW*index, gridY+spacing+200, rounding*2, rounding*2);
					g.fillOval(gridX+spacing+tCardW*index, gridY+spacing+cardH-rounding*2+200, rounding*2, rounding*2);
					g.fillOval(gridX+spacing+tCardW*index+cardW-rounding*2, gridY+spacing+200, rounding*2, rounding*2);
					g.fillOval(gridX+spacing+tCardW*index+cardW-rounding*2, gridY+spacing+cardH-rounding*2+200, rounding*2, rounding*2);
					
					g.setFont(fontCard);
					if (c.shape.equalsIgnoreCase("Hearts") || c.shape.equalsIgnoreCase("Diamonds")) {
						g.setColor(Color.red);
					} else {
						g.setColor(Color.black);
					}
					
					g.drawString(c.symbol, gridX+spacing+tCardW*index+rounding, gridY+spacing+cardH-rounding+200);
					
					if (c.shape.equalsIgnoreCase("Hearts")) {
						g.fillOval(gridX+tCardW*index+42, gridY+70+200, 35, 35);
						g.fillOval(gridX+tCardW*index+73, gridY+70+200, 35, 35);
						g.fillArc(gridX+tCardW*index+30, gridY+90+200, 90, 90, 51, 78);
					} else if (c.shape.equalsIgnoreCase("Diamonds")) {
						polyX[0] = gridX+tCardW*index+75;
						polyX[1] = gridX+tCardW*index+50;
						polyX[2] = gridX+tCardW*index+75;
						polyX[3] = gridX+tCardW*index+100;
						polyY[0] = gridY+60+200;
						polyY[1] = gridY+100+200;
						polyY[2] = gridY+140+200;
						polyY[3] = gridY+100+200;
						g.fillPolygon(polyX, polyY, 4);
					} else if (c.shape.equalsIgnoreCase("Spades")) {
						g.fillOval(gridX+tCardW*index+42, gridY+90+200, 35, 35);
						g.fillOval(gridX+tCardW*index+73, gridY+90+200, 35, 35);
						g.fillArc(gridX+tCardW*index+30, gridY+15+200, 90, 90, 51+180, 78);
						g.fillRect(gridX+tCardW*index+70, gridY+100+200, 10, 40);
					} else {
						g.fillOval(gridX+tCardW*index+40, gridY+90+200, 35, 35);
						g.fillOval(gridX+tCardW*index+75, gridY+90+200, 35, 35);
						g.fillOval(gridX+tCardW*index+58, gridY+62+200, 35, 35);
						g.fillRect(gridX+tCardW*index+70, gridY+75+200, 10, 70);
					}
					
					//-------------------------
					index++;
				}
				
				g.setColor(Color.black);
				g.setFont(fontQuest);
				g.drawString("Your total: ", gridX+gridW+60, gridY+40);
				if (pMaxTotal <= 21) {
					g.drawString(Integer.toString(pMaxTotal), gridX+gridW+60, gridY+120);
				} else {
					g.drawString(Integer.toString(pMinTotal), gridX+gridW+60, gridY+120);
				}
				g.drawString("Dealer's total: ", gridX+gridW+60, gridY+240);
				if (dMaxTotal <= 21) {
					g.drawString(Integer.toString(dMaxTotal), gridX+gridW+60, gridY+320);
				} else {
					g.drawString(Integer.toString(dMinTotal), gridX+gridW+60, gridY+320);
				}
			}
			
		}
		
	}
	
	public class Move implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class Click implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class ActHit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (hit_stay_q == true) {
			//	System.out.println("You clicked 'HIT'");
				
				int tempMax = 0;
				if (pMaxTotal <= 21) {
					tempMax = pMaxTotal;
				} else {
					tempMax = pMinTotal;
				}
				String mess = ("You decided to hit! (total: " + Integer.toString(tempMax) + ")");
				Log.add(new Message(mess, "Player"));
				
				tempC = rand.nextInt(52);
				while (Cards.get(tempC).used == true) {
					tempC = rand.nextInt(52);
				}
				pCards.add(Cards.get(tempC));
				Cards.get(tempC).setUsed();
			//	System.out.println("Card " + pCards.get(pCards.size()-1).name + " of " + pCards.get(pCards.size()-1).shape + " added to the player's cards.");
			}
		}
		
	}
	
	public class ActStay implements ActionListener {//button for stay

		@Override
		public void actionPerformed(ActionEvent e) {
			if (hit_stay_q == true) {//for stay button
			//	System.out.println("You clicked 'STAY'");
				
				int tempMax = 0;
				if (pMaxTotal <= 21) {
					tempMax = pMaxTotal;
				} else {
					tempMax = pMinTotal;
				}
				String mess = ("You decided to stay! (total: " + Integer.toString(tempMax) + ")");
				Log.add(new Message(mess, "Player"));
				
				hit_stay_q = false;//
				dealer_turn = true;//when player stay this allows the dealer to begin his turn 
			}
		}
		
	}
	
	public class ActYes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		//	System.out.println("You clicked 'YES'");
			
			for (Card c : Cards) {
				c.setNotUsed();
			}
			
			pCards.clear();
			dCards.clear();
			Log.clear();
			
			play_more_q = false;
			hit_stay_q = true;
			
			tempC = rand.nextInt(52);
			pCards.add(Cards.get(tempC));
			Cards.get(tempC).setUsed();
	//		System.out.println("Card " + pCards.get(0).name + " of " + pCards.get(0).shape + " added to the player's cards.");
			
			tempC = rand.nextInt(52);
			while (Cards.get(tempC).used == true) {
				tempC = rand.nextInt(52);
			}
			dCards.add(Cards.get(tempC));
			Cards.get(tempC).setUsed();
	//		System.out.println("Card " + dCards.get(0).name + " of " + dCards.get(0).shape + " added to the dealer's cards.");
			
			tempC = rand.nextInt(52);
			while (Cards.get(tempC).used == true) {
				tempC = rand.nextInt(52);
			}
			pCards.add(Cards.get(tempC));
			Cards.get(tempC).setUsed();
	//		System.out.println("Card " + pCards.get(1).name + " of " + pCards.get(1).shape + " added to the player's cards.");
			
			tempC = rand.nextInt(52);
			while (Cards.get(tempC).used == true) {
				tempC = rand.nextInt(52);
			}
			dCards.add(Cards.get(tempC));
			Cards.get(tempC).setUsed();
		//	System.out.println("Card " + dCards.get(1).name + " of " + dCards.get(1).shape + " added to the dealer's cards.");
			
		}
		
	}
	
	public class ActNo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		//	System.out.println("You clicked 'NO'");
			Main.terminator = true;
			dispose();
		}
		
	}
	
}