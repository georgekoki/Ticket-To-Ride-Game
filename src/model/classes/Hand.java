package model.classes;

import java.util.ArrayList;
import java.util.HashMap;

import model.enumerators.TrainColor;
import model.exceptions.CardInactiveException;
import model.exceptions.CardNotFoundException;

/**
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * Hand Class. Represents the Hand of a player. Contains his train cards, destination cards and big city cards he has claimed.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class Hand {
	/**
	 * Array of TrainCards in player's hand
	 */
	private ArrayList<TrainCard> trainCards = new ArrayList<TrainCard>();
	/**
	 * Array of DestinationCards in player's hand
	 */
	private ArrayList<DestinationCard> destinationCards = new ArrayList<DestinationCard>();
	/**
	 * Array of BigCityCards the player has claimed.
	 */
	private ArrayList<BigCityCard> bigCityCards = new ArrayList<BigCityCard>();
	/**
	 * Player that owns this card
	 */
	private Player p;
	
	/**
	 * Constructor
	 * @param trainCards Train Cards for the hand
	 * @param destinationCards Destination cards for the hand
	 * @param bigCityCards Big city cards for the hand
	 * @param p Owner
	 */
	public Hand(ArrayList<TrainCard> trainCards, ArrayList<DestinationCard> destinationCards, ArrayList<BigCityCard> bigCityCards, Player p) {
		this.trainCards = trainCards;
		this.destinationCards = destinationCards;
		this.bigCityCards = bigCityCards;
		this.p = p;
	}
	
	/**
	 * Adds the given card to the correct List.
	 * <br><b>Pre Conditions : </b> Card c is a valid card
	 * <br><b>Post Conditions : </b> Card is added in the hand, in the appropriate place.
	 * @param c Card to be added
	 */
	public void addCard(Card c){
		if(c == null) throw new IllegalArgumentException();
		if(!c.isActive) throw new CardInactiveException(c);
		
		if(c instanceof TrainCard)
			trainCards.add((TrainCard) c);
		else if (c instanceof DestinationCard)
			destinationCards.add((DestinationCard) c);
		else if(c instanceof BigCityCard)
			bigCityCards.add((BigCityCard) c);
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * Removes the given card from the correct list.
	 * <br><b>Pre Conditions : </b> Card c is a valid and existing card
	 * <br><b>Post Conditions : </b> Remove the card from the correct place.
	 * @param c Card to be removed
	 * @throws CardNotFoundException Thrown when c is not a valid card
	 */
	public void removeCard(Card c) throws CardNotFoundException{
		if(c == null) throw new IllegalArgumentException();
		if(!c.isActive) throw new CardInactiveException(c);
		if(c instanceof TrainCard) {
			TrainCard temp = (TrainCard) c;
			for(int i = 0; i < trainCards.size(); i++)
				if(trainCards.get(i).color == temp.color) {
					trainCards.remove(i);
					break;
				}
		}
		else if (c instanceof DestinationCard)
			destinationCards.remove((DestinationCard) c);
		else if(c instanceof BigCityCard)
			bigCityCards.remove((BigCityCard) c);
		else
			throw new IllegalArgumentException();
		
		c.isActive = false;
	}

	/**
	 * Returns the TrainCards in the player's hand
	 * @return the trainCards
	 */
	public ArrayList<TrainCard> getTrainCards() {
		return trainCards;
	}

	/**
	 * Returns the DestinationCards in the player's hand
	 * @return the destinationCards
	 */
	public ArrayList<DestinationCard> getDestinationCards() {
		return destinationCards;
	}

	/**
	 * Returns the BigCityCards in the player's hand
	 * @return the bigCityCards
	 */
	public ArrayList<BigCityCard> getBigCityCards() {
		return bigCityCards;
	}

	/**
	 * Returns the player
	 * @return the Player
	 */
	public Player getPlayer() {
		return p;
	}
	
	/**
	 * Return an array of ints that represent how many cards we have in every color
	 *
	 * <br><b>Pre Conditions : </b> None
	 * <br><b>Post Conditions : </b> Return a valid array of 9 cells
	 * @return int arrays
	 */
	public int[] getTrainCardColors() {
		int[] array = new int[9];
		HashMap<TrainColor, Integer> colorsMap = new HashMap<TrainColor, Integer>(8);
		for(int i = 0; i < 9; i++)
			array[i] = 0;
		colorsMap.put(TrainColor.red, 0);
		colorsMap.put(TrainColor.blue, 1);
		colorsMap.put(TrainColor.yellow, 2);
		colorsMap.put(TrainColor.green, 3);
		colorsMap.put(TrainColor.purple, 4);
		colorsMap.put(TrainColor.white, 5);
		colorsMap.put(TrainColor.orange, 6);
		colorsMap.put(TrainColor.black, 7);
		colorsMap.put(TrainColor.locomotive, 8);
		
		for(TrainCard c: trainCards) {
			array[colorsMap.get(c.color)]++;
		}
		return array;
	}
	
	/**
	 * Set destination card pack
	 * @param c pack
	 */
	public void setDestinationCards(ArrayList<DestinationCard> c) {
		this.destinationCards = c;
	}
	
}
