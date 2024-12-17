package menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import i_o.Entry;

/**
 * A custom table used to store records from the game
 */
public final class RecordTable extends JTable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The columns
	 */
	private final static String[] column_names = {
			"GIOCATORE",
			"ROUND VINTI",
			"POKEMON"
			
		};
	
	/**
	 * The number of columns
	 */
	private final int NUM_COLUMNS = column_names.length;
	
	/**
	 * Store the data to use
	 */
	private static Object[][] data;
	
	/**
	 * Create and fill the table
	 * 
	 * @param entries the array of records to use
	 */
	public RecordTable(Entry[] entries) {
		
		//Create the table and customize it
		super(data,column_names);
		
		this.setGridColor(new Color(0,0,0));
		this.setFont(new Font("Monospaced",Font.BOLD,17));
		this.setBackground(new Color(196, 207, 161));
		
        //Customize the header
        this.getTableHeader().setFocusable(false);
        this.getTableHeader().setReorderingAllowed(false);
        this.getTableHeader().setResizingAllowed(false);
        this.getTableHeader().setFont(new Font("Monospaced",Font.BOLD,15));
        this.setEnabled(false);
		
		//Center the cells text
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        center.setVerticalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        
		//Initialize the data matrix
		data = new Object[entries.length][NUM_COLUMNS];
		
        //Fill the table
		convert(entries);
		
	}
	
	/**
	 * Convert an array of Entry to rows of the table
	 * 
	 * @param entries the array of records to convert
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
