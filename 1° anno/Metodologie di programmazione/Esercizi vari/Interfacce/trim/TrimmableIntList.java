package Interfacce.trim;

import java.util.LinkedList;

public class TrimmableIntList implements Trimmable<LinkedList<Integer>>{

	private LinkedList<Integer> list;
	
	public TrimmableIntList(LinkedList<Integer> list) {
		
		this.list = list;
		
	}

	@Override
	public LinkedList<Integer> trim() {

		if(list.getFirst() == 0) {
			list.removeFirst();
		}
		
		if(list.getLast() == 0) {
			list.removeLast();
		}
		
		return list;
		
	}
	
}
