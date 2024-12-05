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

	private static final long serialVersionUID = 1L;
	
	//The panel containing all the buttons
	private JPanel buttons;
	
	//The four buttons for the moves
	private MoveButton m1;
	private MoveButton m2;
	private MoveButton m3;
	private MoveButton m4;
	
	//The back button
	private JButton back;
	
	public CommandPanel(Pokemon p) {
		
		this.setLayout(new BorderLayout(0,0));
		
		buttons = new JPanel();
		buttons.setLayout(new BorderLayout(10,10));
		buttons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		buttons.setBackground(new Color(192, 192, 192));
		
		//Back button
		back = new JButton("TORNA AL MENÙ");
		back.setPreferredSize(new Dimension(900,25));
		back.setBounds(10, 144, 877, 36);
		back.setFont(new Font("Monospaced",Font.BOLD,13));
		back.setForeground(new Color(255, 255, 255));
		back.setBackground(new Color(0, 128, 255));
		back.setFocusPainted(false);
		back.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		//Instruments button
		JButton strumenti = new JButton("STRUMENTI");
		strumenti.setPreferredSize(new Dimension(100,150));
		strumenti.setEnabled(false);
		strumenti.setBorder(null);
		strumenti.setBackground(new Color(255, 128, 64));
		strumenti.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		strumenti.setFont(new Font("Monospaced",Font.ITALIC,14));
		
		//Pokemon button
		JButton pokemon = new JButton("POKÉMON");
		pokemon.setPreferredSize(new Dimension(100,150));
		pokemon.setEnabled(false);
		pokemon.setBorder(null);
		pokemon.setBackground(new Color(0, 255, 64));
		pokemon.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pokemon.setFont(new Font("Monospaced",Font.ITALIC,14));
		
		//Move container
		JLabel mosse = new JLabel();
		mosse.setLayout(new BorderLayout());
		
		//Top moves
		JLabel mosseUp = new JLabel();
		
		//Bottom moves
		JLabel mosseDown = new JLabel();
		
		mosseUp.setPreferredSize(new Dimension(200,90));
		mosseDown.setPreferredSize(new Dimension(200,90));

		mosseUp.setLayout(new GridLayout());
		mosseUp.setBackground(new Color(255, 0, 0));
		mosseUp.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		mosseDown.setLayout(new GridLayout());
		mosseDown.setBackground(new Color(255, 0, 0));
		mosseDown.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		//Get the player moves and associate them with he buttons
		m1 = new MoveButton(p.getMoves()[0]);
		m2 = new MoveButton(p.getMoves()[1]);
		m3 = new MoveButton();
		m4 = new MoveButton();
		
		//Add the first two to the top
		mosseUp.add(m1);
		mosseUp.add(m2);
		
		//Add the last moves to the bottom
		mosseDown.add(m3);
		mosseDown.add(m4);
		
		mosse.add(mosseUp,BorderLayout.NORTH);
		mosse.add(mosseDown,BorderLayout.SOUTH);
		
		buttons.add(back,BorderLayout.SOUTH);
		buttons.add(strumenti,BorderLayout.WEST);
		buttons.add(pokemon,BorderLayout.EAST);
		buttons.add(mosse,BorderLayout.CENTER);
		
		this.add(buttons);
		
	}

	/**
	 * Update all the buttons
	 */
	public void updateButtons() {
		
		m1.updateText();
		m2.updateText();
		if(m3.getMove() != null) m3.updateText();
		if(m4.getMove() != null) m4.updateText();
		
	}
	
	/**
	 * Disable the moves buttons
	 */
	public void disableMoves() {
		
		m1.setEnabled(false);
		m2.setEnabled(false);
		m3.setEnabled(false);
		m4.setEnabled(false);
		
	}
	
	/**
	 * Enable the moves buttons
	 */
	public void enableMoves() {
		
		m1.setEnabled(true);
		m2.setEnabled(true);
		if(m3.getMove() != null) m3.setEnabled(true);
		if(m4.getMove() != null) m4.setEnabled(true);
		
	}
	
	/**
	 * Disable the back button
	 */
	public void disableBack() {back.setEnabled(false);}
	
	/**
	 * Enable the back button
	 */
	public void enableBack() {back.setEnabled(true);}
	
	//Setter
	
	/**
	 * Set the back button action on click
	 * 
	 * @param a The action listener that contains the function to execute
	 */
	public void setBackButtonAction(ActionListener a) {back.addActionListener(a);}
	
	/**
	 * Set the first button move
	 * 
	 * @param m The move to set
	 */
	public void setFirstMove(Move m) {m1.setMove(m);}
	
	/**
	 * Set the second button move
	 * 
	 * @param m The move to set
	 */
	public void setSecondMove(Move m) {m2.setMove(m);}
	
	/**
	 * Set the third button move
	 * 
	 * @param m The move to set
	 */
	public void setThirdMove(Move m) {m3.setMove(m);}
	
	/**
	 * Set the fourth button move
	 * 
	 * @param m The move to set
	 */
	public void setFourthMove(Move m) {m4.setMove(m);}
	
	//Getter
	
	/**
	 * @return The move of the first button
	 */
	public MoveButton getFirst() {return m1;}
	
	/**
	 * @return The move of the second button
	 */
	public MoveButton getSecond() {return m2;}
	
	/**
	 * @return The move of the third button
	 */
	public MoveButton getThird() {return m3;}
	
	/**
	 * @return The move of the fourth button
	 */
	public MoveButton getFourth() {return m4;}
	

	
}
