package graphic_battle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;

import game_mechanics_pokemon.EnemyPokemon;
import game_mechanics_pokemon.PlayerPokemon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;

public final class BattleVisual extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JProgressBar hp1;
	private JProgressBar hp2;
	
	private JLabel enemyName;
	private JLabel enemySprite;
	
	private JLabel playerLvl;
	private JLabel enemyLvl;
	
	private final String spacer = "     "; 
	
	public BattleVisual(PlayerPokemon player, EnemyPokemon enemy) {
		
		this.setBorder(new LineBorder(Color.GRAY,2));
		
		this.setLayout(new BorderLayout());
		
		//Player panel
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridBagLayout());
		
		//Stats panel
		JPanel statPanelP = new JPanel();
		statPanelP.setLayout(new GridBagLayout());
		
		//Player hp bar
		hp1 = new JProgressBar();
		hp1.setSize(new Dimension(30,100));
		hp1.setMaximum(player.getStats().getHp());
		hp1.setMinimum(0);
		hp1.setValue(player.getStats().getHp());
		hp1.setString(String.valueOf(player.getStats().getHp()));
		hp1.setForeground(new Color(10,200,10));
		hp1.setStringPainted(true);
		
		playerLvl = new JLabel(spacer + "Lv: " + player.getLevel());
		
		//Add name and bar
		statPanelP.add(new JLabel(player.getName()+spacer));
		statPanelP.add(hp1);
		statPanelP.add(playerLvl);
		
		//Add components to player panel
		playerPanel.add(new JLabel(new ImageIcon(player.getBackSprite())));
		playerPanel.add(statPanelP);
		
		//Enemy panel
		JPanel enemyPanel = new JPanel();
		enemyPanel.setLayout(new GridBagLayout());
		
		//Enemy stats
		JPanel statPanelE = new JPanel();
		statPanelE.setLayout(new GridBagLayout());
		
		//Enemy hp bar
		hp2 = new JProgressBar();
		hp2.setMaximum(enemy.getStats().getHp());
		hp2.setMinimum(0);
		hp2.setValue(enemy.getStats().getHp());
		hp2.setString(String.valueOf(enemy.getStats().getHp()));
		hp2.setForeground(new Color(10,200,10));
		hp2.setStringPainted(true);
		
		enemyLvl = new JLabel(spacer + "Lv: " + enemy.getLevel());
		
		//Add name and bar
		enemyName = new JLabel(enemy.getName() + spacer);
		
		statPanelE.add(enemyName);
		statPanelE.add(hp2);
		statPanelE.add(enemyLvl);
		
		//Add components to enemy panel
		enemyPanel.add(statPanelE);
		enemySprite = new JLabel(new ImageIcon(enemy.getFrontSprite()));
		enemyPanel.add(enemySprite);
		
		//Add components to main panel
		this.add(playerPanel,BorderLayout.SOUTH);
		this.add(enemyPanel,BorderLayout.NORTH);
		
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
	
}
