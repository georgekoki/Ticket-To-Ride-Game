package view.classes;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import controller.classes.CheckBoxListenerWindowPicker;
import controller.classes.GameData;
import model.classes.Card;
import model.classes.DestinationCard;
import model.classes.Player;
/**
 * 
 * CS252 Object-Oriented Programming
 *
 * WindowDestinationCardPicker Class extends WindowCard Viewer. A window used to pick destination cards
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class WindowDestinationCardPicker extends WindowCardViewer{
	/**
	 * Checkboxes used to pick the cards
	 */
	private ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	/**
	 * Constructor
	 * @param array Cards
	 * @param p Player manipulated
	 * @param d game's data
	 * @param flag is this taking place at the start of the game (false) or in the middle (true)
	 */
	public WindowDestinationCardPicker(ArrayList<Card> array, Player p, GameData d, boolean flag) {
		super(array);
		this.setSize(100 * array.size() + 5, 270);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/UI/Icon-Png.png")));
		
		title.setText((p == d.g_Player1 ? d.g_Player1.getName() : d.g_Player2.getName()) + ", please pick your destination cards");
		for(int i = 0; i < array.size(); i++) {
			JCheckBox temp = new JCheckBox();
			temp.setBounds(40 + 100 * i, 170, 20, 20);
			temp.setBackground(new Color(51, 102, 102));
			temp.addActionListener(new CheckBoxListenerWindowPicker(i, this));
			checkBoxes.add(temp);
			contentPane.add(temp);
		}
		WindowDestinationCardPicker t = this;
		JButton btnPick = new JButton("Pick");
		btnPick.setBounds(10, 200, 89, 30);
		btnPick.setBackground(new Color(204, 153, 0));
		btnPick.setForeground(new Color(255,255,255));
		btnPick.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e ) { 
			boolean[] bools = new boolean[checkBoxes.size()];
			for(int i = 0; i < checkBoxes.size(); i++)
				bools[i] = checkBoxes.get(i).isSelected();
			
			ArrayList<DestinationCard> temp = new ArrayList<DestinationCard>();
			for(Card c : array)
				temp.add((DestinationCard) c);
			
			d.playerPicksDestinationCards(p, bools, flag, temp);
			d.getGamePane().getDeck().setButtonActivity(false);
			t.dispose();
		} });
		contentPane.add(btnPick);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(51, 0, 0));
		btnNewButton.setBounds(100, 200, 89, 30);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(flag) {
					if(d.getLoop().getPlayerTurn()) {
						d.getGamePane().getPlayer1Area().getOnTheTrack().setButtonStatus(true);
						d.getGamePane().getPlayer1Area().getHand().setButtonStatus(true);
					}
					else {
						d.getGamePane().getPlayer2Area().getOnTheTrack().setButtonStatus(true);
						d.getGamePane().getPlayer2Area().getHand().setButtonStatus(true);
					}
					d.getGamePane().getDeck().setButtonActivity(true);
				}
				t.dispose();
			}
		});
		contentPane.add(btnNewButton);
	}
	
	/**
	 * Return how many checkboxes are ticked
	 * @return number of ticked checkboxes
	 */
	public int getNumOfCheckbox() {
		int i = 0;
		for(JCheckBox c : checkBoxes)
			if(c.isSelected()) i++;
		return i;
	}
	
	/**
	 * get array of checkboxes
	 * @return checkboxes
	 */
	public ArrayList<JCheckBox> getCheckBoxes() {
		return checkBoxes;
	}
	/**
	 * Set array of checkboxes
	 * @param checkBoxes checkboxes to set
	 */
	public void setCheckBoxes(ArrayList<JCheckBox> checkBoxes) {
		this.checkBoxes = checkBoxes;
	}
}
