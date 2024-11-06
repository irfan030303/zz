package DAO;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import confg.Database;
import model.Service;
import model.User;

public class ServiceRepo implements ServiceDao {
    private Connection connection;
    final String insert = "INSERT INTO Service(jenis,harga,status) VALUES(?,?,?);";
    final String select = "SELECT * FROM Service;";
    final String delete = "DELETE FROM Service WHERE id=?;";
    final String update = "UPDATE Service SET jenis=?, harga=?, status=? WHERE id=?;";

    public ServiceRepo() {
        connection = Database.koneksi();
    }

    public void save(Service service) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, service.getJenis());
            st.setString(2, service.getHarga());
            st.setString(3, service.getStatus());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Service> show() {
        List <Service> ls = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getString("id"));
                service.setJenis(rs.getString("jenis"));
                service.setHarga(rs.getString("harga"));
                service.setStatus(rs.getString("status"));
                ls.add(service);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServiceRepo.class.getName()).log(Level.SEVERE, null, e);
        }
        return ls;
    }

    public void update(Service service) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, service.getJenis());
            st.setString(2, service.getHarga());
            st.setString(3, service.getStatus());
            st.setString(4, service.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

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
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

