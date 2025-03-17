package Classi.vm;

final public class Intero extends Operando {

	private int value;
	
	public Intero(int value) {
		
		this.value = value;		
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
}
