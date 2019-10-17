package model.classes;

import model.enumerators.TrainColor;

/**
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * TrainCard Class. Contains the color that the card represents.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class TrainCard extends Card{
	
	/**
	 * Color that the card represents
	 */
	protected TrainColor color;
	
	/**
	 * Uses parent constructor and sets the additional color value
	 * @param owner Player that owns the card
	 * @param image Sprite used for the card
	 * @param color Color this card represents
	 */
	public TrainCard(Player owner, String image, TrainColor color) {
		super(owner, image);
		this.color = color;
	}

	/**
	 * Returns the color of the card
	 * @return the color of the card
	 */
	public TrainColor getColor() {
		return color;
	}
	
	
	/**
	 * Returns the Card in a formatted String
	 * @return Formatted String
	 */
	@Override
	public String toString() { return super.toString() + "Color: " + color.toString() + "\n"; }
}
