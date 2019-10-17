package view.classes;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

/**
 * 
 * CS252 Object-Oriented Programming
 *
 * GameWindow Class. The game takes place in this window.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class GameWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @param gamePanel pane that contains the game
	 */
	public GameWindow(view.classes.Game gamePanel) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/UI/Icon-Png.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1036);
		setTitle("Ticket To Ride");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(gamePanel, BorderLayout.CENTER);
	}

}
