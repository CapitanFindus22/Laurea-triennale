package Classi.game;

public class Alien extends Enemy{

	public Alien(int x, int y) {
		
		super(x,y);
		
	}

	public void nextStep() {

		x--;
		
	}

	@Override
	public String toString() {
		return "A";
	}
	
}
