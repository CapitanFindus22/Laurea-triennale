package Classi.filter;

import java.util.LinkedList;

public class MultiFiltro extends Filtro {

	private FilterTypes[] sequence;
	private int toRemove;
	
	public MultiFiltro(FilterTypes[] s) {
		super();
		sequence = s;
	}
	
	public MultiFiltro(LinkedList<Integer> values, FilterTypes[] s) {
		super(values);
		sequence = s;
	}
	
	public MultiFiltro(LinkedList<Integer> values, FilterTypes[] s, int v) {
		super(values);
		sequence = s;
		toRemove = v;
	}
	
	public void setToRemove(int v) {
		toRemove = v;
	}
	
	@Override
	public LinkedList<Integer> filtra() {

		for(FilterTypes type: sequence) {
			
			switch(type) {
			
			case Primo:
				
				values = new FiltroPrimo(values).filtra();
				
			break;
			
			case Intero:
				
				values = new FiltroIntero(values,toRemove).filtra();
				
			break;
			
			case Dispari:
				
				values = new FiltroDispari(values).filtra();
				
			break;
			
			default:
				break;
			
			}
			
		}
	
		return values;
		
	}
}
