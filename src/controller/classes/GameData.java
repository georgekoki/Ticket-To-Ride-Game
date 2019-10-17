package controller.classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import model.classes.BigCityCard;
import model.classes.Card;
import model.classes.Deck;
import model.classes.DestinationCard;
import model.classes.Hand;
import model.classes.OnTheTrack;
import model.classes.Player;
import model.classes.RailYard;
import model.classes.TrainCard;
import model.enumerators.TrainColor;
import view.classes.GameWindow;
import view.classes.WindowDestinationCardPicker;

/**
 * CS252 Object-Oriented Programming<br>
 *
 * GameData Class. Used to store all data used by the game, and is manipulated by GameLoop.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class GameData {
	/**
	 * Player 1
	 */
	public Player g_Player1;
	/**
	 * Player 2
	 */
	public Player g_Player2;
	/**
	 * Deck in memory
	 */
	public Deck g_Deck;
	/**
	 * Loop this data is run by
	 */
	private GameLoop loop;
	private boolean player1HasPicked = false;
	private boolean player2HasPicked = false;
	/**
	 * Game Window
	 */
	private view.classes.Game gamePane;
	private view.classes.GameWindow gameWindow;
	
	/**
	 * When the game is Constructed, the players get a name, their values set to default values, 
	 * their cards given, and each player then gets the option to pick what destination cards
	 * they wish to keep. After that, GameLoop Takes over.
	 * @param gamePane Pane this game is played in
	 * @param loop GameLoop that runs this data
	 * @param gameWindow Window the game is played in
	 */
	public GameData(view.classes.Game gamePane, GameLoop loop, view.classes.GameWindow gameWindow) {
		// Set up memory
		this.setLoop(loop);
		this.gameWindow = gameWindow;
		this.setGamePane(gamePane);
		
		// Pick names
		String p1name = null, p2name = null;
		p1name = JOptionPane.showInputDialog("Player 1, please enter your name!");
		p2name = JOptionPane.showInputDialog("Player 2, please enter your name!");
		
		// Chose Player initializer
		if(p1name == null || p1name.isEmpty())
			this.g_Player1 = initializePlayer(1);
		else
			this.g_Player1 = initializePlayer(p1name);
		if(p2name == null || p2name.isEmpty())
			this.g_Player2 = initializePlayer(2);
		else
			this.g_Player2 = initializePlayer(p2name);
		// Initialize Deck
		this.intializeDeck();
		
		// Give cards to the players
		giveCards(g_Player1);
		giveCards(g_Player2);
	}
	
	/**
	 * Saves the Game Data to a file.
	 * <br><b>Pre Conditions : </b> Game Loop is Running
	 * <br><b>Post Conditions : </b> Creates a valid save file
	 */
	public void Save() { }
	
	/**
	 * Loads values from a file
	 * <br><b>Pre Conditions : </b> Game Loop is not running
	 * <br><b>Post Conditions : </b> Loads values from a file and creates a GameData from it
	 */
	//private void Load() { }
	/**
	 * Initializes a player, and pops up a window to ask for input for his name.
	 * @param i Player number
	 * <br><b>Pre Conditions : </b> None
	 * <br><b>Post Conditions : </b> Returns a valid constructed Player object.
	 * @return Constructed player object
	 */
	private Player initializePlayer(int i) { return new Player(new ArrayList<DestinationCard>(), 0, "Player " + Integer.toString(i), null, null, null); }
	
	/**
	 * Initialized a player with the specified string as a name
	 * <br><b>Pre Conditions : </b> A valid String is given as a name
	 * <br><b>Post Conditions : </b> Returns a valid constructed Player object.
	 * @param name Name of the player
	 * @return Constructed player object
	 */
	private Player initializePlayer(String name) { return new Player(new ArrayList<DestinationCard>(), 0, name, null, null, null); }
	
	/**
	 * Initializes the Deck
	 * <br><b>Pre Conditions : </b> None
	 * <br><b>Post Conditions : </b> Creates a valid Deck Object and passes it to g_Deck
	 */
	private void intializeDeck() {
		Random rand = new Random();
		// Create pack of train cards
		ArrayList<TrainCard> trainPack = new ArrayList<TrainCard>();

		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 8; j++) {
				trainPack.add(new TrainCard(null,"/trainCards/" + GameUtilities.colors[j] + ".jpg", TrainColor.parseString(GameUtilities.colors[j])));
			}
		}	
		for(int i = 0; i < 16; i++) {
			trainPack.add(new TrainCard(null, "/trainCards/locomotive.jpg", TrainColor.locomotive));
		}
		
		// Create pack of destination Cards
		ArrayList<DestinationCard> destinationPack = null;
		try {
			destinationPack = LoadDestinationCards.readCards();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Prepare Cards in the middle
		ArrayList<TrainCard> cardsInMiddle = new ArrayList<TrainCard>(5);
		for(int i = 0; i < 5; i++)
			cardsInMiddle.add(trainPack.remove(rand.nextInt(trainPack.size())));
		
		// Prepare Big City Cards
		BigCityCard[] bigCityCards = new BigCityCard[6];
		
		int[] bigCityPoints = new int[] {8, 8, 10, 12, 12, 15};
		for(int i = 0; i < 6; i++) 
			bigCityCards[i] = new BigCityCard(null,"/bigCitiesCards/" + GameUtilities.cities[i] + ".jpg", bigCityPoints[i], GameUtilities.cities[i]);
		
		// Create the new deck and store it in memory
		g_Deck = new Deck(trainPack, destinationPack, bigCityCards, cardsInMiddle);
	}
	
	/**
	 * Gives default cards to player
	 * <br><b>Pre Conditions : </b> Players are set correctly
	 * <br><b>Post Conditions : </b> Every player's hand is full of valid cards.
	 * @param p Player to give the cards to
	 */
	private void giveCards(Player p) {
		Random rand = new Random();
		
		// Give the player some cards
		ArrayList<TrainCard> trainPack = this.g_Deck.getTrainCards();
		ArrayList<TrainCard> pTrainCards = new ArrayList<TrainCard>();
		
		// Give the player train cards
		pTrainCards.add(trainPack.remove(this.g_Deck.getTrainCards().size() - 1));
		for(int i = 1; i < 6; i++) {
			pTrainCards.add(trainPack.remove(rand.nextInt(trainPack.size() - 15)));
		}
		
		// Set up a temp array of destination cards we will offer to the player
		ArrayList<Card> pdestinationCards = new ArrayList<Card>(6);
		for(int i = 0; i < 6; i++)
			pdestinationCards.add(this.g_Deck.getCardFromDeck(2));
		
		// Set up player's areas
		p.setOnTheTrackVar(new OnTheTrack(new int[] { 0, 0, 0, 0, 0, 0, 0, 0}, p));
		p.setRailYardVar(new RailYard(new int[] { 0, 0, 0, 0, 0, 0, 0, 0}, p));
		
		// Final setup of player's hand
		p.setHand(new Hand(pTrainCards, new ArrayList<DestinationCard>(), new ArrayList<BigCityCard>(), p));
		
		// Give player options for destination cards
		WindowDestinationCardPicker w = new WindowDestinationCardPicker((ArrayList<Card>) pdestinationCards, p, this, false);
		w.setVisible(true);
	}
	
	/**
	 * Returns from a dialogue window and asks the player which cards does he want to keep.
	 * <br><b>Pre Conditions : </b> Player p is a valid player object
	 * <br><b>Post Conditions : </b> Opens a dialogue window for the player to pick the 
	 * destination cards he wishes, and puts these cards in his hand
	 * @param p Player to pick cards
	 * @param check Array that contains wether the player wants the card indexed or not
	 * @param flag false = start of the game true = middle of the game
	 * @param removedCards cards Removed from the deck, re-placed if not picked by the player 
	 */
	public void playerPicksDestinationCards(Player p, boolean[] check, boolean flag, ArrayList<DestinationCard> removedCards) {
		// Decide where to put the cards
		for(int i = 0; i < removedCards.size(); i++) {
			if(check[i]) {
				p.getHand().getDestinationCards().add(removedCards.get(i));
				p.incrementScore(-removedCards.get(i).getPoints());
			}
			else
				this.g_Deck.addDestinationCard(removedCards.get(i));
		}
		
		// For the start of the game, to decide when to open the game window
		if(!flag) {
			if(p.equals(this.g_Player1))
				this.player1HasPicked = true;
			if(p.equals(this.g_Player2))
				this.player2HasPicked = true;
		}
		
		// Update GUI
		this.gamePane.getDeck().updateDestinationDeck(this.g_Deck.getSizeOfDestinationCards());
		
		// For the start of the game
		if(this.player1HasPicked && this.player2HasPicked && !flag)
			this.gameWindow.setVisible(true);
		
		// Update GUI
		this.gamePane.getDeck().updateScore(this.g_Player1.getScore(), this.g_Player2.getScore());
		
		// Keep Round going
		if(flag) {
			this.loop.updateWindow();
			this.loop.RP2_2_pickDestinationCards();
		}
	}

	/**
	 * @return the gamePane
	 */
	public view.classes.Game getGamePane() {
		return gamePane;
	}

	/**
	 * @param gamePane the gamePane to set
	 */
	public void setGamePane(view.classes.Game gamePane) {
		this.gamePane = gamePane;
	}

	/**
	 * @return the loop
	 */
	public GameLoop getLoop() {
		return loop;
	}

	/**
	 * @param loop the loop to set
	 */
	public void setLoop(GameLoop loop) {
		this.loop = loop;
	}
	
	/**
	 * Set Game Window
	 * @param gameWindow to set
	 */
	public void setGameWindow(view.classes.GameWindow gameWindow) {
		this.gameWindow = gameWindow;
	}
	
	/**
	 * @return the active player
	 */
	public Player getActivePlayer() {
		return this.loop.getPlayerTurn() ? g_Player1 : g_Player2;
	}

	/**
	 * Get the game window
	 * @return the game Window
	 */
	public GameWindow getGameWindow() {
		return this.gameWindow;
	}

}
