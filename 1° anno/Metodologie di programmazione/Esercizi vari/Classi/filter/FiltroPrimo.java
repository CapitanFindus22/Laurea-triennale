package Classi.filter;

import java.util.LinkedList;

public final class FiltroPrimo extends Filtro {
	
	public FiltroPrimo() {
		super();
	}
	
	public FiltroPrimo(LinkedList<Integer> values) {
		super(values);
	}
	
	@Override
	public LinkedList<Integer> filtra() {

		Integer toRemove = values.getFirst();
		
		while(values.contains(toRemove)) {
			
			values.remove(toRemove);
			
		}
		
		return values;
		
	}

}