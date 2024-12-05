package pokemon;

import java.awt.Image;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import game.MoveChange;
import i_o.FileRw;
import i_o.SpriteFinder;
import move.Move;

/**
 * The pokemon used by the player
 */
public class PlayerPokemon extends Pokemon {

	//Sprite
	private Image backSprite;
	
	//Last change move
	private int lastMoveChanged = 0;
	
	//All moves known or "skipped"
	private HashSet<String> pastMoves;
	
	/**
	 * Create a level 1 pokemon with a sprite
	 * 
	 * @param name The name of the pokemon
	 */
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

	/**
	 * Check if the pokemon wants to learn a new move and act accordingly to
	 * how much moves he knows
	 */
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
	
	/**
	 * Change the first move
	 * 
	 * @param m The new move
	 */
	public void changeFirstMove(Move m) {
		
		moves[0] = m;
		lastMoveChanged = 1;
		addInPastMoves(m.getName());
	}
	
	/**
	 * Change the second move
	 * 
	 * @param m The new move
	 */
	public void changeSecondMove(Move m) {
		
		moves[1] = m;
		lastMoveChanged = 2;
		addInPastMoves(m.getName());
		
	}
	
	/**
	 * Change the third move
	 * 
	 * @param m The new move
	 */
	public void changeThirdMove(Move m) {
		
		moves[2] = m;
		lastMoveChanged = 3;
		addInPastMoves(m.getName());
		
	}
	
	/**
	 * Change the fourth move
	 * 
	 * @param m The new move
	 */
	public void changeFourthMove(Move m) {
		
		moves[3] = m;
		lastMoveChanged = 4;
		addInPastMoves(m.getName());
		
	}
	
	/**
	 * Add a move to those known by the pokemon
	 * 
	 * @param name The name of the move
	 */
	public void addInPastMoves(String name) {pastMoves.add(name.replace(" ", "_"));}

	//Getter
	
	/**
	 * @return The sprite of the pokemon
	 */
	public Image getBackSprite() {return backSprite;}
	
	/**
	 * @return The number representing the last move that changed,
	 * 1,2,3,4 or 0 if none
	 */
	public int getlastMoveChanged() {return lastMoveChanged;}

	//Setter
	
	/**
	 * Set lastMoveChanged to 0
	 */
	public void resetlastMoveChanged() {lastMoveChanged = 0;}
	
}
