package pokemon;

import java.util.Arrays;
import java.util.Random;

import i_o.FileRw;
import move.Move;

/**
 * A pokemon
 */
public abstract class Pokemon {

	/**
	 * Name
	 */
	private String name;

	/**
	 * First type
	 */
	private Type type;

	/**
	 * Second type (optional)
	 */
	private Type type2;

	/**
	 * Moves
	 */
	protected Move[] moves;

	/**
	 * Base stats
	 */
	private final Stats BASE_STATS;

	/**
	 * Current stats
	 */
	private Stats stats;

	/**
	 * Accuracy
	 */
	private int acc;

	/**
	 * Evasion
	 */
	private int eva;

	/**
	 * Base xp for defeating an enemy
	 */
	private final int BASE_XP;

	/**
	 * Current Xp
	 */
	private int xp;

	/**
	 * Current level
	 */
	private int level;

	/**
	 * Random generator used to simulate the IVs and to check if a move hit
	 */
	private static final Random RNG = new Random();

	/**
	 * Create a level 1 pokemon
	 * 
	 * @param name the name of the pokemon
	 */
	public Pokemon(String name) {

		// Find the pokemon in the file
		String[] s = FileRw.findPokemon(name).split(",");

		// s should have: {name,type1,type2,move1,move2,baseStats,baseXp}

		this.name = s[0];

		type = Type.valueOf(s[1]);

		if (!s[2].equals(""))
			type2 = Type.valueOf(s[2]);

		moves = new Move[4];

		moves[0] = new Move(s[3]);
		moves[1] = new Move(s[4]);

		stats = new Stats(Arrays.copyOfRange(s, 5, s.length - 1));
		BASE_STATS = new Stats(Arrays.copyOfRange(s, 5, s.length - 1));

		BASE_XP = Integer.parseInt(s[s.length - 1]);

		level = 1;
		xp = 0;

		acc = 100;
		eva = 100;

		level--;

		lvlUp();
	}

	/**
	 * Create a pokemon of the specified level
	 * 
	 * @param name the name of the pokemon
	 * @param lvl  the level of the pokemon
	 */
	public Pokemon(String name, int lvl) {

		// Find the pokemon in the file
		String[] s = FileRw.findPokemon(name).split(",");

		// s should have: {name,type1,type2,move1,move2,baseStats,baseXp}

		this.name = s[0];

		type = Type.valueOf(s[1]);

		if (!s[2].equals(""))
			type2 = Type.valueOf(s[2]);

		moves = new Move[4];

		moves[0] = new Move(s[3]);
		moves[1] = new Move(s[4]);

		stats = new Stats(Arrays.copyOfRange(s, 5, s.length - 1));
		BASE_STATS = new Stats(Arrays.copyOfRange(s, 5, s.length - 1));

		BASE_XP = Integer.parseInt(s[s.length - 1]);

		level = lvl - 1;
		xp = 0;

		acc = 100;
		eva = 100;

		lvlUp();

	}

	/**
	 * {@return the number of moves known by the pokemon}
	 */
	public int getMoveCount() {

		int count = 2;

		if (moves[2] != null)
			count++;
		if (moves[3] != null)
			count++;

		return count;

	}

	/**
	 * Add Xp to a pokemon
	 * 
	 * @param xp the Xp to add
	 * @return true If the pokemon leveled up
	 */
	public boolean addXp(int xp) {

		if (level >= 100) {
			return false;
		}

		this.xp += xp;
		int lv3 = (int) Math.pow(level + 1, 3);

		if (this.xp >= lv3) {

			this.xp -= lv3;
			lvlUp();
			return true;

		}

		return false;

	}

	/**
	 * Calculate the new stats according to the new level
	 */
	protected void lvlUp() {

		level++;

		double temp = (((2 * BASE_STATS.getHp() + RNG.nextInt(16)) * level) / 100) + level + 10;
		stats.setHp((int) temp);

		temp = (((2 * BASE_STATS.getAtt() + RNG.nextInt(16)) * level) / 100) + 5;
		stats.setAtt((int) temp);

		temp = (((2 * BASE_STATS.getDif() + RNG.nextInt(16)) * level) / 100) + 5;
		stats.setDif((int) temp);

		temp = (((2 * BASE_STATS.getSpAtt() + RNG.nextInt(16)) * level) / 100) + 5;
		stats.setSpAtt((int) temp);

		temp = (((2 * BASE_STATS.getSpDif() + RNG.nextInt(16)) * level) / 100) + 5;
		stats.setSpDif((int) temp);

		temp = (((2 * BASE_STATS.getSpeed() + RNG.nextInt(16)) * level) / 100) + 5;
		stats.setSpeed((int) temp);

		for (Move m : moves)
			if (m != null)
				m.setPP(m.getMaxpp());

	}

	/**
	 * Calculate the experience gained by defeating the enemy pokemon
	 * 
	 * @param p the player pokemon
	 * @param e the enemy pokemon
	 * @return the amount of xp
	 */
	public static int calculateXp(PlayerPokemon p, EnemyPokemon e) {

		double result = 3;

		result *= e.getLevel();

		result *= p.getBaseXp();

		result /= 7;

		return (int) result;
	}

	/**
	 * Check if a player move hit
	 * 
	 * @param player the player Pokemon
	 * @param move   the move used
	 * @param enemy  the enemy pokemon
	 * @return true if the player move hit the enemy
	 */
	public static boolean doesHit(PlayerPokemon player, Move move, EnemyPokemon enemy) {

		int acc = move.getAccuracy() * player.getAcc() * enemy.getEva();

		if (acc == 255)
			return true;

		int limit = RNG.nextInt(256);

		return (limit < acc) ? true : false;
	}

	/**
	 * Check if a enemy move hit
	 * 
	 * @param player the player Pokemon
	 * @param enemy the enemy pokemon
	 * @param move   the move used
	 * @return true if the enemy move hit the player
	 */
	public static boolean doesHit(PlayerPokemon player, EnemyPokemon enemy, Move move) {

		int acc = move.getAccuracy() * enemy.getAcc() * player.getEva();

		if (acc == 255)
			return true;

		int limit = RNG.nextInt(256);

		return (limit < acc) ? true : false;
	}

	// Getter

	/**
	 * {@return the name of the pokemon}
	 */
	public String getName() {
		return name;
	}

	/**
	 * {@return the first type of the pokemon}
	 */
	public Type getType() {
		return type;
	}

	/**
	 * {@return the second type of the pokemon}
	 */
	public Type getType2() {
		return type2;
	}

	/**
	 * {@return the moves of the pokemon}
	 */
	public Move[] getMoves() {
		return moves;
	}

	/**
	 * {@return the current stats of the pokemon}
	 */
	public Stats getStats() {
		return stats;
	}

	/**
	 * {@return the accuracy of the pokemon}
	 */
	public int getAcc() {
		return acc;
	}

	/**
	 * {@return the evasion of the pokemon}
	 */
	public int getEva() {
		return eva;
	}

	/**
	 * {@return the level of the pokemon}
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * {@return the base Xp gain of the pokemon}
	 */
	public int getBaseXp() {
		return BASE_XP;
	}

	// Setter

	/**
	 * Set the accuracy
	 * 
	 * @param acc the new value
	 */
	public void setAcc(int acc) {
		this.acc = acc;
	}

	/**
	 * Set the evasion
	 * 
	 * @param eva the new value
	 */
	public void setEva(int eva) {
		this.eva = eva;
	}

}
