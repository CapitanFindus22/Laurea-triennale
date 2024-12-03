package menu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.Game;
import i_o.FileRw;
import pokemon.PlayerPokemon;

public final class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int WIDTH = 900;
	private final int HEIGHT = 700;
	
	private final Path title_path = Paths.get("resource","image","title.png");
	private final Path start_path = Paths.get("resource","image","start.png");
	private final Path lboard_path = Paths.get("resource","image","leaderboard.png");
	
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
		main_panel.setBackground(new Color(196, 207, 161));

		JPanel choosePanel = new JPanel();
		choosePanel.setBackground(new Color(196, 207, 161));
		
		JLabel choose = new JLabel("Scegli il tuo pokemon");
		choose.setFont(new Font("Monospaced",Font.BOLD,20));
		
		JComboBox<String> cbox = new JComboBox<>(FileRw.allPokemon().toArray(String[]::new));
		cbox.setFont(new Font("Monospaced",Font.BOLD,20));
		cbox.setPreferredSize(new Dimension(200,60));
		cbox.setFocusable(false);
		cbox.setBackground(new Color(196, 207, 161));
		((JLabel)cbox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton start_button = new JButton();
		start_button.setOpaque(false);
		start_button.setContentAreaFilled(false);
		start_button.setBorderPainted(false);
		start_button.setFocusPainted(false);
		start_button.setPreferredSize(new Dimension(100,600));
		start_button.setIcon(new ImageIcon(start_path.toString()));

		start_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
					container.add(new Game(c,container,new PlayerPokemon((String)cbox.getSelectedItem())));
					c.last(container);
					if (Leaderboard.getIstance() != null) Leaderboard.getIstance().dispose();
					
			  } 			
		});
		
		JButton lead_button = new JButton();
		lead_button.setOpaque(false);
		lead_button.setContentAreaFilled(false);
		lead_button.setBorderPainted(false);
		lead_button.setFocusPainted(false);
		lead_button.setPreferredSize(new Dimension(100,600));
		lead_button.setIcon(new ImageIcon(lboard_path.toString()));

		lead_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {Leaderboard.getIstance();} 			
		});

		JLabel title = new JLabel("",JLabel.CENTER);
		title.setIcon(new ImageIcon(title_path.toString()));
		title.setOpaque(false);
		
		choosePanel.add(choose);
		choosePanel.add(new JLabel());
		choosePanel.add(cbox);
		
		main_panel.add(choosePanel, BorderLayout.SOUTH);
		main_panel.add(lead_button, BorderLayout.WEST);
		main_panel.add(start_button, BorderLayout.EAST);
		main_panel.add(title, BorderLayout.CENTER);
		
		container.add(main_panel);
		
		this.add(container);
		
		c.first(container);
		
		this.setVisible(true);
		
	}
	
}
