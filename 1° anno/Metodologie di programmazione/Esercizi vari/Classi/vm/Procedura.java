package Classi.vm;

import java.util.List;

final public class Procedura{
 
	private List<Istruzione> list;
	
	public Procedura(List<Istruzione> list) {
		
		this.list = list;
		
	}
	
	public List<Istruzione> getList() {
		
		return list;
		
	}
	
}
