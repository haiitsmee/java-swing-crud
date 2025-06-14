package controller;

import java.util.List;
import dao.barangDao;
import javax.swing.JOptionPane;
import model.Barang;
import view.formBarang;

public class barangController {

    private barangDao dao;
    public Barang barang;
    private formBarang frame;
    private List<Barang> lb;

    public barangController(barangDao dao, formBarang frame) {
        this.dao = dao;
        this.frame = frame; 
        this.barang = new Barang();
    }


    public void create() {
        getDataFromTxt();
        if(barang != null){
            dao.save(barang);  
            frame.tampilTable();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah!");
            clear();
        }        
    }


    public void read() {
        getDataFromTxt();
        String kode = barang.getKode();
        barang = dao.findById(kode);
    }


    public void update() {
        try{
            getDataFromTxt();
            dao.update(barang);
            frame.tampilTable();
            JOptionPane.showMessageDialog(null, "Data berhasil di-update!");
        } catch (Exception e){
        JOptionPane.showMessageDialog(null, "Terjadi Kesalahan", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
        
    }

    public void delete() {
        int response = JOptionPane.showConfirmDialog(
                null, 
                "Apakah Anda yakin ingin menghapus barang " + barang.getNama() + "?", 
                "Konfirmasi Hapus", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE
        );
        
        if (response == JOptionPane.YES_OPTION) {
            System.out.println(barang.getNama()+" dihapus.");
            System.out.println(barang.getKode());
            dao.delete(barang.getKode());
            frame.tampilTable();
            clear();
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
        
        
    }

    public List<Barang> showAllBarang() {
        return lb = dao.findAll();
    }


    public void clear() {
        if (frame != null) { 
            clearFrame();
            frame.getTxtKode().requestFocus();
            
            if(barang != null){
                frame.getTxtKode().setText(barang.getKode());
            }
            
            setButtonState(false,false,false);
            frame.getTxtKode().requestFocus();
            frame.getTxtKode().setText("");
        } else {
            System.out.println("Frame belum diinisialisasi!");
        }
    }
    
    public void focusLost() {
        String kode = frame.getTxtKode().getText(); 
        barang = dao.findById(kode);

        if (barang != null) {
            updateFrameWithBarang(barang);
            setButtonState(false, true, true);
        } else {
            if (isInt(kode)) {
                String kd = autoKode(Integer.parseInt(kode));
                barang = dao.findById(kd);

                if (barang != null) {
                    updateFrameWithBarang(barang);
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


    private void updateFrameWithBarang(Barang barang) {
        frame.getTxtKode().setText(barang.getKode());
        frame.getTxtNama().setText(barang.getNama());
        frame.getTxtSatuan().setText(barang.getSatuan());
        frame.getTxtHarga().setText(String.valueOf(barang.getHarga()));
        frame.getTxtStok().setText(String.valueOf(barang.getStok()));
    }


    public void setButtonState(boolean simpan, boolean ubah, boolean hapus) {
        frame.getBtnSimpan().setEnabled(simpan);
        frame.getBtnUbah().setEnabled(ubah);
        frame.getBtnHapus().setEnabled(hapus);
    }


    private void clearFrame() {
        frame.getTxtNama().setText("");
        frame.getTxtSatuan().setText("");
        frame.getTxtHarga().setText("");
        frame.getTxtStok().setText("");
    }


    public String autoKode(int hit){
        if(hit == 0){
            frame.getTxtKode().setText("B0001");
            return "B0001";
        }else if (hit<10){
            frame.getTxtKode().setText("B000" + hit);
            return "B000"+hit;
        } else if (hit<100){
            frame.getTxtKode().setText("B00" + hit);
            return "B00" + hit;
        } else if (hit<1000){
            frame.getTxtKode().setText("B0" + hit);
            return "B0" + hit;
        } else{
            frame.getTxtKode().setText("B" + hit);
            return "B" + hit;
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
        String kd = frame.getTxtKode().getText();
        String nm = frame.getTxtNama().getText();
        String st = frame.getTxtSatuan().getText();
        int hrg = Integer.parseInt(frame.getTxtHarga().getText());
        int stk = Integer.parseInt(frame.getTxtStok().getText());       
        barang = new Barang(kd,nm,st,hrg,stk);      
    }
    
}
