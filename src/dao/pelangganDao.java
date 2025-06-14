/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

/**
 *
 * @author acer
 */
import java.util.List;
import model.Pelanggan;

public interface pelangganDao {
    void save(Pelanggan pelanggan);
    void update(Pelanggan pelanggan);
    void delete(String kode);
    Pelanggan findById(String kode);
    List<Pelanggan> findAll();
}