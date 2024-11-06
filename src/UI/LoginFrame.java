package UI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import model.User;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername;
    private JTextField txtPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
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
    public LoginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 371);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Laundry Apps");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblNewLabel.setBounds(110, 11, 194, 63);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("Username");
        lblNewLabel_2.setBounds(26, 99, 60, 14);
        contentPane.add(lblNewLabel_2);

        txtUsername = new JTextField();
        txtUsername.setBounds(26, 124, 278, 35);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Password"); 
        lblNewLabel_3.setBounds(26, 170, 60, 14);
        contentPane.add(lblNewLabel_3);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (User.login(txtUsername.getText(), txtPassword.getText())) {
                    new MainFrame().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Login Gagal");
                }
            }
        });

        btnLogin.setBounds(26, 247, 278, 42);
        contentPane.add(btnLogin);
        
        txtPassword = new JTextField();
        txtPassword.setBounds(26, 195, 278, 41);
        contentPane.add(txtPassword);
        txtPassword.setColumns(10);
    }
}