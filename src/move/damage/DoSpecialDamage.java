package move.damage;

import java.util.Random;

import move.Move;
import pokemon.Pokemon;

/**
 * For the special attacks
 */
public class DoSpecialDamage implements Damage {

	private final static Random r = new Random();
	
	/**
	 * Calculate the damage dealt from p1 to p2 with the first gen formula
	 */
	@Override
	public void esegui(Pokemon p1, Pokemon p2, Move m) {

		double damage = p1.getLevel()*2;

		if(r.nextInt(256) < Math.min(p1.getStats().getVel(),255)) damage *= 2;
		
		damage += 2;
		
		damage *= m.getPower();
		
		damage *= p1.getStats().getSpAtt();
		
		damage /= p2.getStats().getSpDif();
		
		damage /= 50;
		
		damage += 2;
		
		if(m.getType()==p1.getType() || m.getType()==p1.getType2()) damage *= 1.5;

		damage *= Move.TYPES_ADVANTAGE[m.getType().toInt()][p2.getType().toInt()];
		
		if(p2.getType2()!=null) damage *= Move.TYPES_ADVANTAGE[m.getType().toInt()][p2.getType2().toInt()];
		
		if(damage>1) {
			
			damage *= (r.nextInt(217, 256));
			damage /= 255;
		}
		
		p2.getStats().setHp((int) (p2.getStats().getHp()-Math.max(1,damage)));
		
	}
	
}
