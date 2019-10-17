package view.classes;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.classes.DestinationCardBuyerListener;
import controller.classes.GameData;
import controller.classes.GameUtilities;
import model.classes.DestinationCard;

/**
 * 
 * CS252 Object-Oriented Programming
 *
 * WindowDestinationCardBuyer Class. Window to buy destination cards in. Only gives the option
 * to buy cards the player is able to buy
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class WindowDestinationCardBuyer extends JFrame {

	private JPanel contentPane;
	private JLabel title;
	
	/**
	 * Create the frame.
	 * @param array Array of cards to display
	 * @param d Data of the game
	 */
	public WindowDestinationCardBuyer(ArrayList<DestinationCard> array, GameData d) {
		setResizable(false);
		getContentPane().setLayout(null);
		setTitle("Ticket To Ride");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/UI/Icon-Png.png")));
		
		if(d.getLoop().getPlayerTurn()) {
			d.getGamePane().getPlayer1Area().getOnTheTrack().setButtonStatus(false);
			d.getGamePane().getPlayer1Area().getHand().setButtonStatus(false);
		}
		else {
			d.getGamePane().getPlayer2Area().getOnTheTrack().setButtonStatus(false);
			d.getGamePane().getPlayer2Area().getHand().setButtonStatus(false);
		}
		d.getGamePane().getDeck().setButtonActivity(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		if(array.size() * 100 < 300)
			this.setSize(305, 227);
		else
			this.setSize(100 * array.size() + 5, 227);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 102, 102));
		
		int i = 0;
		
		for(DestinationCard c : array) {
			JButton lbl = new JButton("");
			lbl.setBounds(10 + 100 * i, 30, 79, 132);
			lbl.setIcon(GameUtilities.resizeCard(c.getImage()));
			lbl.setEnabled(c.canPlayerGetCard(d.getActivePlayer()));
			lbl.addActionListener(new DestinationCardBuyerListener(d, i, this));
			contentPane.add(lbl);
			i++;
		}
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String s = "Cards: ";
		
		title = new JLabel(s);
		title.setForeground(new Color(255, 255, 255));
		title.setBounds(10, 11, 300, 14);
		contentPane.add(title);
		WindowDestinationCardBuyer t = this;
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(51, 0, 0));
		btnNewButton.setBounds(10, 165, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(d.getLoop().getPlayerTurn()) {
					d.getGamePane().getPlayer1Area().getOnTheTrack().setButtonStatus(true);
					d.getGamePane().getPlayer1Area().getHand().setButtonStatus(true);
				}
				else {
					d.getGamePane().getPlayer2Area().getOnTheTrack().setButtonStatus(true);
					d.getGamePane().getPlayer2Area().getHand().setButtonStatus(true);
				}
				d.getGamePane().getDeck().setButtonActivity(true);
				t.dispose();
			}
		});
		contentPane.add(btnNewButton);
	}
}
