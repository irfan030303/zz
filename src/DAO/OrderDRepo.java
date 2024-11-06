package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Order;
import model.OrderDetailModel;
import confg.Database;

public class OrderDRepo implements OrderDDao {
    private Connection connection;
    final String insert = "INSERT INTO order_detail (jenis, qty, total,id_order,id_service) VALUES (?,?,?,?,?);";
    final String select = "SELECT * FROM order_detail;";
    final String delete = "DELETE FROM order_detail WHERE id=?;";
    final String update = "UPDATE order_detail SET jenis=?, qty=?, total=?,id_order=?,id_service=? WHERE id=?;";
    final String selectById = "SELECT * FROM order_detail WHERE id_order = ?;";
    final String inserto = "INSERT INTO orders (Id,id_costumer,id_service,id_user,total,tanggal,tanggal_selesai,status,status_pembayaran) VALUES (?,?,?,?,?);";

    public OrderDRepo() {
        connection = Database.koneksi();
    }
    
    public static String id;

    @Override
    public void save(OrderDetailModel ordd) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, ordd.getJenis());
            st.setString(2, ordd.getQty());
            st.setString(3, ordd.getTotal());
            st.setString(4, ordd.getid_order());
            st.setString(5, ordd.getid_service());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void save2(Order ord) {
        PreparedStatement st = null;
        try {
            // Menyesuaikan query insert sesuai kolom pada tabel
        	String insert = "INSERT INTO orders (id, costumer, total, tanggal, tanggal_selesai, status, pembayaran, status_pembayaran) "
        	        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            st = connection.prepareStatement(insert);

            // Menetapkan nilai ke dalam prepared statement sesuai dengan data dari form
            st.setString(1, ord.getId());               // id_order
            st.setString(2, ord.getCostumer());          // costumer
            st.setString(3, ord.getTotal());             // total
            st.setString(4, ord.getTanggal());           // tanggal
            st.setString(5, ord.getTanggal_selesai());   // tanggal_selesai
            st.setString(6, ord.getStatus());            // status
            st.setString(7, ord.getPembayaran());        // pembayaran
            st.setString(8, ord.getStatus_pembayaran()); // status_pembayaran

            
            // Eksekusi query
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public List<Order> show() {
        List<Order> ls2 = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                // Membuat objek Order atau OrderDetailModel
                Order ordd = new Order();

                // Menyesuaikan dengan kolom yang ada di ResultSet
                ordd.setId(rs.getString("ID")); // ID
                ordd.setCostumer(rs.getString("Costumer")); // Costumer
                ordd.setService(rs.getString("Service")); // Service
                ordd.setTanggal(rs.getDate("Tanggal")); // Tanggal (pastikan tipe data sesuai)
                ordd.setTanggalSelesai(rs.getDate("Tanggal Selesai")); // Tanggal Selesai
                ordd.setStatus(rs.getString("Status")); // Status
                ordd.setPembayaran(rs.getString("Pembayaran")); // Pembayaran
                ordd.setStatusPembayaran(rs.getString("Status Pembayaran")); // Status Pembayaran

                // Menambahkan objek ke dalam list
                ls2.add(ordd);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return ls2;
    }


    @Override
    public void update(OrderDetailModel odm) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, odm.getJenis());
            st.setString(2, odm.getQty());
            st.setString(3, odm.getTotal());
            st.setString(4, odm.getId());
            st.setString(5, odm.getid_service());
            st.setString(6, odm.getId()); // Menggunakan id dari objek
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(delete);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String total(String order_id) {
        String query_total = "SELECT sum(total) as total from order_detail WHERE id_order = ?";
        String result = "";
        try (PreparedStatement st = connection.prepareStatement(query_total)) {
            st.setString(1, order_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getString("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<OrderDetailModel> showById(String id_order1) {
        List<OrderDetailModel> ls = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(selectById);
            st.setString(1, id_order1); // Memperbaiki parameter
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetailModel orderDetailModel = new OrderDetailModel();
                orderDetailModel.setId(rs.getString("id"));
                orderDetailModel.setid_order(rs.getString("id_order"));
                orderDetailModel.setid_service(rs.getString("id_service"));
                orderDetailModel.setQty(rs.getString("qty"));
                orderDetailModel.setTotal(rs.getString("total"));
                ls.add(orderDetailModel);
            }
        } catch (Exception e) {
            Logger.getLogger(OrderDRepo.class.getName()).log(Level.SEVERE, null, e);
        }
        return ls;
    }

    public String getLastOrderIdFromDatabase() {
        String lastOrderId = null;
        String query = "SELECT id_order FROM order_detail ORDER BY id_order DESC LIMIT 1;";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                lastOrderId = rs.getString("id_order");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastOrderId;
    }
    public List<OrderDetailModel> filterByIdOrder(String idOrder) {
        List<OrderDetailModel> filteredList = new ArrayList<>();
        String query = "SELECT * FROM order_detail WHERE id_order = ?;"; // Query untuk memfilter berdasarkan id_order

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, idOrder); // Mengatur parameter id_order
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetailModel orderDetailModel = new OrderDetailModel();
                orderDetailModel.setId(rs.getString("id"));
                orderDetailModel.setid_order(rs.getString("id_order"));
                orderDetailModel.setid_service(rs.getString("id_service"));
                orderDetailModel.setJenis(rs.getString("Jenis"));
                orderDetailModel.setQty(rs.getString("qty"));
                orderDetailModel.setTotal(rs.getString("total"));
                filteredList.add(orderDetailModel); // Menambahkan hasil ke daftar
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDRepo.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return filteredList; // Mengembalikan daftar yang sudah difilter
    }

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}


    
       

}
