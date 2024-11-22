package game_mechanics_moves_damage;

import game_mechanics_moves.Move;
import game_mechanics_pokemon.Pokemon;

public interface Damage {
	
	abstract void esegui(Pokemon p1, Pokemon p2, Move m);
	
}
