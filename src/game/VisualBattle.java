package game;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

import audio.SoundPlayer;
import pokemon.EnemyPokemon;
import pokemon.PlayerPokemon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;

/**
 * A custom panel containing the visual part of the game
 */
public final class VisualBattle extends JPanel {

	/**
	 * Version
	 */
	private static final long serialVersionUID = 1L;

	// Player

	/**
	 * Contain the name of the player pokémon
	 */
	private JLabel playerName;

	/**
	 * Contain the level of the player pokémon
	 */
	private JLabel playerLvl;

	/**
	 * Contain the sprite of the player pokémon
	 */
	private JLabel playerSprite;

	/**
	 * Display the hp of the player pokémon
	 */
	private JProgressBar playerHp;

	// Enemy

	/**
	 * Contain the name of the enemy pokémon
	 */
	private JLabel enemyName;

	/**
	 * Contain the level of the enemy pokémon
	 */
	private JLabel enemyLvl;

	/**
	 * Contain the sprite of the enemy pokémon
	 */
	private JLabel enemySprite;

	/**
	 * Display the hp of the enemy pokémon
	 */
	private JProgressBar enemyHp;

	/**
	 * Used to create space
	 */
	private final String spacer = "     ";

	/**
	 * Background image path
	 */
	private final Path bg_path = Paths.get("resource", "image", "bg.png");

	/**
	 * Create the panel
	 * 
	 * @param player the player pokémon
	 * @param enemy  the Enemy pokémon
	 */
	public VisualBattle(PlayerPokemon player, EnemyPokemon enemy) {

		// Background
		JLabel background = new JLabel();
		background.setLayout(new BorderLayout());
		background.setIcon(new ImageIcon(bg_path.toString()));

		this.setBorder(new LineBorder(Color.GRAY, 2));
		this.setLayout(new BorderLayout());

		// Player panel
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridBagLayout());
		playerPanel.setOpaque(false);

		// Stats panel
		JPanel playerInfo = new JPanel();
		playerInfo.setLayout(new GridBagLayout());
		playerInfo.setOpaque(false);

		// Player hp bar
		playerHp = new JProgressBar();
		playerHp.setVisible(false);

		playerLvl = new JLabel();
		playerName = new JLabel();
		playerSprite = new JLabel();

		// Add name and bar
		playerInfo.add(playerName);
		playerInfo.add(playerHp);
		playerInfo.add(playerLvl);

		// Add components to player panel
		playerPanel.add(playerSprite);
		playerPanel.add(playerInfo);

		// Enemy panel
		JPanel enemyPanel = new JPanel();
		enemyPanel.setLayout(new GridBagLayout());
		enemyPanel.setOpaque(false);

		// Enemy stats
		JPanel enemyInfo = new JPanel();
		enemyInfo.setLayout(new GridBagLayout());
		enemyInfo.setOpaque(false);

		enemyLvl = new JLabel();
		enemyName = new JLabel();
		enemySprite = new JLabel();

		// Enemy hp bar
		enemyHp = new JProgressBar();
		enemyHp.setVisible(false);

		enemyInfo.add(enemyName);
		enemyInfo.add(enemyHp);
		enemyInfo.add(enemyLvl);

		// Add components to enemy panel
		enemyPanel.add(enemyInfo);
		enemyPanel.add(enemySprite);

		// Add components to main panel
		background.add(playerPanel, BorderLayout.SOUTH);
		background.add(enemyPanel, BorderLayout.NORTH);

		this.add(background);

		// Small animation
		new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				
				SoundPlayer sound = new SoundPlayer();
				sound.setFile(player.getName());

				playerSprite.setIcon(new ImageIcon(player.getBackSprite()));
				playerName.setText(player.getName() + spacer);
				playerLvl.setText(spacer + "Lv: " + player.getLevel());

				playerHp.setSize(new Dimension(30, 100));
				playerHp.setMaximum(player.getStats().getHp());
				playerHp.setMinimum(0);
				playerHp.setValue(player.getStats().getHp());
				playerHp.setString(String.valueOf(player.getStats().getHp()));
				playerHp.setForeground(new Color(10, 200, 10));
				playerHp.setStringPainted(true);
				playerHp.setVisible(true);

				sound.play();
				
				sound.setFile(enemy.getName());

				enemySprite.setIcon(new ImageIcon(enemy.getFrontSprite()));
				enemyName.setText(enemy.getName() + spacer);
				enemyLvl.setText(spacer + "Lv: " + enemy.getLevel());

				enemyHp.setSize(new Dimension(30, 100));
				enemyHp.setMaximum(enemy.getStats().getHp());
				enemyHp.setMinimum(0);
				enemyHp.setValue(enemy.getStats().getHp());
				enemyHp.setString(String.valueOf(enemy.getStats().getHp()));
				enemyHp.setForeground(new Color(10, 200, 10));
				enemyHp.setStringPainted(true);
				enemyHp.setVisible(true);

				sound.play();

				sound.close();

				return null;

			}

		}.execute();

	}

	/**
	 * Update the healthbars
	 * 
	 * @param player the player pokémon
	 * @param enemy  the enemy pokémon
	 */
	public void updateBars(PlayerPokemon player, EnemyPokemon enemy) {

		playerHp.setValue(player.getStats().getHp());
		playerHp.setString(String.valueOf(player.getStats().getHp()));

		enemyHp.setValue(enemy.getStats().getHp());
		enemyHp.setString(String.valueOf(enemy.getStats().getHp()));

		playerLvl.setText(spacer + "Lv: " + player.getLevel());

	}

	/**
	 * Update the panel with the new enemy
	 * 
	 * @param enemy the enemy pokémon
	 */
	public void newEnemy(EnemyPokemon enemy) {

		// Update enemy stuff
		enemySprite.setIcon(new ImageIcon(enemy.getFrontSprite()));

		enemyHp.setMaximum(enemy.getStats().getHp());
		enemyHp.setValue(enemy.getStats().getHp());
		enemyHp.setString(String.valueOf(enemy.getStats().getHp()));

		enemyName.setText(enemy.getName() + spacer);

		enemyLvl.setText(spacer + "Lv: " + enemy.getLevel());

	}

	/**
	 * Update the player elements on level up
	 * 
	 * @param player the player pokémon
	 */
	public void lvlUp(PlayerPokemon player) {

		playerHp.setMaximum(player.getStats().getHp());
		playerHp.setValue(player.getStats().getHp());
		playerHp.setString(String.valueOf(player.getStats().getHp()));

		playerLvl.setText(spacer + "Lv: " + player.getLevel());

	}

}
