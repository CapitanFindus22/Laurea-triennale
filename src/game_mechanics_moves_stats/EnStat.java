package game_mechanics_moves_stats;

import game_mechanics_moves.Move;
import game_mechanics_pokemon.Pokemon;

public class EnStat implements StatsChange {

	private Stat[] s;
	private int[] val;

	public EnStat(String[] s, String[] val) {
		
		this.s = new Stat[s.length];
		this.val = new int[val.length];
		
		for(int i=0;i<s.length;i++) this.s[i] = Stat.valueOf(s[i]);
		
		for(int i=0;i<val.length;i++) this.val[i] = Integer.parseInt(val[i]);
		
	}

	@Override
	public void esegui(Pokemon p1, Pokemon p2, Move m) {

		for(int i=0;i<s.length;i++) {
			
			switch(s[i]) {
			
			case Att:
				
				p2.getStats().setAtt(p2.getStats().getAtt()-val[i]);
				
				break;
				
			case Dif:
				
				p2.getStats().setDif(p2.getStats().getDif()-val[i]);
				
				break;
				
			case SpAtt:
				
				p2.getStats().setSpAtt(p2.getStats().getSpAtt()-val[i]);
				
				break;
				
			case SpDif:
				
				p2.getStats().setSpDif(p2.getStats().getSpDif()-val[i]);
				
				break;
				
			case Eva:
				
				p2.setEva(p2.getEva()-val[i]);
				
				break;
				
			case Acc:
				
				p2.setAcc(p2.getAcc()-val[i]);
				
				break;
			
			}
			
		}
		
	}


}
