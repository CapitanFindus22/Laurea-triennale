package game_mechanics_pokemon;

import java.util.Random;

public class Stats {

	private final Random r = new Random();
	
	private int hp;
	private int att;
	private int sp_att;
	private int dif;
	private int sp_dif;
	private int vel;
	
	public Stats(String[] s) {
		
		this.hp = Integer.parseInt(s[0]);
		this.att = Integer.parseInt(s[1]);
		this.dif = Integer.parseInt(s[2]);
		this.sp_att = Integer.parseInt(s[3]);
		this.sp_dif = Integer.parseInt(s[4]);
		this.vel = Integer.parseInt(s[5]);
		
	}
	
	public Stats(String[] s, int lvl) {
		
		this.hp = ((((Integer.parseInt(s[0])+r.nextInt(16))*2*lvl)/100)+lvl+10);
		this.att = ((((Integer.parseInt(s[1])+r.nextInt(16))*2*lvl)/100)+5);
		this.dif = ((((Integer.parseInt(s[2])+r.nextInt(16))*2*lvl)/100)+5);
		this.sp_att = ((((Integer.parseInt(s[3])+r.nextInt(16))*2*lvl)/100)+5);
		this.sp_dif = ((((Integer.parseInt(s[4])+r.nextInt(16))*2*lvl)/100)+5);
		this.vel = ((((Integer.parseInt(s[5])+r.nextInt(16))*2*lvl)/100)+5);
		
	}
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtt() {
		return att;
	}
	public void setAtt(int att) {
		this.att = att;
	}
	public int getSpAtt() {
		return sp_att;
	}
	public void setSpAtt(int sp_att) {
		this.sp_att = sp_att;
	}
	public int getDif() {
		return dif;
	}
	public void setDif(int dif) {
		this.dif = dif;
	}
	public int getSpDif() {
		return sp_dif;
	}
	public void setSpDif(int sp_dif) {
		this.sp_dif = sp_dif;
	}
	public int getVel() {
		return vel;
	}
	public void setVel(int vel) {
		this.vel = vel;
	}
	
	
}
