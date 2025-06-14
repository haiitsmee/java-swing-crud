/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.pelangganDao;
import java.util.List;
import javax.swing.JOptionPane;
import model.Pelanggan;
import view.formPelanggan;

/**
 *
 * @author acer
 */
public class pelangganController {
    private pelangganDao dao;
    public Pelanggan pelanggan;
    private formPelanggan frame;
    private List<Pelanggan> lp;

    public pelangganController(pelangganDao dao, formPelanggan frame) {
        this.dao = dao;
        this.frame = frame; 
        this.pelanggan = new Pelanggan();
    }


    public void create() {
        getDataFromTxt();
        if(pelanggan != null){
            dao.save(pelanggan);  
            frame.tampilTable();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah!");
            clear();
        }
        
        
    }


    public void read() {
        getDataFromTxt();
        String kode = pelanggan.getKode();
        pelanggan = dao.findById(kode);
    }


    public void update() {
        try{
            getDataFromTxt();
            dao.update(pelanggan);
            frame.tampilTable();
            JOptionPane.showMessageDialog(null, "Data berhasil di-update!");
        } catch (Exception e){
            e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi Kesalahan", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
        
    }

    public void delete() {
        int response = JOptionPane.showConfirmDialog(
                null, 
                "Apakah Anda yakin ingin menghapus pelanggan " + pelanggan.getNama() + "?", 
                "Konfirmasi Hapus", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE
        );
        
        if (response == JOptionPane.YES_OPTION) {
            System.out.println("Barang"+pelanggan.getNama()+" dihapus.");
            dao.delete(pelanggan.getKode());
            frame.tampilTable();
            clear();
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
        
        
    }

    public List<Pelanggan> showAllPelanggan() {
        return lp = dao.findAll();
    }


    public void clear() {
        if (frame != null) { 
            clearFrame();
            frame.getTxtKode().requestFocus();
            
            if(pelanggan != null){
                frame.getTxtKode().setText(pelanggan.getKode());
            }
            
            setButtonState(false,false,false);
            frame.getTxtKode().requestFocus();
            frame.getTxtKode().setText("");
        } else {
            System.out.println("Frame belum diinisialisasi!");
        }
    }
    
    public void focusLost() {
        String kode = frame.getTxtKode().getText().trim(); 
        pelanggan = dao.findById(kode);

        if (pelanggan != null) {
            updateFrameWithBarang(pelanggan);
            setButtonState(false, true, true);
        } else {
            if (isInt(kode)) {
                String kd = autoKode(Integer.parseInt(kode));
                pelanggan = dao.findById(kd);

                if (pelanggan != null) {
                    updateFrameWithBarang(pelanggan);
                    setButtonState(false, true, true);
                } else {
                    clearFrame();
                    setButtonState(true, false, false);
                }
            } else {
                clearFrame();
                setButtonState(true, false, false);
            }
        }
    }


    private void updateFrameWithBarang(Pelanggan pelanggan) {
        frame.getTxtKode().setText(pelanggan.getKode());
        frame.getTxtNama().setText(pelanggan.getNama());
        frame.getTxtAlamat().setText(pelanggan.getAlamat());
        frame.getTxtTelepon().setText(pelanggan.getTelepon());
    }


    public void setButtonState(boolean simpan, boolean ubah, boolean hapus) {
        frame.getBtnSimpan().setEnabled(simpan);
        frame.getBtnUbah().setEnabled(ubah);
        frame.getBtnHapus().setEnabled(hapus);
    }


    private void clearFrame() {
        frame.getTxtNama().setText("");
        frame.getTxtAlamat().setText("");
        frame.getTxtTelepon().setText("");
    }


    public String autoKode(int hit){
        if(hit == 0){
            frame.getTxtKode().setText("P0001");
            return "P001";
        }else if (hit<10){
            frame.getTxtKode().setText("P000" + hit);
            return "P000"+hit;
        } else if (hit<100){
            frame.getTxtKode().setText("P00" + hit);
            return "P00" + hit;
        } else if (hit<1000){
            frame.getTxtKode().setText("P0" + hit);
            return "P0" + hit;
        } else{
            frame.getTxtKode().setText("P" + hit);
            return "P" + hit;
        }             
    }

    public static boolean isInt(String data) {
        try {
            Integer.parseInt(data);  
            return true;  
        } catch (NumberFormatException e) {
            return false; 
        }
    }


    public void getDataFromTxt(){
        String kode = frame.getTxtKode().getText();
        String nama = frame.getTxtNama().getText();
        String alamat = frame.getTxtAlamat().getText();
        String telepon = frame.getTxtTelepon().getText();      
        pelanggan = new Pelanggan(kode, nama, alamat, telepon);      
    }
}
