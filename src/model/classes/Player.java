package model.classes;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.exceptions.CardNotFoundException;

/**
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * Player Class. Every Player is represented by this class. The player has a hand, a railyard and a OnTheTrack field. He also
 * has a score, and a name.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class Player {
	/**
	 * Player's score at any given moment
	 */
	private int score = 0;
	/**
	 * Players name, set at the beggining of the game.
	 */
	private String name;
	/**
	 * Player's railyard.
	 */
	private RailYard yard;
	/**
	 * Player's track
	 */
	private OnTheTrack track;
	/**
	 * Player's hand.
	 */
	private Hand hand;
	
	/**
	 * Destination cards that this player has bought
	 */
	private ArrayList<DestinationCard> destinationCardsBought;
	
	/**
	 * Numbers represented in the following order:
	 * Miami, Seattle, Dallas, Chicago, Los Angeles, New York
	 * 
	 * E.g. [2] is how many times we have gone to Dallas
	 */
	private int[] BigCityCollection = new int[6];
	
	/**
	 * Constructor
	 * @param destinationCardsBought Destination cards bought by the player
	 * @param score Score of the player
	 * @param name Name of the player
	 * @param yard Railyard of the player
	 * @param track OnTheTrack of the playerw
	 * @param hand Hand of the player
	 */
	public Player(ArrayList<DestinationCard> destinationCardsBought, int score, String name, RailYard yard, OnTheTrack track, Hand hand){
		this.destinationCardsBought = destinationCardsBought;
		this.name = name;
		this.score = score;
		this.yard = yard;
		this.track = track;
		this.hand = hand;
	}
	
	/**
	 * Returns the player's name
	 * @return Player's name
	 */
	public String getName() { return name; }
	
	/**
	 * Returns the player's score
	 * @return Player's score
	 */
	public int getScore() { return score; }
	
	/**
	 * Sets the player's score
	 * @param score new Score
	 */
	
	public void setScore(int score) { this.score = score; }
	
	/**
	 * Increments score by increment
	 * @param increment ammount to increment by
	 */
	public void incrementScore(int increment) { this.score = this.score + increment; }
	
	/**
	 * Gets a train card from the deck and places it in the player's hand.
	 * <br><b>Pre Conditions : </b> TrainCard pile in deck is not empty
	 * <br><b>Post Conditions : </b> Puts a valid card in the player's hand
	 * @param deck of the game in memory
	 * @throws CardNotFoundException when Deck has no cards to give
	 */
	public void retrieveTrainCard(Deck deck) throws CardNotFoundException {
		if(deck.getSizeOfTrainCards() > 0) 
			hand.addCard(deck.getCardFromDeck(1));
		else
			throw new CardNotFoundException("retrieveTrainCard");
	}
	
	/**
	 * Gets a Destination card from the deck and places it in the player's hand.
	 * <br><b>Pre Conditions : </b> DestinationCard pile in deck is not empty
	 * <br><b>Post Conditions : </b> Puts a valid card in the player's hand
	 * 
	 * @param deck Deck of this player
	 * @throws CardNotFoundException when Deck has no cards to give
	 */
	public void retrieveDestinationCard(Deck deck) throws CardNotFoundException {
		if(deck.getSizeOfTrainCards() > 0) 
			hand.addCard(deck.getCardFromDeck(2));
		else
			throw new CardNotFoundException("retrieveDestinationCard");
	}

	/**
	 * Returns the RailYard of the player
	 * @return the yard
	 */
	public RailYard getRailYard() {
		return yard;
	}

	/**
	 * Returns the OnTheTrack of the player
	 * @return the track
	 */
	public OnTheTrack getOnTheTrack() {
		return track;
	}
	
	/**
	 * Set on the track area
	 * @param colors cards in integer array form
	 */
	public void setOnTheTrack(int[] colors) {
		this.track.colors = colors;
	}
	
	/**
	 * Set railyard area
	 * @param colors cards in integer array form
	 */
	public void setRailYard(int[] colors) {
		this.yard.colors = colors;
	}
	
	/**
	 * Set on the track variable
	 * @param v new onthetrack
	 */
	public void setOnTheTrackVar(OnTheTrack v) {
		this.track = v;
	}
	
	/**
	 * Set railyard variable
	 * @param v new railyard
	 */
	public void setRailYardVar(RailYard v) {
		this.yard = v;
	}
	
	/**
	 * Returns the hand of the player
	 * @return the hand
	 */
	public Hand getHand() {
		return hand;
	}
	
	/**
	 * Set hand variable
	 * @param hand new hand
	 */
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	
	/**
	 * Changes how many times we have gone to each BigCity
	 * <br><b>Pre Conditions : </b> Player has not already gotten the card
	 * <br><b>Post Conditions : </b> Player's BigCityCards status is changed accordingly
	 * @param city City to be toggled
	 */
	public void changeBigCityStatus(int city) { 
		System.out.println("IM IN DIS B");
		BigCityCollection[city]++;
	}
	
	/**
	 * Checks if the player has gotten a bigcitycard
	 * @param deck deck of this player
	 */
	public void checkForBigCities(Deck deck) {
		for(int i = 0; i < 6; i++)
			if(BigCityCollection[i] >= 1) {
				BigCityCard transferCard = deck.getBigCity(i);
				this.hand.addCard(transferCard);
				this.incrementScore(transferCard.getPoints());
				JOptionPane.showMessageDialog(null, "Congratulations, you earned the Big City Card for " + transferCard.getCity() + "!\n" + transferCard.getPoints() + " points have been added to " + this.getName() + ".");
			}
	}

	/**
	 * @return the bigCityCollection
	 */
	public int[] getBigCityCollection() {
		return BigCityCollection;
	}

	/**
	 * @return the destinationCardsBought
	 */
	public ArrayList<DestinationCard> getDestinationCardsBought() {
		return destinationCardsBought;
	}

	/**
	 * @param c Card to be added
	 */
	public void addToDestinationCardsBought(DestinationCard c) {
		this.destinationCardsBought.add(c);
	}
}
