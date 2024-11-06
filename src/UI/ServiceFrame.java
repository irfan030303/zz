package UI;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ServiceRepo; // Ganti menjadi ServiceRepo
import model.Service; // Ganti menjadi Service
import table.TableService; // Ganti menjadi TableService

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServiceFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtJenis;
    private JTextField txtHarga;
    private JTextField txtStatus;
    private JTable tableServices;

    public String id; 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServiceFrame frame = new ServiceFrame();
                    frame.setVisible(true);
                    frame.loadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ServiceFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 724, 505);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblJenis = new JLabel("Jenis");
        lblJenis.setBounds(75, 65, 46, 14);
        contentPane.add(lblJenis);

        JLabel lblHarga = new JLabel("Harga");
        lblHarga.setBounds(75, 95, 63, 14);
        contentPane.add(lblHarga);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(75, 130, 63, 14);
        contentPane.add(lblStatus);

        txtJenis = new JTextField();
        txtJenis.setBounds(154, 62, 201, 20);
        contentPane.add(txtJenis);
        txtJenis.setColumns(10);

        txtHarga = new JTextField();
        txtHarga.setColumns(10);
        txtHarga.setBounds(154, 92, 201, 20);
        contentPane.add(txtHarga);

        txtStatus = new JTextField();
        txtStatus.setColumns(10);
        txtStatus.setBounds(154, 123, 201, 20);
        contentPane.add(txtStatus);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Service service = new Service();
                service.setJenis(txtJenis.getText());
                service.setHarga(txtHarga.getText());
                service.setStatus(txtStatus.getText());
                srv.save(service); 
                reset();
                loadTable();
            }
        });
        btnSave.setBounds(154, 172, 89, 23);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id != null) {
                    Service service = new Service();
                    service.setJenis(txtJenis.getText());
                    service.setHarga(txtHarga.getText());
                    service.setStatus(txtStatus.getText());
                    service.setId(id);
                    srv.update(service);
                    reset();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan Pilih Data yang Akan di Update");
                }
            }
        });
        btnUpdate.setBounds(253, 172, 89, 23);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id != null) {
                    srv.delete(id);
                    reset();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan Pilih Data yang Akan di Hapus");
                }
            }
        });
        btnDelete.setBounds(366, 172, 89, 23);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(465, 172, 89, 23);
        contentPane.add(btnCancel);

        tableServices = new JTable();
        tableServices.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                id = tableServices.getValueAt(tableServices.getSelectedRow(), 0).toString();
                txtJenis.setText(tableServices.getValueAt(tableServices.getSelectedRow(), 1).toString());
                txtHarga.setText(tableServices.getValueAt(tableServices.getSelectedRow(), 2).toString());
                txtStatus.setText(tableServices.getValueAt(tableServices.getSelectedRow(), 3).toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableServices);
        scrollPane.setBounds(10, 206, 674, 249);
        contentPane.add(scrollPane);
    }

    public void reset() {
        txtJenis.setText("");
        txtHarga.setText("");
        txtStatus.setText("");
        id = null; 
    }

    ServiceRepo srv = new ServiceRepo(); 
    List<Service> ls;

    public void loadTable() {
        ls = srv.show(); 
        TableService tu = new TableService(ls); 
        tableServices.setModel(tu);
        tableServices.getTableHeader().setVisible(true);
    }
}
