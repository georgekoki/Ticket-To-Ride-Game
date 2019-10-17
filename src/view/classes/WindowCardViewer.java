package view.classes;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.classes.GameUtilities;
import model.classes.BigCityCard;
import model.classes.Card;
import model.classes.DestinationCard;


import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Toolkit;
/**
 * 
 * CS252 Object-Oriented Programming
 *
 * WindowCardViewer Class. View an array of cards.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class WindowCardViewer extends JFrame {


	protected JPanel contentPane;
	protected JLabel title;
	/**
	 * Constructor
	 * @param array array of cards
	 */
	public WindowCardViewer(ArrayList<Card> array) {
		setResizable(false);
		getContentPane().setLayout(null);
		setTitle("Ticket To Ride");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/UI/Icon-Png.png")));
		
		if(array.size() * 100 < 300)
			this.setSize(300, 227);
		else
			this.setSize(100 * array.size(), 227);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 102, 102));
		
		int i = 0;
		
		for(Card c : array) {
			JLabel lbl = new JLabel("");
			lbl.setBounds(10 + 100 * i, 22, 95, 155);
			lbl.setIcon(GameUtilities.resizeCard(c.getImage()));
			contentPane.add(lbl);
			i++;
		}
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String s = "Cards: ";

		if(array.get(0) instanceof DestinationCard)
			s = "Owned Destination Tickets:";
		
		if(array.get(0) instanceof BigCityCard)
			s = "Earned Big City Cards:";
		
		title = new JLabel(s);
		title.setForeground(new Color(255, 255, 255));
		title.setBounds(10, 11, 300, 14);
		contentPane.add(title);
	}
}
