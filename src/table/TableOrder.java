package table;

import model.Order;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableOrder extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private static List<Order> ls;

    // Nama kolom tabel
    private final String[] columnNames = new String[] {
        "ID", "Costumer", "Service", "Tanggal", "Tanggal Selesai", "Status", "Pembayaran", "Status Pembayaran"
    };

    // Konstruktor untuk menginisialisasi data list order
    public TableOrder(List<Order> ls) {
        this.ls = ls;
    }
    

    @Override
    public int getRowCount() {
        return ls.size(); // Mengembalikan jumlah baris sesuai dengan jumlah data order
    }

    @Override
    public int getColumnCount() {
        return columnNames.length; // Mengembalikan jumlah kolom yang telah didefinisikan
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column]; // Mengembalikan nama kolom berdasarkan indeks kolom
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order order = ls.get(rowIndex); // Mengambil order pada baris yang diminta
        switch (columnIndex) {
            case 0:
                return order.getId(); // ID Order
            case 1:
                return order.getCostumer(); // Nama Customer
            case 2:
                return order.getId_service(); // ID Service
            case 3:
                return order.getTanggal(); // Tanggal Order
            case 4:
                return order.getTanggal_selesai(); // Tanggal Selesai
            case 5:
                return order.getStatus(); // Status Order
            case 6:
                return order.getPembayaran(); // Pembayaran
            case 7:
                return order.getStatus_pembayaran(); // Status Pembayaran
            default:
                return null;
        }
    }

    // Metode untuk mengambil order berdasarkan baris tertentu
    public static Order getOrderAt(int rowIndex) {
        return ls.get(rowIndex);
    }
}
