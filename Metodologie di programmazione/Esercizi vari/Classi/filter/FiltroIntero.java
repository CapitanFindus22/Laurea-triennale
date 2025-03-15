package Classi.filter;

import java.util.LinkedList;

public final class FiltroIntero extends Filtro {

	private Integer toRemove;
	
	public FiltroIntero(int toRemove) {
		super();
		this.toRemove = toRemove;
	}
	
	public FiltroIntero(LinkedList<Integer> values, int toRemove) {
		super(values);
		this.toRemove = toRemove;
	}
	
	@Override
	public LinkedList<Integer> filtra() {

		while(values.contains(toRemove)) {
			
			values.remove(toRemove);
			
		}
		
		return values;
		
	}

}
