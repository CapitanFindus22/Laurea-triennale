package game_mechanics;

import java.awt.Image;
import java.util.Arrays;

import i_o.FindPokemon;

public class Pokemon {
	
	private String name;
	private Type type;
	private Type type2;
	private Image sprite;
	private Move[] moves;
	private Stats stats;
	private int level;
	private Object sound;
	
	public Pokemon(String name) {
		
		String[] s = FindPokemon.find(name).split(",");
		
		name = s[0];
		type = Type.valueOf(s[1]);
		
		if(s[2] != "") type2 = Type.valueOf(s[2]);
		
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
	public Image getSprite() {
		return sprite;
	}
	public void setSprite(Image sprite) {
		this.sprite = sprite;
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
