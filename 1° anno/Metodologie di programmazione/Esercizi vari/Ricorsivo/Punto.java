package Ricorsivo;

public class Punto {

	private int x,y;
	
	public Punto(int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		
		return x;
		
	}
	
	public int getY() {
		
		return y;
		
	}
	
	public int contaPercorsi(Punto p) {
		
		int sum = 0;
		
		if (p.getX() == x && p.getY() == y) return 1;
		
		else if (p.getX() == x) {
			
			sum += contaPercorsi(new Punto(p.getX(),p.getY()-1));
		}
		
		else if (p.getY() == y) {
			
			sum += contaPercorsi(new Punto(p.getX()-1,p.getY()));
		}
		
		else {
			
			sum += contaPercorsi(new Punto(p.getX()-1,p.getY())) + contaPercorsi(new Punto(p.getX(),p.getY()-1));
		}
		
		return sum;
		
	}

}
