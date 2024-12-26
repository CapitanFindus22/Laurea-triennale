package move.stats;

import move.MoveFunction;

/**
 * For the moves that change stats
 */
public interface StatsChange extends MoveFunction {

	/**
	 * The maximum number of times the move has effect
	 */
	public static final int MAX_TIME = 6;

}
