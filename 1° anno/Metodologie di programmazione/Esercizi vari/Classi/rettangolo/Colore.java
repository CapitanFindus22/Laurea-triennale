package Classi.rettangolo;

public class Colore {

	public static final Colore BLACK = new Colore(0,0,0);
	public static final Colore WHITE = new Colore(255,255,255);
	
	private int r,g,b;
	
	public Colore(int r, int g, int b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public int getRed()
	{return r;}
	
	public int getGreen()
	{return g;}
	
	public int getBlue()
	{return b;}
	
	public void setValues(int r, int g, int b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
}
