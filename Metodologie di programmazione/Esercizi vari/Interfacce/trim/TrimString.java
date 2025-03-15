package Interfacce.trim;

public class TrimString implements Trimmable<String>{

	private String s;
	
	
	public TrimString(String string) {
		
		s = string;
		
	}


	@Override
	public String trim() {

		return s.trim();
		
	}
}
