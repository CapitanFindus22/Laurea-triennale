package Interfacce.saliscendi;

import java.util.Optional;

public class AstaBandiera implements SaliScendi{

	boolean dispiegata;

	@Override
	public void sali() {

		dispiegata = true;
		
	}

	@Override
	public void scendi() {

		dispiegata = false;
		
	}
	
	public Optional<Bandiera> getBandiera() {
		
		return Optional.ofNullable((dispiegata)?new Bandiera("a"):null);
		
	}
	
}
