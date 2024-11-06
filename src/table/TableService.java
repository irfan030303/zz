package table;

import javax.swing.table.AbstractTableModel;

import model.Service;

import java.util.List;

public class TableService extends AbstractTableModel{
	List<Service> LS;
	private String[] columnNames={"ID", "Jenis", "Harga", "Status"};
	public TableService(List<Service> LS) {
		this.LS = LS;
	}
	@Override
	public int getRowCount() {
		return LS.size();
	}
	@Override
	public int getColumnCount() {
		return 4;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return LS.get(rowIndex).getId();
		case 1:
			return LS.get(rowIndex).getJenis();
		case 2:
			return LS.get(rowIndex).getHarga();
		case 3:
			return LS.get(rowIndex).getStatus();	
		default:
			
			return null;
		}
	}

}
