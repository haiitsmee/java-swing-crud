                                                                                                        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author acer
 */
public class Pesanan {
    String noPesan,kdPelanggan, kdBarang;
    Date tanggal;
    int jumlah, harga;
    
    public Pesanan(){}
    
    public Pesanan(Date tanggal, String noPesan, String kdPelanggan){
        this.tanggal = tanggal;
        this.noPesan = noPesan;
        this.kdPelanggan = kdPelanggan;
    }
    
    public Pesanan(String noPesan, String kdBarang, int jumlah, int harga ){
        this.noPesan = noPesan;
        this.kdBarang = kdBarang;
        this.jumlah = jumlah;
        this.harga = harga;
    }
    
    public Pesanan(Date tanggal, String noPesan, String kdPelanggan, String kdBarang, int jumlah, int harga){
    this.tanggal = tanggal;
    this.noPesan = noPesan;
    this.kdPelanggan = kdPelanggan;
    this.kdBarang = kdBarang;
    this.jumlah = jumlah;
    this.harga = harga;
    }
    
    public String getNoPesan(){
        return noPesan;
    } public void setNoPesan(String noPesan){
        this.noPesan=noPesan;
    }public String getkdPelanggan(){
        return kdPelanggan;
    } public void setKdPelanggan(String kdPelanggan){
        this.kdPelanggan=kdPelanggan;
    }public String getKdBarang(){
        return kdBarang;
    } public void setKdBarang(String kdBarang){
        this.kdBarang=kdBarang;
    }public Date getTanggal(){
        return tanggal;
    } public void setTanggal(Date tanggal){
        this.tanggal=tanggal;
    }public int getJumlah(){
        return jumlah;
    } public void setJumlah(int jumlah){
        this.jumlah=jumlah;
    }public int getHarga(){
        return harga;
    } public void setHarga(int harga){
        this.harga=harga;
    }
    
}
