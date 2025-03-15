package Classi.vm;

final public class Mov extends Istruzione{

	private Registro r; 
	private Operando o;
	
	public Mov(Registro r, Operando o) {
		
		this.r = r;
		this.o = o;
	}
	
	@Override
	public void esegui() {
		
		r.setValue(o.getValue());
		
	}
	
}
