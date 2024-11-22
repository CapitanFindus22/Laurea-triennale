package graphic_battle;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel txt;
	
	public TextPanel() {
	
		txt = new JLabel();
		txt.setFont(new Font("Lucida Console",Font.BOLD,16));
		this.add(txt);
	
	}
	
	public void write(String s) {
		
		txt.setText(s);
		
	}
	
}
