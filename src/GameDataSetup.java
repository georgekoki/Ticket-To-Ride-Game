import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.classes.GameData;
import controller.classes.GameLoop;
import view.classes.GameWindow;

class GameDataSetup {

	@Test
	void test() {
		GameData data = new GameData(null, null, null);
		GameLoop loop = new GameLoop(true, null, null);
		data.setLoop(loop);
		loop.setData(data);
		view.classes.Game gameWindow = new view.classes.Game(loop, data.g_Player1, data.g_Player2, data.g_Deck);
		
		data.setGamePane(gameWindow);
		
		GameWindow window = new GameWindow(gameWindow);
		data.setGameWindow(window);
		
		System.out.println("Beggining testing on GameData...");
		System.out.println("Testing assignments...");
		if(data.getLoop() != loop)
			fail("Assignment of loop has failed");
		if(data.getGamePane() != gameWindow)
			fail("Assignment of pane has failed");
		if(data.getGameWindow() != window)
			fail("Assignment of window has failed");
		
		System.out.println("Success.");
		System.out.println("Testing Internal Initializations...");
		
		if(data.g_Deck == null)
			fail("Initialization of deck has failed");
		if(data.g_Player1 == null)
			fail("Initialization of Player 1 has failed");
		if(data.g_Player2 == null)
			fail("Initialization of Player 2 has failed");
		if(data.g_Player1.getHand() == null || data.g_Player1.getRailYard() == null || data.g_Player1.getOnTheTrack() == null)
			fail("Initialization of Player 1 has failed");
		if(data.g_Player2.getHand() == null || data.g_Player2.getRailYard() == null || data.g_Player2.getOnTheTrack() == null)
			fail("Initialization of Player 2 has failed");
		System.out.println("Player has been successfully initialized");
		
		if(data.g_Deck == null)
			fail("Initialization of Deck has failed");
		if(data.g_Deck.getTrainCards() == null)
			fail("Initialization of Deck has failed");
		if(data.g_Deck.getCardsInMiddle() == null)
			fail("Initialization of Deck has failed");
		if(data.g_Deck.getSizeOfDestinationCards() == 0)
			fail("Initialization of Deck has failed");
		System.out.println("Deck has been successfully initialized");
		System.out.println("Testing complete.");
	}

}
