package Classi.game;

public class Player {

	private double x,y;
	private boolean laser;
	
	public Player(double x, double y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public void up() {
		
		y++;
		
	}
	
	public void up(double y) {
		
		this.y += y;
		
	}
	
	public void down() {
		
		y--;
		
	}
	
	public void down(double y) {
		
		this.y -= y;
		
	}
	
	public void setLaser(boolean laser) {
		
		this.laser = laser;
		
	}
	
	public boolean getLaser() {
		
		return laser;
		
	}
	
	@Override
	public String toString() {
		
		return "position:" + x + "," + y + " Shooting:" + laser;
		
	}
	
}
