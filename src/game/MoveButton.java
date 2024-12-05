package game;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import move.Move;

/**
 * A custom button that is associated with a pokemon move
 */
public class MoveButton extends JButton {

	private static final long serialVersionUID = 1L;

	//The move of the button
	private Move m;
	
	//The values of the move
	private JLabel text;
	private JLabel type;
	private JLabel pp;
	
	/**
	 * Create an empty button
	 */
	public MoveButton() {
		
		this.setLayout(new GridLayout());
		
		text = new JLabel("-",JLabel.CENTER);
		text.setFont(new Font("Monospaced",Font.BOLD,14));
		
		type = new JLabel("-",JLabel.CENTER);
		type.setFont(new Font("Monospaced",Font.BOLD,14));
		
		pp = new JLabel("-",JLabel.CENTER);
		pp.setFont(new Font("Monospaced",Font.BOLD,14));
		
		this.add(text);
		this.add(type);
		this.add(pp);
		
		this.setFocusable(false);
		
	}
	
	/**
	 * Create a button
	 * 
	 * @param m The move to associate
	 */
	public MoveButton(Move m) {

		this.m = m;
		
		this.setLayout(new GridLayout());
		
		text = new JLabel(m.getName(),JLabel.CENTER);
		text.setFont(new Font("Monospaced",Font.BOLD,14));
		
		type = new JLabel(""+m.getType(),JLabel.CENTER);
		type.setFont(new Font("Monospaced",Font.BOLD,14));
		
		pp = new JLabel(""+m.getCurrentPp(),JLabel.CENTER);
		pp.setFont(new Font("Monospaced",Font.BOLD,14));
		
		this.add(text);
		this.add(type);
		this.add(pp);
		
		this.setFocusable(false);
		
	}
	
	/**
	 * Update the text of the button labels
	 */
	public void updateText() {
		
		text.setText(m.getName());
		type.setText("" + m.getType());
		pp.setText("" + m.getCurrentPp());
		
	}
	
	/**
	 * @return The move of this button
	 */
	public Move getMove() {return m;}
	
	/**
	 * Set a new move for the button
	 * 
	 * @param m The move to set
	 */
	public void setMove(Move m) {
		
		this.m = m;
		updateText();
		
	}
	
}
