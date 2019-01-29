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
    public void save(Item item){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
           
        try {
            stmt = con.prepareStatement("INSERT INTO PRATOS(DESCRICAO, PRECO)VALUES(?,?)");
            stmt.setString(1,item.getDescription());
            stmt.setDouble(2, item.getPrice());
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
    public List<Item> findAll(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Item> items = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM PRATOS");
            rs=stmt.executeQuery();
            
            while(rs.next()){
                Item item = new Item();
                item.setId(rs.getInt("COD_PRATOS"));
                item.setDescription(rs.getString("DESCRICAO"));
                item.setPrice(rs.getDouble("PRECO"));
                items.add(item);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        
        return items;
    }
    @Override
    public Item findByDescription(String description){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Item item = new Item();
        
        try {
            stmt= con.prepareStatement("SELECT * FROM PRATOS WHERE DESCRICAO = ?");
            stmt.setString(1, description);
            rs=stmt.executeQuery();
            if(rs.next()){
                item.setId(rs.getInt("COD_PRATOS"));
                item.setDescription(rs.getString("DESCRICAO"));
                item.setPrice(rs.getDouble("PRECO"));
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
    public Item findById(int id){
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
                item.setDescription(rs.getString("DESCRICAO"));
                item.setPrice(rs.getDouble("PRECO"));
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
    public void update(Item item){
    Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE PRATOS SET COD_PRATO = ?, DESCRICAO = ?, PRECO = ? WHERE COD_MESA = ?");
            stmt.setInt(1,item.getId());
            stmt.setString(2, item.getDescription());
            stmt.setDouble(3,item.getPrice());
            stmt.executeUpdate();
            System.out.println("Update realizado com sucesso!");
        }catch (SQLException ex) {
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            ConnectionFactory.closeConnetion(con, stmt);
        }
    }
    @Override
    public void remove (Item item){}
    
}
