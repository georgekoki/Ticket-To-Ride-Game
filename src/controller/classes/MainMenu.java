package controller.classes;

/**
 * 
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * MainMenu Class. Game Starts here, and forks out to a new game, or a loaded game.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class MainMenu {
	/**
	 * An entry point for the application. User picks whether they want a new game, or load one from a file.
	 * @param args Input Arguments
	 */
	public static void main(String[] args) {
		GameUtilities.setUpUtilities();
		Game.NewGame();
	}
}