package game_mechanics_moves_stats;

import game_mechanics_moves.Move;
import game_mechanics_pokemon.Pokemon;

public class SelfStat implements StatsChange {

	private Stat[] s;
	private int[] val;

	public SelfStat(String[] s, String[] val) {
		
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
				
				p1.getStats().setAtt(p1.getStats().getAtt()+val[i]);
				
				break;
				
			case Dif:
				
				p1.getStats().setDif(p1.getStats().getDif()+val[i]);
				
				break;
				
			case SpAtt:
				
				p1.getStats().setSpAtt(p1.getStats().getSpAtt()+val[i]);
				
				break;
				
			case SpDif:
				
				p1.getStats().setSpDif(p1.getStats().getSpDif()+val[i]);
				
				break;
				
			case Eva:
				
				p1.setEva(p1.getEva()+val[i]);
				
				break;
				
			case Acc:
				
				p1.setAcc(p1.getAcc()+val[i]);
				
				break;
			
			}
			
		}
		
	}


}
