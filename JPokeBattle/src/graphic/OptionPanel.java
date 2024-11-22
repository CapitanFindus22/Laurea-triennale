package graphic;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public final class OptionPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CardLayout c;
	private JLabel txt;
	
	
	public OptionPanel(CardLayout c_grandparent, JPanel grandparent, GamePanel parent) {
		
		c = new CardLayout(0,0);
		this.setLayout(c);
		
		txt = new JLabel();
		txt.setFont(new Font("Lucida Console",Font.BOLD,16));
		
		JPanel buttons = new JPanel();
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
		
		JButton mosse = new JButton("MOSSE");
		mosse.setForeground(new Color(255, 255, 255));
		mosse.setBackground(new Color(255, 0, 0));
		mosse.setBorder(null);
		mosse.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mosse.setFocusPainted(false);
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
		this.add(txt);
		
		c.first(this);
		
	}

	
}
