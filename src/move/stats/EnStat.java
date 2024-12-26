package move.stats;

import move.Move;
import pokemon.Pokemon;

/**
 * For moves that change the enemy stats
 */
public class EnStat implements StatsChange {

	/**
	 * The stats affected
	 */
	private final Stat[] affected;

	/**
	 * How much they are affected
	 */
	private final int[] amount;

	/**
	 * Save the stats to consider and the values for each
	 * 
	 * @param stats  a string containing the stats affected
	 * @param amount an array of string containing the values for each stat
	 */
	public EnStat(String[] stats, String[] amount) {

		this.affected = new Stat[stats.length];
		this.amount = new int[amount.length];

		for (int i = 0; i < stats.length; i++)
			this.affected[i] = Stat.valueOf(stats[i]);

		for (int i = 0; i < amount.length; i++)
			this.amount[i] = Integer.parseInt(amount[i]);

	}

	/**
	 * Decrement the appropriate stats
	 */
	@Override
	public void esegui(Pokemon p1, Pokemon p2, Move m) {

		if (m.getTimeUsed() <= 6) {

			for (int i = 0; i < affected.length; i++) {

				switch (affected[i]) {

				case Att:

					p2.getStats().setAtt(p2.getStats().getAtt() - amount[i]);

					break;

				case Dif:

					p2.getStats().setDif(p2.getStats().getDif() - amount[i]);

					break;

				case SpAtt:

					p2.getStats().setSpAtt(p2.getStats().getSpAtt() - amount[i]);

					break;

				case SpDif:

					p2.getStats().setSpDif(p2.getStats().getSpDif() - amount[i]);

					break;

				case Spd:

					p2.getStats().setSpeed(p2.getStats().getSpeed() - amount[i]);

					break;

				case Eva:

					p2.setEva(p2.getEva() - amount[i]);

					break;

				case Acc:

					p2.setAcc(p2.getAcc() - amount[i]);

					break;

				}

			}

		}

	}

}
