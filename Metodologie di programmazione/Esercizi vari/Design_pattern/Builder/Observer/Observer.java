package Design_pattern.Builder.Observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Observer implements Obs<Esecutori>{

		List<Esecutori> subs;
		
		public Observer() {
			
			subs = new LinkedList<>();
			
		}
		
		public void step() {
			
			subs.parallelStream().forEach(x -> x.exe());
			
		}

		public void subscribe(Esecutori subscriber) {

			subs.add(subscriber);
			
		}
		
		public static void main(String[] args) {
			
			Observer o = new Observer();
			
			o.subscribe(new Esecutori(33));
			o.subscribe(new Esecutori(1));
			o.subscribe(new Esecutori(2));
			
			JFrame jf = new JFrame();
			
			JButton b = new JButton();

			b.setBounds(0,0,100,100);
			
			jf.add(b);
			
			jf.setSize(400, 500);
			
			jf.setLayout(null);
			
			jf.setVisible(true);
			
			b.addActionListener(new ActionListener() {
				
				@Override
			    public void actionPerformed(ActionEvent e) {
					
					o.step();
					
			    }
				
				
			});
			
		}
}
			
