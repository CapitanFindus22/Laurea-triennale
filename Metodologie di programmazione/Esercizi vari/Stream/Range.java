package Stream;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.*;

public class Range {

	public static void main(String[] args) {

		//Stampa 10000 numeri
		IntStream.rangeClosed(0, 10000).forEach(System.out::println);
		
		//Insieme dei valori da 10 a 100
		Set<Integer> t = IntStream.rangeClosed(10,100).boxed().collect(Collectors.toSet());
		
		//Lista dei numeri pari da 100 a 10
		List<Integer> l = IntStream.rangeClosed(10,100).boxed().filter(x -> x%2==0).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		
		return;
		
	}

}
