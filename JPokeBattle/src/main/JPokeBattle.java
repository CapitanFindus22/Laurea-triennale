package main;

import javax.swing.SwingUtilities;

import graphic.MainMenu;

public class JPokeBattle {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new MainMenu();
		    }
		});

		
	}

}
