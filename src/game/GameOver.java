package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import i_o.Entry;

/**
 * A custom window for the game over
 */
public class GameOver extends JDialog {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the window
	 * 
	 * @param streak      the number of rounds won
	 * @param pokemonName the pokemon used
	 */
	public GameOver(int streak, String pokemonName) {

		this.setLayout(new BorderLayout());
		this.setSize(900, 600);
		this.setTitle("Game Over");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(196, 207, 161));

		// Display rounds won
		JLabel roundStreak = new JLabel("Round vinti: " + streak, JLabel.CENTER);
		roundStreak.setFont(new Font("Monospaced", Font.BOLD, 35));
		roundStreak.setPreferredSize(new Dimension(900, 200));

		// Game Over
		JLabel gameOver = new JLabel("GAME OVER", JLabel.CENTER);
		gameOver.setFont(new Font("Monospaced", Font.BOLD, 50));

		// Check if this session set a new record
		if (Entry.checkNewRecord(streak)) {

			JPanel record = new JPanel();
			record.setPreferredSize(new Dimension(900, 150));
			record.setBackground(new Color(196, 207, 161));

			// New record
			JLabel newRecordLabel = new JLabel("<html><center>Nuovo Record!!</center><br>Inserisci il tuo nome</html>",
					JLabel.LEADING);
			newRecordLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
			newRecordLabel.setPreferredSize(new Dimension(300, 100));

			// To enter the name of the player
			JTextField name = new JTextField();
			name.setFont(new Font("Monospaced", Font.BOLD, 20));
			name.setPreferredSize(new Dimension(300, 50));

			// Save button
			JButton save = new JButton("Salva");
			save.setPreferredSize(new Dimension(100, 50));

			JDialog go = this;

			// Save button save the record
			save.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					Entry.saveNewRecord(name.getText(), pokemonName, streak);
					go.dispose();

				}

			});

			// Add components
			record.add(newRecordLabel, BorderLayout.NORTH);
			record.add(name, BorderLayout.CENTER);
			record.add(save, BorderLayout.SOUTH);

			this.add(record, BorderLayout.SOUTH);

		}

		// Add components
		this.add(roundStreak, BorderLayout.NORTH);
		this.add(gameOver, BorderLayout.CENTER);

		this.setVisible(true);

	}

}
