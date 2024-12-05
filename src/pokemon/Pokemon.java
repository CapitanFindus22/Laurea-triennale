package pokemon;

import java.util.Arrays;
import java.util.Random;

import i_o.FileRw;
import move.Move;

/**
 * A pokemon
 */
public abstract class Pokemon {
	
	//Name
	private String name;
	
	//Types
	private Type type;
	private Type type2;
	
	//Moves
	protected Move[] moves;
	
	//Base stats
	private final Stats baseStats;
	
	//Current stats
	private Stats stats;
	
	//Accuracy
	private int acc;
	
	//Evasion
	private int eva;
	
	//Base xp for defeating an enemy
	private final int baseXp;
	
	//Current Xp
	private int xp;
	
	//Current level
	private int level;
	
	private static final Random r = new Random();
	
	/**
	 * Create a level 1 pokemon
	 * 
	 * @param name The name of the pokemon
	 */
	public Pokemon(String name) {
		
		//Find the pokemon in the file
		String[] s = FileRw.findPokemon(name).split(",");
		
		//s should have: {name,type1,type2,move1,move2,baseStats,baseXp}
		
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
	
	/**
	 * Create a pokemon
	 * 
	 * @param name The name of the pokemon
	 * @param lvl The level of the pokemon
	 */
	public Pokemon(String name, int lvl) {
		
		//Find the pokemon in the file
		String[] s = FileRw.findPokemon(name).split(",");
		
		//s should have: {name,type1,type2,move1,move2,baseStats,baseXp}
		
		this.name = s[0];
		
		type = Type.valueOf(s[1]);
		
		if(!s[2].equals("")) type2 = Type.valueOf(s[2]);
		
		moves = new Move[4];
		
		moves[0] = new Move(s[3]);
		moves[1] = new Move(s[4]);
		
		stats = new Stats(Arrays.copyOfRange(s, 5, s.length-1));
		baseStats = new Stats(Arrays.copyOfRange(s, 5, s.length-1));
		
		baseXp = Integer.parseInt(s[s.length-1]);
		
		level = lvl-1;
		xp = 0;
		
		acc=100;
		eva=100;
		
		lvlUp();
		
	}
	
	/**
	 * @return The number of moves known by the pokemon
	 */
	public int getMoveCount() {

		int count = 2;

		if(moves[2] != null) count++;
		if(moves[3] != null) count++;
		
		return count;
		
	}
	
	/**
	 * Add Xp to a pokemon
	 * 
	 * @param xp The Xp to add
	 * @return True if the pokemon leveled up
	 */
	public boolean addXp(int xp) {
		
		if(level >= 100) {
			return false;
		}
		
		this.xp += xp;
		int lv3 = (int) Math.pow(level+1, 3);
		
		if(this.xp >= lv3) {
		
			this.xp -= lv3;
			lvlUp();
			return true;
			
		}
		
		return false;
		
	}
	
	/**
	 * Calculate the new stats according to the new level
	 */
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
		
		for(Move m : moves) if(m != null) m.setPp(m.getMaxpp());
		
	}
	
	/**
	 * Calculate the exp gained by defeating the enemy pokemon
	 * 
	 * @param p The player pokemon
	 * @param e The enemy pokemon
	 * @return The amount of xp
	 */
	public static int calculateXp(PlayerPokemon p, EnemyPokemon e) {
		
		double result = 3;
		
		result *= e.getLevel();
		
		result *= p.getBaseXp();
		
		result /= 7;
		
		return (int) result;
	}
	
	/**
	 * Check if a player move hit
	 * 
	 * @param p The player Pokemon
	 * @param m The move used
	 * @param e The enemy pokemon
	 * @return True if the player move hit the enemy
	 */
	public static boolean doesHit(PlayerPokemon p, Move m, EnemyPokemon e) {
		
		int acc = m.getAccuracy()*p.getAcc()*e.getEva();
		
		if(acc==255) return true;
		
		int limit = r.nextInt(256);
		
		return (limit<acc)?true:false;
	}
	
	/**
	 * Check if a enemy move hit
	 * 
	 * @param p The player Pokemon
	 * @param e The enemy pokemon
	 * @param m The move used
	 * @return True if the enemy move hit the player
	 */
	public static boolean doesHit(PlayerPokemon p, EnemyPokemon e, Move m) {
		
		int acc = m.getAccuracy()*e.getAcc()*p.getEva();
		
		if(acc==255) return true;
		
		int limit = r.nextInt(256);
		
		return (limit<acc)?true:false;
	}
	
	//Getter
	
	/**
	 * @return The name of the pokemon
	 */
	public String getName() {return name;}

	/**
	 * @return The first type of the pokemon
	 */
	public Type getType() {return type;}
	
	/**
	 * @return The second type of the pokemon
	 */
	public Type getType2() {return type2;}

	/**
	 * @return The moves of the pokemon
	 */
	public Move[] getMoves() {return moves;}

	/**
	 * @return The current stats of the pokemon
	 */
	public Stats getStats() {return stats;}

	/**
	 * @return The accuracy of the pokemon
	 */
	public int getAcc() {return acc;}

	/**
	 * @return The evasion of the pokemon
	 */
	public int getEva() {return eva;}
	
	/**
	 * @return The level of the pokemon
	 */
	public int getLevel() {return level;}
	
	/**
	 * @return The base Xp gain of the pokemon
	 */
	public int getBaseXp() {return baseXp;}

	//Setter
	
	/**
	 * Set the accuracy
	 * 
	 * @param acc The new value
	 */
	public void setAcc(int acc) {this.acc = acc;}
	
	/**
	 * Set the evasion
	 * 
	 * @param eva The new value
	 */
	public void setEva(int eva) {this.eva = eva;}
	
}
