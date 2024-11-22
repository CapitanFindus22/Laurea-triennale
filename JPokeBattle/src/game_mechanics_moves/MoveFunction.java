package game_mechanics_moves;

import java.util.Random;

import game_mechanics_pokemon.Pokemon;

public abstract class MoveFunction {

	protected final static Random r = new Random();
	
	abstract void esegui(Pokemon p1, Pokemon p2, Move m);
	
}
