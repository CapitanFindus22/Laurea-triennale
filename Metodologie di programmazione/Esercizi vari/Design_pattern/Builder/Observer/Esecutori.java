package Design_pattern.Builder.Observer;

public class Esecutori implements Subs{

	private int id;
	
	public Esecutori(int val) {
		
		id = val;
		
	}
	
	public void exe() {
		
		System.out.println(id);
		
	}
	
}
