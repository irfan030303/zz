package table;

import model.costumer; 
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableCostumer extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private List<costumer> ls;

    private final String[] columnNames = new String[] {
            "ID", "Nama", "Alamat", "Nomor HP"
    };

    public TableCostumer(List<costumer> ls) {
        this.ls = ls;
    }

    @Override
    public int getRowCount() {
        return ls.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        costumer costumer = ls.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ls.get(rowIndex).getId();
            case 1:
                return ls.get(rowIndex).getNama();
            case 2:
                return ls.get(rowIndex).getAlamat();
            case 3:
                return ls.get(rowIndex).getNomorhp();
            default:
                return null;
        }
    }
    public costumer getCostumerAt(int rowIndex) {
    	return ls.get(rowIndex);
    }
}
