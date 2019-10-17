package model.classes;

import model.exceptions.CardInactiveException;
/**
 * CS252 Object-Oriented Programming<br>
 *
 * BigCityCard Class for BigCityCards, uses PointCard.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class BigCityCard extends PointCard {
	
	/**
	 * The city this Big City card represents
	 */
	private String city;
	
	/**
	 * Constructor for the BigCityCard, uses the parent constructor with an extra parameter for the string city 
	 * @param owner Player that owns the card
	 * @param image Sprite for the card
	 * @param points Points this card represents
	 * @param city City that this card is for
	 */
	public BigCityCard(Player owner, String image, int points, String city) {
		super(owner, image, points);
		this.city = city;
	}
	
	/**
	 * Determine wether the player is entitled to this card.
	 * <br><b>Pre Conditions : </b> Player p is a valid object, Card is active
	 * <br><b>Post Conditions : </b> Returns true or false
	 * 
	 * @param p Player
	 * @return true of false.
	 */
	boolean canPlayerGetCard(Player p) { 
		if(p == null)
			throw new IllegalArgumentException();
		if(!this.isActive)
			throw new CardInactiveException(this);
		
		String[] cities = new String[] {"Miami", "Seattle", "Dallas", "Chicago", "Los Angeles", "New York"};
		
		for(int i = 0; i < 6; i++) {
			if(this.city == cities[i])
				if(p.getBigCityCollection()[i] >= 1)
					return true;
		}
		return false;
	}

	/**
	 * Get the city this card represents.
	 * <br><b>Pre Conditions : </b> None
	 * <br><b>Post Conditions : </b> Return a String with only the city name in it
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Returns the Card in a formatted String
	 * <br><b>Pre Conditions : </b> None
	 * <br><b>Post Conditions : </b> Return a formatted String
	 * @return Formatted String
	 */
	@Override
	public String toString() { return super.toString() + "City: " + city + "\n"; }
}
