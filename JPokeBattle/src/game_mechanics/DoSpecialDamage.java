package game_mechanics;

public class DoSpecialDamage extends MoveFunction {

	@Override
	void esegui(Pokemon p1, Pokemon p2, Move m) {

		p2.getStats().setHp((int)(p2.getStats().getHp()- 
				
				
				(((((2 * p1.getLevel() / 5) + 2) * m.getPower() * 
				(p1.getStats().getSp_att()/p2.getStats().getSp_dif())) /50) + 2) *
				(((p1.getType()==m.getType())||(p1.getType2()==m.getType()))?1.5:1) * Move.TYPES_ADVANTAGE[m.getType().toInt()][p2.getType().toInt()] /
				(r.nextInt(217, 256)) / 255));
	
		
		
	}
	
}
