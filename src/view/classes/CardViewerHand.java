package view.classes;

import controller.classes.CheckBoxListener;
import controller.classes.GameLoop;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
/**
 * 
 * 
 * CS252 Object-Oriented Programming<br>
 *
 * CardViewerHand Class. Extends CardViewer, it supports Locomotive card display.
 * 
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class CardViewerHand extends CardViewer {
	
	/**
	 * Extra Locomotive Card
	 */
	private SingleCardViewer locoCard;
	/**
	 * Extra ammount of locomotive cards
	 */
	private int locoCardValue;
	/**
	 * Array that contains the status of the checkboxes
	 */
	private boolean[] checkBoxesStatus = new boolean[8];
	/**
	 * Checkboxes
	 */
	private JCheckBox[] checkBoxes = new JCheckBox[8];
	/**
	 * Simple labels
	 */
	private JTextField textFieldCardCount, textFieldLocoCount;
	/**
	 * Label for the ammount of locomotive cards
	 */
	private JLabel lblAmountOfLocomotives;
	
	/**
	 * Create the panel.
	 * @param loop Loop the game is running on
	 * @param title Title of the CardViewer
	 * @param button String on the button
	 * @param colors Values to intiate the viewer to
	 */
	public CardViewerHand(GameLoop loop, String title, String button, int[] colors) {
		super(loop, title, button, colors);
		setBackground(new Color(102, 102, 51));
		locoCardValue = colors[8];
		locoCard = new SingleCardViewer("/trainCards/locomotive.jpg", locoCardValue);
		locoCard.setBounds(10 + 8 * 100, 90, 120, 171);
		add(locoCard);
		
		textFieldCardCount = new JTextField();
		textFieldCardCount.setBounds(870, 269, 47, 20);
		add(textFieldCardCount);
		textFieldCardCount.setColumns(10);
		
		textFieldLocoCount = new JTextField();
		textFieldLocoCount.setBounds(682, 269, 47, 20);
		add(textFieldLocoCount);
		textFieldLocoCount.setColumns(10);
		
		JLabel lblAmountOfCards = new JLabel("Amount of Cards:");
		lblAmountOfCards.setForeground(new Color(255, 255, 255));
		lblAmountOfCards.setBounds(753, 272, 107, 14);
		add(lblAmountOfCards);
		
		lblAmountOfLocomotives = new JLabel("Amount of Locomotives:");
		lblAmountOfLocomotives.setForeground(new Color(255, 255, 255));
		lblAmountOfLocomotives.setBounds(527, 272, 145, 14);
		add(lblAmountOfLocomotives);
		
		for(int i = 0; i < 8; i++) {
			checkBoxesStatus[i] = false;
			checkBoxes[i] = new JCheckBox("");
			checkBoxes[i].setBounds(55 + 100 * i, 63, 21, 23);
			checkBoxes[i].addActionListener(new CheckBoxListener(i, this));
			checkBoxes[i].setBackground(new Color(102, 102, 51));
			add(checkBoxes[i]);
		}
	}
	
	/**
	 * Update the contents of the cardViewer
	 * @param numbers Number of cards in each stack (red, green etc)
	 */
	@Override
	public void update(int[] numbers) {
		int[] t = new int[8];
		for(int i = 0; i < 8; i++)
			t[i] = numbers[i];
		locoCardValue = numbers[8];
		locoCard.update(numbers[8]);
		super.update(t);
	}
	/**
	 * Return checkBoxesStatus
	 * @return checkBoxesStatus
	 */
	public boolean[] getCheckBoxesStatus() {
		return checkBoxesStatus;
	}
	/**
	 * Set checkBoxesStatus
	 * @param status Status to set
	 */
	public void setCheckBoxesStatus(boolean[] status) {
		checkBoxesStatus = status;
	}
	/**
	 * updates the checkboxes to the contents of checkBoxesStatus
	 */
	public void updateCheckBoxes() {
		for(int i = 0; i < 8; i++)
			checkBoxes[i].setSelected(checkBoxesStatus[i]);
	}
	/**
	 * Returns the ammount of cards
	 * <br><b>Pre Conditions : </b> Valid input in the textbox
	 * <br><b>Post Conditions : </b> Return a valid integer
	 * @return ammount of cards, if invalid -1
	 */
	public int getAmountOfCards() {
		try {
			int i = Integer.parseInt(textFieldCardCount.getText());
			return i;
		}catch(NumberFormatException | NullPointerException e) {
			if(!textFieldCardCount.getText().equals("")) {
				return -1;
			}
		}
		return 0;
	}
	/**
	 * Returns the ammount of loco cards
	 * <br><b>Pre Conditions : </b> Valid input in the textbox
	 * <br><b>Post Conditions : </b> Return a valid integer
	 * @return ammount of loco cards if invalid -1
	 */
	public int getAmountOfLocoCards(){
		try {
			int i = Integer.parseInt(textFieldLocoCount.getText());
			return i;
		}catch(NumberFormatException | NullPointerException e) {
			if(!textFieldLocoCount.getText().equals("")) {
				return -1;
			}
		}
		return 0;
	}
	/**
	 * Return the checkbox that is ticked
	 * <br><b>Pre Conditions : </b> Valid input in the textbox
	 * <br><b>Post Conditions : </b> Return a valid integer
	 * @return index of the valid checkbox, if invalid -1
	 */
	public int getActiveCheckBox() {
		for(int i = 0; i < 8; i++) {
			if(checkBoxesStatus[i] == true)
				return i;
		}
		return -1;
	}
}
