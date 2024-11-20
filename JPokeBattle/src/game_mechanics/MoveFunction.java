package game_mechanics;

import java.util.Random;

public abstract class MoveFunction {

	protected final static Random r = new Random();
	
	abstract void esegui(Pokemon p1, Pokemon p2, Move m);
	
}
