package game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import move.Move;
import pokemon.Pokemon;

/**
 * A custom panel containing all the choices for the player
 */
public final class CommandPanel extends JPanel {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The panel containing everything
	 */
	private JPanel buttons;

	/**
	 * The button associated to the first move
	 */
	private MoveButton b1;

	/**
	 * The button associated to the second move
	 */
	private MoveButton b2;

	/**
	 * The button associated to the third move
	 */
	private MoveButton b3;

	/**
	 * The button associated to the fourth move
	 */
	private MoveButton b4;

	/**
	 * The button for returning to the menu
	 */
	private JButton backButton;

	/**
	 * Create the panel
	 * 
	 * @param player the pokemon used by the player
	 */
	public CommandPanel(Pokemon player) {

		this.setLayout(new BorderLayout(0, 0));

		buttons = new JPanel();
		buttons.setLayout(new BorderLayout(10, 10));
		buttons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		buttons.setBackground(new Color(192, 192, 192));

		// Back button
		backButton = new JButton("TORNA AL MENÙ");
		backButton.setPreferredSize(new Dimension(900, 25));
		backButton.setBounds(10, 144, 877, 36);
		backButton.setFont(new Font("Monospaced", Font.BOLD, 13));
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(0, 128, 255));
		backButton.setFocusPainted(false);
		backButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		// Instruments button
		JButton strumenti = new JButton("STRUMENTI");
		strumenti.setPreferredSize(new Dimension(100, 150));
		strumenti.setEnabled(false);
		strumenti.setBorder(null);
		strumenti.setBackground(new Color(255, 128, 64));
		strumenti.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		strumenti.setFont(new Font("Monospaced", Font.ITALIC, 14));

		// Pokemon button
		JButton pokemon = new JButton("POKÉMON");
		pokemon.setPreferredSize(new Dimension(100, 150));
		pokemon.setEnabled(false);
		pokemon.setBorder(null);
		pokemon.setBackground(new Color(0, 255, 64));
		pokemon.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pokemon.setFont(new Font("Monospaced", Font.ITALIC, 14));

		// Move container
		JLabel mosse = new JLabel();
		mosse.setLayout(new BorderLayout());

		// Top moves
		JLabel mosseUp = new JLabel();

		// Bottom moves
		JLabel mosseDown = new JLabel();

		mosseUp.setPreferredSize(new Dimension(200, 88));
		mosseDown.setPreferredSize(new Dimension(200, 88));

		mosseUp.setLayout(new GridLayout());
		mosseUp.setBackground(new Color(255, 0, 0));
		mosseUp.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		mosseDown.setLayout(new GridLayout());
		mosseDown.setBackground(new Color(255, 0, 0));
		mosseDown.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		// Get the player moves and associate them with he buttons
		b1 = new MoveButton(player.getMoves()[0]);
		b2 = new MoveButton(player.getMoves()[1]);
		b3 = new MoveButton();
		b4 = new MoveButton();

		// Add the first two to the top
		mosseUp.add(b1);
		mosseUp.add(b2);

		// Add the last moves to the bottom
		mosseDown.add(b3);
		mosseDown.add(b4);

		mosse.add(mosseUp, BorderLayout.NORTH);
		mosse.add(mosseDown, BorderLayout.SOUTH);

		buttons.add(backButton, BorderLayout.SOUTH);
		buttons.add(strumenti, BorderLayout.WEST);
		buttons.add(pokemon, BorderLayout.EAST);
		buttons.add(mosse, BorderLayout.CENTER);

		this.add(buttons);

	}

	/**
	 * Update all the buttons
	 */
	public void updateButtons() {

		b1.updateText();
		b2.updateText();
		if (b3.getMove() != null)
			b3.updateText();
		if (b4.getMove() != null)
			b4.updateText();

	}

	/**
	 * Disable the moves buttons
	 */
	public void disableMoves() {

		b1.setEnabled(false);
		b2.setEnabled(false);
		b3.setEnabled(false);
		b4.setEnabled(false);

	}

	/**
	 * Enable the moves buttons
	 */
	public void enableMoves() {

		b1.setEnabled(true);
		b2.setEnabled(true);
		if (b3.getMove() != null)
			b3.setEnabled(true);
		if (b4.getMove() != null)
			b4.setEnabled(true);

	}

	/**
	 * Disable the back button
	 */
	public void disableBack() {
		backButton.setEnabled(false);
	}

	/**
	 * Enable the back button
	 */
	public void enableBack() {
		backButton.setEnabled(true);
	}

	// Setter

	/**
	 * Set the back button action on click
	 * 
	 * @param a the action listener that contains the function to execute
	 */
	public void setBackButtonAction(ActionListener a) {
		backButton.addActionListener(a);
	}

	/**
	 * Set the first button move
	 * 
	 * @param m the move to set
	 */
	public void setFirstMove(Move m) {
		b1.setMove(m);
	}

	/**
	 * Set the second button move
	 * 
	 * @param m the move to set
	 */
	public void setSecondMove(Move m) {
		b2.setMove(m);
	}

	/**
	 * Set the third button move
	 * 
	 * @param m the move to set
	 */
	public void setThirdMove(Move m) {
		b3.setMove(m);
	}

	/**
	 * Set the fourth button move
	 * 
	 * @param m the move to set
	 */
	public void setFourthMove(Move m) {
		b4.setMove(m);
	}

	// Getter

	/**
	 * {@return the move of the first button}
	 */
	public MoveButton getFirstMove() {
		return b1;
	}

	/**
	 * {@return the move of the second button}
	 */
	public MoveButton getSecondMove() {
		return b2;
	}

	/**
	 * {@return the move of the third button}
	 */
	public MoveButton getThirdMove() {
		return b3;
	}

	/**
	 * {@return the move of the fourth button}
	 */
	public MoveButton getFourthMove() {
		return b4;
	}

}
