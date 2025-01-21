package move;

import pokemon.Pokemon;

/**
 * The function executed by a move
 */
public interface MoveFunction {

	/**
	 * Execute the move
	 * 
	 * @param p1 the pokémon that's using the move
	 * @param p2 the other pokémon
	 * @param m  the move used
	 */
	abstract void esegui(Pokemon p1, Pokemon p2, Move m);

}
