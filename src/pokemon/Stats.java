package pokemon;

/**
 * The stats of a pokemon
 */
public class Stats {
	
	//Stats
	private int hp;
	private int att;
	private int sp_att;
	private int dif;
	private int sp_dif;
	private int vel;
	
	/**
	 * Create and save the values
	 * 
	 * @param s The array containing all the stats in order: hp, att, sp_att, dif, sp_dif, vel
	 */
	public Stats(String[] s) {
		
		this.hp = Integer.parseInt(s[0]);
		this.att = Integer.parseInt(s[1]);
		this.dif = Integer.parseInt(s[2]);
		this.sp_att = Integer.parseInt(s[3]);
		this.sp_dif = Integer.parseInt(s[4]);
		this.vel = Integer.parseInt(s[5]);
		
	}
	
	//Getter
	
	/**
	 * @return The Health value
	 */
	public int getHp() {return hp;}

	/**
	 * @return The Attack value
	 */
	public int getAtt() {return att;}
	
	/**
	 * @return The Special Attack value
	 */
	public int getSpAtt() {return sp_att;}
	
	/**
	 * @return The Defense value
	 */
	public int getDif() {return dif;}
	
	/**
	 * @return The Special Defense value
	 */
	public int getSpDif() {return sp_dif;}
	
	/**
	 * @return The Velocity value
	 */
	public int getVel() {return vel;}
	
	//Setter
	
	/**
	 * Set the health value
	 * 
	 * @param hp The new value
	 */
	public void setHp(int hp) {this.hp = Math.max(hp, 0);}
	
	/**
	 * Set the attack value
	 * 
	 * @param att The new value
	 */
	public void setAtt(int att) {this.att = Math.max(att, 1);}
	
	/**
	 * Set the Special Attack value
	 * 
	 * @param sp_att The new value
	 */
	public void setSpAtt(int sp_att) {this.sp_att = Math.max(sp_att, 1);}
	
	/**
	 * Set the Defense value
	 * 
	 * @param dif The new value
	 */
	public void setDif(int dif) {this.dif = Math.max(dif, 1);}
	
	/**
	 * Set the Special Defense value
	 * 
	 * @param sp_dif The new value
	 */
	public void setSpDif(int sp_dif) {this.sp_dif = Math.max(sp_dif, 1);}
	
	/**
	 * Set the Velocity value
	 * 
	 * @param vel The new value
	 */
	public void setVel(int vel) {this.vel = Math.max(vel, 1);}
	
}
