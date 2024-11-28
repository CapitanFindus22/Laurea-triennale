package game;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import move.Move;

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
		text.setFont(new Font("Lucida Console",Font.PLAIN,14));
		
		type = new JLabel("-",JLabel.CENTER);
		type.setFont(new Font("Lucida Console",Font.PLAIN,14));
		
		pp = new JLabel("-",JLabel.CENTER);
		pp.setFont(new Font("Lucida Console",Font.PLAIN,14));
		
		this.add(text);
		this.add(type);
		this.add(pp);
		
		this.setFocusable(false);
		
	}
	
	public MoveButton(Move m) {

		this.m = m;
		
		this.setLayout(new GridLayout());
		
		text = new JLabel(m.getName(),JLabel.CENTER);
		text.setFont(new Font("Lucida Console",Font.PLAIN,14));
		
		type = new JLabel(""+m.getType(),JLabel.CENTER);
		type.setFont(new Font("Lucida Console",Font.PLAIN,14));
		
		pp = new JLabel(""+m.getPp(),JLabel.CENTER);
		pp.setFont(new Font("Lucida Console",Font.PLAIN,14));
		
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
