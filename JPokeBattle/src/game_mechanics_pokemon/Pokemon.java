package game_mechanics_pokemon;

import java.util.Arrays;

import game_mechanics_moves.Move;
import i_o.FileReader;

public abstract class Pokemon {
	
	private String name;
	private Type type;
	private Type type2;
	private Move[] moves;
	private Stats stats;
	private int level;
	private Object sound;
	
	public Pokemon(String name) {
		
		String[] s = FileReader.findPokemon(name).split(",");
		
		this.name = s[0];
		
		type = Type.valueOf(s[1]);
		
		if(!s[2].equals("")) type2 = Type.valueOf(s[2]);
		
		moves = new Move[4];
		
		moves[0] = new Move(s[3]);
		moves[1] = new Move(s[4]);
		
		stats = new Stats(Arrays.copyOfRange(s, 5, s.length));
		
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


	
}
