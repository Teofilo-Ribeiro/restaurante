/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.dao;

import com.restaurante.java.model.Item;
import com.restaurante.java.connection.ConnectionFactory;
import com.restaurante.java.interfaces.ItemDao;
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
public class ItemDaoJDBC implements ItemDao{
    
    @Override
    public void salvar(Item p){
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
    
    @Override
    public List<Item> buscarTodos(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Item> pratos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM PRATOS");
            rs=stmt.executeQuery();
            
            while(rs.next()){
                Item prato = new Item();
                prato.setId(rs.getInt("COD_PRATOS"));
                prato.setDescricao(rs.getString("DESCRICAO"));
                prato.setPreco(rs.getDouble("PRECO"));
                pratos.add(prato);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        
        return pratos;
    }
    @Override
    public Item buscarPorDescricao(String Descricao){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Item prato = new Item();
        
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
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        
        
        return prato;
    }
    @Override
    public Item buscarPorId(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Item item = new Item();
        
        try {
            stmt= con.prepareStatement("SELECT * FROM PRATOS WHERE COD_PRATO = ?");
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            if(rs.next()){
                item.setId(rs.getInt("COD_PRATOS"));
                item.setDescricao(rs.getString("DESCRICAO"));
                item.setPreco(rs.getDouble("PRECO"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        
        
        return item;
        
    } 
    @Override
    public void atualizar(Item item){
    Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE PRATOS SET COD_PRATO = ?, DESCRICAO = ?, PRECO = ? WHERE COD_MESA = ?");
            stmt.setInt(1,item.getId());
            stmt.setString(2, item.getDescricao());
            stmt.setDouble(3,item.getPreco());
            stmt.executeUpdate();
            System.out.println("Update realizado com sucesso!");
        }catch (SQLException ex) {
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            ConnectionFactory.closeConnetion(con, stmt);
        }
    }
    @Override
    public void remover (Item item){}
    
}
