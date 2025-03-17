package Design_pattern.Builder.Strategy;

import java.util.Arrays;

public class Ordinamento implements TipoOrd{

	private TipoOrd t;
	
	public void setOrd(TipoOrd t) {
		
		this.t = t;
		
	}

	@Override
	public void ordina(int[] t) {

		this.t.ordina(t);
		
	}
	
	public static void main(String[] args) {
		
		Ordinamento o = new Ordinamento();
		
		o.setOrd(new BubbleSort());
		
		int[] arr = new int [] {9,8,7,6,5,4,3,2,1,0};
		
		o.ordina(arr);
		
		Arrays.stream(arr).forEach(System.out::println);
		
	}
	
}
