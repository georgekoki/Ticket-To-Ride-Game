package model.exceptions;

import model.classes.Player;

/**
 * 
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * NoCardsSelectedException Class. Is thrown when the user is supposed to select
 * some cards, but he picked none.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class NoCardsSelectedException extends Exception {
	public NoCardsSelectedException(Player p) {
		super("Player picked no cards:\n" + p.toString() + "\n");
	}
}