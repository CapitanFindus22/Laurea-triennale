package Interfacce.notevoli;

public class MyArray implements Comparable<Integer[]>
{
	private int a[];
	
	public MyArray(int[] a) {
		this.a = a;
	}
	
	@Override
	public int compareTo(Integer[] o) {

		int different = 0;
		
		int max = 0;
		
		for(int x : o) 
		{
			if (max < x) max = x;
		}
		
		for(int x : a) 
		{
			if(x > max) different++;
		}
		
		return different;
	}
}
