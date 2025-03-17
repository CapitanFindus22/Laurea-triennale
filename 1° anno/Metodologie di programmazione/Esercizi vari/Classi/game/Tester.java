package Classi.game;

public class Tester {

	public static void main(String[] args) {

		Enemy[] e = new Enemy[4];
		
		e[0] = new Alien(7,1);
		e[1] = new Alien(5,4);
		e[2] = new Meteor(8,0);
		e[3] = new Meteor(3,3);
		
		Sparatutto s = new Sparatutto(0,2,e);
		
		System.out.println(s.toString());
		s.nextStep();
		System.out.println("---------------------------------------");
		System.out.println(s.toString());
		s.nextStep();
		System.out.println("---------------------------------------");
		System.out.println(s.toString());
		s.nextStep();
		System.out.println("---------------------------------------");
		System.out.println(s.toString());
		s.nextStep();
		System.out.println("---------------------------------------");
		System.out.println(s.toString());
		s.nextStep();

	}

}
