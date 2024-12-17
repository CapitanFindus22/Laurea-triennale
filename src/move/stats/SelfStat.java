package move.stats;

import move.Move;
import pokemon.Pokemon;

/** 
 * For moves that change the stats of the user itself 
 */
public class SelfStat implements StatsChange {

	/**
	 * The stats affected
	 */
	private Stat[] s;
	
	/**
	 * How much they are affected
	 */
	private int[] val;

	/**
	 * Save the stats to consider and the values for each
	 * 
	 * @param stats a string containing the stats affected
	 * @param amount an array of string containing the values for each stat
	 */
	public SelfStat(String[] stats, String[] amount) {
		
		this.s = new Stat[stats.length];
		this.val = new int[amount.length];
		
		for(int i=0;i<stats.length;i++) this.s[i] = Stat.valueOf(stats[i]);
		
		for(int i=0;i<amount.length;i++) this.val[i] = Integer.parseInt(amount[i]);
		
	}

	/**
	 * Increment the appropriate stats 
	 */
	@Override
	public void esegui(Pokemon p1, Pokemon p2, Move m) {

		if(m.getTimeUsed() <= 6) {
		
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
					
				case Spd:
					
					p1.getStats().setSpeed(p1.getStats().getSpeed()+val[i]);
					
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


}
