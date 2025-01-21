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

/**
 * A custom window that serves as the main window of the game
 */
public final class MainMenu extends JFrame {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The width of the window
	 */
	private final int WIDTH = 900;

	/**
	 * The height of the window
	 */
	private final int HEIGHT = 700;

	/**
	 * The path of the title image
	 */
	private final static Path title_path = Paths.get("resource", "image", "title.png");

	/**
	 * The path of the start button image
	 */
	private final static Path start_path = Paths.get("resource", "image", "start.png");

	/**
	 * The path of the leaderboard button image
	 */
	private final static Path lboard_path = Paths.get("resource", "image", "leaderboard.png");

	/**
	 * Create the window
	 */
	public MainMenu() {

		super("Menù");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(0, 0, WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		/*
		 * Container will contain the main menu panel and the game panel, they'll be
		 * switched using a card layout
		 */
		JPanel container = new JPanel();
		CardLayout c = new CardLayout(0, 0);
		container.setLayout(c);

		// The main panel
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BorderLayout(0, 0));
		main_panel.setBackground(new Color(196, 207, 161));

		// The panel containing stuff to select the pokémon
		JPanel choosePanel = new JPanel();
		choosePanel.setBackground(new Color(196, 207, 161));

		// Just text
		JLabel choose = new JLabel("Scegli il tuo pokémon");
		choose.setFont(new Font("Monospaced", Font.BOLD, 20));

		// A list of pokémon to choose from
		JComboBox<String> cbox = new JComboBox<>(FileRw.allPokemon().toArray(String[]::new));
		cbox.setFont(new Font("Monospaced", Font.BOLD, 20));
		cbox.setPreferredSize(new Dimension(200, 60));
		cbox.setFocusable(false);
		cbox.setBackground(new Color(196, 207, 161));
		((JLabel) cbox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		// Start button
		JButton start_button = new JButton();
		start_button.setOpaque(false);
		start_button.setContentAreaFilled(false);
		start_button.setBorderPainted(false);
		start_button.setFocusPainted(false);
		start_button.setPreferredSize(new Dimension(100, 600));
		start_button.setIcon(new ImageIcon(start_path.toString()));

		// Start the game on click
		start_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Create a game panel and switch to it
				container.add(new Game(c, container, new PlayerPokemon((String) cbox.getSelectedItem())));
				c.last(container);

				// Close the leaderboard if it's open
				if (Leaderboard.existIstance())
					Leaderboard.getIstance().dispose();

			}
		});

		// Leaderboard button
		JButton lead_button = new JButton();
		lead_button.setOpaque(false);
		lead_button.setContentAreaFilled(false);
		lead_button.setBorderPainted(false);
		lead_button.setFocusPainted(false);
		lead_button.setPreferredSize(new Dimension(100, 600));
		lead_button.setIcon(new ImageIcon(lboard_path.toString()));

		// Open the leaderboard on click
		lead_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Leaderboard.getIstance();
			}
		});

		// Title
		JLabel title = new JLabel("", JLabel.CENTER);
		title.setIcon(new ImageIcon(title_path.toString()));
		title.setOpaque(false);

		// Layout of the choice panel
		choosePanel.add(choose);
		choosePanel.add(new JLabel());
		choosePanel.add(cbox);

		// Layout of the main panel
		main_panel.add(choosePanel, BorderLayout.SOUTH);
		main_panel.add(lead_button, BorderLayout.WEST);
		main_panel.add(start_button, BorderLayout.EAST);
		main_panel.add(title, BorderLayout.CENTER);

		// Add the main panel to the container
		container.add(main_panel);

		// Add the container to this window
		this.add(container);

		// Display the first card in the container
		c.first(container);

		this.setVisible(true);

	}

}
