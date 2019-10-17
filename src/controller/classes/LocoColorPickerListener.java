package controller.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.classes.TrainCard;
import model.enumerators.TrainColor;
import model.exceptions.CardNotFoundException;
import view.classes.WindowColorPickerLoco;
/**
 * CS252 Object-Oriented Programming
 *
 * LocoColorPickerListener Class implements ActionListener. Listener used to pick a color when exclusively playing a locomotive card
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class LocoColorPickerListener implements ActionListener {
	
	/**
	 * Game's data
	 */
	private GameData d;
	/**
	 * Color picked
	 */
	private int color;
	/**
	 * Number of cards we will play
	 */
	private int a;
	/**
	 * Window
	 */
	private WindowColorPickerLoco w;
	/**
	 * Constructor
	 * @param d Game's data
	 * @param a Number of cards we will play
	 * @param w Window
	 */
	public LocoColorPickerListener(GameData d, int a, WindowColorPickerLoco w) {
		this.d = d;
		this.color = 0; 
		this.a = a;
		this.w = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Get choice from box
		color = w.getChoice().getSelectedIndex();
		
		// remove cards from player's hand
		try {
			for(int i = 0; i < a; i++)
				d.getActivePlayer().getHand().removeCard(new TrainCard(null, TrainColor.locomotive.toString(), TrainColor.locomotive));
		} catch (CardNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// Add cards to railyard
		int[] temp = new int[8];
		temp = d.getActivePlayer().getRailYard().getColors();
		temp[color] += a;
		d.getActivePlayer().setRailYard(temp);
		
		// Close window and go back to the loop
		w.dispose();
		if(d.getLoop().getPlayerTurn()) {
			d.getGamePane().getPlayer1Area().getOnTheTrack().setButtonStatus(true);
			d.getGamePane().getPlayer1Area().getHand().setButtonStatus(true);
		}
		else {
			d.getGamePane().getPlayer2Area().getOnTheTrack().setButtonStatus(true);
			d.getGamePane().getPlayer2Area().getHand().setButtonStatus(true);
		}
		d.getGamePane().getDeck().setButtonActivity(true);
		this.d.getLoop().updateWindow();
		this.d.getLoop().RP2_3_throwTrainCards();
	}
}
