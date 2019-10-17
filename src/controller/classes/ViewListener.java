package controller.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.classes.Card;
import model.classes.Player;
import model.classes.TrainCard;
import model.exceptions.CardNotFoundException;
import model.exceptions.InvalidColorException;
import model.exceptions.NotEnoughCardsException;
import view.classes.PlayerArea;
import view.classes.WindowColorPickerLoco;
import view.classes.WindowDestinationCardBuyer;
import view.classes.WindowDestinationCardPicker;

public class ViewListener implements ActionListener {
	
	private String s;
	private GameLoop loop;
	public ViewListener(String s, GameLoop loop) {
		super();
		this.s = s;
		this.loop = loop;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Player p;
		p = this.loop.getPlayerTurn()? this.loop.getGame().g_Player1 : this.loop.getGame().g_Player2;
		
		// RailYard button was pressed
		if(this.s == "RailYard") {
				p.getRailYard().collectFromRailyard();
			
			this.loop.updateWindow();
			this.loop.RoundPhase2();
		}
		// DestinationCard button was pressed
		else if(this.s == "Get Destination Cards") {
			
			if(this.loop.getGame().g_Deck.getSizeOfDestinationCards() == 0) {
				GameUtilities.showError("No more Destination Cards!", "Out of Destination Cards");
				return;
			}
			int max = this.loop.getGame().g_Deck.getSizeOfDestinationCards() >= 4? 4 :  this.loop.getGame().g_Deck.getSizeOfDestinationCards();
			ArrayList<Card> destinations = new ArrayList<Card>(max);
			for(int i = 0; i < max; i++)
				destinations.add(this.loop.getGame().g_Deck.getCardFromDeck(2));
			this.loop.getGame().getGamePane().getDeck().setButtonActivity(false);
			WindowDestinationCardPicker w = new WindowDestinationCardPicker(destinations, p, this.loop.getGame(),  true);
			w.setVisible(true);
		}
		// TrainCard button was pressed
		else if(this.s == "Get Train Cards") {
			try {
				p.retrieveTrainCard(this.loop.getGame().g_Deck);
				this.loop.updateWindow();
				this.loop.RP2_1_pickTrainCards();
			} catch (CardNotFoundException e1) {
				GameUtilities.showError("No more Train Cards!", "Out of Train Cards");
			}
		}
		// One of the cards in middle was pressed
		else if(this.s.charAt(0) == '_') {
			int index = Character.getNumericValue(this.s.charAt(10));
			p.getHand().addCard(this.loop.getGame().g_Deck.getCardsInMiddle().get(index));
			this.loop.getGame().g_Deck.getCardsInMiddle().set(index, null);
			this.loop.updateWindow();
			this.loop.RP2_1_2_pickTrainCardsFromMiddle();
		}
		// On The Track button was pressed
		else if(this.s == "On The Track") {
			WindowDestinationCardBuyer w = new WindowDestinationCardBuyer(p.getHand().getDestinationCards(), this.loop.getGame());
			w.setVisible(true);
		}
		// Hand Button was pressed
		else if(this.s == "Hand") {
			boolean res = handAction();
			this.loop.updateWindow();	
			if(res) this.loop.RP2_3_throwTrainCards();
		}
	}
	
	public boolean handAction() {
		view.classes.Game gameWindow = this.loop.getGame().getGamePane();
		PlayerArea area;
		
		// Get Players
		Player p1, p2;
		p1 = this.loop.getPlayerTurn()? this.loop.getGame().g_Player1 : this.loop.getGame().g_Player2;
		p2 = this.loop.getPlayerTurn()? this.loop.getGame().g_Player2 : this.loop.getGame().g_Player1;
		area = this.loop.getPlayerTurn() ? gameWindow.getPlayer1Area() : gameWindow.getPlayer2Area();
		
		// Get Input
		int numberOfCardsChosen = area.getHand().getAmountOfCards();
		int numberOfLocoCards = area.getHand().getAmountOfLocoCards();
		
		// Get info from the GUI
		int numberOfLocosInPlayerHand = p1.getHand().getTrainCardColors()[8];
		
		// Check Input
		if(numberOfLocoCards == -1) {
			GameUtilities.showError("Invalid ammount of Loco Cards given", "Your Hand");
			return false;
		} else if(numberOfCardsChosen == -1) {
			GameUtilities.showError("Invalid ammount of Train Cards given", "Your Hand");
			return false;
		} else if(numberOfCardsChosen == 0 && numberOfLocoCards <= 1) {
			GameUtilities.showError("Invalid ammount of Cards given", "Your Hand");
			return false;
		}
		if(numberOfCardsChosen <= 0 && numberOfLocoCards >= 2) {
			// Player only picked locomotive cards
			if(numberOfLocoCards > numberOfLocosInPlayerHand) {
				GameUtilities.showError("Invalid ammount of Locomotive Cards given", "Your Hand");
				return false;
			}
			WindowColorPickerLoco w = new WindowColorPickerLoco(this, this.loop.getGame(), numberOfLocoCards);
			w.setVisible(true);
			return false;
		}
		if(area.getHand().getActiveCheckBox() == -1) {
			GameUtilities.showError("Please pick a color via the checkboxes, or input 0 train cards to use only locomotive cards", "Your Hand");
			return false;
		}
		
		// Get info from the GUI
		int numberOfCardsInPlayerHand = p1.getHand().getTrainCardColors()[area.getHand().getActiveCheckBox()];
		
		// Temp array containing the cards that will be played
		TrainCard[] temp = new TrainCard[numberOfCardsChosen + numberOfLocoCards];
		
		// Some more checks to see if player can make move
		if (numberOfCardsChosen > numberOfCardsInPlayerHand || numberOfCardsChosen == -1) {
			GameUtilities.showError("Invalid ammount of Train Cards given", "Your Hand");
			return false;
		}
		if (numberOfLocoCards > numberOfLocosInPlayerHand || numberOfLocoCards == -1) {
			GameUtilities.showError("Invalid ammount of Locomotive Cards given", "Your Hand");
			return false;
		}
		
		// Remove cards from player's hand and into railyard
		for (int i = 0; i < numberOfCardsChosen + numberOfLocoCards; i++)
			temp[i] = new TrainCard(null, GameUtilities.colorsMapRev.get(area.getHand().getActiveCheckBox()).toString(),
					GameUtilities.colorsMapRev.get(area.getHand().getActiveCheckBox()));
		try {
			p1.getRailYard().placeInRailYard(temp, p2, numberOfLocoCards);
			return true;
		} catch (InvalidColorException e) {
			GameUtilities.showError(
					"Something went internally wrong. A team of highly trained monkeys are working to resolve the issue.",
					"Oops");
			e.printStackTrace();
			return false;
		} catch (NotEnoughCardsException e) {
			GameUtilities.showError(
					"Something went internally wrong. A team of highly trained monkeys are working to resolve the issue.",
					"Oops");
			e.printStackTrace();
			return false;
		}
				
	}
}
