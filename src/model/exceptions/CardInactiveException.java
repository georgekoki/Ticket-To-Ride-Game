package model.exceptions;

import model.classes.Card;

/**
 * 
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * CardInactiveException Class. Is thrown when a card is inactive but is used anyway.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class CardInactiveException extends RuntimeException {
	public CardInactiveException(Card c) {
		super("Attempted to manipulate inactive card\n" + "Card:\n" + c.toString());
	}
}
