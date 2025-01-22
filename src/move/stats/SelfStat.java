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
	private final Stat[] affected;

	/**
	 * How much they are affected
	 */
	private final int[] amount;

	/**
	 * Save the stats to consider and the values for each
	 * 
	 * @param stats  an array of strings containing the stats affected
	 * @param amount an array of strings containing the values for each stat
	 */
	public SelfStat(String[] stats, String[] amount) {

		this.affected = new Stat[stats.length];
		this.amount = new int[amount.length];

		for (int i = 0; i < stats.length; i++)
			this.affected[i] = Stat.valueOf(stats[i]);

		for (int i = 0; i < amount.length; i++)
			this.amount[i] = Integer.parseInt(amount[i]);

	}

	/**
	 * Increment the appropriate stats
	 */
	@Override
	public void execute(Pokemon p1, Pokemon p2, Move m) {

		if (m.getTimeUsed() <= 6) {

			for (int i = 0; i < affected.length; i++) {

				switch (affected[i]) {

				case Att:

					p1.getStats().setAtt(p1.getStats().getAtt() + amount[i]);

					break;

				case Dif:

					p1.getStats().setDif(p1.getStats().getDif() + amount[i]);

					break;

				case SpAtt:

					p1.getStats().setSpAtt(p1.getStats().getSpAtt() + amount[i]);

					break;

				case SpDif:

					p1.getStats().setSpDif(p1.getStats().getSpDif() + amount[i]);

					break;

				case Spd:

					p1.getStats().setSpeed(p1.getStats().getSpeed() + amount[i]);

					break;

				case Eva:

					p1.setEva(p1.getEva() + amount[i]);

					break;

				case Acc:

					p1.setAcc(p1.getAcc() + amount[i]);

					break;

				}

			}

		}

	}

}
