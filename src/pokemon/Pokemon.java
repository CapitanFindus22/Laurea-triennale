package pokemon;

import java.util.Arrays;
import java.util.Optional;
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
	 * The IVs of the pokémon
	 */
	private final Stats IVs;
	
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
	private final Random RNG = new Random();

	/**
	 * Create a level 1 pokemon
	 * 
	 * @param name the name of the pokémon
	 */
	public Pokemon(String name) {

		// Find the pokémon in the file
		String[] s = FileRw.findPokemon(name).split(",");

		// s should have: {name,type1,type2,move1,move2,baseStats,baseXp}

		this.name = s[0];

		type = Type.valueOf(s[1]);

		if (!s[2].equals(""))
			type2 = Type.valueOf(s[2]);

		// Create the base moves
		moves = new Move[4];
		moves[0] = new Move(s[3]);
		moves[1] = new Move(s[4]);

		// Create the stats
		stats = new Stats(Arrays.copyOfRange(s, 5, s.length - 1));
		BASE_STATS = new Stats(Arrays.copyOfRange(s, 5, s.length - 1));

		BASE_XP = Integer.parseInt(s[s.length - 1]);
		
		// Create the IVs
		int[] rng = new int[] {RNG.nextInt(16),RNG.nextInt(16),RNG.nextInt(16),RNG.nextInt(16),RNG.nextInt(16),RNG.nextInt(16)};
		IVs = new Stats(rng);

		level = 1;
		xp = 0;

		acc = 100;
		eva = 100;

		level--;

		lvlUp();
	}

	/**
	 * Create a pokémon of the specified level
	 * 
	 * @param name the name of the pokémon
	 * @param lvl  the level of the pokémon
	 */
	public Pokemon(String name, int lvl) {

		// Find the pokémon in the file
		String[] s = FileRw.findPokemon(name).split(",");

		// s should have: {name,type1,type2,move1,move2,baseStats,baseXp}

		this.name = s[0];

		type = Type.valueOf(s[1]);

		if (!s[2].equals(""))
			type2 = Type.valueOf(s[2]);

		// Create the base moves
		moves = new Move[4];
		moves[0] = new Move(s[3]);
		moves[1] = new Move(s[4]);

		// Create the stats
		stats = new Stats(Arrays.copyOfRange(s, 5, s.length - 1));
		BASE_STATS = new Stats(Arrays.copyOfRange(s, 5, s.length - 1));

		BASE_XP = Integer.parseInt(s[s.length - 1]);
		
		// Create the IVs
		int[] rng = new int[] {RNG.nextInt(16),RNG.nextInt(16),RNG.nextInt(16),RNG.nextInt(16),RNG.nextInt(16),RNG.nextInt(16)};
		IVs = new Stats(rng);

		level = lvl - 1;
		xp = 0;

		acc = 100;
		eva = 100;

		lvlUp();

	}

	/**
	 * {@return the number of moves known by the pokémon}
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
	 * Add Xp to the pokémon
	 * 
	 * @param xp the Xp to add
	 * @return true If the pokémon leveled up
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
	 * Add a level and calculate the new stats
	 */
	protected void lvlUp() {

		level++;

		double temp = (((2 * BASE_STATS.getHp() + IVs.getHp()) * level) / 100) + level + 10;
		stats.setHp((int) temp);

		temp = (((2 * BASE_STATS.getAtt() + IVs.getAtt()) * level) / 100) + 5;
		stats.setAtt((int) temp);

		temp = (((2 * BASE_STATS.getDif() + IVs.getDif()) * level) / 100) + 5;
		stats.setDif((int) temp);

		temp = (((2 * BASE_STATS.getSpAtt() + IVs.getSpAtt()) * level) / 100) + 5;
		stats.setSpAtt((int) temp);

		temp = (((2 * BASE_STATS.getSpDif() + IVs.getSpDif()) * level) / 100) + 5;
		stats.setSpDif((int) temp);

		temp = (((2 * BASE_STATS.getSpeed() + IVs.getSpeed()) * level) / 100) + 5;
		stats.setSpeed((int) temp);

		for (Move m : moves)
			if (m != null)
				m.setPP(m.getMaxpp());

	}

	/**
	 * Calculate the experience gained by defeating a pokémon
	 * 
	 * @param defeated the defeated pokémon
	 * @return the amount of xp gained
	 */
	public int calculateXp(Pokemon defeated) {

		double result = 3;

		result *= defeated.getLevel();

		result *= BASE_XP;

		result /= 7;

		return (int) result;
	}

	/**
	 * Check if a move hit
	 * 
	 * @param move   the move used
	 * @param defender  the defending pokémon
	 * @return true if the player move hit the enemy
	 */
	public boolean doesHit(Move move, Pokemon defender) {

		int accuracy = move.getAccuracy() * acc * defender.getEva();

		if (accuracy == 255)
			return true;

		int limit = RNG.nextInt(256);

		return limit < accuracy;
	}

	// Getter

	/**
	 * {@return the name of the pokémon}
	 */
	public String getName() {
		return name;
	}

	/**
	 * {@return the first type of the pokémon}
	 */
	public Type getType() {
		return type;
	}

	/**
	 * {@return the second type of the pokémon}
	 */
	public Optional<Type> getType2() {
		return Optional.ofNullable(type2);
	}

	/**
	 * {@return the moves of the pokémon}
	 */
	public Move[] getMoves() {
		return moves;
	}

	/**
	 * {@return the current stats of the pokémon}
	 */
	public Stats getStats() {
		return stats;
	}

	/**
	 * {@return the accuracy of the pokémon}
	 */
	public int getAcc() {
		return acc;
	}

	/**
	 * {@return the evasion of the pokémon}
	 */
	public int getEva() {
		return eva;
	}

	/**
	 * {@return the level of the pokémon}
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * {@return the base Xp gain of the pokémon}
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
