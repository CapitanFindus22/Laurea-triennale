package move.damage;

import java.util.Random;

import move.MoveFunction;

/**
 * For the moves that deal damage
 */
public interface Damage extends MoveFunction {

	/**
	 * Random generator used for critical strikes and other stuff
	 */
	final static Random RNG = new Random();

}
