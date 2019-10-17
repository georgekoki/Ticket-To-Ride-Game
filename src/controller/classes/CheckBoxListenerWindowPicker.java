package controller.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.classes.WindowDestinationCardPicker;
/**
 * CS252 Object-Oriented Programming
 *
 * CheckBoxListenerWindowPicker Class implements ActionListener. Used to handle tickboxes on the destination card picker UI
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class CheckBoxListenerWindowPicker implements ActionListener{
	/**
	 * Index of the checkbox ticked
	 */
	private int index;
	/**
	 * Picker window
	 */
	private WindowDestinationCardPicker w;
	/**
	 * Constructor
	 * @param index Index of checkbox
	 * @param w Window
	 */
	public CheckBoxListenerWindowPicker(int index, WindowDestinationCardPicker w){
		 this.index = index;
		 this.w = w;
	 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(w.getNumOfCheckbox() == 5) 
			w.getCheckBoxes().get(index).setSelected(false);
	}

}
