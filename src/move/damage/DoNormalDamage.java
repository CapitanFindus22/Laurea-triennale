package move.damage;

import move.Move;
import pokemon.Pokemon;

/**
 * For the normal attacks
 */
public class DoNormalDamage implements Damage {

	/**
	 * Calculate the damage dealt from p1 to p2 with the first gen formula
	 */
	@Override
	public void esegui(Pokemon p1, Pokemon p2, Move m) {

		float damage = p1.getLevel() * 2;

		if (RNG.nextInt(256) < Math.min(p1.getStats().getSpeed(), 255))
			damage *= 2;

		damage += 2;

		damage *= m.getPower();

		damage *= p1.getStats().getAtt();

		damage /= p2.getStats().getDif();

		damage /= 50;

		damage += 2;

		if (m.getType() == p1.getType() || m.getType() == p1.getType2().orElse(null))
			damage *= 1.5;

		damage *= Move.TYPES_ADVANTAGE[m.getType().toInt()][p2.getType().toInt()];

		if (p2.getType2().isPresent())
			damage *= Move.TYPES_ADVANTAGE[m.getType().toInt()][p2.getType2().orElseThrow().toInt()];

		if (damage > 1) {

			damage *= (RNG.nextInt(217, 256));
			damage /= 255;
		}

		p2.getStats().setHp((int) (p2.getStats().getHp() - Math.max(1, damage)));

	}

}
