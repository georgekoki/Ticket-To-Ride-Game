package model.exceptions;

/**
 * 
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * InvalidColourException Class. Is thrown when an invalid Colour is chosen.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class InvalidColorException extends Exception {
	public InvalidColorException(String s) {
		super("Mistake:\n" + s + "\n");
	}
}
