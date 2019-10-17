package view.classes;

import javax.swing.JPanel;

import controller.classes.GameLoop;
import controller.classes.ViewListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;

/**
 * 
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * CardViewer Class. A big part of the GUI, represents a collection of cards.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class CardViewer extends JPanel {
	
	/**
	 * Gameloop of this session
	 */
	private GameLoop loop;
	/**
	 * Ammount of every card
	 */
	private int [] values;
	/**
	 * Sprites, turned visible or invisible whether their respective pile is empty or not
	 */
	private SingleCardViewer[] cards = new SingleCardViewer[8];
	/**
	 * Action button, different for every area
	 */
	private JButton actionButton;
	/**
	 * Title of the CardViewer
	 */
	private String title;
	/**
	 * label for the title
	 */
	private JLabel lblTitle;
	/**
	 * Create the panel.
	 * @param loop Loop the game is running on
	 * @param title The title of the cardViewer
	 * @param Button String on the button
	 * @param values Values to intiate this cardviewer to
	 */
	public CardViewer(GameLoop loop, String title, String Button, int[] values) {
		setBackground(new Color(102, 102, 0));
		setLayout(null);
		this.loop = loop;
		this.values = values;
		this.title = title;
		final String[] colors = new String[] {"red", "blue", "yellow", "green", "purple", "white", "orange", "black"};
		
		for(int i = 0; i < 8; i++) {
			cards[i] = new SingleCardViewer("/trainCards/" + colors[i] + ".jpg", values[i]);
			cards[i].setBounds(10 + i * 100, 90, 120, 171);
			add(cards[i]);
		}
		
		actionButton = new JButton(Button);
		actionButton.setBounds(707, 11, 176, 48);
		actionButton.addActionListener(new ViewListener(this.title, this.loop));
		actionButton.setBackground(new Color(204, 153, 51));
		actionButton.setForeground(new Color(255, 255, 255));
		add(actionButton);
		
		lblTitle = new JLabel(this.title);
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblTitle.setBounds(25, 16, 201, 43);
		add(lblTitle);
	}

	/**
	 * Update the contents of the cardViewer
	 * <br><b>Pre Conditions : </b> numbers[] is of size 8 and its contents are bigger than 0
	 * <br><b>Post Conditions : </b> View numbers are changed to the given numbers
	 * @param numbers Number of cards in each stack (red, green etc)
	 */
	public void update(int[] numbers) {
		this.values = numbers;
		for(int i = 0; i < 8; i++) {
			cards[i].update(this.values[i]);
		}
		this.repaint();
	}
	/**
	 * Sets the button status.
	 * @param s Status to set it to
	 */
	public void setButtonStatus(boolean s) {
		actionButton.setEnabled(s);
	}
}
