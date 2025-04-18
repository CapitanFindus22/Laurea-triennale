package pokemon;

/**
 * The stats of a pokémon
 */
public class Stats {

	/**
	 * Health
	 */
	private int hp;

	/**
	 * Attack
	 */
	private int att;

	/**
	 * Special attack
	 */
	private int spAtt;

	/**
	 * Defense
	 */
	private int dif;

	/**
	 * Special defense
	 */
	private int spDif;

	/**
	 * Speed
	 */
	private int speed;

	/**
	 * Create and save the values
	 * 
	 * @param allStats the array containing all the stats in order: hp, att, sp_att,
	 *                 dif, sp_dif, vel
	 */
	public Stats(String[] allStats) {

		this.hp = Integer.parseInt(allStats[0]);
		this.att = Integer.parseInt(allStats[1]);
		this.dif = Integer.parseInt(allStats[2]);
		this.spAtt = Integer.parseInt(allStats[3]);
		this.spDif = Integer.parseInt(allStats[4]);
		this.speed = Integer.parseInt(allStats[5]);

	}

	/**
	 * Create and save the values
	 * 
	 * @param allStats the array containing all the stats in order: hp, att, sp_att,
	 *                 dif, sp_dif, vel
	 */
	public Stats(int[] allStats) {

		this.hp = allStats[0];
		this.att = allStats[1];
		this.dif = allStats[2];
		this.spAtt = allStats[3];
		this.spDif = allStats[4];
		this.speed = allStats[5];

	}

	// Getter

	/**
	 * {@return the health value}
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * {@return the attack value}
	 */
	public int getAtt() {
		return att;
	}

	/**
	 * {@return the special attack value}
	 */
	public int getSpAtt() {
		return spAtt;
	}

	/**
	 * {@return the defense value}
	 */
	public int getDif() {
		return dif;
	}

	/**
	 * {@return the special defense value}
	 */
	public int getSpDif() {
		return spDif;
	}

	/**
	 * {@return the velocity value}
	 */
	public int getSpeed() {
		return speed;
	}

	// Setter

	/**
	 * Set the health value
	 * 
	 * @param hp the new value
	 */
	public void setHp(int hp) {
		this.hp = Math.max(hp, 0);
	}

	/**
	 * Set the attack value
	 * 
	 * @param att the new value
	 */
	public void setAtt(int att) {
		this.att = Math.max(att, 1);
	}

	/**
	 * Set the special attack value
	 * 
	 * @param spAtt the new value
	 */
	public void setSpAtt(int spAtt) {
		this.spAtt = Math.max(spAtt, 1);
	}

	/**
	 * Set the defense value
	 * 
	 * @param dif the new value
	 */
	public void setDif(int dif) {
		this.dif = Math.max(dif, 1);
	}

	/**
	 * Set the special defense value
	 * 
	 * @param spDif the new value
	 */
	public void setSpDif(int spDif) {
		this.spDif = Math.max(spDif, 1);
	}

	/**
	 * Set the speed value
	 * 
	 * @param speed the new value
	 */
	public void setSpeed(int speed) {
		this.speed = Math.max(speed, 1);
	}

}
