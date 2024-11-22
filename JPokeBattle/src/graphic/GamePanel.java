package graphic;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.JPanel;

import game_mechanics_pokemon.EnemyPokemon;
import game_mechanics_pokemon.PlayerPokemon;
import i_o.FileReader;

public final class GamePanel extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CardLayout c_parent;
	private JPanel parent;
	
	private final TreeSet<String> allPokemon = FileReader.allPokemon();
	private final Random r = new Random();
	
	public GamePanel(CardLayout c, JPanel container) {
		
		super();
		
		c_parent = c;
		parent = container;
	
		
	}
	
	public void start(PlayerPokemon player) {
		
		this.setLayout(new BorderLayout());
		
		GameplayPanel gameplay = new GameplayPanel(player,new EnemyPokemon(getRandomPokemon()));
		gameplay.setPreferredSize(new Dimension(900,380));
		
		OptionPanel options = new OptionPanel(c_parent,parent,this);
		options.setPreferredSize(new Dimension(900,220));
		options.setBorder(getBorder());

		this.add(gameplay,BorderLayout.NORTH);
		this.add(options,BorderLayout.SOUTH);
		
		c_parent.last(parent);
		
	}
	
	private String getRandomPokemon() {
		
		int index = r.nextInt(allPokemon.size());
		
		for(String s : allPokemon) {
			
			if(index==0) return s;
			
			index--;
			
		}
		return null;
		
	}
	
}
