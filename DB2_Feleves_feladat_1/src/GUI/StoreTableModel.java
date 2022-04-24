package GUI;

import javax.swing.table.DefaultTableModel;

public class StoreTableModel extends DefaultTableModel {

	public StoreTableModel(Object[] fieldNames, int rows) {
		super(fieldNames, rows);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == 0) {return true;}
		return false;
	}
	
	@Override
	public Class<?> getColumnClass(int index) {
		switch (index) {
		case 0: case 3:
			return(Integer.class);
		case 1: case 2:
			return(String.class);
		default:
			return (Integer.class);
		}
		
	}

	
	
}
