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

/**
 * A custom panel containing the game itself
 */
public final class Game extends JPanel {

	/**
	 * Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The Card Layout of the parent, to switch back to the menu
	 */
	private CardLayout cParent;

	/**
	 * The panel containing the buttons
	 */
	private CommandPanel commands;

	/**
	 * The visual part of the game
	 */
	private VisualBattle bv;

	/**
	 * Used to describe what is happening
	 */
	private static JLabel txt;

	/**
	 * All the available pokémon
	 */
	private final TreeSet<String> ALL_POKEMON = FileRw.allPokemon();

	/**
	 * A random generator
	 */
	private final Random RNG = new Random();

	/**
	 * The enemy pokémon
	 */
	private EnemyPokemon enemy;

	/**
	 * The player pokémon
	 */
	private PlayerPokemon player;

	/**
	 * The sound player
	 */
	private final SoundPlayer SOUNDS = new SoundPlayer();

	/**
	 * Delay used to "animate"
	 */
	private final int MILLIS_DELAY = 100;

	/**
	 * The number of rounds won
	 */
	private int streak;

	/**
	 * Create and start the game
	 * 
	 * @param cLayout   the card layout used for switching
	 * @param container the parent of this panel
	 * @param player    the pokémon chose by the player
	 */
	public Game(CardLayout cLayout, JPanel container, PlayerPokemon player) {

		super();

		cParent = cLayout;

		this.setLayout(new BorderLayout());

		// Get a random enemy
		enemy = new EnemyPokemon(getRandomPokemon());

		this.player = player;

		// Bottom part of the gui
		commands = new CommandPanel(player);
		commands.setPreferredSize(new Dimension(900, 220));
		commands.setBorder(getBorder());

		// The back button return to the main menu on click
		commands.setBackButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				close();

			}
		});

		// Each button in commands is related to one of the player pokémon moves

		// First
		commands.getFirstMove().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (commands.getFirstMove().getMove().getCurrentPp() > 0) {

					battleStep(commands.getFirstMove().getMove());

				}
			}
		});

		// Second
		commands.getSecondMove().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (commands.getSecondMove().getMove().getCurrentPp() > 0) {

					battleStep(commands.getSecondMove().getMove());

				}
			}
		});

		// At level 1 there are only two moves, third and fourth buttons aren't enabled

		// Third
		commands.getThirdMove().setEnabled(false);
		commands.getThirdMove().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (commands.getThirdMove().getMove().getCurrentPp() > 0) {

					battleStep(commands.getThirdMove().getMove());

				}
			}
		});

		// Fourth
		commands.getFourthMove().setEnabled(false);
		commands.getFourthMove().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (commands.getFourthMove().getMove().getCurrentPp() > 0) {

					battleStep(commands.getFourthMove().getMove());

				}
			}
		});

		// Disable all the buttons for the animation
		commands.disableMoves();
		commands.disableBack();

		// Middle part of the gui
		txt = new JLabel("", JLabel.CENTER);
		txt.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		txt.setPreferredSize(new Dimension(900, 100));
		txt.setBorder(new LineBorder(Color.GRAY, 5));

		// Top part of the gui
		bv = new VisualBattle(player, enemy);
		bv.setPreferredSize(new Dimension(900, 380));

		// Add the components
		this.add(bv, BorderLayout.NORTH);
		this.add(txt);
		this.add(commands, BorderLayout.SOUTH);

		// Display this card
		cLayout.last(container);

		// Wait for the animation to finish
		new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {

				textWrite("Hai scelto " + player.getName());
				Thread.sleep(1100);

				textWrite("Appare " + enemy.getName());
				Thread.sleep(1100);

				// Enable the buttons
				commands.enableMoves();
				commands.enableBack();
				return null;
			}
			
		}.execute();
	}

	/**
	 * Execute a step of the battle
	 * 
	 * @param move The move chose by the player
	 */
	private void battleStep(Move move) {

		new SwingWorker<>() {

			@Override
			protected Void doInBackground() throws Exception {

				// Disable the move buttons
				commands.disableMoves();

				// Get a random enemy move
				Move used = getRandomMove();

				// Check who goes first
				if (player.getStats().getSpeed() > enemy.getStats().getSpeed()) {

					textWrite(player.getName() + " usa " + move.getName());
					Thread.sleep(MILLIS_DELAY);

					// Check if the move hit
					if (player.doesHit(move, enemy)||(move.alwaysHit())) {

						move.esegui(player, enemy);
						commands.updateButtons();
						Thread.sleep(MILLIS_DELAY);
						bv.updateBars(player, enemy);
						Thread.sleep(MILLIS_DELAY);

					}

					else {

						textWrite(enemy.getName() + " avversario riesce a schivare ");
						move.decreasePP();
						Thread.sleep(MILLIS_DELAY);

					}

					if (!checkKo()) {

						textWrite(enemy.getName() + " avversario usa " + used.getName());
						Thread.sleep(MILLIS_DELAY);

						// Check if the move hit
						if (enemy.doesHit( used, player)||(used.alwaysHit())) {

							used.esegui(enemy, player);
							Thread.sleep(MILLIS_DELAY);
							bv.updateBars(player, enemy);
							Thread.sleep(MILLIS_DELAY);
							checkGameOver();

						}

						else {

							textWrite(player.getName() + " riesce a schivare.");
							Thread.sleep(MILLIS_DELAY);

						}

					}

				}

				else {

					textWrite(enemy.getName() + " avversario usa " + used.getName());
					Thread.sleep(MILLIS_DELAY);

					// Check if the move hit
					if (enemy.doesHit(used, player)||(used.alwaysHit())) {

						used.esegui(enemy, player);
						Thread.sleep(MILLIS_DELAY);
						bv.updateBars(player, enemy);
						Thread.sleep(MILLIS_DELAY);
						checkGameOver();
						Thread.sleep(MILLIS_DELAY);

					}

					else {

						textWrite(player.getName() + " riesce a schivare.");
						Thread.sleep(MILLIS_DELAY);

					}

					textWrite(player.getName() + " usa " + move.getName());
					Thread.sleep(MILLIS_DELAY);

					// Check if the move hit
					if (player.doesHit(move, enemy)||(move.alwaysHit())) {

						move.esegui(player, enemy);
						commands.updateButtons();
						Thread.sleep(MILLIS_DELAY);
						bv.updateBars(player, enemy);
						Thread.sleep(MILLIS_DELAY);
						checkKo();

					}

					else {

						textWrite(enemy.getName() + " avversario riesce a schivare ");
						move.decreasePP();
						Thread.sleep(MILLIS_DELAY);

					}

				}

				txt.setText("");
				commands.enableMoves();
				System.gc();
				return null;

			}

		}.execute();

	}

	/**
	 * Check if the enemy pokémon has 0 HP
	 * 
	 * @return true if the enemy pokémon fainted
	 * @throws InterruptedException if Thread.sleep fails
	 */
	private boolean checkKo() throws InterruptedException {

		if (enemy.getStats().getHp() <= 0) {

			textWrite(enemy.getName() + " avversario è stato sconfitto!");

			Thread.sleep(MILLIS_DELAY*2);

			streak++;

			if (player.addXp(player.calculateXp(enemy))) {
				
				textWrite(player.getName() + " sale di livello");

				Thread.sleep(MILLIS_DELAY*2);
				
				bv.lvlUp(player);
				checkNewMove();
				
			}

			newPkmn();

			return true;

		}

		return false;

	}

	/**
	 * Check if the player lost
	 * 
	 * @throws InterruptedException if Thread.sleep fails
	 */
	private void checkGameOver() throws InterruptedException {

		if (player.getStats().getHp() <= 0) {

			textWrite(player.getName() + " è stato sconfitto!");

			Thread.sleep(MILLIS_DELAY * 2);

			close();

			new GameOver(streak, player.getName());

		}

	}

	/**
	 * Choose a random move from those available to the enemy pokémon
	 * 
	 * @return a random move of the enemy pokémon
	 */
	private Move getRandomMove() {

		int index = enemy.getMoveCount();

		return enemy.getMoves()[RNG.nextInt(index)];

	}

	/**
	 * Get a random enemy pokémon from those available
	 * 
	 * @return a random pokémon name from the allPokemon set
	 */
	private String getRandomPokemon() {

		int index = RNG.nextInt(ALL_POKEMON.size());

		for (String s : ALL_POKEMON) {

			if (index == 0)
				return s;

			index--;

		}

		return null;

	}

	/**
	 * Create a new enemy pokémon
	 * 
	 * @throws InterruptedException if Thread.sleep fails
	 */
	private void newPkmn() throws InterruptedException {

		Thread.sleep(MILLIS_DELAY*2);
		
		enemy = null;

		enemy = new EnemyPokemon(getRandomPokemon(), RNG.nextInt(Math.max(1, player.getLevel()-5), player.getLevel()+1));

		SOUNDS.setFile(enemy.getName());

		textWrite("Appare " + enemy.getName());

		bv.newEnemy(enemy);
		bv.updateBars(player, enemy);

		SOUNDS.play();

		for (Move m : player.getMoves())
			if (m != null)
				m.resetTimeUsed();

		commands.updateButtons();

	}

	/**
	 * Check if the player pokémon has a new move and update the associated button
	 */
	private void checkNewMove() {

		switch (player.getlastMoveChanged()) {

		case 1:

			commands.setFirstMove(player.getMoves()[0]);
			player.resetlastMoveChanged();

			break;

		case 2:

			commands.setSecondMove(player.getMoves()[1]);
			player.resetlastMoveChanged();

			break;

		case 3:

			if (!commands.getThirdMove().isEnabled())
				commands.getThirdMove().setEnabled(true);
			commands.setThirdMove(player.getMoves()[2]);
			player.resetlastMoveChanged();

			break;

		case 4:

			if (!commands.getFourthMove().isEnabled())
				commands.getFourthMove().setEnabled(true);
			commands.setFourthMove(player.getMoves()[3]);
			player.resetlastMoveChanged();

			break;

		}

	}

	/**
	 * 
	 * Write the text passed slowly on the txt label
	 * 
	 * @param toWrite the string to write
	 * @throws InterruptedException if Thread.sleep fails
	 */
	private void textWrite(String toWrite) throws InterruptedException {

		txt.setText("");

		for (int i = 0; i < toWrite.length(); i++) {

			txt.setText(txt.getText() + toWrite.charAt(i));

			Thread.sleep(MILLIS_DELAY / 5);
		}

		Thread.sleep(MILLIS_DELAY);
	}

	/**
	 * Close everything and return to the main menu
	 */
	private void close() {

		if (SOUNDS.isSet())
			SOUNDS.close();
		
		cParent.first(this.getParent());
		
		this.getParent().remove(this);
		
	}

}
