package Design_pattern.Builder;

import java.util.function.Function;

public class StringBuilder {

	private String base;
	
	public StringBuilder(String s) {
		
		base = s;
		
	}
	
	public StringBuilder perform(Function<String,String> f) {
		
		base = f.apply(base);
		return this;
		
	}
	
	public String build() {
		
		return base;
		
	}
	
	public static void main(String[] args) {
		
		String s = new StringBuilder("ciao")
				.perform(x -> x.substring(1))
				.perform(x -> "m"+x)
				.perform(x -> "--"+x+"--").build();

		System.out.println(s);

	}

}
