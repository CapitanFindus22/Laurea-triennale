package Classi.vm;

public class MyVirtualMachine {
	
	public void esegui(Procedura proc) {
		
		for(Istruzione p : proc.getList()) {
			
			p.esegui();
			
		}
		
	}
	
}
