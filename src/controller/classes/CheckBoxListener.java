package controller.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.classes.CardViewerHand;
/**
 * CS252 Object-Oriented Programming
 *
 * CheckBox Listener Class implements ActionListener. Used to handle checkboxes for the Hand area of the UI
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class CheckBoxListener implements ActionListener{
	/**
	 * Index of box ticked
	 */
	private int index;
	/**
	 * The UI hand of the player
	 */
	private CardViewerHand hand;
	/**
	 * Constructor
	 * @param i indes
	 * @param hand UI Hand
	 */
	public CheckBoxListener(int i, CardViewerHand hand) {
		index = i;
		this.hand = hand;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Just make sure one of them is ticked.
		boolean[] temp = hand.getCheckBoxesStatus();
		for(int i = 0; i < 8; i++) {
			temp[i] = false;
		}
		
		temp[index] = true;
		hand.setCheckBoxesStatus(temp);
		hand.updateCheckBoxes();
	}

}
