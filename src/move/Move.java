package move;

import java.util.Arrays;

import i_o.FileRw;

import move.damage.DoNormalDamage;
import move.damage.DoSpecialDamage;

import move.stats.EnStat;
import move.stats.SelfStat;

import pokemon.Pokemon;
import pokemon.Type;

/**
 * A move performed by a pokemon
 */
public class Move {

	//The move type advantage
	public static final double[][] TYPES_ADVANTAGE = new double[][] {
		
	{1,1,1,1,1,1,1,1,1,1,1,1,0.5,0,1},
	{1,0.5,0.5,1,2,2,1,1,1,1,1,2,0.5,1,0.5},
	{1,2,0.5,1,0.5,1,1,1,2,1,1,1,2,1,0.5},
	{1,1,2,0.5,0.5,1,1,1,0,2,1,1,1,1,0.5},
	{1,0.5,2,1,0.5,1,1,0.5,2,0.5,1,0.5,2,1,0.5},
	{1,1,0.5,1,2,0.5,1,1,2,2,1,1,1,1,2},
	{2,1,1,1,1,2,1,0.5,1,0.5,0.5,0.5,2,0,1},
	{1,1,1,1,2,1,1,0.5,0.5,1,1,2,0.5,0.5,1},
	{1,2,1,2,0.5,1,1,2,1,0,1,0.5,2,1,1},
	{1,1,1,0.5,2,1,2,1,1,1,1,2,0.5,1,1},
	{1,1,1,1,1,1,2,2,1,1,0.5,1,1,1,1},
	{1,0.5,1,1,2,1,0.5,2,1,0.5,2,1,1,0.5,1},
	{1,2,1,1,1,2,0.5,1,0.5,2,1,2,1,1,1},
	{0,1,1,1,1,1,1,1,1,1,0,1,1,2,1},
	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,2}
		
	};
	
	//The function 
	private MoveFunction execute;
	
	//The move name
	private String name;
	
	//The move type
	private Type type;
	
	//The move values
	private int power;
	private int accuracy;
	private final int Maxpp;
	private int pp;
	
	//Counter
	private int timeUsed = 0;
	
	/**
	 * Create a new move by reading from the file
	 * 
	 * @param name The name of the move
	 */
	public Move(String name) {
		
		//Find the move
		String[] values = FileRw.findMove(name.replace("-", " ")).split("-");
		
		//Extract the values
		this.name = values[0].replace("_", " ");
		type = Type.valueOf(values[1]);
		if(!values[2].equals("")) power = Integer.parseInt(values[2]);
		if(!values[3].equals("")) accuracy = Integer.parseInt(values[3]);
		Maxpp = Integer.parseInt(values[4]);
		pp = Integer.parseInt(values[4]);
		
		findFunction(Arrays.copyOfRange(values, 5, values.length));
		
	}
	
	/**
	 * Find the appropriate function for the move
	 * 
	 * @param s The string containing the function type and other values
	 */
	private void findFunction(String[] s) {
		
		execute = switch(MoveType.valueOf(s[0])) {
		
		case NorAtt -> new DoNormalDamage();
		case SpAtt -> new DoSpecialDamage();
		case SelfStats -> new SelfStat(s[1].split(","),s[2].split(","));
		case EnStats -> new EnStat(s[1].split(","),s[2].split(","));
		
		};
		
	}
	
	/**
	 * Execute the move 
	 * 
	 * @param p1 The attacking pokemon
	 * @param p2 The defending pokemon
	 */
	public void esegui(Pokemon p1, Pokemon p2) {

		execute.esegui(p1, p2, this);
		pp--;
		timeUsed++;
		
	}
	
	//Getter
	
	/**
	 * @return The name of the move
	 */
	public String getName() {return name;}
	
	/**
	 * @return The type of the move
	 */
	public Type getType() {return type;}
	
	/**
	 * @return The power of the move
	 */
	public int getPower() {return power;}

	/**
	 * @return The accuracy of the move
	 */
	public int getAccuracy() {return accuracy;}

	/**
	 * @return The current PP of the move
	 */
	public int getCurrentPp() {return pp;}

	/**
	 * @return The maximum PP of the move
	 */
	public int getMaxpp() {return Maxpp;}

	/**
	 * @return How many times this move has been used
	 */
	public int getTimeUsed() {return timeUsed;}

	//Setter
	
	/**
	 * @param pp The new value for the field PP
	 */
	public void setPp(int pp) {this.pp = pp;}
	
	/**
	 * Reset the counter
	 */
	public void resetTimeUsed() {timeUsed = 0;}

}
