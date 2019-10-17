package model.exceptions;

/**
 * 
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * CardNotFound Exception. Is thrown when the card looked up is either found an inactive, or not found at all.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class CardNotFoundException extends Exception {
	public CardNotFoundException(String s) {
		super("Attempted to retrieve card from empty deck at:\n" + s + "\n");
	}
}
