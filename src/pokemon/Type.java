package pokemon;

/**
 * All the pokemon and move types in the first gen
 */
public enum Type {

	/**
	 * Normal
	 */
	Normale(0),

	/**
	 * Fire
	 */
	Fuoco(1),

	/**
	 * Water
	 */
	Acqua(2),

	/**
	 * Electric
	 */
	Elettro(3),

	/**
	 * Grass
	 */
	Erba(4),

	/**
	 * Ice
	 */
	Ghiaccio(5),

	/**
	 * Fighting
	 */
	Lotta(6),

	/**
	 * Poison
	 */
	Veleno(7),

	/**
	 * Ground
	 */
	Terra(8),

	/**
	 * Flying
	 */
	Volante(9),

	/**
	 * Psychic
	 */
	Psico(10),

	/**
	 * Bug
	 */
	Coleottero(11),

	/**
	 * Rock
	 */
	Roccia(12),

	/**
	 * Ghost
	 */
	Spettro(13),

	/**
	 * Dragon
	 */
	Drago(14);

	/**
	 * Integer value of a constant
	 */
	private int val;

	/**
	 * Constructor
	 * 
	 * @param val the value to assign to the constant
	 */
	Type(int val) {
		this.val = val;
	}

	/**
	 * {@return the integer value of the constant}
	 */
	public int toInt() {
		return val;
	}

}
