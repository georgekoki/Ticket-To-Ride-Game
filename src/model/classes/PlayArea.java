package model.classes;

/**
 * 
 * CS252 Object-Oriented Programming
 *
 * PlayArea Class. Simple class used by the OnTheTrack and RailYard areas of the player, since they have many things in common.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class PlayArea {
	/**
	 * Ammount of Cards on tracks
	 * Indexed as follows: 
	 * red, blue, yellow, green, purple, white, orange, black
	 */
	protected int[] colors = new int[8];
	
	/**
	 * Player that owns this card
	 */
	protected Player p;
	
	/**
	 * Constructor
	 * @param colors Initial colors of this playArea
	 * @param p owner
	 */
	protected PlayArea(int[] colors, Player p) {
		this.colors = colors;
		this.p = p;
	}
	
	/**
	 * Return the colors
	 * @return colors
	 */
	public int[] getColors() {
		return colors;
	}
	
	/**
	 * Get The ammount of cards on the track
	 * @return the red
	 */
	public int getRed() {
		return colors[0];
	}
	/**
	 * Set the ammount of cards on the track
	 * @param red the red to set
	 */
	public void setRed(int red) {
		this.colors[0] = red;
	}
	/**
	 * Get The ammount of cards on the track
	 * @return the black
	 */
	public int getBlack() {
		return colors[1];
	}
	/**
	 * Set the ammount of cards on the track
	 * @param black the black to set
	 */
	public void setBlack(int black) {
		this.colors[1] = black;
	}
	/**
	 * Get The ammount of cards on the track
	 * @return the blue
	 */
	public int getBlue() {
		return  colors[2];
	}
	/**
	 * Set the ammount of cards on the track
	 * @param blue the blue to set
	 */
	public void setBlue(int blue) {
		this.colors[2] = blue;
	}
	/**
	 * Get The ammount of cards on the track
	 * @return the purple
	 */
	public int getPurple() {
		return colors[3];
	}
	/**
	 * Set the ammount of cards on the track
	 * @param purple the purple to set
	 */
	public void setPurple(int purple) {
		this.colors[3] = purple;
	}
	/**
	 * Get The ammount of cards on the track
	 * @return the green
	 */
	public int getGreen() {
		return colors[4];
	}
	/**
	 * Set the ammount of cards on the track
	 * @param green the green to set
	 */
	public void setGreen(int green) {
		this.colors[4] = green;
	}
	/**
	 * Get The ammount of cards on the track
	 * @return the white
	 */
	public int getWhite() {
		return colors[5];
	}
	/**
	 * Set the ammount of cards on the track
	 * @param white the white to set
	 */
	public void setWhite(int white) {
		this.colors[5] = white;
	}
	/**
	 * Get The ammount of cards on the track
	 * @return the yellow
	 */
	public int getYellow() {
		return colors[6];
	}
	/**
	 * Set the ammount of cards on the track
	 * @param yellow the yellow to set
	 */
	public void setYellow(int yellow) {
		this.colors[6] = yellow;
	}
	/**
	 * Get The ammount of cards on the track
	 * @return the orange
	 */
	public int getOrange() {
		return colors[7];
	}
	/**
	 * Set the ammount of cards on the track
	 * @param orange the orange to set
	 */
	public void setOrange(int orange) {
		this.colors[7] = orange;
	}

	/**
	 * Get The player that owns this PlayArea
	 * @return the player that owns this OnTheTrack
	 */
	public Player getPlayer() {
		return p;
	}
	
	/**
	 * @return is this area empty
	 */
	public boolean isEmpty() {
		for(int i = 0; i < 8; i++)
			if(colors[i] != 0)
				return false;
		return true;
	}
}
