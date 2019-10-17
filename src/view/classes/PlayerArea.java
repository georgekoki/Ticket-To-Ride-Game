package view.classes;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import controller.classes.GameUtilities;
import controller.classes.GameLoop;
import controller.classes.ViewListener;
import model.classes.BigCityCard;
import model.classes.Card;
import model.classes.DestinationCard;
import model.classes.Player;
/**
 * CS252 Object-Oriented Programming<br>
 *
 * Total Player area, contains everything required for the player to keep track
 * of his side of the game
 * 
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class PlayerArea extends JPanel {
	/**
	 * Owner of this area
	 */
	private Player p;
	/**
	 * Display for onTheTrack Area
	 */
	private CardViewer onTheTrack;
	/**
	 * Display for Railyard Area
	 */
	private CardViewer railYard;
	/**
	 * Display for Hand Area
	 */
	private CardViewerHand hand;
	/**
	 * Button to open a window to display DestinationCards owned by player
	 */
	private JButton btnMyDestinationCards;
	/**
	 * Button to open a window to display BigCityCards owned by player
	 */
	private JButton btnMyBigCities;
	/**
	 * Button to open a window to display Bought Destination Cards
	 */
	private JButton btnBouhgtDestinationTickets;
	/**
	 * This game's gameloop
	 */
	private GameLoop loop;
	/**
	 * Create the panel.
	 * @param p The player that owns the area
	 * @param loop the Loop the game is running
	 * @param onTheTrackColors Colors of onthetrack
	 * @param railYardColors Colors of railyard
	 * @param handColors Colors of hand
	 */
	public PlayerArea(Player p, GameLoop loop, int[] onTheTrackColors, int[] railYardColors, int[] handColors) {
		setLayout(null);
		setBackground(new Color(153, 102, 51));
		this.loop = loop;
		this.p = p;
		btnMyDestinationCards = new JButton("My Destination Tickets");
		btnMyDestinationCards.setBounds(10, 803, 170, 32);
		btnMyDestinationCards.setBackground(new Color(204, 153, 51));
		btnMyDestinationCards.setForeground(new Color(255, 255, 255));
		btnMyDestinationCards.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { openDestinationTickets(); } } );
		add(btnMyDestinationCards);
		
		btnMyBigCities = new JButton("My Big Cities Cards");
		btnMyBigCities.setBounds(190, 803, 170, 32);
		btnMyBigCities.addActionListener(new ViewListener("View Big City Cards", this.loop));
		btnMyBigCities.setBackground(new Color(204, 153, 51));
		btnMyBigCities.setForeground(new Color(255, 255, 255));
		btnMyBigCities.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { openBigCitiesCards(); } } );
		add(btnMyBigCities);
		
		btnBouhgtDestinationTickets = new JButton("Bought Tickets");
		btnBouhgtDestinationTickets.setForeground(Color.WHITE);
		btnBouhgtDestinationTickets.setBackground(new Color(204, 153, 51));
		btnBouhgtDestinationTickets.setBounds(370, 803, 146, 32);
		btnBouhgtDestinationTickets.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { openDestinationBoughtTickets(); } } );
		add(btnBouhgtDestinationTickets);
		
		onTheTrack = new CardViewer(loop, "On The Track", "Buy Destination Cards", onTheTrackColors);
		onTheTrack.setBounds(10, 11, 914, 253);
		add(onTheTrack);
		
		railYard = new CardViewer(loop, "RailYard", "To track", railYardColors);
		railYard.setBounds(10, 275, 914, 253);
		add(railYard);
		
		hand = new CardViewerHand(loop, "Hand", "To Railyard", handColors);
		hand.setBounds(10, 539, 934, 308);
		add(hand);
	}
	
	/**
	 * Open a window to display BigCityCards owned by player
	 */
	protected void openBigCitiesCards() {
		ArrayList<BigCityCard> inPlayerHand = p.getHand().getBigCityCards();
		if(inPlayerHand.size() == 0) {
			GameUtilities.showError("No Big City Cards earned yet. You need to travel more!", "Nothing to show");
			return;
		}
		
		ArrayList<Card> temp = new ArrayList<Card>();
		
		for(BigCityCard c : inPlayerHand)
			temp.add((Card) c);
			
		
		WindowCardViewer w = new WindowCardViewer(temp);
		w.setVisible(true);
	}
	
	/**
	 * Open a window to display DestinationCards owned by player
	 */
	protected void openDestinationTickets() {
		ArrayList<DestinationCard> inPlayerHand = p.getHand().getDestinationCards();
		if(inPlayerHand.size() == 0) {
			GameUtilities.showError("You do not posess any Destination Tickets!", "Nothing to show");
			return;
		}
		
		ArrayList<Card> temp = new ArrayList<Card>();
		
		for(DestinationCard c : inPlayerHand)
			temp.add((Card) c);
			
		
		WindowCardViewer w = new WindowCardViewer(temp);
		w.setVisible(true);
	}
	/**
	 * Open a window to display bought DestinationCards owned by player
	 */
	protected void openDestinationBoughtTickets() {
		ArrayList<DestinationCard> inPlayerHand = p.getDestinationCardsBought();
		if(inPlayerHand.size() == 0) {
			GameUtilities.showError("You have not bought any Destination Tickets!", "Nothing to show");
			return;
		}
		
		ArrayList<Card> temp = new ArrayList<Card>();
		
		for(DestinationCard c : inPlayerHand)
			temp.add((Card) c);
			
		
		WindowCardViewer w = new WindowCardViewer(temp);
		w.setVisible(true);
	}
	/**
	 * Update OnTheTrack area
	 * @param values Values to set
	 */
	public void updateOnTheTrack(int[] values) {
		onTheTrack.update(values);
	}
	/**
	 * Update RailYard area
	 * @param values Values to set
	 */
	public void updateRailYard(int[] values) {
		railYard.update(values);
	}
	/**
	 * Update Hand area
	 * @param values Values to set
	 */
	public void updateHand(int[] values) {
		hand.update(values);
	}
	/**
	 * Get hand viewer
	 * @return hand viewer
	 */
	public CardViewerHand getHand() {
		return hand;
	}
	/**
	 * Get railyard viewer
	 * @return railyard viewer
	 */
	public CardViewer getRailYard() {
		return railYard;
	}
	/**
	 * Get on the track viewer
	 * @return on the track viewer
	 */
	public CardViewer getOnTheTrack() {
		return onTheTrack;
	}
}
