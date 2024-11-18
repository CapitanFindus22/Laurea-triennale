package graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainMenu() {
		
		super("Menù");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(0, 0, 1000, 1000);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JButton b = new JButton("Leaderboard");
		
		b.setBounds(0, 0, 100, 100);
		
		b.addActionListener(new ActionListener() {
			
		  public void actionPerformed(ActionEvent e) { 
			    Leaderboard.getIstance();
			  } 			
		});
		
		this.add(b);
		
		this.setVisible(true);
	}
	
}
