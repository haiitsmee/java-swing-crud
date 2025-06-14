/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import dao.notaDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Nota;
import view.formNota;
import view.formLaporanPenjualan;


public class notaController {
    private notaDao dao;
    public Nota nota;
    private formNota frame;
    private formLaporanPenjualan frame2;
    private List<Nota> ln;
    
    public notaController(notaDao dao, formNota frame) {
        this.dao = dao;
        this.frame = frame; 
        this.nota = new Nota();
    }
    
    public notaController(notaDao dao, formLaporanPenjualan frame) {
        this.dao = dao;
        this.frame2 = frame; 
        this.nota = new Nota();
    }
    
    public void save(){
        String noNota = frame.getNoNota().getText();
        String tanggalString = frame.getTanggal().getText();
        String noPesan = frame.getcbPesan().getSelectedItem().toString();
        System.out.println(noPesan);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        Date tanggal = null;

        try {
            tanggal = dateFormat.parse(tanggalString); 
        } catch (ParseException e) {
            e.printStackTrace(); 
        }
        this.nota = new Nota(noNota, noPesan, tanggal);
        dao.save(nota);
        Print();
        frame.getNoNota().setText("");
        frame.getTanggal().setText("");
        
    }
    
    public void initComboBox() {
        List<String> noPesan = dao.getAllPesanan();
        frame.getcbPesan().setModel(new DefaultComboBoxModel<>(noPesan.toArray(new String[0]))); 
    }
    
    public void clear(){
        int response = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin ingin menghapus semua data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
           frame.getNoNota().setText("");
           frame.getTanggal().setText("");
            
        }
    }
    
    public void Print(){
        dao.print(nota);
    }
    
    public void recap(){
        dao.recap(nota);
    }
    
    public void savePeriode(){
        nota.setP1(frame2.p1().getText());
        nota.setP2(frame2.p2().getText());
        recap();
    }
    
   
}
