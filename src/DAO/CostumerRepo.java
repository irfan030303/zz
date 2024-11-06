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

import confg.Database;
import model.costumer; 

public class CostumerRepo implements CostumerDao {

    private Connection connection;
    final String insert = "INSERT INTO Costumer(nama, alamat, nomorhp) VALUES(?, ?, ?);";
    final String select = "SELECT * FROM Costumer;";
    final String update = "UPDATE Costumer SET nama=?, alamat=?, nomorhp=? WHERE id=?;";
    final String delete = "DELETE FROM Costumer WHERE id=?;";
    final String sql = "SELECT nama FROM Costumer";

    public CostumerRepo() {
        connection = Database.koneksi(); 
    }

    @Override
    public void save(costumer costumer) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());
            st.setString(3, costumer.getNomorhp());
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
    public List<costumer> show() {
        List<costumer> ls = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                costumer costumer = new costumer();
                costumer.setId(rs.getString("id")); 
                costumer.setNama(rs.getString("nama"));
                costumer.setAlamat(rs.getString("alamat"));
                costumer.setNomorhp(rs.getString("nomorhp"));
                ls.add(costumer);
            }
        } catch (SQLException e) {
            Logger.getLogger(CostumerDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return ls;
    }

    @Override
    public void update(costumer costumer) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());
            st.setString(3, costumer.getNomorhp());
            st.setString(4, costumer.getId());
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
    public List<String> getAllNames() {
        List<String> customerNames = new ArrayList<>();
        String sql = "SELECT nama FROM Costumer"; // Query untuk mengambil nama pelanggan

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customerNames.add(rs.getString("nama")); // Ambil kolom "nama"
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerNames;
    }

	
}
