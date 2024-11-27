package graphic_battle;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import game_mechanics_moves.Move;

public class MoveButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Move m;
	
	private JLabel text;
	private JLabel type;
	private JLabel pp;
	
	public MoveButton() {
		
		this.setLayout(new GridLayout());
		
		text = new JLabel("-",JLabel.CENTER);
		type = new JLabel("-",JLabel.CENTER);
		pp = new JLabel("-",JLabel.CENTER);
		
		this.add(text);
		this.add(type);
		this.add(pp);
		
		this.setFocusable(false);
		
	}
	
	public MoveButton(Move m) {

		this.m = m;
		
		this.setLayout(new GridLayout());
		
		text = new JLabel(m.getName(),JLabel.CENTER);
		type = new JLabel(""+m.getType(),JLabel.CENTER);
		pp = new JLabel(""+m.getPp(),JLabel.CENTER);
		
		this.add(text);
		this.add(type);
		this.add(pp);
		
		this.setFocusable(false);
		
	}
	
	public void updateText() {
		
		text.setText(m.getName());
		type.setText(""+m.getType());
		pp.setText(""+m.getPp());
		
	}
	
	public Move getMove() {
		
		return m;
		
	}
	
	public void setMove(Move m) {
		
		this.m = m;
		updateText();
		
	}
	
}
