package controller.classes;

import model.classes.Player;
import view.classes.PlayerArea;
/**
 * CS252 Object-Oriented Programming<br>
 *
 * GameLoop Class. An instance of this class controlls the game, and edits the given GameData.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class GameLoop {
	
	/**
	 * The data of the game we are manipulating
	 */
	private GameData game;
	
	/**
	 * True = p1's turn, false = p2's turn
	 */
	private boolean playerTurn;
	
	/**
	 * The window of the game
	 */
	private view.classes.Game gameWindow;
	
	/**
	 * Creates a new Game Loop.
	 * @param playerTurn a boolean that represents the active player
	 * @param game GameData for the loop
	 * @param gameWindow Window the game is played in
	 */
	public GameLoop(boolean playerTurn, GameData game, view.classes.Game gameWindow) {
		this.playerTurn = playerTurn;
		this.game = game;
		this.gameWindow = gameWindow;
	}
	
	/**
	 * Starts the game loop
	 * <br><b>Pre Conditions : </b> GameData game and Game gameWindow are valid and existing objects
	 * <br><b>Post Conditions : </b> Returns 0 or 1.
	 */
	public void Start() {
		this.gameWindow.getDeck().updateStatus(this.playerTurn);
		this.gameWindow.updateColors(this.playerTurn);
		doRound();
	}
	
	/**
	 * Start a new round, and check if the game has ended
	 */
	public void doRound() { 
		this.gameWindow.getDeck().updateStatus(playerTurn);
		this.gameWindow.updateColors(this.playerTurn);
		if(this.game.g_Player1.getScore() > 100)
			
			announceWinner(this.game.g_Player1);
		
		else if(this.game.g_Player2.getScore() > 100)
			
			announceWinner(this.game.g_Player2);
		
		else if(this.game.g_Deck.getSizeOfTrainCards() == 0 && this.game.g_Player1.getScore() != this.game.g_Player2.getScore())
			
			announceWinner(this.game.g_Player1.getScore() > this.game.g_Player2.getScore() ? this.game.g_Player1 : this.game.g_Player2);
			
		else if(this.game.g_Deck.getSizeOfTrainCards() == 0 && this.game.g_Player1.getScore() == this.game.g_Player2.getScore()) {
			
			if(this.game.g_Player1.getDestinationCardsBought().size() != this.game.g_Player2.getDestinationCardsBought().size())
				
				announceWinner(this.game.g_Player1.getDestinationCardsBought().size() > this.game.g_Player2.getDestinationCardsBought().size() ? this.game.g_Player1 : this.game.g_Player2);
			
			else if(this.game.g_Player1.getHand().getBigCityCards().size() != this.game.g_Player2.getHand().getBigCityCards().size() )
				
				announceWinner(this.game.g_Player1.getHand().getBigCityCards().size() > this.game.g_Player2.getHand().getBigCityCards().size() ? this.game.g_Player1 : this.game.g_Player2);
			
			else
				
				announceWinner(null);
			
		} else {
			RoundPhase1(this.playerTurn? this.game.getGamePane().getPlayer1Area() : this.game.getGamePane().getPlayer2Area(), this.playerTurn? this.game.getGamePane().getPlayer2Area() : this.game.getGamePane().getPlayer1Area());
		}
	}
	
	/**
	 * Announces the winner of the game, and closes the game.
	 * <br><b>Pre Conditions : </b> doRound has returns true once and the Player p is the winned
	 * <br><b>Post Conditions : </b> Announces the winner in a dialogue box and exits.
	 * @param p Player that won
	 */
	private void announceWinner(Player p) {
		GameUtilities.showError(p.getName() + " Just won! Thank you for playing", "Congratulations!");
		System.exit(1);
	}
	
	/**
	 * Phase one of a game round. Here we present the player
	 * only with the option of Moving cards to onthetrack. Every
	 * other button is unclickable.
	 * <br><b>Pre Conditions : </b> Player p is a valid object
	 * <br><b>Post Conditions : </b> Full round is executed
	 * @param p1 Player area of player 1
	 * @param p2 Player area of player 2
	 */
	public void RoundPhase1(PlayerArea p1, PlayerArea p2) {
		p1.getOnTheTrack().setButtonStatus(false);
		p1.getRailYard().setButtonStatus(true);
		p1.getHand().setButtonStatus(false);
		p2.getOnTheTrack().setButtonStatus(false);
		p2.getRailYard().setButtonStatus(false);
		p2.getHand().setButtonStatus(false);
		
		this.getGame().getGamePane().getDeck().setButtonActivity(false);
	}
	
	/**
	 * Phase 2 has multiple options described in other methods.
	 * All buttons are again, active.
	 * <br><b>Pre Conditions : </b> Player p1 and p2 is a valid object
	 * <br><b>Post Conditions : </b> Full round is executed
	 */
	public void RoundPhase2() {
		PlayerArea p1;
		p1 = this.playerTurn? this.game.getGamePane().getPlayer1Area() : this.game.getGamePane().getPlayer2Area();
		p1.getOnTheTrack().setButtonStatus(true);
		p1.getRailYard().setButtonStatus(false);
		p1.getHand().setButtonStatus(true);
		
		this.getGame().getGamePane().getDeck().setButtonActivity(true);
	}
	
	/**
	 * Phase 2 Option 1, Pick some train cards
	 * <br><b>Pre Conditions : </b> none
	 * <br><b>Post Conditions : </b> Full round is executed
	 */
	public void RP2_1_pickTrainCards() {
		this.gameWindow.getDeck().updateTrainColorDeck((this.game.g_Deck.getSizeOfTrainCards()));
		this.playerTurn = !this.playerTurn;
		doRound();
	}
	
	/**
	 * Phase 2 Option 1, Pick some train cards
	 * <br><b>Pre Conditions : </b> none
	 * <br><b>Post Conditions : </b> Full round is executed
	 */
	public void RP2_1_2_pickTrainCardsFromMiddle() {
		this.gameWindow.getDeck().updateCardsInMiddle();
		this.playerTurn = !this.playerTurn;
		doRound();
	}
	
	/**
	 * Phase 2 Option 2, Pick some Destination Cards
	 * <br><b>Pre Conditions : </b> none
	 * <br><b>Post Conditions : </b> Full round is executed
	 */
	public void RP2_2_pickDestinationCards() {
		this.gameWindow.getDeck().updateDestinationDeck(this.game.g_Deck.getSizeOfDestinationCards());
		this.gameWindow.getDeck().updateScore(this.game.g_Player1.getScore(), this.game.g_Player2.getScore());
		this.playerTurn = !this.playerTurn;
		doRound();
	}
	
	/**
	 * Phase 2 Option 3, throw some train cards (Train Robbery included)
	 * <br><b>Pre Conditions : </b> none
	 * <br><b>Post Conditions : </b> Full round is executed
	 */
	public void RP2_3_throwTrainCards() {
		this.playerTurn = !this.playerTurn;
		doRound();
	}
	
	/**
	 * Phase 2 Option 4, buy some destination cards
	 * <br><b>Pre Conditions : </b> Player p is a valid object
	 * <br><b>Post Conditions : </b> Full round is executed
	 */
	public void RP2_4_buyDestinationCards() {
		this.getGame().getActivePlayer().checkForBigCities(this.getGame().g_Deck);
		boolean[] values = new boolean[6];
		for(int i = 0; i < 6; i++)
			values[i] = this.getGame().g_Deck.getBigCityCards()[i] != null;
		this.getGame().getGamePane().getDeck().updateBigCityCards(values);
		this.playerTurn = !this.playerTurn;
		this.gameWindow.getDeck().updateStatus(this.playerTurn);
		this.gameWindow.updateColors(this.playerTurn);
		updateWindow();	
		doRound();
	}

	/**
	 * @return the game
	 */
	public GameData getGame() {
		return game;
	}
	
	public void updateWindow() {
		this.gameWindow.getPlayer1Area().updateOnTheTrack(this.game.g_Player1.getOnTheTrack().getColors());
		this.gameWindow.getPlayer1Area().updateRailYard(this.game.g_Player1.getRailYard().getColors());
		this.gameWindow.getPlayer1Area().updateHand(this.game.g_Player1.getHand().getTrainCardColors());
		this.gameWindow.getPlayer2Area().updateOnTheTrack(this.game.g_Player2.getOnTheTrack().getColors());
		this.gameWindow.getPlayer2Area().updateRailYard(this.game.g_Player2.getRailYard().getColors());
		this.gameWindow.getPlayer2Area().updateHand(this.game.g_Player2.getHand().getTrainCardColors());
		this.gameWindow.repaint();
	}

	/**
	 * @return the playerTurn
	 */
	public boolean getPlayerTurn() {
		return playerTurn;
	}
	
	public void setWindow(view.classes.Game window) {
		this.gameWindow = window;
	}
	
	public void setData(GameData data) {
		this.game = data;
	}
}
