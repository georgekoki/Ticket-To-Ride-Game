package controller.classes;

import view.classes.GameWindow;
import java.util.Random;

/**
 * CS252 Object-Oriented Programming<br>
 *
 * A class made of static methods, used to set up the game
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class Game {
	/**
	 * A game starts with default values.
	 * <br><b>Pre Conditions : </b> None
	 * <br><b>Post Conditions : </b> Successfully creates a new GameData and runs a GameLoop with it
	 */
	public static void NewGame() {
		Random rand = new Random();
		GameData data = new GameData(null, null, null);
		GameLoop loop = new GameLoop(rand.nextInt(100) > 50? true : false, null, null);
		data.setLoop(loop);
		loop.setData(data);
		view.classes.Game gameWindow = new view.classes.Game(loop, data.g_Player1, data.g_Player2, data.g_Deck);
		
		data.setGamePane(gameWindow);
		
		GameWindow window = new GameWindow(gameWindow);
		data.setGameWindow(window);
		loop.setWindow(gameWindow);
		
		loop.Start();
	}
	
	/**
	 * When a game is loaded, a GameData is constructed out of the file read
	 * and a game loop is initiated.
	 * <br><b>Pre Conditions : </b> None
	 * <br><b>Post Conditions : </b> Successfully creates a new GameData out of a file and runs a GameLoop with it
	 */
	public static void LoadGame() { }
	
}
