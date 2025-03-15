package Classi.filter;

import java.util.LinkedList;

public final class FiltroDispari extends Filtro {
	
	public FiltroDispari() {
		super();
	}
	
	public FiltroDispari(LinkedList<Integer> values) {
		super(values);
	}
	
	@Override
	public LinkedList<Integer> filtra() {

		LinkedList<Integer> result = new LinkedList<>();
		
		boolean odd = true;
		
		for(int v: values) {
			
			if(!odd) result.add(v);
			odd = !odd;
			
		}
		
		return result;
		
	}

}
