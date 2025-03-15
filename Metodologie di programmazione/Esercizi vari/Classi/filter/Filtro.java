package Classi.filter;

import java.util.LinkedList;

public abstract class Filtro {
	
	protected LinkedList<Integer> values;

	public Filtro() {}
	public Filtro(LinkedList<Integer> values) {
		this.values = values;
	}
	
	public abstract LinkedList<Integer> filtra();
	
	public void setNewValues(LinkedList<Integer> values) {
		
		this.values = values;
		
	}
	
}
