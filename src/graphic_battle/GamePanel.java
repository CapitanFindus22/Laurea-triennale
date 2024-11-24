package graphic_battle;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game_mechanics_moves.Move;
import game_mechanics_pokemon.EnemyPokemon;
import game_mechanics_pokemon.PlayerPokemon;
import i_o.FileRw;

public final class GamePanel extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CardLayout cParent;
	private JPanel parent;
	
	private CommandPanel commands;
	private BattleVisual bv;
	
	private final TreeSet<String> allPokemon = FileRw.allPokemon();
	private final Random r = new Random();
	
	private EnemyPokemon enemy;
	private PlayerPokemon player;
	
	private JDialog text;
	
	private boolean newPk = false;
	
	private int streak;
	
	private JLabel topTxt;
	private JLabel bottomTxt;
	
	public GamePanel(CardLayout c, JPanel container) {
		
		super();
		cParent = c;
		parent = container;
		
		topTxt = new JLabel("Inizio", JLabel.CENTER);
		topTxt.setBorder(BorderFactory.createTitledBorder("Prima mossa"));
		topTxt.setPreferredSize(new Dimension(650,200));
		topTxt.setFont(new Font("Lucida Console",Font.PLAIN,25));
		
		bottomTxt = new JLabel("Partita", JLabel.CENTER);
		bottomTxt.setBorder(BorderFactory.createTitledBorder("Seconda mossa"));
		bottomTxt.setPreferredSize(new Dimension(650,200));
		bottomTxt.setFont(new Font("Lucida Console",Font.PLAIN,25));
		
		text = new JDialog();
		text.setLayout(new BorderLayout());
		text.add(topTxt,BorderLayout.NORTH);
		text.add(bottomTxt,BorderLayout.SOUTH);
		text.setSize(650, 400);
		text.setTitle("Descrizione");
		text.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		text.setResizable(false);
		text.setVisible(true);
		
	}
	
	public void start(PlayerPokemon player) {
		
		this.setLayout(new BorderLayout());
		
		enemy = new EnemyPokemon(getRandomPokemon());
		this.player = player;
		
		bv = new BattleVisual(player, enemy);
		bv.setPreferredSize(new Dimension(900,380));
		
		commands = new CommandPanel(cParent,parent,text,this,player);
		commands.setPreferredSize(new Dimension(900,220));
		commands.setBorder(getBorder());
		
		commands.getFirst().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(commands.getFirst().getMove().getPp() > 0) {
				
					battleStep(player,enemy,commands.getFirst().getMove());
					
					if(newPk) newPkmn();
			
				}
			}});
		
		commands.getSecond().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(commands.getSecond().getMove().getPp() > 0) {
					
					battleStep(player,enemy,commands.getSecond().getMove());
					
					if(newPk) newPkmn();
				}
			}});
		
		this.add(bv,BorderLayout.NORTH);
		this.add(commands,BorderLayout.SOUTH);
		
		cParent.last(parent);
		
	}

	
	private void battleStep(PlayerPokemon player, EnemyPokemon enemy, Move m) {

		    
		Move used = randomMove(enemy);
		
		if(player.getStats().getVel() > enemy.getStats().getVel()) {
			
			topTxt.setText(player.getName() + " usa " + m.getName());
			m.esegui(player, enemy);
			checkKo(enemy.getName());
			
			if(!newPk) {
				bottomTxt.setText(enemy.getName() + " avversario usa " + m.getName());
				used.esegui(enemy, player);
				checkGameOver();
			}
		}
		
		else {
			
			
			topTxt.setText(enemy.getName() + " avversario usa " + used.getName());
			used.esegui(enemy, player);
			checkGameOver();
			
			bottomTxt.setText(player.getName() + " usa " + m.getName());
			m.esegui(player, enemy);
			checkKo(enemy.getName());
			
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
	
	private void checkKo(String name) {
		
		if(enemy.getStats().getHp() <= 0) {
			
			bottomTxt.setText(name + " avversario è stato sconfitto!");
			streak++;
			player.addXp(calculateXp());
			newPk = true;
			
		}
		
		
	}
	
	private int calculateXp() {
		
		double result = 1.5;
		
		result *= enemy.getLevel();
		
		result *= player.getBaseXp();
		
		result /= 7;
		
		return (int) result;
	}
	
	private void checkGameOver() {
		
		if(player.getStats().getHp() <= 0) {

			topTxt.setText("Sei stato sconfitto!");
			
			JLabel go = new JLabel("Round vinti: " + streak,JLabel.CENTER);
			go.setFont(new Font("Lucida Console",Font.BOLD,40));
			
			JDialog gameOver = new JDialog();
			gameOver.add(go);
			gameOver.setSize(900, 600);
			gameOver.setTitle("Game Over");
			gameOver.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			gameOver.setResizable(false);
			gameOver.setModal(true);
			gameOver.setLocationRelativeTo(null);
			gameOver.setVisible(true);
			//record
	
			text.dispose();
			cParent.first(parent);
			cParent.removeLayoutComponent(this);
			
		}
		
		
	}
	
	private void newPkmn() {
		
		enemy = new EnemyPokemon(getRandomPokemon(),player.getLevel());
		bv.newEnemy(enemy);
		bv.updateBars(player, enemy);
		newPk = false;
		
	}
	
}
