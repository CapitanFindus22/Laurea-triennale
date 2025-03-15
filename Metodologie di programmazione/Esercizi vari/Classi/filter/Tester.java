package Classi.filter;

import java.util.Arrays;
import java.util.LinkedList;

public class Tester {

	public static void main(String[] args) {
		
		LinkedList<Integer> l = new FiltroIntero(new LinkedList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,7,77)),7).filtra();
		
		System.out.println(l.toString());
		
		LinkedList<Integer> l1 = new FiltroPrimo(new LinkedList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,1,77))).filtra();
		
		System.out.println(l1.toString());
		
		LinkedList<Integer> l2 = new FiltroDispari(new LinkedList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,1,77))).filtra();
		
		System.out.println(l2.toString());
		
		LinkedList<Integer> l3 = new MultiFiltro(new LinkedList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,1,77)), 
												new FilterTypes[] {FilterTypes.Primo,FilterTypes.Dispari})
												.filtra();
		
		System.out.println(l3.toString());
		
		LinkedList<Integer> l4 = new MultiFiltro(new LinkedList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,1,77)), 
				new FilterTypes[] {FilterTypes.Primo,FilterTypes.Intero}, 77)
				.filtra();

		System.out.println(l4.toString());
		
	}
	
}
