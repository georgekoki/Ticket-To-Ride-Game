package view.classes;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.classes.GameUtilities;

import java.awt.Color;
/**
 * 
 * CS252 Object-Oriented Programming
 *
 * SingleCardViewer Class. Displays a single card
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class SingleCardViewer extends JPanel {

	/**
	 * Sprite of the card
	 */
	private JLabel lblSprite;
	/**
	 * Number of cards
	 */
	private JLabel lblNumber;
	
	/**
	 * Constructor
	 * @param imgpath path to the image sprite
	 * @param displayNum number of cards for this display
	 */
	public SingleCardViewer(String imgpath, int displayNum) {
		setBackground(new Color(51, 51, 0));
		setLayout(null);
		
		lblSprite = new JLabel("hello");
		lblSprite.setIcon(GameUtilities.resizeCard(imgpath));
		lblSprite.setBounds(20, 11, 79, 130);
		add(lblSprite);
		
		lblNumber = new JLabel(Integer.toString(displayNum));
		lblNumber.setForeground(new Color(255, 255, 255));
		lblNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumber.setBounds(20, 144, 79, 14);
		add(lblNumber);
		
		lblSprite.setVisible(Integer.parseInt(this.lblNumber.getText()) != 0);
		
	}
	
	/**
	 * Update the contents of this viewer
	 * @param num Number of cards
	 */
	public void update(int num) {
		lblSprite.setVisible(num != 0);
		lblNumber.setText(Integer.toString(num));
		this.repaint();
	}
}
