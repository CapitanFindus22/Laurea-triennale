package game_mechanics_moves;

import game_mechanics_pokemon.Pokemon;

public interface MoveFunction {

	abstract void esegui(Pokemon p1, Pokemon p2, Move m);
	
}
