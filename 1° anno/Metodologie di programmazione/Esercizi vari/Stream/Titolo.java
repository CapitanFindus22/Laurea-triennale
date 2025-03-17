package Stream;

import java.awt.datatransfer.StringSelection;
import java.lang.classfile.components.ClassPrinter.Verbosity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Titolo {
	
	public enum Allineamento { CX, SX, DX }
	
	private Allineamento allineamento;
	private int asd;
	private List<Riga> righe;
	
	public Titolo(Allineamento a) { this(a, new ArrayList<>()); asd = 0;}
	
	public Titolo(Allineamento a, List<Riga> righe)
	{
	allineamento = a; this.righe = righe; asd = 1;
	}
	
	public void add(Riga r) { righe.add(r); }
	
	public boolean isCentered() { return allineamento == Allineamento.CX; }
	
	@Override public String toString() { return righe.toString(); }
	
	public Allineamento getAllineamento() { return allineamento; }
	
	public int getAsd() { return asd; }
	
	public List<Riga> getRighe() { return new ArrayList<>(righe); }
	
	static public class Riga
	{
		
		private String riga;
		private int numero;
		
		public Riga(String riga, int numero) { this.riga = riga; this.numero = numero; }
		
		public Riga(String riga) { this(riga, -1); }
		
		@Override public String toString() { return (numero == -1 ? "" : numero+": ")+riga; }
		
	}
	
	public static void main(String[] args) {

		List<Titolo> tit = new ArrayList<>();
		
		tit.add(new Titolo(Allineamento.CX,new ArrayList<Riga>(Arrays.asList(
				
				new Riga("qfegegfdg"),
				new Riga("qfegegfdg"),
				new Riga("qfegegfdg")))));

		tit.add(new Titolo(Allineamento.CX,new ArrayList<Riga>(Arrays.asList(
				
				new Riga(" "),
				new Riga(" "),
				new Riga(" ")))));
		
		tit.add(new Titolo(Allineamento.DX,new ArrayList<Riga>(Arrays.asList(
				
				new Riga("214"),
				new Riga("34543"),
				new Riga("5")))));
		
		tit.add(new Titolo(Allineamento.SX,new ArrayList<Riga>(Arrays.asList(
				
				new Riga("tnt"),
				new Riga("qfengf gegfdg"),
				new Riga("reger")))));
		
		tit.add(new Titolo(Allineamento.DX,new ArrayList<Riga>(Arrays.asList(
				
				new Riga(""),
				new Riga(""),
				new Riga("")))));
		
		
		tit.stream().filter(Titolo::isCentered).forEach(System.out::println);
		
		//Divisi per allineamento
		Map<Allineamento,List<Titolo>> m = tit.stream().collect(Collectors.groupingBy(Titolo::getAllineamento));
		
		//Come sopra ma insieme
		Map<Allineamento,Set<Titolo>> ms = tit.stream().collect(Collectors.groupingBy(Titolo::getAllineamento, Collectors.toSet()));
		
		//Come sopra ma stringhe
		Map<Allineamento,String> mstr = tit.stream().collect(Collectors.groupingBy(Titolo::getAllineamento, Collectors.flatMapping(x -> x.getRighe().stream().map(Riga::toString), Collectors.joining())));
		
		//Allineamento -> somma asd
		Map<Allineamento,Integer> mstf43r = tit.stream().collect(Collectors.groupingBy(Titolo::getAllineamento, Collectors.mapping(x -> x.getAsd(), Collectors.summingInt(Integer::valueOf))));
		
		//Allineamento -> lista nomi
		Map<Allineamento,List<String>> mst = tit.stream().collect(Collectors.groupingBy(Titolo::getAllineamento, Collectors.flatMapping(x -> x.getRighe().stream().map(Riga::toString), Collectors.toList())));
		
		return;
		
	}

}
