package model.classes;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;

import model.exceptions.CardInactiveException;
/**
 * CS252 Object-Oriented Programming<br>
 *
 * Deck Class. This class is instansiated once and contains every card at the beggining of the game, and is
 * used to keep track of the cards that are not in the player's hands. They are in an ArrayList and not a stack
 * (That cards are traditionally in) So we can pull random cards off the deck without the need to shuffle them.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class Deck {
	/**
	 * Availiable TrainCards. 
	 */
	private ArrayList<TrainCard> trainCards;
	/**
	 * Availiable DestinationCards
	 */
	private ArrayList<DestinationCard> destinationCards;
	/**
	 * Availiable BigCityCards
	 * Arranged as mentioned in Player Class
	 */
	private BigCityCard[] bigCityCards;
	/**
	 * TrainCards that are placed in the middle of the table
	 */
	private ArrayList<TrainCard> cardsInTheMiddle;

	/**
	 * Constructor
	 * @param trainCards Train Card Pack
	 * @param destinationCards Destination Card Pack
	 * @param bigCityCards Big City Cards Pack
	 * @param cardsInTheMiddle Cards in the middle
	 */
	public Deck(ArrayList<TrainCard> trainCards, ArrayList<DestinationCard> destinationCards, BigCityCard[] bigCityCards, ArrayList<TrainCard> cardsInTheMiddle) {
		this.trainCards = trainCards;
		this.destinationCards = destinationCards;
		this.bigCityCards = bigCityCards;
		this.cardsInTheMiddle = cardsInTheMiddle;
	}
	
	/**
	 * Returns the size of the Train Card Pack
	 * @return size
	 */
	public int getSizeOfTrainCards() {
		return this.getTrainCards().size();
	}
	
	/**
	 * Returns the size of the Destination Card Pack
	 * @return size
	 */
	public int getSizeOfDestinationCards() {
		return destinationCards.size();
	}
	
	/**
	 * Returns the size of the Big city Card Pack
	 * @return size
	 */
	public BigCityCard[] getBigCityCards() {
		return bigCityCards;
	}
	
	/**
	 * Return a random card from the appropriate deck, and also remove it from the deck.
	 * <br><b>Pre Conditions : </b> deck is within the range 1-3
	 * <br><b>Post Conditions : </b> Returns a valid card object, and deletes the card from the deck.
	 * @throws InvalidParameterException When deck number is invalid as an unchecked exception.
	 * 
	 * @param deck 1 for a TrainCard, 2 for a destinationCard, 3 for cardsInTheMiddle
	 * 
	 * @return Card that was picked
	 */
	public Card getCardFromDeck(int deck) { 
		Random rand = new Random();
		Card temp;
		int index;
		switch(deck) {
			case 1: {
				index = rand.nextInt(this.trainCards.size());
				temp = this.trainCards.get(index);
				if(!temp.isActive) throw new CardInactiveException(temp);
				this.trainCards.remove(index);
				return temp;
			}
			case 2: 
			{
				index = rand.nextInt(this.destinationCards.size());
				temp = this.destinationCards.get(index);
				if(!temp.isActive) throw new CardInactiveException(temp);
				this.destinationCards.remove(index);
				return temp;
			}
			case 3: 
			{
				index = rand.nextInt(this.cardsInTheMiddle.size());
				temp = this.cardsInTheMiddle.get(index);
				if(!temp.isActive) throw new CardInactiveException(temp);
				this.cardsInTheMiddle.remove(index);
				return temp;
			}
			default: return null;
		}
	}
	
	/**
	 * Fetches a BigCityCard
	 * <br><b>Pre Conditions : </b> card is within 0-5
	 * <br><b>Post Conditions : </b> Returns a valid card object, and deletes the card from the deck.
	 * 
	 * @param card Which City
	 * @return card fetched
	 */
	public BigCityCard getBigCity(int card){
		System.out.println(bigCityCards[card]);
		if(card < 0 || card > 5)
			throw new IllegalArgumentException();
		if(bigCityCards[card] == null)
			throw new IllegalArgumentException();
		
		if(!bigCityCards[card].isActive)
			throw new CardInactiveException(bigCityCards[card]);
		
		BigCityCard temp;
		temp = bigCityCards[card];
		bigCityCards[card] = null;
		return temp;
	}
	
	/**
	 * Get train Card pack
	 * @return pack
	 */
	public ArrayList<TrainCard> getTrainCards(){
		return this.trainCards;
	}
	
	/**
	 * Get cards in middle pack
	 * @return pack
	 */
	public ArrayList<TrainCard> getCardsInMiddle(){
		return this.cardsInTheMiddle;
	}
	
	/**
	 * Add a destination card to the pack
	 * @param c Card to add
	 */
	public void addDestinationCard(DestinationCard c) {
		this.destinationCards.add(c);
	}
	
}
