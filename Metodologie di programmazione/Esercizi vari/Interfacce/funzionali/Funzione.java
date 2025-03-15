package Interfacce.funzionali;

import java.util.List;
import java.util.Set;

@FunctionalInterface
public interface Funzione{

	public abstract Set<Integer> applica(List<Integer> list, int k);
	
}
