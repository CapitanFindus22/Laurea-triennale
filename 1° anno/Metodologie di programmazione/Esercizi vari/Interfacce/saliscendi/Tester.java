package Interfacce.saliscendi;

import java.util.Optional;

public class Tester {

	public static void main(String[] args) {

		Ascensore a = new Ascensore();
		
		a.sali();
		
		AstaBandiera b = new AstaBandiera();

		b.sali();
		
		Optional<Bandiera> c = b.getBandiera();
		
		System.out.println(a.getPiano());
		
		if(c.isPresent()) System.out.println(c.get().getNome());
		
	}

}
