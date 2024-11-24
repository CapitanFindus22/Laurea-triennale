package game_mechanics_moves;

import java.util.Arrays;

import game_mechanics_moves_damage.Damage;
import game_mechanics_moves_damage.DoNormalDamage;
import game_mechanics_moves_damage.DoSpecialDamage;
import game_mechanics_moves_stats.EnStat;
import game_mechanics_moves_stats.SelfStat;
import game_mechanics_pokemon.Pokemon;
import game_mechanics_pokemon.Type;
import i_o.FileRw;

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
	private final int Maxpp;
	private int pp;
	
	public Move(String s) {
		
		String[] values = FileRw.findMove(s).split(",");
		
		name = values[0];
		type = Type.valueOf(values[1]);
		if(!values[2].equals("")) power = Integer.parseInt(values[2]);
		if(!values[3].equals("")) accuracy = Integer.parseInt(values[3]);
		Maxpp = Integer.parseInt(values[4]);
		pp = Integer.parseInt(values[4]);
		
		findType(Arrays.copyOfRange(values, 5, values.length));
		
	}
	
	private void findType(String[] s) {
		
		execute = switch(MoveType.valueOf(s[0])) {
		
		case NorAtt -> new DoNormalDamage();
		case SpAtt -> new DoSpecialDamage();
		case SelfStats -> new SelfStat(s[1].split(","),s[2].split(","));
		case EnStats -> new EnStat(s[1].split(","),s[2].split(","));
		
		};
		
	}
	
	public void setFunction(Damage function) {
		
		execute = function;
		
	}
	
	public void esegui(Pokemon p1, Pokemon p2) {

		execute.esegui(p1, p2, this);
		pp--;
		
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

	public int getMaxpp() {
		return Maxpp;
	}

}
