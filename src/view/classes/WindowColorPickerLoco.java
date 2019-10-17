package view.classes;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.classes.GameData;
import controller.classes.LocoColorPickerListener;
import controller.classes.ViewListener;


import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Toolkit;

/**
 * CS252 Object-Oriented Programming
 *
 * WindowColorPickerLoco Class. Window for picking a color when putting down only locomotive cards
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class WindowColorPickerLoco extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> choice;

	public WindowColorPickerLoco(ViewListener l, GameData d, int a) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 283, 91);
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
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 102, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		choice = new JComboBox<String>();
		choice.addItem("red");
		choice.addItem("blue");
		choice.addItem("yellow");
		choice.addItem("green");
		choice.addItem("purple");
		choice.addItem("white");
		choice.addItem("orange");
		choice.addItem("black");
		contentPane.add(choice, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Pick Color for Locomotive");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Pick");
		btnNewButton.setBackground(new Color(51, 102, 153));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new LocoColorPickerListener(d, a, this));
		contentPane.add(btnNewButton, BorderLayout.EAST);
	}
	/**
	 * @return Choice
	 */
	public JComboBox<String> getChoice() {
		return choice;
	}
	
}
