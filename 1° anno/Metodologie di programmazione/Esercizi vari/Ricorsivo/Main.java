package Ricorsivo;

import java.util.Arrays;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) {

		System.out.println(sommaFinoA(5));
		System.out.println(min(new int[] {5,1,4,8,6,2,0,5,1,2,-3}));
		System.out.println(inverti("Roma"));
		System.out.println(cambiaBase(12,8));
		System.out.println(genera(new HashSet<Character>(Arrays.asList('a', 'b', 'c')), 2));
		
		Punto p = new Punto(0,0);
		
		System.out.println(p.contaPercorsi(new Punto(2,2)));
		
		espandi("10*1*");
		
		hamming("0000",2);

	}

	public static int sommaFinoA(int limit) {
		
		if(limit == 1) return 1;
		
		return sommaFinoA(limit-1) + limit;
		
	}
	
	public static int min(int[] arr) {
		
		if(arr.length == 1) return arr[0];
		
		return Math.min(arr[0], min(Arrays.copyOfRange(arr, 1, arr.length)));
		
	}
	
	public static String inverti(String s) {
		
		if(s.length() == 1) return s;
		
		return inverti(s.substring(1)) + s.charAt(0);
		
	}
	
	public static String cambiaBase(int num, int base) {
		
		if(num == 0) return "";
		
		return cambiaBase((int)num/base,base) + (int)num%base;
		
	}
	
	public static HashSet<String> genera(HashSet<Character> set, int limite) {
		
		HashSet<String> h = new HashSet<>();
		
		if(limite == 0) { 
		
			h.add("");
			return h;
			
		}
		
		for(char v : set) {
			
			genera(set,limite-1).forEach(x -> h.add(v+x));
			
		}
		
		return h;
		
	}

	public static void espandi(String s) {
		
		if(s.contains("*")) {
			
			String s0 = new String("");
			String s1 = new String("");
			
			s0 += s;
			s1 += s;
			
			espandi(s0.replaceFirst("\\*", "0"));
			espandi(s1.replaceFirst("\\*", "1"));
		}
		
		else {
			
			System.out.println(s);
			
		}
	}

	public static void hamming(String s, int distance) {
		
			String s0 = new String("");
			String s1 = new String("");
			
			s0 += '0';
			s1 += '1';
			
			hamming(s,s0,distance);
			hamming(s,s1,distance);
			
	}

	private static void hamming(String s, String sub, int distance) {
		
		if(s.length() > sub.length()) {
			
			String s0 = new String("");
			String s1 = new String("");
			
			s0 += sub + '0';
			s1 += sub + '1';
			
			hamming(s,s0,distance);
			hamming(s,s1,distance);
			
		}
		
		else {
			
			if(checkDistance(s,sub,distance)) System.out.println(sub);
			
		}
	}
	
	private static boolean checkDistance(String s, String sub, int distance) {
		
		int d = 0;
		
		for(int i=0;i<s.length();i++) {
			
			if(s.charAt(i) != sub.charAt(i)) d++;
			
		}
			
			
		return d == distance;
		
	}
	
}