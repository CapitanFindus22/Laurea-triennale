package pokemon;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import i_o.FileRw;
import i_o.SpriteFinder;
import move.Move;

public class EnemyPokemon extends Pokemon {

	private Image frontSprite;
	
	public EnemyPokemon(String name) {
		
		super(name);
		frontSprite = SpriteFinder.findFront(name);
		frontSprite = frontSprite.getScaledInstance(frontSprite.getWidth(null)*3, frontSprite.getHeight(null)*3, Image.SCALE_DEFAULT);
		
	}
	
	public EnemyPokemon(String name, int level) {
		
		super(name,level);
		chooseMoves();
		frontSprite = SpriteFinder.findFront(name);
		frontSprite = frontSprite.getScaledInstance(frontSprite.getWidth(null)*3, frontSprite.getHeight(null)*3, Image.SCALE_DEFAULT);
		
	}
	
	private void chooseMoves() {
		
		TreeMap<Integer,String> allMoves = FileRw.getLvlUpMoves(getName());
		
		allMoves.put(0, getMoves()[0].getName());
		allMoves.put(1, getMoves()[1].getName());
		
		ArrayList<String> available = new ArrayList<>();
		
		for(Map.Entry<Integer,String> e : allMoves.entrySet()) if(e.getKey() <= getLevel()) available.add(e.getValue());
		
		Collections.shuffle(available);
		
		getMoves()[0] = new Move(available.getFirst());
		available.removeFirst();
		
		getMoves()[1] = new Move(available.getFirst());
		available.removeFirst();
		
		if(available.size() > 0) {
			
			getMoves()[2] = new Move(available.getFirst());
			available.removeFirst();
		
		}
			
		if(available.size() > 0) {
			
			getMoves()[3] = new Move(available.getFirst());
			available.removeFirst();
		
		}
		
	}
	
	public Image getFrontSprite() {return frontSprite;}

	public void setFrontSprite(Image frontSprite) {this.frontSprite = frontSprite;}

	
}
