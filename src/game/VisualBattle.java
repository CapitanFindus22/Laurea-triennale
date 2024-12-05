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

	private static final long serialVersionUID = 1L;
	
	//The two healthbars
	private JProgressBar hp1;
	private JProgressBar hp2;
	
	//Player
	private JLabel playerName;
	private JLabel playerLvl;
	private JLabel playerSprite;
	
	//Enemy
	private JLabel enemyName;
	private JLabel enemyLvl;
	private JLabel enemySprite;

	//Used to create space
	private final String spacer = "     "; 
	
	//Background image path
	private final Path bg_path = Paths.get("resource","image","bg.png");
	
	/**
	 * Create the panel
	 * 
	 * @param player The player pokemon
	 * @param enemy The Enemy pokemon
	 */
	public VisualBattle(PlayerPokemon player, EnemyPokemon enemy) {
		
		//Background
		JLabel background = new JLabel();
		background.setLayout(new BorderLayout());
		background.setIcon(new ImageIcon(bg_path.toString()));
		
		this.setBorder(new LineBorder(Color.GRAY,2));
		this.setLayout(new BorderLayout());
		
		//Player panel
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridBagLayout());
		playerPanel.setOpaque(false);
		
		//Stats panel
		JPanel playerInfo = new JPanel();
		playerInfo.setLayout(new GridBagLayout());
		playerInfo.setOpaque(false);
		
		//Player hp bar
		hp1 = new JProgressBar();
		hp1.setVisible(false);
		
		playerLvl = new JLabel();
		playerName = new JLabel();
		playerSprite = new JLabel();
		
		//Add name and bar
		playerInfo.add(playerName);
		playerInfo.add(hp1);
		playerInfo.add(playerLvl);
		
		//Add components to player panel
		playerPanel.add(playerSprite);
		playerPanel.add(playerInfo);
		
		//Enemy panel
		JPanel enemyPanel = new JPanel();
		enemyPanel.setLayout(new GridBagLayout());
		enemyPanel.setOpaque(false);
		
		//Enemy stats
		JPanel enemyInfo = new JPanel();
		enemyInfo.setLayout(new GridBagLayout());
		enemyInfo.setOpaque(false);
		
		enemyLvl = new JLabel();
		enemyName = new JLabel();
		enemySprite = new JLabel();
		
		//Enemy hp bar
		hp2 = new JProgressBar();
		hp2.setVisible(false);

		enemyInfo.add(enemyName);
		enemyInfo.add(hp2);
		enemyInfo.add(enemyLvl);
		
		//Add components to enemy panel
		enemyPanel.add(enemyInfo);
		enemyPanel.add(enemySprite);
		
		//Add components to main panel
		background.add(playerPanel,BorderLayout.SOUTH);
		background.add(enemyPanel,BorderLayout.NORTH);
		
		this.add(background);
	
		//Small animation
		new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				
				SoundPlayer sound = new SoundPlayer();
				sound.setFile(player.getName());
				
				Thread.sleep(1000);
				
				playerSprite.setIcon(new ImageIcon(player.getBackSprite()));
				playerName.setText(player.getName() + spacer);
				playerLvl.setText(spacer + "Lv: " + player.getLevel());
				
				hp1.setSize(new Dimension(30,100));
				hp1.setMaximum(player.getStats().getHp());
				hp1.setMinimum(0);
				hp1.setValue(player.getStats().getHp());
				hp1.setString(String.valueOf(player.getStats().getHp()));
				hp1.setForeground(new Color(10,200,10));
				hp1.setStringPainted(true);
				hp1.setVisible(true);
				
				sound.play();
				
				sound.setFile(enemy.getName());
				
				Thread.sleep(1000);
				
				enemySprite.setIcon(new ImageIcon(enemy.getFrontSprite()));
				enemyName.setText(enemy.getName() + spacer);
				enemyLvl.setText(spacer + "Lv: " + enemy.getLevel());
				
				hp2.setSize(new Dimension(30,100));
				hp2.setMaximum(enemy.getStats().getHp());
				hp2.setMinimum(0);
				hp2.setValue(enemy.getStats().getHp());
				hp2.setString(String.valueOf(enemy.getStats().getHp()));
				hp2.setForeground(new Color(10,200,10));
				hp2.setStringPainted(true);
				hp2.setVisible(true);
				
				sound.play();
				
				Thread.sleep(1000);
				
				sound.close();
				
				return null;
				
			}
			
		}.execute();
		
	}
		
	/**
	 * Update the healthbars
	 * 
	 * @param player The player pokemon
	 * @param enemy The enemy pokemon
	 */
	public void updateBars(PlayerPokemon player, EnemyPokemon enemy) {
		
		hp1.setValue(player.getStats().getHp());
		hp1.setString(String.valueOf(player.getStats().getHp()));
		
		hp2.setValue(enemy.getStats().getHp());
		hp2.setString(String.valueOf(enemy.getStats().getHp()));
		
		playerLvl.setText(spacer + "Lv: " + player.getLevel());
		
	}
	
	/**
	 * Update the panel with the new enemy
	 * 
	 * @param p The enemy pokemon
	 */
	public void newEnemy(EnemyPokemon p) {
		
		//Update enemy stuff
		enemySprite.setIcon(new ImageIcon(p.getFrontSprite()));
		
		hp2.setMaximum(p.getStats().getHp());
		hp2.setValue(p.getStats().getHp());
		hp2.setString(String.valueOf(p.getStats().getHp()));

		enemyName.setText(p.getName() + spacer);
		
		enemyLvl.setText(spacer + "Lv: " + p.getLevel());
		
	}
	
	/**
	 * Update the player elements on level up
	 * 
	 * @param p The player pokemon
	 */
	public void lvlUp(PlayerPokemon p) {
		
		hp1.setMaximum(p.getStats().getHp());
		hp1.setValue(p.getStats().getHp());
		hp1.setString(String.valueOf(p.getStats().getHp()));
		
		playerLvl.setText(spacer + "Lv: " + p.getLevel());
		
	}
	
}
