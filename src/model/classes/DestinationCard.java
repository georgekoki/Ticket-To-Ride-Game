package model.classes;

import java.util.ArrayList;

import controller.classes.GameUtilities;
import model.enumerators.TrainColor;

/**
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * DestinationCard Class. Represents a Destination card that can be purchased with TrainCards. The cost is placed
 * in the colors array.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class DestinationCard extends PointCard{
	/**
	 * Unique card id
	 */
	private int id;
	
	/**
	 * Where the trip starts
	 */
	private String from;
	/**
	 * Where the trip ends
	 */
	private String to;
	/**
	 * Colors required to purchase the card
	 */
	private ArrayList<TrainColor> colors = new ArrayList<TrainColor>();
	
	/**
	 * Constructor that uses its parent's constructor and adds to the required specific params.
	 * @param owner Player that owns the card
	 * @param image Sprite for the card
	 * @param points Points this card represents
	 * @param id unique card ID
	 * @param from Where the trip starts
	 * @param to Where the trip ends
	 * @param colors Colors required to purchase the card
	 */
	public DestinationCard(Player owner, String image, int points, int id, String from, String to, ArrayList<String> colors) {
		super(owner, image, points);
		this.id = id;
		this.from = from;
		this.to = to;
		for(int i = 0; i < colors.size(); i++)
			this.colors.add(TrainColor.parseString(colors.get(i).toLowerCase()));
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	@Override
	public String toString() {
		return super.toString() + "ID: " + id + "\nFrom: " + from + "\nTo: " + to + "\nColors: " + colors.toString();
	}

	/**
	 * @return the colors
	 */
	public ArrayList<TrainColor> getColors() {
		return colors;
	}
	
	/**
	 * Returns wether the player p can get this card
	 * <br><b>Pre Conditions : </b> Player p is a valid object and has a full On The Track
	 * <br><b>Post Conditions : </b> return true if player has enough cards on the track, else false
	 *
	 * @param p Player
	 * @return True if player has enough cards on the track, else false
	 */
	public boolean canPlayerGetCard(Player p) {
		if(p == null) return false;

		// Get player's OnTheTrack area and make sure it at least has something in it
		int[] playersColors = p.getOnTheTrack().getColors();
		if(p.getOnTheTrack().isEmpty())
			return false;
		
		int[] cardColors = new int[8];
		for(int i = 0; i < 8; i++)
			cardColors[i] = 0;
		
		// Convert an array of colors to an array of color numbers
		for(TrainColor c : this.colors)
			if(c != null) {
				cardColors[GameUtilities.colorsMap.get(c)]++;
			}
		// Compare contents to requirements
		for(int i = 0; i < 8; i++)
			if(cardColors[i] > playersColors[i]) return false;
		return true;
	}
}