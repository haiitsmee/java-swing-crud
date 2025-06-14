package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.*;
import javax.swing.JOptionPane;
import koneksi.koneksi;

public class Barang {
    protected String kode,nama,satuan;
    protected int stok,harga;
    
    public Barang(String kode, String nama, String satuan, int stok, int harga){
        this.kode = kode;
        this.nama = nama;
        this.satuan = satuan;
        this.stok = stok;
        this.harga = harga;
    }
    
    public Barang(){}
    
   public String getKode(){return kode;}
   public void setKode(String kode){this.kode = kode;}
   
   public String getNama(){return nama;}
   public void setNama(String nama){this.nama = nama;}
   
   public String getSatuan(){return satuan;}
   public void setSatuan(String satuan){this.satuan = satuan;}
   
   public int getStok(){return stok;}
   public void setStok(int stok){this.stok = stok;}
   
   public int getHarga(){return harga;}
   public void setHarga(int harga){this.harga = harga;} 
    
}