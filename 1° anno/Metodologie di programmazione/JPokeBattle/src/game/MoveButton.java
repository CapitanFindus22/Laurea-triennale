package game;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import move.Move;

/**
 * A custom button associated with a {@link move.Move}
 */
public class MoveButton extends JButton {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The move associated with this button
	 */
	private Move move;

	/**
	 * The name of the move
	 */
	private JLabel text;

	/**
	 * The type of the move
	 */
	private JLabel type;

	/**
	 * The pp of the move
	 */
	private JLabel pp;

	/**
	 * Create an empty button
	 */
	public MoveButton() {

		this.setLayout(new GridLayout());

		text = new JLabel("-", JLabel.CENTER);
		text.setFont(new Font("Monospaced", Font.BOLD, 14));

		type = new JLabel("-", JLabel.CENTER);
		type.setFont(new Font("Monospaced", Font.BOLD, 14));

		pp = new JLabel("-", JLabel.CENTER);
		pp.setFont(new Font("Monospaced", Font.BOLD, 14));

		this.add(text);
		this.add(type);
		this.add(pp);

		this.setFocusable(false);

	}

	/**
	 * Create a button with a move
	 * 
	 * @param move the move to associate
	 */
	public MoveButton(Move move) {

		this.move = move;

		this.setLayout(new GridLayout());

		text = new JLabel(move.getName(), JLabel.CENTER);
		text.setFont(new Font("Monospaced", Font.BOLD, 14));

		type = new JLabel("" + move.getType(), JLabel.CENTER);
		type.setFont(new Font("Monospaced", Font.BOLD, 14));

		pp = new JLabel("" + move.getCurrentPp() + "/" + move.getMaxpp(), JLabel.CENTER);
		pp.setFont(new Font("Monospaced", Font.BOLD, 14));

		this.add(text);
		this.add(type);
		this.add(pp);

		this.setFocusable(false);

	}

	/**
	 * Update all the text present in the button
	 */
	public void updateText() {

		text.setText(move.getName());
		type.setText("" + move.getType());
		pp.setText("" + move.getCurrentPp() + "/" + move.getMaxpp());

	}

	// Getter

	/**
	 * {@return the move of this button}
	 */	
	public Move getMove() {
		return move;
	}

	// Setter

	/**
	 * Associate a move to this button
	 * 
	 * @param newMove the new move
	 */
	public void setMove(Move newMove) {

		move = newMove;
		updateText();

	}

}
