package Classi.lotto;

import java.util.LinkedList;
import java.util.Random;

public class Estrazioni {

	LinkedList<Integer> l;
	Random r;
	
	public Estrazioni() {
		
		l = new LinkedList<>();
		r = new Random();
		
		estrai();
		
	}
	
	public void estrai() {
		
		for(short i=0;i<5;i++) {
			
			l.add(r.nextInt(100));
			
		}
		
	}
	
	public short numeriContenuti(int[] values) {
		
		short con = 0;
		
		for(int v: values) {
			
			if(l.subList(l.size()-5, l.size()).contains(v)) {
				
				++con;
				
			}
			
		}
		
		return con;
		
	}
	
	public boolean vincita(int[] values) {
		
		short con = numeriContenuti(values);
		
		return (con>=2)?true:false;
		
		
	}
	
	public long getTotaleNumeriEstratti() {
		
		long sum = 0;
		
		for(int v: l) {
			
			sum += v;
			
		}
		
		return sum;
		
	}
	
	@Override
	public String toString() {
		
		String total = new String(""+l.getFirst());
		
		for(int v: l.subList(1,l.size())) {
			
			total += "," + v;
			
		}
		
		return total;
		
	}
	
}
