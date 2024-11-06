package UI;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DAO.CostumerRepo;
import model.OrderDetailModel;
import model.costumer;  
import table.TableCostumer;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameminiCostumer extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableCostumer;

    private CostumerRepo costumerRepo = new CostumerRepo(); 
    private List<costumer> ls;  
    public FrameminiCostumer(OrderDetailModel orderDetailModel) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 724, 395);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        tableCostumer = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableCostumer);
        scrollPane.setBounds(10, 24, 674, 308);
        contentPane.add(scrollPane);
        loadTable();

        tableCostumer.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableCostumer.getSelectedRow();
                
                if (selectedRow != -1) {
                	costumer selectedCostumer = ((TableCostumer) tableCostumer.getModel()).getCostumerAt(selectedRow);
                    OrderDetail.SetCostumer(selectedCostumer);
                    FrameminiCostumer.this.dispose();
                }
            }
        });
    }

    public void loadTable() {
        ls = costumerRepo.show();
        TableCostumer tc = new TableCostumer(ls);
        tableCostumer.setModel(tc);
        tableCostumer.getTableHeader().setVisible(true); 
    }
}