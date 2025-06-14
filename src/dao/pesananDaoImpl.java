/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Pelanggan;
import model.Barang;
import model.Pesanan;

/**
 *
 * @author acer
 */
public class pesananDaoImpl implements pesananDao{
    private Connection connection;
    
    public pesananDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void savePesanan(Pesanan pesanan) {
     String sql = "INSERT INTO pesanan (tanggal, noPesan, kdPelanggan) VALUES (?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalFormatted = dateFormat.format(pesanan.getTanggal());

        statement.setString(1, tanggalFormatted); 
        statement.setString(2, pesanan.getNoPesan());
        statement.setString(3, pesanan.getkdPelanggan());

        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    @Override
    public void saveDetailPesanan(Pesanan pesanan){
        String sql = "INSERT INTO detailPesanan VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pesanan.getNoPesan());
            statement.setString(2, pesanan.getKdBarang());
            statement.setString(3, String.valueOf(pesanan.getJumlah()));
            statement.setString(4, String.valueOf(pesanan.getHarga()));            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     
    @Override
    public Pelanggan findPelangganById(String kd) {
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
    public Barang findBarangById(String kd) {
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
    public List<String> findKodeBarang() {
        List<String> listKode = new ArrayList<>();
        String sql = "SELECT kdBarang FROM barang";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                listKode.add(rs.getString("kdBarang"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listKode;
    }
    
    @Override
    public List<String> findKodePelanggan() {
        List<String> listKode = new ArrayList<>();
        String sql = "SELECT kdPelanggan FROM pelanggan";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                listKode.add(rs.getString("kdPelanggan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listKode;
    }


}
