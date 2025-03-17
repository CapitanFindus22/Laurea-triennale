package Stream;

import java.util.Map;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.*;

public class Main {

	public static void main(String[] args) {

		LinkedList<String> l = new LinkedList<>();
		
		l.add(".");
		l.add("fdsgd\t");
		l.add(" ");
		l.add("323re");
		l.add("323");
		l.add("");
		l.add("r32");
		l.add("sss");
		l.add("a");
	
		LinkedList<Integer> l1 = new LinkedList<>();
		
		l1.add(1);
		l1.add(10);
		l1.add(3);
		
		//Insieme delle prime 3 stringhe
		HashSet<String> h = l.stream().filter(x -> x.length() >= 4).limit(3).collect(Collectors.toCollection(HashSet::new));
		
		//Insieme delle lunghezze delle stringhe
		HashSet<Integer> len = l.stream().map(x -> x.length()).collect(Collectors.toCollection(HashSet::new));
		
		//Come sopra ma in ordine
		TreeSet<Integer> len_s = l.stream().map(x -> x.length()).sorted().collect(Collectors.toCollection(TreeSet::new));
		
		//Mappa stringa->lunghezza
		Map<Integer,List<String>> map = l.stream().collect(Collectors.groupingBy(x -> x.length()));
		
		//Somma dei valori in l1
		int sum = l1.stream().mapToInt(Integer::valueOf).sum();
		
		//Stringa con i quadrati di l1
		String boh = l1.stream().filter(x -> x>=3).map(x -> String.valueOf(x*x)).collect(Collectors.joining(","));
		
		//Stringa con i quadrati di l1
		System.out.print(l1.stream().filter(x -> x>=3).map(x -> String.valueOf(x*x)).collect(Collectors.counting()));
		
		Stream.of(h).forEach(System.out::println);
		System.out.println(Arrays.asList(1,2,3,4,5,6,-4).parallelStream().filter(x -> x >= 0).sorted(Comparator.reverseOrder()).findFirst().orElse(100000));
		
		return;
		
	}

}
