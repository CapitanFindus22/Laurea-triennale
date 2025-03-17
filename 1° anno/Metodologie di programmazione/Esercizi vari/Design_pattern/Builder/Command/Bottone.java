package Design_pattern.Builder.Command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Bottone {

	JButton b;
	
	public Bottone() {
		
		b = new JButton();
		b.setBounds(0, 0, 100, 100);
		
	}
	
	public JButton getBottone() {
		
		return b;
		
	}
	
	public static void main(String[] args) {
		
		JFrame jf = new JFrame();
		
		Bottone b = new Bottone();

		jf.add(b.getBottone());
		
		jf.setSize(400, 500);
		
		jf.setLayout(null);
		
		jf.setVisible(true);
		
		b.getBottone().addActionListener(new ActionListener() {
			
			@Override
		    public void actionPerformed(ActionEvent e) {
				
				Print command = new Print(b);
				Invoker i = new Invoker(command);
				i.execute();
		    }
			
			
		});
		
	}
}
