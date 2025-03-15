package Classi.vm;

import java.util.Arrays;

public class Tester {

	public static void main(String[] args) {
	
		Registro eax = new Registro("EAX");
		Registro ebx = new Registro("EBX");
		Registro ecx = new Registro("ECX");
		Registro edx = new Registro("EDX");
		
		Istruzione[] codice1 = new Istruzione[] {
				new Mov(edx, new Intero(0)),
				new Add(edx, ecx) };
		
		Procedura proc1 = new Procedura(Arrays.asList(codice1));
	
		Istruzione[] codice2 = new Istruzione[] {
				new Mov(eax, new Intero(3)),
				new Mov(ebx, new Intero(5)),
				new Mov(ecx, new Intero(8)),
				new Add(eax, ebx),
				new Cmp(eax, ecx),
				new Call(proc1),
				};
		
		Procedura main_proc = new Procedura(Arrays.asList(codice2));
				
		MyVirtualMachine m = new MyVirtualMachine();
		
		m.esegui(main_proc);

		System.out.println(eax.getValue());
		System.out.println(ebx.getValue());
		System.out.println(ecx.getValue());
		System.out.println(edx.getValue());
		
	}
}
