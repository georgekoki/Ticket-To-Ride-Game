package controller.classes;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.enumerators.TrainColor;
import view.classes.SingleCardViewer;

/**
 * CS252 Object-Oriented Programming
 *
 * GameUtilities Class. General Utilities used by the game's code
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class GameUtilities {
	/**
	 * Static int for the card height
	 */
	private final static int SPRITE_H = 130;
	/**
	 * Static int for the card width
	 */
	private static final int SPRITE_W = 77;
	/**
	 * Hash map to map colors to an integer.
	 */
	public static final HashMap<TrainColor, Integer> colorsMap = new HashMap<TrainColor, Integer>();
	public static final HashMap<Integer, TrainColor> colorsMapRev = new HashMap<Integer, TrainColor>();
	
	/**
	 * Simple array containing all colors in the correct indexing order
	 */
	public static final String[] colors = new String[] { "red", "blue", "yellow", "green", "purple", "white", "orange", "black" };
	
	/**
	 * Simple array containing all Big Cities in the correct indexing order
	 */
	public static final String[] cities = new String[] {"Miami", "Seattle", "Dallas", "Chicago", "LosAngeles", "NewYork"};
	
	/**
	 * Set up Utilities
	 *
	 * <br><b>Pre Conditions : </b> None
	 * <br><b>Post Conditions : </b> Set up Utilities
	 */
	public static void setUpUtilities() {
		colorsMap.put(TrainColor.red, 0);
		colorsMap.put(TrainColor.blue, 1);
		colorsMap.put(TrainColor.yellow, 2);
		colorsMap.put(TrainColor.green, 3);
		colorsMap.put(TrainColor.purple, 4);
		colorsMap.put(TrainColor.white, 5);
		colorsMap.put(TrainColor.orange, 6);
		colorsMap.put(TrainColor.black, 7);
		colorsMap.put(TrainColor.locomotive, 8);
		
		colorsMapRev.put(0, TrainColor.red);
		colorsMapRev.put(1, TrainColor.blue);
		colorsMapRev.put(2, TrainColor.yellow);
		colorsMapRev.put(3, TrainColor.green);
		colorsMapRev.put(4, TrainColor.purple);
		colorsMapRev.put(5, TrainColor.white);
		colorsMapRev.put(6, TrainColor.orange);
		colorsMapRev.put(7, TrainColor.black);

	}
	/**
	 * Show an error
	 *
	 * <br><b>Pre Conditions : </b> error and title are valid strings
	 * <br><b>Post Conditions : </b> Show a valid Error message
	 * @param error Content of the window
	 * @param title Title of the window
	 */
	public static void showError(String error, String title) {
		JOptionPane.showMessageDialog(null, error, title, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Resize the image specified by imgpath and return it as an icon
	 * <br><b>Pre Conditions : </b> imgpath is valid
	 * @param imgpath image path
	 * @return valid resized ImageIcon
	 */
	public static ImageIcon resizeCard(String imgpath) {
		ImageIcon imageIcon = new ImageIcon(SingleCardViewer.class.getResource(imgpath));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(SPRITE_W, SPRITE_H,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		return imageIcon;
	}
	
	/**
	 * Resize the image specified by imgpath and return it as an icon
	 * <br><b>Pre Conditions : </b> imgpath is valid
	 * @param imgpath image path
	 * @param height Desired height
	 * @param width Desired width
	 * @return valid resized ImageIcon
	 */
	public static ImageIcon resizeCard(String imgpath, int width, int height) {
		ImageIcon imageIcon = new ImageIcon(SingleCardViewer.class.getResource(imgpath));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		return imageIcon;
	}
}
