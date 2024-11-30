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

public final class Game extends JPanel {

	private static final long serialVersionUID = 1L;

	private CardLayout cParent;
	private JPanel parent;
	
	private CommandPanel commands;
	private VisualBattle bv;
	private static JLabel txt;
	
	private final TreeSet<String> allPokemon = FileRw.allPokemon();
	private final Random r = new Random();
	
	private EnemyPokemon enemy;
	private PlayerPokemon player;
	
	private final SoundPlayer sounds = new SoundPlayer();
	
	private static final int millisDelay = 90;
	
	private int streak;
	
	public Game(CardLayout c, JPanel container, PlayerPokemon player) {
		
		super();
		
		cParent = c;
		parent = container;
		
		this.setLayout(new BorderLayout());
		
		enemy = new EnemyPokemon(getRandomPokemon());
		
		this.player = player;
		
		commands = new CommandPanel(player);
		commands.setPreferredSize(new Dimension(900,220));
		commands.setBorder(getBorder());
		commands.setActionBack(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
				
					c.first(container);
					c.removeLayoutComponent(container);
				
			  }});
		
		commands.getFirst().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(commands.getFirst().getMove().getPp() > 0) {
				
					battleStep(commands.getFirst().getMove());
			
				}
			}});
		
		commands.getSecond().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(commands.getSecond().getMove().getPp() > 0) {
					
					battleStep(commands.getSecond().getMove());

				}
			}});
		
		commands.getThird().setEnabled(false);
		commands.getThird().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(commands.getThird().getMove().getPp() > 0) {
					
					battleStep(commands.getThird().getMove());

				}
			}});
		
		commands.getFourth().setEnabled(false);
		commands.getFourth().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(commands.getFourth().getMove().getPp() > 0) {

					battleStep(commands.getFourth().getMove());
					
				}
			}});
		
		commands.disable();
		
		txt = new JLabel("",JLabel.CENTER);
		txt.setFont(new Font("Lucida Console",Font.PLAIN,20));
		txt.setPreferredSize(new Dimension(900,100));
		txt.setBorder(new LineBorder(Color.GRAY, 5));

		bv = new VisualBattle(player, enemy);
		bv.setPreferredSize(new Dimension(900,380));
		
		this.add(bv,BorderLayout.NORTH);
		this.add(txt);
		this.add(commands,BorderLayout.SOUTH);
		
		c.last(container);
		
		new SwingWorker<Void, Void>() {
			
			@Override
			protected Void doInBackground() throws Exception {
				Thread.sleep(2000);
				commands.enable();
				return null;
			}
		}.execute();
	}

	private void battleStep(Move m) {

		new SwingWorker<Void, Void>() {
			
			@Override
			protected Void doInBackground() throws Exception {
				
				commands.disable();
				
				Move used = getRandomMove(enemy);
				
				if(player.getStats().getVel() > enemy.getStats().getVel()) {
				
					textWrite(player.getName() + " usa " + m.getName());
					Thread.sleep(millisDelay);
					m.esegui(player, enemy);
					commands.updateButtons();
					Thread.sleep(millisDelay);
					bv.updateBars(player, enemy);
					Thread.sleep(millisDelay*7);
					
					if(!checkKo()) {
						
						textWrite(enemy.getName() + " avversario usa " + used.getName());
						Thread.sleep(millisDelay);
						used.esegui(enemy, player);
						Thread.sleep(millisDelay);
						bv.updateBars(player, enemy);
						Thread.sleep(millisDelay);
						checkGameOver();
						
					}
				
				}
				
				else {
					
					textWrite(enemy.getName() + " avversario usa " + used.getName());
					Thread.sleep(millisDelay);
					used.esegui(enemy,player);
					Thread.sleep(millisDelay);
					bv.updateBars(player, enemy);
					Thread.sleep(millisDelay);
					checkGameOver();
					Thread.sleep(millisDelay*7);
					
					textWrite(player.getName() + " usa " + m.getName());
					Thread.sleep(millisDelay);
					m.esegui(player, enemy);
					commands.updateButtons();
					Thread.sleep(millisDelay);
					bv.updateBars(player, enemy);
					Thread.sleep(millisDelay);
					checkKo();
					
				}
				
				Thread.sleep(millisDelay*2);
				txt.setText("");
				commands.enable();
				return null;
				
			}
			
		}.execute();
		
	}
		
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
	
	private void checkGameOver() throws InterruptedException {
		
		if(player.getStats().getHp() <= 0) {
			
			textWrite(player.getName() + " è stato sconfitto!");
			
			Thread.sleep(millisDelay*3);
			
			cParent.first(parent);
			cParent.removeLayoutComponent(this);
			new GameOver(streak,player.getName());

		}
		
	}
	
	private Move getRandomMove(EnemyPokemon p) {
		
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
	
	private void newPkmn() throws InterruptedException {
		
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
	
	private void checkNewMove() {
		
		switch(player.getMoveChange()) {
		
		case 0:
			
			commands.setFirstMove(player.getMoves()[0]);
			player.setMoveChange(-1);
			
			break;
		
		case 1:
			
			commands.setSecondMove(player.getMoves()[1]);
			player.setMoveChange(-1);
			
			break;
			
		case 2:
			
			if(!commands.getThird().isEnabled()) commands.getThird().setEnabled(true);
			commands.setThirdMove(player.getMoves()[2]);
			player.setMoveChange(-1);
			
			break;
			
		case 3:
			
			if(!commands.getFourth().isEnabled()) commands.getFourth().setEnabled(true);
			commands.setFourthMove(player.getMoves()[3]);
			player.setMoveChange(-1);
			
			break;	
			
		}
			
	}

	public static void textWrite(String s) throws InterruptedException {
		
		txt.setText("");
		
		for(int i=0;i<s.length();i++) {
			
			txt.setText(txt.getText() + s.charAt(i));
			
			Thread.sleep(millisDelay/5);
		}
				
		Thread.sleep(millisDelay);
		
	}
	

}
