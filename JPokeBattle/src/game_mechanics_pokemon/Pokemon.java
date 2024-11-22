package game_mechanics_pokemon;

import java.util.Arrays;
import java.util.Random;

import game_mechanics_moves.Move;
import i_o.FileReader;

public abstract class Pokemon {
	
	private String name;
	
	private Type type;
	private Type type2;
	
	private Move[] moves;
	
	private final Stats baseStats;
	private Stats stats;
	
	private int accuracy;
	private int evasion;
	
	private int xp;
	private int level;
	
	private Object sound;
	
	private Random r;
	
	public Pokemon(String name) {
		
		String[] s = FileReader.findPokemon(name).split(",");
		
		this.name = s[0];
		
		type = Type.valueOf(s[1]);
		
		if(!s[2].equals("")) type2 = Type.valueOf(s[2]);
		
		moves = new Move[4];
		
		moves[0] = new Move(s[3]);
		moves[1] = new Move(s[4]);
		
		stats = new Stats(Arrays.copyOfRange(s, 5, s.length));
		baseStats = new Stats(Arrays.copyOfRange(s, 5, s.length));
		
		level = 1;
		xp = 0;
	}
	
	public Pokemon(String name, int lvl) {
		
		String[] s = FileReader.findPokemon(name).split(",");
		
		this.name = s[0];
		
		type = Type.valueOf(s[1]);
		
		if(!s[2].equals("")) type2 = Type.valueOf(s[2]);
		
		moves = new Move[4];
		
		moves[0] = new Move(s[3]);
		moves[1] = new Move(s[4]);
		
		stats = new Stats(Arrays.copyOfRange(s, 5, s.length),lvl);
		baseStats = new Stats(Arrays.copyOfRange(s, 5, s.length));
		
		level = lvl;
		xp = 0;
		
	}
	
	public int getMoveCount() {

		int count = 0;
		
		if(moves[0] != null) count++;
		if(moves[1] != null) count++;
		if(moves[2] != null) count++;
		if(moves[3] != null) count++;
		
		return count;
		
	}
	
	public void addXp(int xp) {
		
		this.xp += xp;
		int lv3 = (int) Math.pow(level, 3);
		
		if(this.xp > lv3) {
			
			level++;
			this.xp %= lv3;
			lvlUp();
			
		}
		
	}
	
	private void lvlUp() {
		
		stats.setHp((((baseStats.getHp()+r.nextInt(16))*2*level)/100)+level+10);
		stats.setAtt((((baseStats.getAtt()+r.nextInt(16))*2*level)/100)+5);
		stats.setDif((((baseStats.getDif()+r.nextInt(16))*2*level)/100)+5);
		stats.setSpAtt((((baseStats.getSpAtt()+r.nextInt(16))*2*level)/100)+5);
		stats.setSpDif((((baseStats.getSpDif()+r.nextInt(16))*2*level)/100)+5);
		stats.setVel((((baseStats.getVel()+r.nextInt(16))*2*level)/100)+5);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Move[] getMoves() {
		return moves;
	}
	public void setMoves(Move[] moves) {
		this.moves = moves;
	}
	public Stats getStats() {
		return stats;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	public Object getSound() {
		return sound;
	}
	public void setSound(Object sound) {
		this.sound = sound;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Type getType2() {
		return type2;
	}
	public void setType2(Type type2) {
		this.type2 = type2;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public int getEvasion() {
		return evasion;
	}

	public void setEvasion(int evasion) {
		this.evasion = evasion;
	}


	
}
