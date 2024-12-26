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

	/**
	 * The sprite of the pokemon
	 */
	private Image backSprite;

	/**
	 * Keeps track of the last changed move
	 */
	private int lastMoveChanged = 0;

	/**
	 * All moves learned or "skipped"
	 */
	private HashSet<String> pastMoves;

	/**
	 * Create a level 1 pokemon with a sprite
	 * 
	 * @param name the name of the pokemon
	 */
	public PlayerPokemon(String name) {

		super(name);
		backSprite = SpriteFinder.findBack(name);
		backSprite = backSprite.getScaledInstance(backSprite.getWidth(null) * 2, backSprite.getHeight(null) * 2,
				Image.SCALE_DEFAULT);

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
	 * Check if the pokemon wants to learn a new move and act accordingly to how
	 * many moves he knows
	 */
	private void checkNewMove() {

		TreeMap<Integer, String> allMoves = FileRw.getLvlUpMoves(getName());

		TreeMap<Integer, String> newMoves = new TreeMap<>();

		for (Map.Entry<Integer, String> e : allMoves.entrySet()) {

			if (e.getKey() <= getLevel() && !pastMoves.contains(e.getValue()))
				newMoves.put(e.getKey(), e.getValue());

		}

		if (newMoves.size() > 0) {

			if (getMoves()[2] == null)
				changeThirdMove(new Move(newMoves.get(newMoves.firstKey())));

			else if (getMoves()[3] == null)
				changeFourthMove(new Move(newMoves.get(newMoves.firstKey())));

			else
				new MoveChange(this, newMoves.get(newMoves.firstKey()));
		}

	}

	/**
	 * Change the first move
	 * 
	 * @param move the new move
	 */
	public void changeFirstMove(Move move) {

		moves[0] = move;
		lastMoveChanged = 1;
		addInPastMoves(move.getName());
	}

	/**
	 * Change the second move
	 * 
	 * @param move the new move
	 */
	public void changeSecondMove(Move move) {

		moves[1] = move;
		lastMoveChanged = 2;
		addInPastMoves(move.getName());

	}

	/**
	 * Change the third move
	 * 
	 * @param move the new move
	 */
	public void changeThirdMove(Move move) {

		moves[2] = move;
		lastMoveChanged = 3;
		addInPastMoves(move.getName());

	}

	/**
	 * Change the fourth move
	 * 
	 * @param move the new move
	 */
	public void changeFourthMove(Move move) {

		moves[3] = move;
		lastMoveChanged = 4;
		addInPastMoves(move.getName());

	}

	/**
	 * Add a move to those known by the pokemon
	 * 
	 * @param name the name of the move
	 */
	public void addInPastMoves(String name) {
		pastMoves.add(name.replace(" ", "_"));
	}

	// Getter

	/**
	 * {@return the sprite of the pokemon}
	 */
	public Image getBackSprite() {
		return backSprite;
	}

	/**
	 * {@return the number representing the last move that changed, 1,2,3,4 or 0 if
	 * none}
	 */
	public int getlastMoveChanged() {
		return lastMoveChanged;
	}

	// Setter

	/**
	 * Set lastMoveChanged to 0
	 */
	public void resetlastMoveChanged() {
		lastMoveChanged = 0;
	}

}
