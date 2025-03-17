package Interfacce.trim;

import java.util.Arrays;
import java.util.LinkedList;

public class Tester {

	public static void main(String[] args) {

		TrimString t1 = new TrimString("asdasf     ");
		TrimString t2 = new TrimString("    asdasf     ");
		TrimString t3 = new TrimString("   a  sdasf");
		TrimString t4 = new TrimString("asdasf");
		
		TrimmableIntList l1 = new TrimmableIntList(new LinkedList<Integer> (Arrays.asList(0,1,2)));
		TrimmableIntList l2 = new TrimmableIntList(new LinkedList<Integer> (Arrays.asList(1,2,0)));
		TrimmableIntList l3 = new TrimmableIntList(new LinkedList<Integer> (Arrays.asList(0,1,2,0)));
		TrimmableIntList l4 = new TrimmableIntList(new LinkedList<Integer> (Arrays.asList(1,2)));

		System.out.println(t1.trim());
		System.out.println(t2.trim());
		System.out.println(t3.trim());
		System.out.println(t4.trim());
		
		System.out.println(l1.trim().toString());
		System.out.println(l2.trim().toString());
		System.out.println(l3.trim().toString());
		System.out.println(l4.trim().toString());
		
	}

}
