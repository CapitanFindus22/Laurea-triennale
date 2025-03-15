package Classi.filter;

public enum FilterTypes {

	Primo(1),Intero(2),Dispari(3);
	
	private int val;
	
	FilterTypes(int val) {this.val = val;}
	public int toInt() {return val;}
	
}
