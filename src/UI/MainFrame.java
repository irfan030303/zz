package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Laundry Apps");
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 40));
		lblNewLabel.setBounds(291, 11, 335, 44);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Pesan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(101, 101, 193, 44);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Layanan ");
		btnNewButton_1.setBounds(101, 156, 193, 44);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Pelanggan");
		btnNewButton_2.setBounds(100, 211, 194, 44);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Pengguna");
		btnNewButton_3.setBounds(338, 101, 193, 44);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Laporan");
		btnNewButton_4.setBounds(338, 156, 193, 44);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Profile");
		btnNewButton_5.setBounds(338, 208, 193, 47);
		contentPane.add(btnNewButton_5);
	}

}
