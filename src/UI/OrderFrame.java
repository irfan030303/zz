package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.OrderDRepo;
import DAO.ServiceRepo;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import model.Order;  // Sesuaikan dengan lokasi package Order
import model.OrderDetailModel;
import model.Service;
import table.TableOrder; 

public class OrderFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable OrderTable;
	private static OrderDetail orderdetail;
	/**
	 * Launch the application.
	 */
	OrderDRepo srvr = new OrderDRepo();
	List<Order> ls;
	String id;
	
	public void loadTable() {
        ls = srvr.show();
        TableOrder tu = new TableOrder(ls);
        OrderTable.setModel(tu);
        OrderTable.getTableHeader().setVisible(true);
    }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame frame = new OrderFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderFrame() {
		orderdetail = new OrderDetail();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDataOrderan = new JLabel("DATA ORDERAN");
		lblDataOrderan.setFont(new Font("Serif", Font.BOLD, 20));
		lblDataOrderan.setBounds(23, 42, 685, 38);
		contentPane.add(lblDataOrderan);
		
		JButton btnCustomer = new JButton("Edit/Detail");
		btnCustomer.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnCustomer.setBounds(598, 83, 110, 21);
		contentPane.add(btnCustomer);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnDelete.setBounds(478, 84, 110, 21);
		contentPane.add(btnDelete);
		
		OrderTable = new JTable(new DefaultTableModel(
			    new Object[][] {},
			    new String[] {"ID Order", "Nama Costumer", "ID Service", "Tanggal Order", "Tanggal Selesai", "Status Order", "Pembayaran", "Status Pembayaran"}
			));
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        OrderDRepo orderDRepo = new OrderDRepo(); // Membuat instance OrderDRepo
		        String lastOrderId = orderDRepo.getLastOrderIdFromDatabase(); // Mendapatkan ID terakhir
		        String newOrderId = generateOrderID(lastOrderId); // Menggunakan ID terakhir untuk menghasilkan ID baru
		        orderdetail.setOrderID(newOrderId);
		        orderdetail.setId_order(newOrderId);
		        orderdetail.setVisible(true);
		        OrderDetail.txtOrderID.setText(newOrderId);
		        OrderDetail.loadTable();
		        OrderDetail.loadTableOrderDetail();    
		        dispose();
		    }

		    private String generateOrderID(String lastOrderId) {
		        int idNumber;

		        // Jika ID terakhir adalah null atau kosong, kita mulai dari 1
		        if (lastOrderId == null || lastOrderId.length() < 4) {
		            idNumber = 1; // ID pertama
		        } else {
		            idNumber = Integer.parseInt(lastOrderId.substring(4)); // Mengambil angka dari ID terakhir
		            idNumber++; // Menambahkan 1 untuk ID baru
		        }

		        return String.format("TRX-%06d", idNumber); // Mengembalikan ID baru dengan format 'TRX-000001'
		    }

		    
		});

		btnOrder.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnOrder.setBounds(23, 91, 139, 21);
		contentPane.add(btnOrder);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 119, 698, 254);
		contentPane.add(scrollPane);
		
		
		OrderTable.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // Mendapatkan baris yang dipilih
		        int selectedRow = OrderTable.getSelectedRow();
		        
		        // Pastikan baris yang dipilih valid (tidak -1)
		        if (selectedRow != -1) {
		            // Ambil data berdasarkan kolom yang dipilih
		            String idOrder = OrderTable.getValueAt(selectedRow, 0).toString();       // Kolom 0: ID Order
		            String costumer = OrderTable.getValueAt(selectedRow, 1).toString();      // Kolom 1: Nama Costumer
		            String idService = OrderTable.getValueAt(selectedRow, 2).toString();     // Kolom 2: ID Service
		            String tanggal = OrderTable.getValueAt(selectedRow, 3).toString();       // Kolom 3: Tanggal Order
		            String tanggalSelesai = OrderTable.getValueAt(selectedRow, 4).toString(); // Kolom 4: Tanggal Selesai
		            String status = OrderTable.getValueAt(selectedRow, 5).toString();        // Kolom 5: Status Order
		            String pembayaran = OrderTable.getValueAt(selectedRow, 6).toString();    // Kolom 6: Pembayaran
		            String statusPembayaran = OrderTable.getValueAt(selectedRow, 7).toString(); // Kolom 7: Status Pembayaran
		            
		            // Gunakan data yang diambil sesuai kebutuhan, misalnya untuk menampilkan detail atau proses lainnya
		            // Misalnya, menampilkan informasi ke dalam form detail atau dialog
		        }
		    }
		});

		OrderTable.setToolTipText("");
		OrderTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
		OrderTable.setFillsViewportHeight(true);
		OrderTable.setBackground(Color.WHITE);
		scrollPane.setViewportView(OrderTable);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnBack.setBounds(598, 10, 110, 21);
		contentPane.add(btnBack);
	}
}