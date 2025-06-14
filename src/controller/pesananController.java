package controller;

import static controller.barangController.isInt;
import java.util.List;
import dao.pesananDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Barang;
import model.Pesanan;
import model.Pelanggan;
import view.formPesanan;

public class pesananController {
    private pesananDao dao;
    public Barang barang;
    public Pelanggan pelanggan;
    public Pesanan pesanan;
    private formPesanan frame;
    private List<Pesanan> lp;

    public pesananController(pesananDao dao, formPesanan frame) {
        this.dao = dao;
        this.frame = frame; 
        this.barang = new Barang();
        this.pelanggan = new Pelanggan();
        this.pesanan = new Pesanan();
    }
    
    public void savePesanan() {
        String no = frame.txtNoPesan().getText();
        String kdPelanggan = (String) frame.cbPelanggan().getSelectedItem();
        String tanggalString = frame.txtTanggal().getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        Date tanggal = null;

        try {
            tanggal = dateFormat.parse(tanggalString); 
        } catch (ParseException e) {
            e.printStackTrace(); 
        }
        pesanan = new Pesanan(tanggal, no, kdPelanggan);
        dao.savePesanan(pesanan);
    }
    
    public void saveDetailPesan() {
        int row = frame.tvData().getRowCount();
        
        for (int i = 0; i < row; i++) {
            String no = frame.txtNoPesan().getText();
            String kode = String.valueOf(frame.tvData().getValueAt(i, 0));
            int jumlah = Integer.parseInt(frame.tvData().getValueAt(i, 3).toString());
            int harga = Integer.parseInt(frame.tvData().getValueAt(i, 2).toString()) * jumlah;
            pesanan = new Pesanan(no, kode, jumlah, harga);
            dao.saveDetailPesanan(pesanan);
        }
        JOptionPane.showMessageDialog(frame, "Data Berhasil Disimpan!", "Done", JOptionPane.OK_OPTION);
        clear();
    }
    
    public void tampilPelanggan(String kode) {       
        pelanggan = dao.findPelangganById(kode);
        frame.txtNmPelanggan().setText(pelanggan.getNama());
        frame.txtAlamat().setText(pelanggan.getAlamat());
        frame.txtTelepon().setText(String.valueOf(pelanggan.getTelepon()));
    }
    
    public void tampilBarang(String kode) {
        barang = dao.findBarangById(kode);
        frame.txtNmBarang().setText(barang.getNama());
        frame.txtSatuan().setText(barang.getSatuan());
        frame.txtHarga().setText(String.valueOf(barang.getHarga()));
        frame.txtJumlah().requestFocus();
    }
    
    public void initComboBox() {
        List<String> kodeBarangList = dao.findKodeBarang();
        frame.cbBarang().setModel(new DefaultComboBoxModel<>(kodeBarangList.toArray(new String[0]))); 

        List<String> kodePelangganList = dao.findKodePelanggan();
        frame.cbPelanggan().setModel(new DefaultComboBoxModel<>(kodePelangganList.toArray(new String[0])));   
    }
    
    public void add() {
        String txtJumlah = frame.txtJumlah().getText();
        
        if (txtJumlah != null && !txtJumlah.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) frame.tvData().getModel();
            String kode = frame.cbBarang().getSelectedItem().toString();
            String nama = frame.txtNmBarang().getText();

            Barang brg = dao.findBarangById(kode);
            
            for (int i = 0; i < model.getRowCount(); i++) {
                String existingKode = (String) model.getValueAt(i, 0); 
                if (existingKode.equals(kode)) {
                    JOptionPane.showMessageDialog(frame, "Kode barang sudah ada", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return; 
                }
            }

            int jumlah = 0;
            try {
                jumlah = Integer.parseInt(txtJumlah);
                if (jumlah <= 0) {
                    JOptionPane.showMessageDialog(frame, "Jumlah harus lebih besar dari 0", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return; 
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Jumlah harus berupa angka", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return; 
            }

            int harga = brg.getHarga();
            int total = jumlah * harga;

            String[] data = {kode, nama, String.valueOf(harga), String.valueOf(jumlah), String.valueOf(total)};
            model.addRow(data);
        } else {
            JOptionPane.showMessageDialog(frame, "Data belum lengkap", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void clear() {
        int response = JOptionPane.showConfirmDialog(frame, "Clear data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) frame.tvData().getModel();
            model.setRowCount(0);
            frame.txtNoPesan().setText("");
            frame.txtTanggal().setText("");
            frame.txtNmPelanggan().setText("");
            frame.txtAlamat().setText("");
            frame.txtTelepon().setText("");
            frame.txtNmBarang().setText("");
            frame.txtSatuan().setText("");
            frame.txtHarga().setText("");
            frame.txtJumlah().setText("");
        }
    }
    
    public boolean isKodeBarangDuplicate(String kodeBarang) {
        DefaultTableModel model = (DefaultTableModel) frame.tvData().getModel();
        Set<String> existingCodes = new HashSet<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            String existingCode = (String) model.getValueAt(i, 0); 
            existingCodes.add(existingCode);
        }

        return existingCodes.contains(kodeBarang);
    }
     
    public void focusLost() {
        String kode = frame.txtNoPesan().getText().trim(); 
        
        if (isInt(kode)) {
            String kd = autoKode(Integer.parseInt(kode));
            frame.txtNoPesan().setText(kd);
        }         
    }
     
    public String autoKode(int hit) {
        if (hit == 0) {
            frame.txtNoPesan().setText("PS001");
            return "PS001";
        } else if (hit < 10) {
            frame.txtNoPesan().setText("PS00" + hit);
            return "PS00" + hit;
        } else if (hit < 100) {
            frame.txtNoPesan().setText("PS0" + hit);
            return "PS0" + hit;
        } else if (hit < 1000) {
            frame.txtNoPesan().setText("PS" + hit);
            return "PS" + hit;
        } else {
            JOptionPane.showMessageDialog(frame, "Jumlah kode terlalu panjang", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return null;
        }             
    }
}
