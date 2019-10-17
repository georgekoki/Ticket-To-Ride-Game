package model.classes;

/**
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * PointCard Class. An abstract class that
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public abstract class PointCard extends Card{
	/**
	 * Points the card represents
	 */
	protected int points;
	
	/**
	 * Simply uses parent constructor and assigns points as well.
	 * @param owner Player that owns the card
	 * @param image Sprite used for the card
	 * @param points Points the card represents.
	 */
	public PointCard(Player owner, String image, int points) { super(owner, image); this.points = points; }
	
	/**
	 * Returns the ammount of points the card has
	 * @return the ammount of points the card has
	 */
	public int getPoints() { return points; } 
}
