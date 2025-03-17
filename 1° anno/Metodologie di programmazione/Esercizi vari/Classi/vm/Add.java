package Classi.vm;

final public class Add extends Istruzione{

	private Registro r; 
	private Operando o;
	
	public Add(Registro r, Operando o) {
		
		this.r = r;
		this.o = o;
	}
	
	@Override
	public void esegui() {
		
		r.setValue(r.getValue()+o.getValue());
		
	}
	
}
