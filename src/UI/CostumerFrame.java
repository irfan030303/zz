package UI;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DAO.CostumerRepo;
import model.costumer;
import table.TableCostumer;
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

public class CostumerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNama;
    private JTextField txtAlamat;
    private JTextField txtNomorhp;
    private JTable tableCostumer;

    private CostumerRepo costumerRepo = new CostumerRepo(); 
    private List<costumer> ls; 
    private String id; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CostumerFrame frame = new CostumerFrame();
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
    public CostumerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 724, 505);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(75, 65, 46, 14);
        contentPane.add(lblNama);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(75, 95, 63, 14);
        contentPane.add(lblAlamat);

        JLabel lblNomorhp = new JLabel("Nomor HP");
        lblNomorhp.setBounds(75, 130, 63, 14);
        contentPane.add(lblNomorhp);

        txtNama = new JTextField();
        txtNama.setBounds(154, 62, 201, 20);
        contentPane.add(txtNama);
        txtNama.setColumns(10);

        txtAlamat = new JTextField();
        txtAlamat.setColumns(10);
        txtAlamat.setBounds(154, 92, 201, 20);
        contentPane.add(txtAlamat);

        txtNomorhp = new JTextField();
        txtNomorhp.setColumns(10);
        txtNomorhp.setBounds(154, 123, 201, 20);
        contentPane.add(txtNomorhp);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                costumer costumer = new costumer();
                costumer.setNama(txtNama.getText());
                costumer.setAlamat(txtAlamat.getText());
                costumer.setNomorhp(txtNomorhp.getText());
                costumerRepo.save(costumer); 
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
                    costumer costumer = new costumer();
                    costumer.setNama(txtNama.getText());
                    costumer.setAlamat(txtAlamat.getText());
                    costumer.setNomorhp(txtNomorhp.getText());
                    costumer.setId(id); 
                    costumerRepo.update(costumer);
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
                    costumerRepo.delete(id);
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
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset(); 
            }
        });
        btnCancel.setBounds(465, 172, 89, 23);
        contentPane.add(btnCancel);

        tableCostumer = new JTable();
        tableCostumer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                id = tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 0).toString();
                txtNama.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 1).toString());
                txtAlamat.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 2).toString());
                txtNomorhp.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 3).toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableCostumer);
        scrollPane.setBounds(10, 206, 674, 249);
        contentPane.add(scrollPane);
    }

    
    public void reset() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNomorhp.setText("");
        id = null;
    }

   
    public void loadTable() {
        ls = costumerRepo.show();
        TableCostumer tc = new TableCostumer(ls); 
        tableCostumer.setModel(tc);
        tableCostumer.getTableHeader().setVisible(true);
    }
}
