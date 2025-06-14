/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */

public class Pelanggan {
    
    protected String kode,nama, alamat, telepon;
    
    public Pelanggan(){}
    
    public Pelanggan(String kode, String nama, String alamat, String telepon){
        this.kode = kode;
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
    }
    
    public void setKode(String kd){kode = kd;}
    public String getKode(){return(kode);}
    
    public void setNama(String nm){
        nama = nm;
    }
    public void setAlamat(String st){
        alamat = st;
    }
    public void setTelepon(String hrg){
        telepon = hrg;
    }
    
    public String getNama(){
        return(nama);
    }
    public String getAlamat(){
        return(alamat);
    }
    public String getTelepon(){
        return(telepon);
    }
    
    
}
