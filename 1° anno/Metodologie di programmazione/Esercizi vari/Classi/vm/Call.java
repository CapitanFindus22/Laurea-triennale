package Classi.vm;

final public class Call extends Istruzione{

	private Procedura p;
	
	public Call(Procedura p) {
		
		this.p = p;
		
	}
	
	@Override
	public void esegui() {
		
		for(Istruzione l : p.getList()) {
			
			l.esegui();
			
		}
	}
}