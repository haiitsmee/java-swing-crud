/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

/**
 *
 * @author acer
 */
import java.util.List;
import model.Nota;

public interface notaDao {
    void save(Nota nota);
    public List<String> getAllPesanan(); 
    public void print(Nota nota);
    public void recap(Nota nota);

}
