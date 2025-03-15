package Classi.game;

public class Meteor extends Enemy{

	public Meteor(int x, int y) {
		
		super(x,y);
		
	}
	
	public void nextStep() {

		y++;
		x--;
		
	}

	@Override
	public String toString() {
		return "M";
	}

}
