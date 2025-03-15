package Interfacce.notevoli;

public class Tester {

	public static void main(String[] args) {

		MyArray m = new MyArray(new int[] {1,2,3,7});
		MyArray[] m1 = new MyArray[2];
			
		m1[0] = new MyArray(new int[] {9,2,3,7});
		m1[1] = new MyArray(new int[] {1,2,5,7});
		
		System.out.println(m.compareTo(new Integer[] {1,2,3,4}));
		System.out.println(m1[0].compareTo(new Integer[] {1,2,3,4}));
		System.out.println(m1[1].compareTo(new Integer[] {1,2,3,4}));

	}
	
}
