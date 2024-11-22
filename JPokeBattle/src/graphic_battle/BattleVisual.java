package graphic_battle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import game_mechanics_pokemon.EnemyPokemon;
import game_mechanics_pokemon.PlayerPokemon;

import java.awt.Color;

import javax.swing.ImageIcon;


public final class BattleVisual extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JProgressBar hp1;
	private JProgressBar hp2;
	
	public BattleVisual(PlayerPokemon player, EnemyPokemon enemy) {
		

		
		this.add(new JLabel(new ImageIcon(player.getBackSprite())));
		
		hp1 = new JProgressBar();
		hp1.setMaximum(player.getStats().getHp());
		hp1.setMinimum(0);
		hp1.setValue(player.getStats().getHp());
		hp1.setString(String.valueOf(player.getStats().getHp()));
		hp1.setForeground(new Color(10,200,10));
		hp1.setStringPainted(true);
		this.add(hp1);
		this.add(new JLabel(player.getName()));
		
		
		this.add(new JLabel(new ImageIcon(enemy.getFrontSprite())));
		
		hp2 = new JProgressBar();
		hp2.setMaximum(enemy.getStats().getHp());
		hp2.setMinimum(0);
		hp2.setValue(enemy.getStats().getHp());
		hp2.setString(String.valueOf(enemy.getStats().getHp()));
		hp2.setForeground(new Color(10,200,10));
		hp2.setStringPainted(true);
		this.add(hp2);
		this.add(new JLabel(enemy.getName()));
		
		
	}
	
	public void updateBars(PlayerPokemon player, EnemyPokemon enemy) {
		
		hp1.setValue(player.getStats().getHp());
		hp2.setValue(enemy.getStats().getHp());
		
	}
	
}
