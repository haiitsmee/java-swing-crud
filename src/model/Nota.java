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
public class Nota {
    protected String nonota, nopesan, p1,p2;
    protected Date tgl;
    
    public Nota(){}
    
    public Nota(String nonota, String nopesan, Date tgl ){
        this.nonota = nonota;
        this.nopesan = nopesan;
        this.tgl = tgl;
    }
    
    public void setNoNota(String nonota){
        this.nonota = nonota;
    }
    public void setNoPesan(String nopesan){
        this.nopesan = nopesan;
    }
    public void setTanggal(Date tgl){
        this.tgl = tgl;
    }
    public String getNoNota(){
        return nonota;
    }
    public String getNoPesan(){
        return nopesan;
    }
    public Date getTanggal(){
        return tgl;
    }
    public String getP1(){
        return p1;
    }
    public String getP2(){
        return p2;
    }
    public void setP1(String p1){
        this.p1 = p1;
    }
    public void setP2(String p2){
        this.p2 = p2;
    }
    
}
