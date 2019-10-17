package model.exceptions;

/**
 * 
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * NotEnoughCardsException Class. Is thrown when there are no sufficient cards
 * to complete an action.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class NotEnoughCardsException extends Exception {
	public NotEnoughCardsException(String s) {
		super("Not enough cards: \n" + s + "\n");
	}

}
