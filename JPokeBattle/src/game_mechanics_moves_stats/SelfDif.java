package game_mechanics_moves_stats;

import game_mechanics_pokemon.Pokemon;

public class SelfDif implements StatsChange {


	@Override
	public void esegui(Pokemon p, int val) {

		p.getStats().setDif(p.getStats().getDif()+val);
		
	}

}
