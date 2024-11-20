package graphic;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public final class GamePanel extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CardLayout c_parent;
	private JPanel parent;
	
	public GamePanel(CardLayout c, JPanel container) {
		
		super();
		
		c_parent = c;
		parent = container;
	
		
	}
	
	public void start() {
		
		this.setLayout(new BorderLayout());
		
		GameplayPanel gameplay = new GameplayPanel();
		gameplay.setPreferredSize(new Dimension(900,380));
		
		OptionPanel options = new OptionPanel(c_parent,parent);
		options.setPreferredSize(new Dimension(900,220));
		options.setBorder(getBorder());

		this.add(gameplay,BorderLayout.NORTH);
		this.add(options,BorderLayout.SOUTH);
		
		c_parent.last(parent);
		
	}
	
}
