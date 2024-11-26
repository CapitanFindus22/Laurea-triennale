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
	
	private JLabel pp; 
	
	public MoveButton() {
		
		this.setLayout(new GridLayout());
		
	}
	
	public MoveButton(Move m) {

		this.m = m;
		
		String.valueOf(m.getPp());
		
		this.setLayout(new GridLayout());
		this.add(new JLabel(m.getName()));
		this.add(new JLabel(m.getType().toString()));
		
		pp = new JLabel(String.valueOf(m.getPp()));
		
		this.add(pp);
		
		this.setFocusable(false);
		
		
	}
	
	public void updatePp() {
		
		pp.setText(String.valueOf(m.getPp()));
		
	}
	
	public Move getMove() {
		
		return m;
		
	}
	
	public void setMove(Move m) {
		
		this.m = m;
		
	}
	
}
