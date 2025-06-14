/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author acer
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pelanggan;

public class pelangganDaoImpl implements pelangganDao {
     private Connection connection;

     public pelangganDaoImpl(Connection connection) {
        this.connection = connection;
    }
     
    public void save(Pelanggan pelanggan) {
        String sql = "INSERT INTO pelanggan VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pelanggan.getKode());
            statement.setString(2, pelanggan.getNama());
            statement.setString(3, pelanggan.getAlamat());
            statement.setString(4, pelanggan.getTelepon());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public void update(Pelanggan pelanggan) {
        String sql = "UPDATE pelanggan SET  nama = ?, alamat = ?, telepon = ? WHERE kdPelanggan = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pelanggan.getNama());
            statement.setString(2, pelanggan.getAlamat());
            statement.setString(3, pelanggan.getTelepon());
            statement.setString(4, pelanggan.getKode());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     public void delete(String kd) {
        String sql = "DELETE FROM pelanggan WHERE kdPelanggan = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, kd);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     @Override
    public Pelanggan findById(String kd) {
        String sql = "SELECT * FROM pelanggan WHERE kdPelanggan = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, kd);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Pelanggan(rs.getString("kdPelanggan"), rs.getString("nama"), 
                        rs.getString("alamat"), rs.getString("telepon"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pelanggan> findAll() {
        List<Pelanggan> listPelanggan = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                listPelanggan.add(new Pelanggan(rs.getString("kdPelanggan"), rs.getString("nama"), 
                        rs.getString("alamat"), rs.getString("telepon")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPelanggan;
    }

}
