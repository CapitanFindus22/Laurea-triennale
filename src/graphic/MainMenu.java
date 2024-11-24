package graphic;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game_mechanics_pokemon.PlayerPokemon;
import graphic_battle.GamePanel;
import i_o.FileRw;

public final class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 900;
	private static final int HEIGHT = 700;
	
	public MainMenu() {
		
		super("Menù");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(0, 0, WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JPanel container = new JPanel();
		CardLayout c = new CardLayout(0,0);
		container.setLayout(c);
		
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BorderLayout(0, 0));

		JComboBox<String> cbox = new JComboBox<>(FileRw.allPokemon().toArray(String[]::new));
		cbox.setPreferredSize(new Dimension(100,50));
		
		JButton start_button = new JButton(String.format("<html>G<br>I<br>O<br>C<br>A</html>"));
		start_button.setOpaque(false);
		start_button.setContentAreaFilled(false);
		start_button.setBorderPainted(false);
		start_button.setFocusPainted(false);
		start_button.setFont(new Font("Lucida Console",Font.BOLD,20));
		start_button.setPreferredSize(new Dimension(100,600));

		start_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
				
					GamePanel gp = new GamePanel(c,container);
					container.add(gp);
					c.last(container);
					if (Leaderboard.getIstance() != null) Leaderboard.getIstance().dispose();
					gp.start(new PlayerPokemon((String)cbox.getSelectedItem()));
					
			  } 			
		});
		
		JButton lead_button = new JButton("<html>L<br>E<br>A<br>D<br>E<br>R<br>B<br>O<br>A<br>R<br>D</html>");
		lead_button.setOpaque(false);
		lead_button.setContentAreaFilled(false);
		lead_button.setBorderPainted(false);
		lead_button.setFocusPainted(false);
		lead_button.setFont(new Font("Lucida Console",Font.BOLD,20));
		lead_button.setPreferredSize(new Dimension(100,600));

		lead_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
			    	Leaderboard.getIstance();
			  } 			
		});

		JLabel title = new JLabel("JPokeBattle");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Vineta Bt",Font.PLAIN,18));
		
		main_panel.add(cbox, BorderLayout.SOUTH);
		main_panel.add(lead_button, BorderLayout.WEST);
		main_panel.add(start_button, BorderLayout.EAST);
		main_panel.add(title, BorderLayout.CENTER);
		
		container.add(main_panel);
		
		this.add(container);
		
		c.first(container);
		
		this.setVisible(true);

		
	}
	
}
