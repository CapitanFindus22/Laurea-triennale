package pokemon;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import i_o.FileRw;
import i_o.SpriteFinder;
import move.Move;

/**
 * The pokémon used by the enemy
 */
public class EnemyPokemon extends Pokemon {

	/**
	 * The sprite of the pokémon
	 */
	private Image frontSprite;

	/**
	 * Create a level 1 enemy pokémon
	 * 
	 * @param name the name of the pokémon
	 */
	public EnemyPokemon(String name) {

		super(name);
		frontSprite = SpriteFinder.findFront(name);
		frontSprite = frontSprite.getScaledInstance(frontSprite.getWidth(null) * 3, frontSprite.getHeight(null) * 3,
				Image.SCALE_DEFAULT);

	}

	/**
	 * Create an enemy pokémon of the specified level
	 * 
	 * @param name  the name of the pokémon
	 * @param level the level of the pokémon
	 */
	public EnemyPokemon(String name, int level) {

		super(name, level);
		chooseMoves();
		frontSprite = SpriteFinder.findFront(name);
		frontSprite = frontSprite.getScaledInstance(frontSprite.getWidth(null) * 3, frontSprite.getHeight(null) * 3,
				Image.SCALE_DEFAULT);

	}

	/**
	 * Choose random moves for the pokémon according to its level and the available
	 * moves for that level
	 */
	private void chooseMoves() {

		// Get all the moves
		TreeMap<Integer, String> allMoves = FileRw.getLvlUpMoves(getName());

		allMoves.put(0, getMoves()[0].getName());
		allMoves.put(1, getMoves()[1].getName());

		ArrayList<String> available = new ArrayList<>();

		// Filter
		for (Map.Entry<Integer, String> e : allMoves.entrySet())
			if (e.getKey() <= getLevel())
				available.add(e.getValue());

		Collections.shuffle(available);

		// Take up to 4 moves from those available

		getMoves()[0] = new Move(available.getFirst());
		available.removeFirst();

		getMoves()[1] = new Move(available.getFirst());
		available.removeFirst();

		if (available.size() > 0) {

			getMoves()[2] = new Move(available.getFirst());
			available.removeFirst();

		}

		if (available.size() > 0) {

			getMoves()[3] = new Move(available.getFirst());
			available.removeFirst();

		}

	}

	// Getter

	/**
	 * {@return the sprite of the pokémon}
	 */
	public Image getFrontSprite() {
		return frontSprite;
	}

}
