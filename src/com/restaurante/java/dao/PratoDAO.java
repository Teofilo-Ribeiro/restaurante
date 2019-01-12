/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.dao;

import com.restaurante.java.model.Prato;
import com.restaurante.java.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author teo
 */
public class PratoDAO {
    public void create(Prato p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO PRATOS(DESCRICAO, PRECO)VALUES(?,?)");
            stmt.setString(1,p.getDescricao());
            stmt.setDouble(2, p.getPreco());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar!");
            throw new RuntimeException ("ERRO: ",ex);
        }
        finally{
            ConnectionFactory.closeConnetion(con, stmt);
        }
    }
    
    /*public List<Prato> listarPratos(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Prato> pratos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM PRATOS");
            rs=stmt.executeQuery();
            
            while(rs.next()){
                Prato prato = new Prato();
                prato.setId(rs.getInt("COD_PRATOS"));
                prato.setDescricao(rs.getString("DESCRICAO"));
                prato.setPreco(rs.getDouble("PRECO"));
                pratos.add(prato);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(PratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        
        return pratos;
    }*/
    public Prato listarPratos(String Descricao){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Prato prato = new Prato();
        
        try {
            stmt= con.prepareStatement("SELECT * FROM PRATOS WHERE DESCRICAO = ?");
            stmt.setString(1, Descricao);
            rs=stmt.executeQuery();
            if(rs.next()){
                prato.setId(rs.getInt("COD_PRATOS"));
                prato.setDescricao(rs.getString("DESCRICAO"));
                prato.setPreco(rs.getDouble("PRECO"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        
        
        return prato;
    }
}
