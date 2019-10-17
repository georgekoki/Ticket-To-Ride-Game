package view.classes;

import controller.classes.GameData;
import controller.classes.GameUtilities;
import controller.classes.ViewListener;
import model.classes.TrainCard;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * 
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * Deck class. Includes all info on the GUI that is shared by the 2 players
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class Deck extends JPanel {
	/**
	 * Game's data
	 */
	private GameData game;
	
	/**
	 * Player's scores
	 */
	private int s1, s2;
	
	private JLabel lblName1, lblName2;
	private JLabel lblScore1, lblScore2;
	/**
	 * true = p1's turn, false, p2's turn
	 */
	private boolean turn; // true = p1, false = p2
	/**
	 * Ticket and Destination deck buttons
	 */
	private JButton lblTicketdeckbacksprite, lblDestinationdeckbacksprite;
	
	/**
	 * Status of the players (If its their turn or not)
	 */
	private JLabel lblStatus1, lblStatus2;
	/**
	 * Numbers of cards in decks
	 */
	private JLabel lblTicketDeckNum, lblDestinationDecknum;
	/**
	 * TrainCards in middle of table
	 */
	private JButton[] lblTraincolor = new JButton[5];
	/**
	 * Availiable BigCityCards.
	 */
	private JLabel[] lblBigcitycard = new JLabel[6];
	/**
	 * Create the panel.
	 * @param game this game's data
	 */
	public Deck(GameData game) {
		setForeground(Color.WHITE);
		setLayout(null);
		setBackground(new Color(153, 102, 51));
		
		this.game = game;
		
		this.s1 = this.game.g_Player1.getScore();
		this.s2 = this.game.g_Player2.getScore();
		this.turn = this.game.getLoop().getPlayerTurn();
		
		// Player Set up
		lblName1 = new JLabel(game.g_Player1.getName());
		lblName1.setForeground(Color.WHITE);
		lblName1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName1.setBounds(46, 11, 130, 25);
		add(lblName1);
		
		lblName2 = new JLabel(game.g_Player2.getName());
		lblName2.setForeground(Color.WHITE);
		lblName2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName2.setBounds(1650, 11, 130, 25);
		add(lblName2);
		
		lblScore1 = new JLabel(Integer.toString(s1));
		lblScore1.setForeground(Color.LIGHT_GRAY);
		lblScore1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblScore1.setBounds(46, 36, 130, 25);
		add(lblScore1);
		
		lblScore2 = new JLabel(Integer.toString(s2));
		lblScore2.setForeground(Color.LIGHT_GRAY);
		lblScore2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblScore2.setBounds(1650, 36, 130, 25);
		add(lblScore2);
		
		lblStatus1 = new JLabel("Status");
		lblStatus1.setIcon(new ImageIcon(Deck.class.getResource("/UI/S_I.png")));
		lblStatus1.setBounds(10, 11, 26, 25);
		add(lblStatus1);
		
		lblStatus2 = new JLabel("Status");
		lblStatus2.setIcon(new ImageIcon(Deck.class.getResource("/UI/S_I.png")));
		lblStatus2.setBounds(1618, 6, 31, 34);
		add(lblStatus2);
		
		// Card Setup
		
		lblTicketDeckNum = new JLabel(Integer.toString(this.game.g_Deck.getSizeOfTrainCards()));
		lblTicketDeckNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblTicketDeckNum.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTicketDeckNum.setForeground(new Color(255, 255, 255));
		lblTicketDeckNum.setBounds(145, 103, 79, 46);
		add(lblTicketDeckNum);
		
		lblDestinationDecknum = new JLabel(Integer.toString(this.game.g_Deck.getSizeOfDestinationCards()));
		lblDestinationDecknum.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestinationDecknum.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDestinationDecknum.setForeground(new Color(255, 255, 255));
		lblDestinationDecknum.setBounds(369, 109, 79, 34);
		add(lblDestinationDecknum);
		
		lblTicketdeckbacksprite = new JButton("");
		lblTicketdeckbacksprite.setBounds(212, 11, 79, 132);
		lblTicketdeckbacksprite.setIcon(GameUtilities.resizeCard("/trainCards/trainBackCard.jpg"));
		lblTicketdeckbacksprite.addActionListener(new ViewListener("Get Train Cards", this.game.getLoop()));
		add(lblTicketdeckbacksprite);
		
		lblDestinationdeckbacksprite = new JButton("");
		lblDestinationdeckbacksprite.setIcon(GameUtilities.resizeCard("/destination_Tickets/desBackCard.jpg"));
		lblDestinationdeckbacksprite.addActionListener(new ViewListener("Get Destination Cards", this.game.getLoop()));
		lblDestinationdeckbacksprite.setBounds(301, 11, 79, 132);
		add(lblDestinationdeckbacksprite);
		
		for(int i = 0; i < 5; i++)
			if(this.game.g_Deck.getCardsInMiddle().get(i) != null) {
				lblTraincolor[i] = new JButton("");
				lblTraincolor[i].setBounds(450 + 100 * i, 6, 79, 132);
				lblTraincolor[i].setIcon(GameUtilities.resizeCard(this.game.g_Deck.getCardsInMiddle().get(i).getImage()));
				lblTraincolor[i].addActionListener(new ViewListener("_trainCard" + Integer.toString(i), this.game.getLoop()));
				add(lblTraincolor[i]);
			}
		
		for(int i = 0; i < 6; i++)
			if(this.game.g_Deck.getBigCityCards()[i] != null) {
				lblBigcitycard[i] = new JLabel("");
				lblBigcitycard[i].setBounds(1000 + 100 * i, 6, 82, 132);
				lblBigcitycard[i].setIcon(GameUtilities.resizeCard(this.game.g_Deck.getBigCityCards()[i].getImage()));
				add(lblBigcitycard[i]);
			}
		
		updateStatus(turn);
		
	}
	
	/**
	 * Update score on the UI
	 * @param s1 Score for player 1
	 * @param s2 Score for player 2
	 */
	public void updateScore(int s1, int s2) {
		lblScore1.setText(Integer.toString(s1));
		lblScore2.setText(Integer.toString(s2));
		this.game.getLoop().updateWindow();
	}
	/**
	 * Update availiable BigCityCards
	 * @param values Values to set
	 */
	public void updateBigCityCards(boolean[] values) {
		for(int i = 0; i < 6; i++)
			this.lblBigcitycard[i].setEnabled(values[i]);
	}
	/**
	 * Update TrainColor Deck
	 * @param v number of cards
	 */
	public void updateTrainColorDeck(int v) {
		this.lblTicketDeckNum.setText(Integer.toString(v));
	}
	/**
	 * Update Destination Deck
	 * @param v number of cards
	 */
	public void updateDestinationDeck(int v) {
		this.lblDestinationDecknum.setText(Integer.toString(v));
	}
	/**
	 * Update Player's Status
	 * @param t boolean that contains who's active
	 */
	public void updateStatus(boolean t) {
		if(t) {
			this.lblStatus1.setIcon(new ImageIcon(Deck.class.getResource("/UI/S_A.png")));
			this.lblStatus2.setIcon(new ImageIcon(Deck.class.getResource("/UI/S_I.png")));
		} else {
			this.lblStatus1.setIcon(new ImageIcon(Deck.class.getResource("/UI/S_I.png")));
			this.lblStatus2.setIcon(new ImageIcon(Deck.class.getResource("/UI/S_A.png")));
		}
	}
	
	/**
	 * Set Button activity for the deck buttons
	 * @param status Status to set to
	 */
	public void setButtonActivity(boolean status) {
		for(int i = 0; i < 5; i++)
			this.lblTraincolor[i].setEnabled(status);
		this.lblDestinationdeckbacksprite.setEnabled(status);
		this.lblTicketdeckbacksprite.setEnabled(status);
	}
	/**
	 * update what cards in the middle have been taken or not
	 */
	public void updateCardsInMiddle() {
		ArrayList<TrainCard> cardsInMem = this.game.g_Deck.getCardsInMiddle();
		for(int i = 0; i < 5; i++) {
			if(cardsInMem.get(i) == null)
				this.remove(this.lblTraincolor[i]);
		}
	}
}
