package Interfacce.saliscendi;

public class Ascensore implements SaliScendi{

	private int piano;

	@Override
	public void sali() {

		piano++;
		
	}

	@Override
	public void scendi() {

		piano--;
		
	}
	
	public int getPiano() {
		
		return piano;
		
	}
	
}
