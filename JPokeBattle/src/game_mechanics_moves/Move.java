package game_mechanics_moves;

import game_mechanics_pokemon.Pokemon;
import game_mechanics_pokemon.Type;
import i_o.FileReader;

public class Move {

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
	
	private MoveFunction execute;
	
	private Type type;
	
	private String name;
	
	private int power;
	private int accuracy;
	private int pp;
	
	public Move(String s) {
		
		String[] values = FileReader.findMove(s).split(",");
		
		name = values[0];
		type = Type.valueOf(values[1]);
		if(!values[2].equals("")) power = Integer.parseInt(values[2]);
		if(!values[3].equals("")) accuracy = Integer.parseInt(values[3]);
		pp = Integer.parseInt(values[4]);
		
		
	}
	
	public void setFunction(MoveFunction function) {
		
		execute = function;
		
	}
	
	public void esegui(Pokemon p1, Pokemon p2, Move m) {

		execute.esegui(p1, p2, m);
		
	}
	
	public Type getType() {
		
		return type;
		
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPp() {
		return pp;
	}

	public void setPp(int pp) {
		this.pp = pp;
	}

}
