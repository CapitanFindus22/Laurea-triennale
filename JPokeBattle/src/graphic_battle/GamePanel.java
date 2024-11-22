package graphic_battle;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.JPanel;

import game_mechanics_moves.Move;
import game_mechanics_moves_damage.DoNormalDamage;
import game_mechanics_moves_damage.DoSpecialDamage;
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
	
	private CommandPanel commands;
	private BattleVisual bv;
	
	private final TreeSet<String> allPokemon = FileReader.allPokemon();
	private final Random r = new Random();
	
	public GamePanel(CardLayout c, JPanel container) {
		
		super();
		c_parent = c;
		parent = container;
	
		
	}
	
	public void start(PlayerPokemon player) {
		
		player.getMoves()[0].setFunction(new DoNormalDamage());
		player.getMoves()[1].setFunction(new DoSpecialDamage());
		
		this.setLayout(new BorderLayout());
		
		EnemyPokemon enemy = new EnemyPokemon(getRandomPokemon());
		
		enemy.getMoves()[0].setFunction(new DoNormalDamage());
		enemy.getMoves()[1].setFunction(new DoNormalDamage());
		
		bv = new BattleVisual(player, enemy);
		bv.setPreferredSize(new Dimension(900,380));
		
		commands = new CommandPanel(c_parent,parent,this,player);
		commands.setPreferredSize(new Dimension(900,220));
		commands.setBorder(getBorder());
		
		commands.getFirst().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {battleStep(player,enemy,commands.getFirst().getMove());
			}});
		
		commands.getSecond().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {battleStep(player,enemy,commands.getSecond().getMove());
			}});
		
		this.add(bv,BorderLayout.NORTH);
		this.add(commands,BorderLayout.SOUTH);
		
		c_parent.last(parent);
		
	}
	
	private void battleStep(PlayerPokemon player, EnemyPokemon enemy, Move m) {
		
		if(player.getStats().getVel() > enemy.getStats().getVel()) {
			
			m.esegui(player, enemy);
			bv.updateBars(player, enemy);
			randomMove(enemy).esegui(enemy, player);
		}
		
		else {
			
			randomMove(enemy).esegui(enemy, player);
			bv.updateBars(player, enemy);
			m.esegui(player, enemy);
			
		}
		
		commands.updateButtons();
		bv.updateBars(player, enemy);
		
	}
	
	private Move randomMove(EnemyPokemon p) {
		
		int index = p.getMoveCount();
		
		return p.getMoves()[r.nextInt(index)];
		
		
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
