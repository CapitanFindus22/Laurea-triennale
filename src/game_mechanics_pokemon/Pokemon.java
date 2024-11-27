package game_mechanics_pokemon;

import java.util.Arrays;
import java.util.Random;

import game_mechanics_moves.Move;
import i_o.FileRw;

public abstract class Pokemon {
	
	private String name;
	
	private Type type;
	private Type type2;
	
	protected Move[] moves;
	
	private final Stats baseStats;
	private Stats stats;
	
	private int acc;
	private int eva;
	
	private final int baseXp;
	private int xp;
	private int level;
	
	private Object sound;
	
	private final Random r = new Random();
	
	public Pokemon(String name) {
		
		String[] s = FileRw.findPokemon(name).split(",");
		
		this.name = s[0];
		
		type = Type.valueOf(s[1]);
		
		if(!s[2].equals("")) type2 = Type.valueOf(s[2]);
		
		moves = new Move[4];
		
		moves[0] = new Move(s[3]);
		moves[1] = new Move(s[4]);
		
		stats = new Stats(Arrays.copyOfRange(s, 5, s.length-1));
		baseStats = new Stats(Arrays.copyOfRange(s, 5, s.length-1));
		
		baseXp = Integer.parseInt(s[s.length-1]);
		
		level = 1;
		xp = 0;
		
		acc=100;
		eva=100;
		
		level--;
		lvlUp();
	}
	
	public Pokemon(String name, int lvl) {
		
		String[] s = FileRw.findPokemon(name).split(",");
		
		this.name = s[0];
		
		type = Type.valueOf(s[1]);
		
		if(!s[2].equals("")) type2 = Type.valueOf(s[2]);
		
		moves = new Move[4];
		
		moves[0] = new Move(s[3]);
		moves[1] = new Move(s[4]);
		
		stats = new Stats(Arrays.copyOfRange(s, 5, s.length-1));
		baseStats = new Stats(Arrays.copyOfRange(s, 5, s.length-1));
		
		baseXp = Integer.parseInt(s[s.length-1]);
		
		level = lvl;
		xp = 0;
		
		acc=100;
		eva=100;
		
		lvlUp();
		
	}
	
	public int getMoveCount() {

		int count = 2;

		if(moves[2] != null) count++;
		if(moves[3] != null) count++;
		
		return count;
		
	}
	
	public void addXp(int xp) {
		
		if(level >= 100) {
			return;
		}
		
		this.xp += xp;
		int lv3 = (int) Math.pow(level+1, 3);
		
		if(this.xp >= lv3) {
		
			this.xp -= lv3;
			lvlUp();
			
		}
		
	}
	
	protected void lvlUp() {

		level++;
		
		double temp = (((2*baseStats.getHp()+r.nextInt(16))*level)/100)+level+10;
		stats.setHp((int) temp);
		
		temp = (((2*baseStats.getAtt()+r.nextInt(16))*level)/100)+5;
		stats.setAtt((int) temp);
		
		temp = (((2*baseStats.getDif()+r.nextInt(16))*level)/100)+5;
		stats.setDif((int) temp);
		
		temp = (((2*baseStats.getSpAtt()+r.nextInt(16))*level)/100)+5;
		stats.setSpAtt((int) temp);
		
		temp = (((2*baseStats.getSpDif()+r.nextInt(16))*level)/100)+5;
		stats.setSpDif((int) temp);
		
		temp = (((2*baseStats.getVel()+r.nextInt(16))*level)/100)+5;
		stats.setVel((int) temp);
		
		for(Move m : moves) {
			
			if(m != null) m.setPp(m.getMaxpp());
			
		}
		
	}
	
	//Getter
	
	public String getName() {return name;}

	public Type getType() {return type;}
	
	public Type getType2() {return type2;}

	public Move[] getMoves() {return moves;}

	public Stats getStats() {return stats;}

	public int getAcc() {return acc;}

	public int getEva() {return eva;}

	public Object getSound() {return sound;}
	
	public int getLevel() {return level;}
	
	public int getBaseXp() {return baseXp;}

	//Setter
	
	public void setAcc(int acc) {this.acc = acc;}
	
	public void setEva(int eva) {this.eva = eva;}
	
}
