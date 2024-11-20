package game_mechanics;

public class Move {

	public static final double[][] TYPES_ADVANTAGE = new double[][] {
		
	{1,1,1,1,1,1,1,1,1,1,1,1,0.5,0,1},
	{1,0.5,0.5,1,2,2,1,1,1,1,1,2,0.5,1,0.5},
	{1,2,0.5,1,0.5,1,1,1,2,1,1,1,2,1,0.5},
	{1,1,2,0.5,0.5,1,1,1,0,2,1,1,1,1,0.5},
	{1,0.5,2,1,0.5,1,1,0.5,2,0.5,1,0.5,2,1,0.5},
	{1,1,0.5,1,2,0.5,1,1,2,2,1,1,1,1,2},
	{2,1,1,1,1,2,1,0.5,1,0.5,0.5,0.5,2,0,1},
	{1,1,1,1,2,1,1,0.5,0.5,1,1,2,0.5,0.5,1},
	{1,2,1,2,0.5,1,1,2,1,0,1,0.5,2,1,1},
	{1,1,1,0.5,2,1,2,1,1,1,1,2,0.5,1,1},
	{1,1,1,1,1,1,2,2,1,1,0.5,1,1,1,1},
	{1,0.5,1,1,2,1,0.5,2,1,0.5,2,1,1,0.5,1},
	{1,2,1,1,1,2,0.5,1,0.5,2,1,2,1,1,1},
	{0,1,1,1,1,1,1,1,1,1,0,1,1,2,1},
	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,2}
		
	};
	
	private MoveFunction execute;
	
	private Type type;
	
	private String name;
	
	private int power;
	private int accuracy;
	private int pp;
	
	public Move(String s) {
		
		
		
	}
	
	public void setFunction(MoveFunction function) {
		
		execute = function;
		
	}
	
	public void esegui(Pokemon p1, Pokemon p2, Move m) {

		execute.esegui(p1, p2, m);
		
	}
	
	public Type getType() {
		
		return type;
		
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPp() {
		return pp;
	}

	public void setPp(int pp) {
		this.pp = pp;
	}

}
