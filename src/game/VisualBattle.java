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

public final class VisualBattle extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JProgressBar hp1;
	private JProgressBar hp2;
	
	private JLabel playerName;
	private JLabel playerSprite;
	
	private JLabel enemyName;
	private JLabel enemySprite;
	
	private JLabel playerLvl;
	private JLabel enemyLvl;
	
	private final String spacer = "     "; 
	
	private final Path bg_path = Paths.get("resource","image","bg.png");
	
	public VisualBattle(PlayerPokemon player, EnemyPokemon enemy) {
		
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
		JPanel statPanelP = new JPanel();
		statPanelP.setLayout(new GridBagLayout());
		statPanelP.setOpaque(false);
		
		//Player hp bar
		hp1 = new JProgressBar();
		hp1.setVisible(false);
		
		playerLvl = new JLabel();
		playerName = new JLabel();
		playerSprite = new JLabel();
		
		//Add name and bar
		statPanelP.add(playerName);
		statPanelP.add(hp1);
		statPanelP.add(playerLvl);
		
		//Add components to player panel
		playerPanel.add(playerSprite);
		playerPanel.add(statPanelP);
		
		//Enemy panel
		JPanel enemyPanel = new JPanel();
		enemyPanel.setLayout(new GridBagLayout());
		enemyPanel.setOpaque(false);
		
		//Enemy stats
		JPanel statPanelE = new JPanel();
		statPanelE.setLayout(new GridBagLayout());
		statPanelE.setOpaque(false);
		
		enemyLvl = new JLabel();
		enemyName = new JLabel();
		enemySprite = new JLabel();
		
		//Enemy hp bar
		hp2 = new JProgressBar();
		hp2.setVisible(false);

		statPanelE.add(enemyName);
		statPanelE.add(hp2);
		statPanelE.add(enemyLvl);
		
		//Add components to enemy panel
		enemyPanel.add(statPanelE);
		enemyPanel.add(enemySprite);
		
		//Add components to main panel
		background.add(playerPanel,BorderLayout.SOUTH);
		background.add(enemyPanel,BorderLayout.NORTH);
		
		this.add(background);
	
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
				
				sound.close();
				
				return null;
				
			}
			
		}.execute();
		
	}
		
	public void updateBars(PlayerPokemon player, EnemyPokemon enemy) {
		
		hp1.setValue(player.getStats().getHp());
		hp1.setString(String.valueOf(player.getStats().getHp()));
		
		hp2.setValue(enemy.getStats().getHp());
		hp2.setString(String.valueOf(enemy.getStats().getHp()));
		
		playerLvl.setText(spacer + "Lv: " + player.getLevel());
		
	}
	
	public void newEnemy(EnemyPokemon p) {
		
		//Update enemy stuff
		enemySprite.setIcon(new ImageIcon(p.getFrontSprite()));
		
		hp2.setMaximum(p.getStats().getHp());
		hp2.setValue(p.getStats().getHp());
		hp2.setString(String.valueOf(p.getStats().getHp()));

		enemyName.setText(p.getName() + spacer);
		
		enemyLvl.setText(spacer + "Lv: " + p.getLevel());
		
	}
	
	public void lvlUp(PlayerPokemon p) {
		
		hp1.setMaximum(p.getStats().getHp());
		hp1.setValue(p.getStats().getHp());
		hp1.setString(String.valueOf(p.getStats().getHp()));
		
		playerLvl.setText(spacer + "Lv: " + p.getLevel());
		
	}
	
}
