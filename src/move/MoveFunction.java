package move;

import pokemon.Pokemon;

/**
 * All moves must have an execution function
 */
public interface MoveFunction {

	abstract void esegui(Pokemon p1, Pokemon p2, Move m);
	
}
