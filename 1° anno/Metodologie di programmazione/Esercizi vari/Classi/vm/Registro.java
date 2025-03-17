package Classi.vm;

final public class Registro extends Operando {

	private String name;
	private int value;
	
	public Registro(String name) {
		
		this.name = name;
		
	}
	
	public void setValue(int value) {
		
		this.value = value;
		
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
}
