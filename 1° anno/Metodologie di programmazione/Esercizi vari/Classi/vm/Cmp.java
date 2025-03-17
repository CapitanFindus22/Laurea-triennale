package Classi.vm;

final public class Cmp extends Istruzione{

	private Registro r; 
	private Operando o;
	
	public Cmp(Registro r, Operando o) {
		
		this.r = r;
		this.o = o;
	}
	
	@Override
	public void esegui() {
		
		if(r.getValue() == o.getValue()) {
			
			r.setValue(0);
			
		}
		
		else if(r.getValue() > o.getValue()) {
			
			r.setValue(-1);
			
		}
		
		else {
			
			r.setValue(1);
			
		}
		
	}
	
}
