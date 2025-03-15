package Stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Ristorante {

	private String nome;
	private TipoRistorante tipo;
	private int coperti;
	public Ristorante(String nome, TipoRistorante tipo, int coperti)
	{
	this.nome = nome; this.coperti = coperti; this.tipo = tipo;
	}
	public String getNome() { return nome; }
	public TipoRistorante getTipo() { return tipo; }

	public int getCoperti() { return coperti; }
	@Override
	public String toString() { return nome+":"+tipo+":"+coperti; }
	
	public static void main(String[] args) {

		List<Ristorante> risto = Arrays.asList(
				new Ristorante("La pergola", TipoRistorante.RISTO, 55),
				new Ristorante("L’etico", TipoRistorante.PIZZERIA, 25),
				new Ristorante("Da Rossi", TipoRistorante.RISTO, 47),
				new Ristorante("Da Gigi", TipoRistorante.PIZZERIA, 42),
				new Ristorante("Giggetto", TipoRistorante.PIZZERIA, 80),
				new Ristorante("Da Ivo", TipoRistorante.PIZZERIA, 150),
				new Ristorante("Romolo e Luigi", TipoRistorante.PIZZERIA, 50),
				new Ristorante("La terrazza", TipoRistorante.RISTO, 40));
				
		risto.stream().sorted(Comparator.comparing(Ristorante::getCoperti).reversed()).forEach(System.out::println);

		//Insieme dei ristoranti con più di 45 coperti
		Set<Ristorante> h = risto.stream().filter(x -> x.getCoperti()>=45).collect(Collectors.toSet());
		
		//Ristoranti divisi per tipo
		Map<TipoRistorante,List<Ristorante>> boh = risto.stream().sorted(Comparator.comparing(Ristorante::getCoperti)).collect(Collectors.groupingBy(Ristorante::getTipo));
			
		Map<Boolean,List<Ristorante>> map = risto.stream().collect(Collectors.partitioningBy(x -> x.getTipo() == TipoRistorante.PIZZERIA));
		
		//Stampa tutti i nomi dei ristoranti
		System.out.println(risto.stream().sorted(Comparator.comparing(Ristorante::getNome)).map(x -> x.getNome()).collect(Collectors.joining(",")));
		
		return;
		
	}

}
