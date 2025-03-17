package Design_pattern.Builder;

import java.util.LinkedList;
import java.util.List;

public class IntList {

	private List<Integer> l;
	
	public IntList() {
		
		l = new LinkedList<>();
		
	}
	
	public IntList add(int val) {
		
		l.add(val);
		return this;
		
	}
	
	public IntList removeAll() {
		
		l.clear();
		return this;
		
	}
	
	public List<Integer> build() {
		
		return l;
		
	}
	
	public static void main(String[] args) {

		List<Integer> l = new IntList()
						  .add(0)
						  .add(1)
						  .add(2)
						  .build();
		
		l.stream().forEach(System.out::println);

	}

}
