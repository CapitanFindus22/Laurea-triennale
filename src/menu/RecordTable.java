package menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import i_o.Entry;

public final class RecordTable extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Object[] column_names = {
			"GIOCATORE",
			"ROUND VINTI",
			"POKEMON"
			
		};
	
	private final static int NUM_COLUMNS = column_names.length;
	
	private static Object[][] data;
	
	/*
	 * Create a custom table
	 * 
	 * @argument entries The values to display
	 */
	public RecordTable(Entry[] entries) {
		
		super(data,column_names);
		
		data = new Object[entries.length][NUM_COLUMNS];
		
		this.setGridColor(new Color(0,0,0));
		
		this.setFont(new Font("Monospaced",Font.BOLD,17));
		this.setBackground(new Color(196, 207, 161));
		
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        center.setVerticalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        
        this.getTableHeader().setFocusable(false);
        this.getTableHeader().setReorderingAllowed(false);
        this.getTableHeader().setResizingAllowed(false);
        this.getTableHeader().setFont(new Font("Monospaced",Font.BOLD,15));
        this.setEnabled(false);
		
		convert(entries);
		
	}
	
	/*
	 * Convert and save the values
	 * 
	 * @argument The values to convert
	 */
	private void convert(Entry[] entries) {
		
		for(int i=0; i < entries.length; i++) {

			if(entries[i] != null) {
				
				data[i][0] = entries[i].getName();
				data[i][1] = entries[i].getStreak();
				data[i][2] = entries[i].getPkmn();
				
			}
			
		}
		
	}
	
	//Getter
	
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return column_names.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

}
