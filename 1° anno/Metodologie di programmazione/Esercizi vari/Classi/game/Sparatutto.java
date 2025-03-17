package Classi.game;

import java.util.Arrays;
import java.util.Random;

public class Sparatutto {

	Player p;
	Enemy[] e;
	Star[] s;
	char[][] table;
	
	public Sparatutto(double x, double y, Enemy[] e) {
		
		table = new char[5][10];
		
		s = new Star[3];
		
		for(char[] t: table) {
			
			Arrays.fill(t, ' ');
			
		}
		
		Random r = new Random();
		
		s[0] = new Star(r.nextInt(10), r.nextInt(5));
		s[1] = new Star(r.nextInt(10), r.nextInt(5));
		s[2] = new Star(r.nextInt(10), r.nextInt(5));
		
		for(Star en: s) {
			
			table[en.y][en.x] = en.toString().charAt(0);
			
		}
		
		p = new Player(x,y);
		this.e = e;
		
		for(Enemy en: e) {
			
			table[en.y][en.x] = en.toString().charAt(0);
			
		}
		
	}
	
	public void nextStep() {
		
		for(Enemy x: e) {
			
			if((x instanceof Alien && x.x > 0)||(x instanceof Meteor && (x.y <4 && x.x > 0))) x.nextStep();
			
		}
		
		for(char[] t: table) {
			
			Arrays.fill(t, ' ');
			
		}
		
		for(Enemy en: e) {
			
			table[en.y][en.x] = en.toString().charAt(0);
			
		}
		
		for(Star en: s) {
			
			table[en.y][en.x] = en.toString().charAt(0);
			
		}
		
	}
	
	@Override
	public String toString() {
		
		String result = "";
		
		for(int i=0;i<5;i++) {
			
			
			for(int j=0;j<10;j++) {
				
				result += table[i][j];
				
			}
			
			result += "\n";
		}
		
		return result;
		
	}
	
}
