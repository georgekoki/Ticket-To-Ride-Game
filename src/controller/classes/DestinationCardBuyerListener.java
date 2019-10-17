package controller.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.classes.DestinationCard;
import view.classes.WindowDestinationCardBuyer;
/**
 * CS252 Object-Oriented Programming
 *
 * DestinationCardBuyerListener implements ActionListener. Used to handle button presses on the Destination card buyer
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class DestinationCardBuyerListener implements ActionListener{
	/**
	 * Game's data
	 */
	private GameData d;
	/**
	 * Index of the card picked
	 */
	private int index;
	/**
	 * Picker window
	 */
	private WindowDestinationCardBuyer t;
	/**
	 * Constructor
	 * @param d GameData
	 * @param index Index of card
	 * @param t Window
	 */
	public DestinationCardBuyerListener(GameData d, int index, WindowDestinationCardBuyer t){
		this.t = t;
		this.d = d;
		this.index = index;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Make necessary modifications to the memory
		DestinationCard transferCard = d.getActivePlayer().getHand().getDestinationCards().remove(index);
		d.getActivePlayer().addToDestinationCardsBought(transferCard);
		d.getActivePlayer().getOnTheTrack().buyDestinationCard(transferCard);
		
		// Update UI 
		this.d.getGamePane().getDeck().updateScore(this.d.g_Player1.getScore(), this.d.g_Player2.getScore());
		
		// Close Picker
		t.dispose();
		
		if(d.getLoop().getPlayerTurn()) {
			d.getGamePane().getPlayer1Area().getOnTheTrack().setButtonStatus(true);
			d.getGamePane().getPlayer1Area().getHand().setButtonStatus(true);
		}
		else {
			d.getGamePane().getPlayer2Area().getOnTheTrack().setButtonStatus(true);
			d.getGamePane().getPlayer2Area().getHand().setButtonStatus(true);
		}
		
		// Update Big city cards
		if(transferCard.getTo().equals("Miami"))
			d.getActivePlayer().changeBigCityStatus(0);
		else if(transferCard.getTo().equals("Seattle"))
			d.getActivePlayer().changeBigCityStatus(1);
		else if(transferCard.getTo().equals("Dallas"))
			d.getActivePlayer().changeBigCityStatus(2);
		else if(transferCard.getTo().equals("Chicago"))
			d.getActivePlayer().changeBigCityStatus(3);
		else if(transferCard.getTo().equals("Los Angeles"))
			d.getActivePlayer().changeBigCityStatus(4);
		else if(transferCard.getTo().equals("New York"))
			d.getActivePlayer().changeBigCityStatus(5);
		
		// Go back to the loop and update UI
		this.d.getLoop().RP2_4_buyDestinationCards();
	}
}
