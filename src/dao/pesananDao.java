/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.Pesanan;
import model.Pelanggan;
import model.Barang;

public interface pesananDao {
    void savePesanan(Pesanan pesanan);
    void saveDetailPesanan(Pesanan pesanan);
    Pelanggan findPelangganById(String kode);
    Barang findBarangById(String kode);
    List<String> findKodeBarang();
    List<String> findKodePelanggan();
}
