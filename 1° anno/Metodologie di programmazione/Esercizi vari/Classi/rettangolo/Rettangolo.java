package Classi.rettangolo;

public class Rettangolo {

	private Punto origine;
	private double length, height;
	private Colore c = Colore.BLACK;
	
	public Rettangolo(Punto p, double length, double height)
	{
		origine = p;
		this.length = length;
		this.height = height;
	}
	
	public void setColor(Colore c)
	{this.c = c;}
	
	public void trasla(double x, double y)
	{
		origine.trasla(x, y);
	}
	
	@Override
	public String toString()
	{
		return String.format("(%f,%f)-(%f,%f) colore:%d-%d-%d", 
				origine.getX(),
				origine.getY(),
				origine.getX()+length,
				origine.getY()+height,
				c.getRed(),
				c.getGreen(),
				c.getBlue());
	}
	
	public static void main(String[] args)
	{
	
		Rettangolo r = new Rettangolo(new Punto(0,0),20,10);
		Rettangolo r1 = new Rettangolo(new Punto(10,10),1,88);
		r1.setColor(new Colore(50,50,50));
		
		System.out.println(r.toString() + " " + r1.toString());
		
	}

	
}
