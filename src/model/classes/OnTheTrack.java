package model.classes;

import controller.classes.GameUtilities;
import model.enumerators.TrainColor;

/**
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * OnTheTrack Class, extends PlayArea. Train wagons we have on our tracks. These cards can be used to buy Destination Cards.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class OnTheTrack extends PlayArea{
	public OnTheTrack(int[] colors, Player p) {
		super(colors, p);
	}
	/**
	 * Buy a destination card
	 *
	 * <br><b>Pre Conditions : </b> Destination Card is a valid card
	 * <br><b>Post Conditions : </b> Remove appropriate 
	 *
	 * @param destination Destination card to be bought
	 */
	public void buyDestinationCard(DestinationCard destination){
		if(destination == null) throw new IllegalArgumentException();
		
		// Remove cards from here
		for(TrainColor c : destination.getColors()) {
			colors[GameUtilities.colorsMap.get(c)]--;
		}
		
		// Increment score appropriately
		p.incrementScore(destination.getPoints() * 2);
	}
}
