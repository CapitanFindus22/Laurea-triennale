package graphic_battle;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import game_mechanics_pokemon.Pokemon;

public final class CommandPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CardLayout c;
	private CommandPanel label;
	private TextPanel text;
	private JPanel buttons;
	private MoveButton m1;
	private MoveButton m2;
	private MoveButton m3;
	private MoveButton m4;
	
	public CommandPanel(CardLayout c_grandparent, JPanel grandparent, GamePanel parent, Pokemon p) {
		
		c = new CardLayout(0,0);
		this.setLayout(c);
		
		label = this;
		
		text = new TextPanel();
		
		buttons = new JPanel();
		buttons.setFont(new Font("Lucida Console",Font.BOLD,24));
		buttons.setLayout(new BorderLayout(5,5));
		buttons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		buttons.setBackground(new Color(192, 192, 192));
		
		JButton back = new JButton("TORNA AL MENÙ");
		back.setPreferredSize(new Dimension(900,25));
		back.setBounds(10, 144, 877, 36);
		back.setForeground(new Color(255, 255, 255));
		back.setBackground(new Color(0, 128, 255));
		back.setFocusPainted(false);
		back.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		buttons.add(back,BorderLayout.SOUTH);
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
				
					c_grandparent.first(grandparent);
					c_grandparent.removeLayoutComponent(parent);
				
			  }
		});
		
		JLabel mosse = new JLabel();
		mosse.setLayout(new GridLayout());
		mosse.setBackground(new Color(255, 0, 0));
		mosse.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		m1 = new MoveButton(p.getMoves()[0]);
		m2 = new MoveButton(p.getMoves()[1]);
		m3 = new MoveButton(p.getMoves()[1]);
		m4 = new MoveButton(p.getMoves()[1]);
		
		mosse.add(m1);
		mosse.add(m2);
		
		buttons.add(mosse,BorderLayout.CENTER);
		
		JButton strumenti = new JButton("STRUMENTI");
		strumenti.setPreferredSize(new Dimension(100,150));
		strumenti.setEnabled(false);
		strumenti.setBorder(null);
		strumenti.setBackground(new Color(255, 128, 64));
		strumenti.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		buttons.add(strumenti,BorderLayout.WEST);
		
		JButton pokemon = new JButton("POKÉMON");
		pokemon.setPreferredSize(new Dimension(100,150));
		pokemon.setEnabled(false);
		pokemon.setBorder(null);
		pokemon.setBackground(new Color(0, 255, 64));
		pokemon.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		buttons.add(pokemon,BorderLayout.EAST);
		
		this.add(buttons);
		this.add(text);
		
		c.first(this);
		
	}

	
	public void updateButtons() {
		
		m1.updatePp();
		m2.updatePp();
		m3.updatePp();
		m4.updatePp();
		
	}
	
	public void write(String s) {
		
		c.show(label,text.getName());
		
		text.write(s);
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.show(label,buttons.getName());
		
	}
	
	public MoveButton getFirst() {
		return m1;
	}
	
	public MoveButton getSecond() {
		return m2;
	}
	
	public MoveButton getThird() {
		return m3;
	}
	
	public MoveButton getFourth() {
		return m4;
	}
	
}
