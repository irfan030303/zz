package table;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.OrderDetailModel;
public class TableOrderDetailModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	List<OrderDetailModel> ls;
	private String[] columnNames = {"ID", "Jenis", "Quantity", "Total","id_order","id_service"};
	public TableOrderDetailModel(List<OrderDetailModel> ls) {
		this.ls = ls;
	}
	
	public int getRowCount() {
		return ls.size();
	}
	
	public int getColumnCount() {
		return 6;
	}
	
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return ls.get(rowIndex).getId();
		case 1:
			return ls.get(rowIndex).getJenis();
		case 2:
			return ls.get(rowIndex).getQty();
		case 3:
			return ls.get(rowIndex).getTotal();
		case 4:
			return ls.get(rowIndex).getid_order();
		case 5:
			return ls.get(rowIndex).getid_service();
		default:
			return null;
		}
	}
}