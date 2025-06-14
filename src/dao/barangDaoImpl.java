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
import model.Barang;

public class barangDaoImpl implements barangDao {
     private Connection connection;

     public barangDaoImpl(Connection connection) {
        this.connection = connection;
    }
     
    public void save(Barang barang) {
        String sql = "INSERT INTO barang VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, barang.getKode());
            statement.setString(2, barang.getNama());
            statement.setString(3, barang.getSatuan());
            statement.setInt(4,barang.getHarga());
            statement.setInt(5, barang.getStok());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public void update(Barang barang) {
        String sql = "UPDATE barang SET  nmBarang = ?, satuan = ?, harga = ?, stok = ? WHERE kdBarang = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, barang.getNama());
            statement.setString(2, barang.getSatuan());
            statement.setInt(3, barang.getHarga());
            statement.setInt(4, barang.getStok());
            statement.setString(5,barang.getKode());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     public void delete(String kd) {
        String sql = "DELETE FROM barang WHERE kdBarang = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, kd);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     @Override
    public Barang findById(String kd) {
        String sql = "SELECT * FROM barang WHERE kdBarang = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, kd);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Barang(rs.getString("kdBarang"), rs.getString("nmBarang"), 
                        rs.getString("satuan"), rs.getInt("harga"), rs.getInt("stok"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Barang> findAll() {
        List<Barang> listBarang = new ArrayList<>();
        String sql = "SELECT * FROM barang";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                listBarang.add(new Barang(rs.getString("kdBarang"), rs.getString("nmBarang"), 
                        rs.getString("satuan"), rs.getInt("harga"), rs.getInt("stok")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(listBarang);

        return listBarang;
    }

}
