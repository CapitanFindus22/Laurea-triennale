package graphic;

import javax.swing.JLabel;
import javax.swing.JPanel;

import game_mechanics_pokemon.EnemyPokemon;
import game_mechanics_pokemon.PlayerPokemon;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;


public final class GameplayPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameplayPanel(PlayerPokemon player, EnemyPokemon enemy) {
		
		this.setLayout(new BorderLayout());
		
		this.add(new JLabel(new ImageIcon(player.getBackSprite())),BorderLayout.SOUTH);
		this.add(new JLabel(player.getName()),BorderLayout.SOUTH);
		
		this.add(new JLabel(new ImageIcon(enemy.getFrontSprite())),BorderLayout.NORTH);
		this.add(new JLabel(enemy.getName()),BorderLayout.NORTH);
		
		
	}
	
}
