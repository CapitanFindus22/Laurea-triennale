package menu;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import i_o.Entry;

public final class Leaderboard extends JFrame{

	/**
	 * 
	 */
	private final static long serialVersionUID = 1L;
	
	private final int MAX_SIZE = 10;
	
	private final Path icon_path = Paths.get("resource","image","icons_victory.png");
	
	private final int WIDTH = 350;
	private final int HEIGHT = 500;
	
	private static Leaderboard istance;
	
	private Entry[] record;

	/*
	 * Create the window
	 */
	private Leaderboard() {
		
		super("Leaderboard");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(10, 10, WIDTH, HEIGHT);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setIconImage(new ImageIcon(icon_path.toString()).getImage());
		this.setBackground(new Color(196, 207, 161));
		
		record = new Entry[MAX_SIZE];
		Entry.fill(record);
		
		RecordTable table = new RecordTable(record);
		table.setRowHeight(this.getHeight()/table.getRowCount()); 
		
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() + table.getTableHeader().getPreferredSize().height + 3));
		
		this.add(pane);
		
		this.pack();
		
		this.setVisible(true);
	}
	
	public static Leaderboard getIstance() {
		
		if(istance == null) istance = new Leaderboard();
		
		return istance;
		
	}
	
	@Override
	public void dispose() {
	    super.dispose();
	    istance = null;
	}
	
}
