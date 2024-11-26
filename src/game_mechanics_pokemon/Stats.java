package game_mechanics_pokemon;

public class Stats {
	
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
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = Math.max(hp, 0);
	}
	public int getAtt() {
		return att;
	}
	public void setAtt(int att) {
		this.att = Math.max(att, 1);
	}
	public int getSpAtt() {
		return sp_att;
	}
	public void setSpAtt(int sp_att) {
		this.sp_att = Math.max(sp_att, 1);
	}
	public int getDif() {
		return dif;
	}
	public void setDif(int dif) {
		this.dif = Math.max(dif, 1);
	}
	public int getSpDif() {
		return sp_dif;
	}
	public void setSpDif(int sp_dif) {
		this.sp_dif = Math.max(sp_dif, 1);
	}
	public int getVel() {
		return vel;
	}
	public void setVel(int vel) {
		this.vel = Math.max(vel, 1);
	}
	
	@Override
	public String toString() {
		
		String result = "<html>";
		
		result += "Att: ";
		result += getAtt();
		result += "<br>";
		
		result += "Dif: ";
		result += getDif();
		result += "<br>";
		
		result += "SpAtt: ";
		result += getSpAtt();
		result += "<br>";
		
		result += "SpDif: ";
		result += getSpDif();
		result += "<br>";
		
		result += "Vel: ";
		result += getVel();
		result += "<br>";
		
		result += "</html>";
		
		return result;
		
	}
	
}
