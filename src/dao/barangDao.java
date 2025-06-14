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
import model.Barang;

public interface barangDao {
    void save(Barang barang);
    void update(Barang barang);
    void delete(String kode);
    Barang findById(String kode);
    List<Barang> findAll();
}
