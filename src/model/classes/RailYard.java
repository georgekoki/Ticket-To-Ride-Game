package model.classes;

import controller.classes.GameUtilities;
import model.enumerators.TrainColor;
import model.exceptions.CardNotFoundException;
import model.exceptions.InvalidColorException;
import model.exceptions.NotEnoughCardsException;
/**
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * RailYard Class. Has a stack Field for every TrainCard color. This method also takes care of collecting from the hand and sending to 
 * the track.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class RailYard extends PlayArea{
	
	public RailYard(int[] colors, Player p) {
		super(colors, p);
	}
	
	/**
	 * Sends cards to the track of the player
	 * <br><b>Pre Conditions : </b> There are cards in the Railyard
	 * <br><b>Post Conditions : </b> Cards are transfered from the Railyard to the OnTheTrack
	 */
	public void collectFromRailyard() {
		
		int[] increments = new int[8];
		
		for(int i = 0; i < 8; i++) 
			if(colors[i] != 0) {
				increments[i] = 1;
				colors[i]--;
			}
			else
				increments[i] = 0;
		
		int[] tempArr = new int[8];
		tempArr = this.p.getOnTheTrack().colors;
		
		for(int i = 0; i < 8; i++)
			tempArr[i] = tempArr[i] + increments[i];
		
		this.p.setOnTheTrack(tempArr);
	}
	
	/**
	 * Place selected cards in railyard.
	 * <br><b>Pre Conditions : </b> cards[] , opponent are valid objects. Also, opponent is the enemy
	 * player, and cards[] is not empty. Also, check for the case of train robbing.
	 * <br><b>Post Conditions : </b> Cards are transfered from the Hand to RailYard.
	 * @param cards Cards to transfer
	 * @param opponent Inactive Player
	 * @param numOfLocoCards number of locomotive cards used
	 * @throws InvalidColorException Thrown when invalid colours are picked
	 * @throws NotEnoughCardsException Thrown when the user has picked too few cards
	 */
	public void placeInRailYard(TrainCard[] cards, Player opponent, int numOfLocoCards) throws InvalidColorException, NotEnoughCardsException{
		
		// Basic input checks
		if(cards.length < 2)
			throw new NotEnoughCardsException("Player doesnt have enough cards to place in railyard");
		if(opponent == null)
			throw new IllegalArgumentException();
		TrainColor color = cards[0].color;
		for(TrainCard c : cards)
			if(color != c.color)
				throw new InvalidColorException(p.toString() + "\n not uniform pick");
		
		if(p.getHand().getTrainCardColors()[GameUtilities.colorsMap.get(color)] + numOfLocoCards < cards.length)
			throw new NotEnoughCardsException("Player doesnt have enough cards to place in railyard");
		
		if(this.colors[GameUtilities.colorsMap.get(color)] != 0)
			throw new InvalidColorException(p.toString() + "\n cards already there");
		
		if(opponent.getRailYard().colors[GameUtilities.colorsMap.get(color)] > numOfLocoCards + cards.length)
			throw new InvalidColorException(p.toString() + "\n wrong opponent pick");
		
		// Train Robbing
		if(opponent.getRailYard().colors[GameUtilities.colorsMap.get(color)] < cards.length) {
			int[] tempOp = new int[8];
			tempOp = opponent.getRailYard().colors;
			tempOp[GameUtilities.colorsMap.get(color)] = 0;
			opponent.setRailYard(tempOp);
		}
		
		// Moving cards
		int[] temp = new int[8];
		temp = p.getRailYard().colors;
		temp[GameUtilities.colorsMap.get(color)] += cards.length;
		
		try {
			for(int i = 0; i < cards.length - numOfLocoCards; i++)
				p.getHand().removeCard(new TrainCard(p, color.toString(), color));
			for(int i = 0; i < numOfLocoCards; i++)
				p.getHand().removeCard(new TrainCard(p, TrainColor.locomotive.toString(), TrainColor.locomotive));
		} catch (CardNotFoundException e) {
			e.printStackTrace();
		}
		
		p.setRailYard(temp);
	}
}
