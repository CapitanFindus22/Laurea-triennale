package Interfacce.funzionali;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.*;
import java.util.stream.Collectors;

public class Funzionali {

	public static void main(String[] args) {

		HashSet<Integer> h = new HashSet<>();
		
		h.add(1);
		h.add(2);
		h.add(3);
		h.add(4);
		h.add(5);
		h.add(6);
		h.add(7);
		h.add(8);
		
		HashSet<Integer> h1 = new HashSet<>();
		
		LinkedList<Integer> l = new LinkedList<>();
		
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		l.add(6);
		l.add(7);
		
		//Interfacce standard
		
		//Stampa
		Consumer<Object> printer = x -> System.out.println(x);
		
		//Ritorna il quadrato
		IntFunction<Integer> square = x -> x*x;
		
		//Controlla la lunghezza
		BiPredicate<String,Integer> b = (s,len) -> s.length()==len;
		
		//Interfacce sviluppate
		
		//Ritorna stringa di max 5 caratteri
		ElaboraStringhe s = x -> x.substring(0, (x.length()>5)?5:x.length());
		
		//Trasforma in stringa
		ElaboraStringhe sl = x -> ""+x.length();
		
		//Somma gli elementi minori di k
		FunzioneSuInsieme f = (x,k) -> x.stream().mapToInt(Integer::intValue).filter(c -> c <= k).sum();
		
		//Trova il minimo
		FunzioneSuInsieme min = (x,k) -> x.stream().mapToInt(Integer::intValue).min().orElseThrow();
		
		//Ritorna un insieme con i primi k elementi
		Funzione funz = (x,k) -> x.stream().limit(k).collect(Collectors.toSet());
		
		FunzioneStringaIntero fsi = (x,k) -> x.repeat(k);
		
		Funz fu = x -> x+1;
		
		Funz fu2 = new Funz() {

			@Override
			public double esegui(double x) {
				// TODO Auto-generated method stub
				return x+1+1;
			}
			
		};
		
		printer.accept(2);
		
		printer.accept(square.apply(2));
		
		printer.accept(b.test("ciao", 3));
		
		printer.accept(s.elabora("tre"));
		printer.accept(s.elabora("ciaooooooooooooooo"));
		
		printer.accept(sl.elabora("ciaooooooooooooooo"));
		
		printer.accept(f.applica(h, 5));
		
		try {printer.accept(min.applica(h1, 5));}
		
		catch(NoSuchElementException e) {printer.accept("Insieme vuoto");}
		
		printer.accept(funz.applica(l, 3));
		printer.accept(funz.applica(l, 110));
		printer.accept(funz.applica(l, l.size()));
		
		printer.accept(fsi.applica("culo ", 3));
		
		printer.accept(fu.esegui(1));		
		printer.accept(fu2.esegui(1));	
	}

}
