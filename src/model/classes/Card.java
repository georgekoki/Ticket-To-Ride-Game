package model.classes;

/**
 * CS252 Object-Oriented Programming<br>
 *
 * Abstract Card Class. Every Card derives from this class.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public abstract class Card {
	/**
	 * The owner of this card, it can be either player or null if its in a deck.
	 */
	protected Player owner;
	
	/**
	 * If this card is active, it has not been disposed of in a purchase of a Destination Card
	 */
	protected boolean isActive;
	
	/**
	 * Sprite the Card Uses
	 */
	protected String image;
	
	/**
	 * Constructor for the type card. Simply assigns the owner and image.
	 * @param owner Player that owns the card
	 * @param image Sprite used for the card
	 */
	public Card(Player owner, String image) { 
		this.isActive = true;
		this.owner = owner; 
		this.image = image;
	}
	
	/**
	 * Returns the contents of the card in a formatted string
	 * <br><b>Pre Conditions : </b> None
	 * <br><b>Post Conditions : </b> Return a formatted String
	 * @return formatted String
	 */
	public String toString() { return "Card\nOwner: " + owner + "\nisActive: " + isActive + "\nImage path: " + image + "\n"; };
	
	/**
	 * Returns the owner of the card
	 * <br><b>Pre Conditions : </b> Card has been dealt (Has Owner) and is active
	 * <br><b>Post Conditions : </b> Returns a valid Player object
	 * @return Player object
	 */
	public Player getOwner() { return this.owner; }
	
	/**
	 * Return if this card is active
	 * <br><b>Pre Conditions : </b> None
	 * <br><b>Post Conditions : </b> None
	 * @return state of activity of card
	 */
	public boolean getActive() { return this.isActive; }
	
	/**
	 * Return the path to the image that is attached to this card
	 * @return the path
	 */
	public String getImage() { return image; }
	
}
