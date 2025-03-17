package Classi.rettangolo;

public class Punto {

	private double x,y;
	
	public Punto(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void trasla(double x, double y)
	{
		this.x += x;
		this.y += y;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	
	@Override
	public String toString()
	{
		return String.format("x:%f - y:%f", x,y);
	}
	
}
