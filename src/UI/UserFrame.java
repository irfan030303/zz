package UI;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.UserRepo;
import model.User;
import table.TableUser;

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

public class UserFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTable tableUsers;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserFrame frame = new UserFrame();
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
    public UserFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 724, 505);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nama");
        lblNewLabel.setBounds(75, 65, 46, 14);
        contentPane.add(lblNewLabel);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(75, 95, 63, 14);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(75, 130, 63, 14);
        contentPane.add(lblPassword);

        txtName = new JTextField();
        txtName.setBounds(154, 62, 201, 20);
        contentPane.add(txtName);
        txtName.setColumns(10);

        txtUsername = new JTextField();
        txtUsername.setColumns(10);
        txtUsername.setBounds(154, 92, 201, 20);
        contentPane.add(txtUsername);

        txtPassword =  new JTextField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(154, 123, 201, 20);
        contentPane.add(txtPassword);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setNama(txtName.getText());
                user.setUsername(txtUsername.getText());
                user.setPassword(txtPassword.getText());
                usr.save(user);
                reset();
                loadTable();
            }
        });
        btnSave.setBounds(154, 172, 89, 23);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		User user = new User();
        		user.setNama(txtName.getText());
        		user.setUsername(txtUsername.getText());
        		user.setPassword(txtPassword.getText());
        		user.setId(id);
        		usr.update(user);
        		reset();
        		loadTable();
        	}
        });
        btnUpdate.setBounds(253, 172, 89, 23);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(id != null) {
        			usr.delete(id);
        			reset();
        			loadTable();
        		}else {
        			JOptionPane.showMessageDialog(null, "Silahkan Pilih Data yang Akan di Hapus");
        		}
        	}
        });
        btnDelete.setBounds(366, 172, 89, 23);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(465, 172, 89, 23);
        contentPane.add(btnCancel);

        tableUsers = new JTable();
        tableUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                id = tableUsers.getValueAt(tableUsers.getSelectedRow(), 0).toString();
                txtName.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 1).toString());
                txtUsername.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 2).toString());
                txtPassword.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 3).toString());
            }
        });


        JScrollPane scrollPane = new JScrollPane(tableUsers);
        scrollPane.setBounds(10, 206, 674, 249);
        contentPane.add(scrollPane);
    }

    public void reset() {
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }

    UserRepo usr = new UserRepo();
    List<User> ls;
    public String id;

    public void loadTable() {
        ls = usr.show();
        TableUser tu = new TableUser(ls);
        tableUsers.setModel(tu);
        tableUsers.getTableHeader().setVisible(true);
    }
}
