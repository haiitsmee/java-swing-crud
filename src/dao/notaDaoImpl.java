/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author acer
 */
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import model.Nota;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class notaDaoImpl implements notaDao {
    private Connection connection;

     public notaDaoImpl(Connection connection) {
        this.connection = connection;
    }

    
    public void save(Nota nota){
        String sql = "INSERT INTO nota (noNota,tanggal,noPesan) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tanggalFormatted = dateFormat.format(nota.getTanggal());
            
            statement.setString(1, nota.getNoNota());
            statement.setString(2, tanggalFormatted);
            statement.setString(3, nota.getNoPesan());
            
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Disimpan!", "Berhasil", JOptionPane.OK_OPTION);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
       

        
        
    }
    
    public List<String> getAllPesanan(){
    List<String> listNoPesan = new ArrayList<>();
    String sql = "SELECT noPesan FROM pesanan WHERE noPesan NOT IN (SELECT noPesan FROM nota)";

    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)) {

        while (resultSet.next()) {
            String noPesan = resultSet.getString("noPesan");
            listNoPesan.add(noPesan);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listNoPesan;
}

    @Override
    public void print(Nota nota) {
        try {
            JasperPrint jPrint = null;  
            
            String fileName = "C:\\CodingSkuyy\\netbin\\penjualan\\src\\report\\nota.jasper";
            String noNota = nota.getNoNota();
            System.out.println(noNota);
            HashMap map = new HashMap();
            map.put("nomorNota", noNota);

            File reportFile = new File(fileName);
            JasperReport jReport = (JasperReport) JRLoader.loadObject(reportFile);

            jPrint = JasperFillManager.fillReport(jReport, map, connection);

            JasperViewer.viewReport(jPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Tidak dapat menampilkan Nota!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *
     * @param nota
     */
    @Override
    public void recap(Nota nota) {  
        try{
            JasperPrint jPrint = null;  

            String fileName = "C:\\CodingSkuyy\\netbin\\penjualan\\src\\report\\lapJual.jasper";
            String p1 = nota.getP1();   
            String p2 = nota.getP2();  

            HashMap map = new HashMap();
                map.put("periode1", p1);
                map.put("periode2", p2);

            File reportFile = new File(fileName);
            JasperReport jReport = (JasperReport) JRLoader.loadObject(reportFile);

            jPrint = JasperFillManager.fillReport(jReport, map, connection);

            JasperViewer.viewReport(jPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);

        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Tidak dapat menampilkan Nota!", "Error", JOptionPane.ERROR_MESSAGE);
        
        }
    }
    
}
