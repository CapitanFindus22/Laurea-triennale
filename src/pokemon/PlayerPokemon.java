package pokemon;

import java.awt.Image;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import game.MoveChange;
import i_o.FileRw;
import i_o.SpriteFinder;
import move.Move;

public class PlayerPokemon extends Pokemon {

	private Image backSprite;
	
	private int moveChange = -1;
	
	private HashSet<String> pastMoves;
	
	public PlayerPokemon(String name) {
		
		super(name);
		backSprite = SpriteFinder.findBack(name);
		backSprite = backSprite.getScaledInstance(backSprite.getWidth(null)*2, backSprite.getHeight(null)*2, Image.SCALE_DEFAULT);
		
		pastMoves = new HashSet<>();
		
		pastMoves.add(getMoves()[0].getName());
		pastMoves.add(getMoves()[1].getName());
		
	}
	
	@Override
	protected void lvlUp() {
		
		super.lvlUp();
		
		checkNewMove();
		
	}
	
	public Image getBackSprite() {return backSprite;}

	public void setBackSprite(Image backSprite) {this.backSprite = backSprite;}

	public int getMoveChange() {return moveChange;}

	public void setMoveChange(int i) {this.moveChange = i;}

	private void checkNewMove() {
		
		TreeMap<Integer,String> allMoves = FileRw.getLvlUpMoves(getName());
		
		TreeMap<Integer,String> newMoves = new TreeMap<>();
		
		for(Map.Entry<Integer, String> e : allMoves.entrySet()) {
			
			if(e.getKey() <= getLevel() && !pastMoves.contains(e.getValue())) newMoves.put(e.getKey(),e.getValue());
			
		}
		
		if(newMoves.size() > 0) {
			
			if(getMoves()[2] == null) changeThirdMove(new Move(newMoves.get(newMoves.firstKey())));
			
			else if(getMoves()[3] == null) changeFourthMove(new Move(newMoves.get(newMoves.firstKey())));
			
			else new MoveChange(this,newMoves.get(newMoves.firstKey()));
		}
		
	}
	
	public void changeFirstMove(Move m) {
		
		moves[0] = m;
		moveChange = 0;
		addInPastMoves(m.getName());
	}
	
	public void changeSecondMove(Move m) {
		
		moves[1] = m;
		moveChange = 1;
		addInPastMoves(m.getName());
		
	}
	
	public void changeThirdMove(Move m) {
		
		moves[2] = m;
		moveChange = 2;
		addInPastMoves(m.getName());
		
	}
	
	public void changeFourthMove(Move m) {
		
		moves[3] = m;
		moveChange = 3;
		addInPastMoves(m.getName());
		
	}
	
	public void addInPastMoves(String name) {pastMoves.add(name.replace(" ", "_"));}
	
}
