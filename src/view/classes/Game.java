package view.classes;

import javax.swing.JPanel;

import controller.classes.GameLoop;
import model.classes.Player;

import java.awt.Color;

public class Game extends JPanel {
	/**
	 * Player Areas in Window
	 */
	private PlayerArea player1Area, player2Area;
	/**
	 * Deck in window
	 */
	private Deck viewDeck;
	/**
	 * Create the panel.
	 * @param loop Loop the game is running
	 * @param p1 Player 1
	 * @param p2 Player 2
	 * @param deck Deck of the GUI
	 */
	public Game(GameLoop loop, Player p1, Player p2, model.classes.Deck deck) {
		setBackground(new Color(153, 102, 51));
		setLayout(null);
		
		player1Area = new PlayerArea(p1, loop, p1.getOnTheTrack().getColors(), p1.getRailYard().getColors(), p1.getHand().getTrainCardColors());
		player1Area.setBounds(10, 151, 938, 863);
		add(player1Area);
		player2Area = new PlayerArea(p2, loop, p2.getOnTheTrack().getColors(), p2.getRailYard().getColors(), p2.getHand().getTrainCardColors());
		player2Area.setBounds(958, 151, 929, 863);
		add(player2Area);
		viewDeck = new view.classes.Deck(loop.getGame());
		viewDeck.setBounds(57, 0, 1797, 155);
		add(viewDeck);
	}
	/**
	 * Retrurns player 1's area
	 * @return Player 1 area
	 */
	public PlayerArea getPlayer1Area() { return player1Area; }
	/**
	 * Retrurns player 2's area
	 * @return Player 2 area
	 */
	public PlayerArea getPlayer2Area() { return player2Area; }
	/**
	 * Retrurns deck
	 * @return deck
	 */
	public Deck getDeck() { return viewDeck; }
	
	/**
	 * Update Background colors
	 * <br><b>Pre Conditions : </b> none
	 * <br><b>Post Conditions : </b> Brighter color for the active player. darker for inactive player
	 *
	 * @param turn Boolean that contains who's active
	 */
	public void updateColors(boolean turn) {
		if(turn) {
			player1Area.setBackground(new Color(183, 132, 81));
			player2Area.setBackground(new Color(123, 82, 31));
		} else {
			player2Area.setBackground(new Color(183, 132, 81));
			player1Area.setBackground(new Color(123, 82, 31));
		}
	}

}
