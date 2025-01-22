package move;

import java.util.Arrays;

import i_o.FileRw;

import move.damage.DoNormalDamage;
import move.damage.DoSpecialDamage;

import move.stats.EnStat;
import move.stats.SelfStat;

import pokemon.Pokemon;
import pokemon.Type;

/**
 * A move performed by a pokémon
 */
public class Move {

	/**
	 * Represent the move types advantage, to use with Types as indexes
	 */
	public static final float[][] TYPES_ADVANTAGE = {
		
	{ 1f, 1f,   1f,   1f,   1f,   1f,   1f,   1f,   1f,   1f,   1f,   1f,   0.5f, 0f,   1f   },
	{ 1f, 0.5f, 0.5f, 1f,   2f,   2f,   1f,   1f,   1f,   1f,   1f,   2f,   0.5f, 1f,   0.5f },
	{ 1f, 2f,   0.5f, 1f,   0.5f, 1f,   1f,   1f,   2f,   1f,   1f,   1f,   2f,   1f,   0.5f },
	{ 1f, 1f,   2f,   0.5f, 0.5f, 1f,   1f,   1f,   0f,   2f,   1f,   1f,   1f,   1f,   0.5f },
	{ 1f, 0.5f, 2f,   1f,   0.5f, 1f,   1f,   0.5f, 2f,   0.5f, 1f,   0.5f, 2f,   1f,   0.5f },
	{ 1f, 1f,   0.5f, 1f,   2f,   0.5f, 1f,   1f,   2f,   2f,   1f,   1f,   1f,   1f,   2f   },
	{ 2f, 1f,   1f,   1f,   1f,   2f,   1f,   0.5f, 1f,   0.5f, 0.5f, 0.5f, 2f,   0f,   1f   },
	{ 1f, 1f,   1f,   1f,   2f,   1f,   1f,   0.5f, 0.5f, 1f,   1f,   2f,   0.5f, 0.5f, 1f   },
	{ 1f, 2f,   1f,   2f,   0.5f, 1f,   1f,   2f,   1f,   0f,   1f,   0.5f, 2f,   1f,   1f   },
	{ 1f, 1f,   1f,   0.5f, 2f,   1f,   2f,   1f,   1f,   1f,   1f,   2f,   0.5f, 1f,   1f   },
	{ 1f, 1f,   1f,   1f,   1f,   1f,   2f,   2f,   1f,   1f,   0.5f, 1f,   1f,   1f,   1f   },
	{ 1f, 0.5f, 1f,   1f,   2f,   1f,   0.5f, 2f,   1f,   0.5f, 2f,   1f,   1f,   0.5f, 1f   },
	{ 1f, 2f,   1f,   1f,   1f,   2f,   0.5f, 1f,   0.5f, 2f,   1f,   2f,   1f,   1f,   1f   },
	{ 0f, 1f,   1f,   1f,   1f,   1f,   1f,   1f,   1f,   1f,   0f,   1f,   1f,   2f,   1f   },
	{ 1f, 1f,   1f,   1f,   1f,   1f,   1f,   1f,   1f,   1f,   1f,   1f,   1f,   1f,   2f   }
		
	};
	
	/**
	 * The function associated
	 */
	private MoveFunction function;

	/**
	 * The move name
	 */
	private String name;

	/**
	 * The move type
	 */
	private Type type;

	/**
	 * The move power
	 */
	private int power;

	/**
	 * The move accuracy
	 */
	private int accuracy;

	/**
	 * The move maximum pp
	 */
	private final int maxPP;

	/**
	 * The move current pp
	 */
	private int pp;
	
	/**
	 * Counter for the number of times this move has been used
	 */
	private int timeUsed = 0;

	/**
	 * Create a new move by reading from the file
	 * 
	 * @param name the name of the move
	 */
	public Move(String name) {

		// Find the move
		String[] values = FileRw.findMove(name.replace(",", " ")).split(",");

		// Extract the values
		this.name = values[0].replace("_", " ");
		type = Type.valueOf(values[1]);
		if (!values[2].equals(""))
			power = Integer.parseInt(values[2]);
		if (!values[3].equals(""))
			accuracy = Integer.parseInt(values[3]);
		maxPP = Integer.parseInt(values[4]);
		pp = maxPP;

		findFunction(Arrays.copyOfRange(values, 5, values.length));

	}

	/**
	 * Find the appropriate function for the move
	 * 
	 * @param subString the string containing the function type and other values
	 */
	private void findFunction(String[] subString) {

		function = switch (MoveType.valueOf(subString[0])) {

		case NorAtt -> new DoNormalDamage();
		case SpAtt -> new DoSpecialDamage();
		case SelfStats -> new SelfStat(subString[1].split(","), subString[2].split(","));
		case EnStats -> new EnStat(subString[1].split(","), subString[2].split(","));

		// Not yet implemented
		case Other -> throw new IllegalArgumentException("Unexpected value: " + MoveType.valueOf(subString[0]));

		};
		
	}

	/**
	 * Execute the move
	 * 
	 * @param p1 the attacking pokémon
	 * @param p2 the defending pokémon
	 */
	public void esegui(Pokemon p1, Pokemon p2) {

		function.execute(p1, p2, this);
		pp--;
		timeUsed++;

	}

	/**
	 * {@return true if the move always hit}
	 */
	public boolean alwaysHit() {
		
		return (accuracy==0||accuracy==100);
		
	}
	
	/**
	 * Decrease PP by one
	 */
	public void decreasePP() {pp--;}
	
	// Getter

	/**
	 * {@return the name of the move}
	 */
	public String getName() {
		return name;
	}

	/**
	 * {@return the type of the move}
	 */
	public Type getType() {
		return type;
	}

	/**
	 * {@return the power of the move}
	 */
	public int getPower() {
		return power;
	}

	/**
	 * {@return the accuracy of the move}
	 */
	public int getAccuracy() {
		return accuracy;
	}

	/**
	 * {@return the current PP of the move}
	 */
	public int getCurrentPp() {
		return pp;
	}

	/**
	 * {@return the maximum PP of the move}
	 */
	public int getMaxpp() {
		return maxPP;
	}

	/**
	 * {@return how many times this move has been used}
	 */
	public int getTimeUsed() {
		return timeUsed;
	}

	// Setter

	/**
	 * Set a new value for pp
	 * 
	 * @param pp the new value
	 */
	public void setPP(int pp) {
		this.pp = pp;
	}

	/**
	 * Reset the counter
	 */
	public void resetTimeUsed() {
		timeUsed = 0;
	}

}
