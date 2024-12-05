package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

import audio.SoundPlayer;

import i_o.FileRw;

import move.Move;

import pokemon.EnemyPokemon;
import pokemon.PlayerPokemon;
import pokemon.Pokemon;

/**
 * A custom panel containing the game itself
 */
public final class Game extends JPanel {

	private static final long serialVersionUID = 1L;

	//Used to switch with the main menu
	private CardLayout cParent;
	
	//The panel with the options for the player
	private CommandPanel commands;
	
	//The visual part of the game
	private VisualBattle bv;
	
	//Used to display what happens
	private static JLabel txt;
	
	//A set of all the pokemon available
	private final static TreeSet<String> allPokemon = FileRw.allPokemon();
	
	//A random generator
	private final Random r = new Random();
	
	//The enemy pokemon
	private EnemyPokemon enemy;
	
	//The player pokemon
	private PlayerPokemon player;
	
	//The sound player
	private final SoundPlayer sounds = new SoundPlayer();
	
	//Delay for the "animation"
	private final int millisDelay = 90;
	
	//The number of rounds won
	private int streak;
	
	/**
	 * Create and start the game
	 * 
	 * @param c The card layout used for switching
	 * @param container The parent of this panel
	 * @param player The pokemon chose by the player
	 */
	public Game(CardLayout c, JPanel container, PlayerPokemon player) {
		
		super();
		
		cParent = c;
		
		this.setLayout(new BorderLayout());
		
		//Get a random enemy
		enemy = new EnemyPokemon(getRandomPokemon());
		
		this.player = player;
		
		//Bottom part of the gui
		commands = new CommandPanel(player);
		commands.setPreferredSize(new Dimension(900,220));
		commands.setBorder(getBorder());
		
		//The back button return to the main menu on click
		commands.setBackButtonAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
				
				close();
				
			  }});
		
		//Each button in commands is related to one of the player pokemon moves
		
		//First
		commands.getFirst().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(commands.getFirst().getMove().getCurrentPp() > 0) {
				
					battleStep(commands.getFirst().getMove());
			
				}
			}});
		
		//Second
		commands.getSecond().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(commands.getSecond().getMove().getCurrentPp() > 0) {
					
					battleStep(commands.getSecond().getMove());

				}
			}});
		
		//At level 1 there are only two moves, third and fourth buttons aren't enabled
		
		//Third
		commands.getThird().setEnabled(false);
		commands.getThird().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(commands.getThird().getMove().getCurrentPp() > 0) {
					
					battleStep(commands.getThird().getMove());

				}
			}});
		
		//Fourth
		commands.getFourth().setEnabled(false);
		commands.getFourth().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(commands.getFourth().getMove().getCurrentPp() > 0) {

					battleStep(commands.getFourth().getMove());
					
				}
			}});
		
		//Disable all the buttons for the animation
		commands.disableMoves();
		commands.disableBack();
		
		//Middle part of the gui
		txt = new JLabel("",JLabel.CENTER);
		txt.setFont(new Font("Lucida Console",Font.PLAIN,20));
		txt.setPreferredSize(new Dimension(900,100));
		txt.setBorder(new LineBorder(Color.GRAY,5));

		//Top part of the gui
		bv = new VisualBattle(player, enemy);
		bv.setPreferredSize(new Dimension(900,380));
		
		//Add the components
		this.add(bv,BorderLayout.NORTH);
		this.add(txt);
		this.add(commands,BorderLayout.SOUTH);
		
		//Display this card
		c.last(container);
		
		//Wait for the animation to finish
		new SwingWorker<Void, Void>() {
			
			@Override
			protected Void doInBackground() throws Exception {
				
				textWrite("Hai scelto " + player.getName());
				Thread.sleep(1200);
				
				textWrite("Appare " + enemy.getName());
				Thread.sleep(1600);
				
				//Enable the buttons
				commands.enableMoves();
				commands.enableBack();
				return null;
			}
		}.execute();
	}

	/**
	 * Execute a step of the battle 
	 * 
	 * @param m The move chose by the player
	 */
	private void battleStep(Move m) {
		
		new SwingWorker<> () {
			
			@Override
			protected Void doInBackground() throws Exception {
				
				//Disable the move buttons
				commands.disableMoves();
				
				//Get a random enemy move
				Move used = getRandomMove(enemy);
				
				//Check who goes first
				if(player.getStats().getVel() > enemy.getStats().getVel()) {
				
					textWrite(player.getName() + " usa " + m.getName());
					Thread.sleep(millisDelay);
					
					//Check if the move hit
					if(Pokemon.doesHit(player,m,enemy)) {
					
						m.esegui(player, enemy);
						commands.updateButtons();
						Thread.sleep(millisDelay);
						bv.updateBars(player, enemy);
						Thread.sleep(millisDelay*7);
					
					}
					
					else {
						
						textWrite(enemy.getName() + " avversario riesce a schivare ");
						Thread.sleep(millisDelay);
						
					}
					
					if(!checkKo()) {
						
						textWrite(enemy.getName() + " avversario usa " + used.getName());
						Thread.sleep(millisDelay);
						
						//Check if the move hit
						if(Pokemon.doesHit(player,enemy,m)) {
						
							used.esegui(enemy, player);
							Thread.sleep(millisDelay);
							bv.updateBars(player, enemy);
							Thread.sleep(millisDelay);
							checkGameOver();
						
						}
						
						else {
							
							textWrite(player.getName() + " riesce a schivare.");
							Thread.sleep(millisDelay);
						
						}
				
					}
				
				}
				
				else {
					
					textWrite(enemy.getName() + " avversario usa " + used.getName());
					Thread.sleep(millisDelay);
					
					//Check if the move hit
					if(Pokemon.doesHit(player,enemy,m)) {
					
						used.esegui(enemy,player);
						Thread.sleep(millisDelay);
						bv.updateBars(player, enemy);
						Thread.sleep(millisDelay);
						checkGameOver();
						Thread.sleep(millisDelay*7);
					
					}
					
					else {
						
						textWrite(player.getName() + " riesce a schivare.");
						Thread.sleep(millisDelay);
					
					}
					
					textWrite(player.getName() + " usa " + m.getName());
					Thread.sleep(millisDelay);

					//Check if the move hit
					if(Pokemon.doesHit(player,m,enemy)) {
					
						m.esegui(player, enemy);
						commands.updateButtons();
						Thread.sleep(millisDelay);
						bv.updateBars(player, enemy);
						Thread.sleep(millisDelay);
						checkKo();
					
					}
					
					else {
						
						textWrite(enemy.getName() + " avversario riesce a schivare ");
						Thread.sleep(millisDelay);
						
					}
					
				}
				
				Thread.sleep(millisDelay*2);
				txt.setText("");
				commands.enableMoves();
				System.gc();
				return null;
				
			}
			
		}.execute();
		
	}
		
	/**
	 * Check if the enemy pokemon has 0 HP
	 * 
	 * @return True if the enemy pokemon fainted
	 * @throws InterruptedException
	 */
	private boolean checkKo() throws InterruptedException {
		
		if(enemy.getStats().getHp() <= 0) {
			
			textWrite(enemy.getName() + " avversario è stato sconfitto!");
			
			Thread.sleep(millisDelay*3);
			
			streak++;
			
			if(player.addXp(Pokemon.calculateXp(player,enemy))) bv.lvlUp(player);
			
			checkNewMove();
			
			newPkmn();
			
			return true;
			
		}
		
		return false;
		
	}	
	
	/**
	 * Check if the player lost
	 * 
	 * @throws InterruptedException
	 */
	private void checkGameOver() throws InterruptedException {
		
		if(player.getStats().getHp() <= 0) {
			
			textWrite(player.getName() + " è stato sconfitto!");
			
			Thread.sleep(millisDelay*3);
			
			close();
			
			new GameOver(streak,player.getName());

		}
		
	}
	
	/**
	 * Choose a random move from those available to the enemy pokemon
	 * 
	 * @param p The enmy pokemon
	 * @return A random move of the enemy pokemon
	 */
	private Move getRandomMove(EnemyPokemon p) {
		
		int index = p.getMoveCount();
		
		return p.getMoves()[r.nextInt(index)];
		
	}
	
	/**
	 * Get a random enemy pokemon from those available
	 * 
	 * @return A random pokemon name from the allPokemon set
	 */
	private String getRandomPokemon() {
		
		int index = r.nextInt(allPokemon.size());
		
		for(String s : allPokemon) {
			
			if(index==0) return s;
			
			index--;
			
		}
		
		return null;
		
	}
	
	/**
	 * Create a new enemy pokemon
	 * 
	 * @throws InterruptedException
	 */
	private void newPkmn() throws InterruptedException {
		
		enemy = null;
		
		enemy = new EnemyPokemon(getRandomPokemon(),r.nextInt(1,player.getLevel()));
		
		sounds.setFile(enemy.getName());
		
		Thread.sleep(millisDelay*3);
		
		textWrite("Appare " + enemy.getName());
		
		Thread.sleep(millisDelay*3);
		
		bv.newEnemy(enemy);
		bv.updateBars(player, enemy);
		
		Thread.sleep(millisDelay);
		
		sounds.play();
		
		for(Move m : player.getMoves()) if(m != null) m.resetTimeUsed();
		
		commands.updateButtons();
		
		Thread.sleep(millisDelay);
		
	}
	
	/**
	 * Check if the player pokemon has a new move and update the associated button
	 */
	private void checkNewMove() {
		
		switch(player.getlastMoveChanged()) {
		
		case 1:
			
			commands.setFirstMove(player.getMoves()[0]);
			player.resetlastMoveChanged();
			
			break;
		
		case 2:
			
			commands.setSecondMove(player.getMoves()[1]);
			player.resetlastMoveChanged();
			
			break;
			
		case 3:
			
			if(!commands.getThird().isEnabled()) commands.getThird().setEnabled(true);
			commands.setThirdMove(player.getMoves()[2]);
			player.resetlastMoveChanged();
			
			break;
			
		case 4:
			
			if(!commands.getFourth().isEnabled()) commands.getFourth().setEnabled(true);
			commands.setFourthMove(player.getMoves()[3]);
			player.resetlastMoveChanged();
			
			break;	
			
		}
			
	}

	/**
	 * 
	 * Write the text passed slowly on the txt label
	 * 
	 * @param s The string to write
	 * @throws InterruptedException
	 */
	private void textWrite(String s) throws InterruptedException {
		
		txt.setText("");
		
		for(int i=0;i<s.length();i++) {
			
			txt.setText(txt.getText() + s.charAt(i));
			
			Thread.sleep(millisDelay/5);
		}
				
		Thread.sleep(millisDelay);
		
	}
	
	/**
	 * Close everything and return to the main menu
	 */
	private void close() {
		
		cParent.first(this.getParent());
		this.removeAll();
		this.getParent().remove(this);
		if(sounds.isSet()) sounds.close();
		
		System.gc();
		
	}
	
}
