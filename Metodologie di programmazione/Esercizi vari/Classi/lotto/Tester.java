package Classi.lotto;

public class Tester {

	public static void main(String[] args) {

		Estrazioni e = new Estrazioni();
		
		System.out.println(e.numeriContenuti(new int[] {1,2,3,4,5,6,7,8,9,0}));
		System.out.println(e.vincita(new int[] {1,2,3,4,5,6,7,8,9,0}));
		System.out.println(e.toString());
		
		e.estrai();
		System.out.println(e.numeriContenuti(new int[] {1,2,3,4,5,6,7,8,9,0}));
		System.out.println(e.vincita(new int[] {1,2,3,4,5,6,7,8,9,0}));
		System.out.println(e.toString());
		
		e.estrai();
		System.out.println(e.numeriContenuti(new int[] {1,2,3,4,5,6,7,8,9,0}));
		System.out.println(e.vincita(new int[] {1,2,3,4,5,6,7,8,9,0}));
		System.out.println(e.toString());
		
		e.estrai();
		System.out.println(e.numeriContenuti(new int[] {1,2,3,4,5,6,7,8,9,0}));
		System.out.println(e.vincita(new int[] {1,2,3,4,5,6,7,8,9,0}));
		System.out.println(e.toString());
		
		e.estrai();
		System.out.println(e.numeriContenuti(new int[] {1,2,3,4,5,6,7,8,9,0}));
		System.out.println(e.vincita(new int[] {1,2,3,4,5,6,7,8,9,0}));
		System.out.println(e.toString());
		
		e.estrai();
		System.out.println(e.numeriContenuti(new int[] {1,2,3,4,5,6,7,8,9,0}));
		System.out.println(e.vincita(new int[] {1,2,3,4,5,6,7,8,9,0}));
		System.out.println(e.toString());
		
		System.out.println(e.getTotaleNumeriEstratti());

	}

}
