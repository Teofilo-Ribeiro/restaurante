/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.dao;

import com.restaurante.java.model.Item;
import com.restaurante.java.connection.ConnectionFactory;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.interfaces.ItemDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author teo
 */
public class ItemDaoJDBC implements ItemDao {
    Connection con;

    public ItemDaoJDBC() throws DbException{
        this.con = ConnectionFactory.getConnection();
    }
    @Override
    public void create(Item item)throws DbException{
        //Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;   
        try {
            stmt = con.prepareStatement("INSERT INTO ITENS(DESCRICAO, PRECO)VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,item.getDescription());
            stmt.setDouble(2, item.getPrice());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if(rs.next())
                item.setId(rs.getInt(1));
            else
                throw new DbException("ERRO AO SALVAR ITEM, ID DO PRODUTO NÃO FOI RETORNADO!");
        } catch (SQLException ex) {            
            throw new DbException ("ERRO AO SALVAR ITEM: " + ex.getMessage());
        }
        finally{
            ConnectionFactory.closeConnetion(null,stmt);
        }        
    }
    
    @Override
    public List<Item> findAll()throws DbException{
        //Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Item> items = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM ITENS");
            rs=stmt.executeQuery();
            
            while(rs.next()){
                Item item = new Item();
                item.setId(rs.getInt("COD_ITEM"));
                item.setDescription(rs.getString("DESCRICAO"));
                item.setPrice(rs.getDouble("PRECO"));
                items.add(item);
            
            }
        } catch (SQLException ex) {
            throw new DbException ("ERRO AO BUSCAR ITEM: " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnetion(null,stmt,rs);
        }
        
        return items;
    }
    @Override
    public Item findByDescription(String description)throws DbException{
        //Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Item item = null;
        
        try {
            stmt= con.prepareStatement("SELECT * FROM ITENS WHERE DESCRICAO = ?");
            stmt.setString(1, description);
            rs=stmt.executeQuery();
            if(rs.next()){
                item = new Item();
                item.setId(rs.getInt("COD_ITEM"));
                item.setDescription(rs.getString("DESCRICAO"));
                item.setPrice(rs.getDouble("PRECO"));
            }
        } catch (SQLException ex) {
            throw new DbException ("ERRO AO BUSCAR ITEM: " + ex.getMessage());
        }
        finally{
            ConnectionFactory.closeConnetion(null, stmt, rs);
        }
        
        
        return item;
    }
    @Override
    public List<Item> smartFindByDescription(String description) throws DbException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Item> items = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM ITENS WHERE DESCRICAO LIKE ?");
            stmt.setString(1, "%" + description + "%");
            rs=stmt.executeQuery();
            
            while(rs.next()){
                Item item = new Item();
                item.setId(rs.getInt("COD_ITEM"));
                item.setDescription(rs.getString("DESCRICAO"));
                item.setPrice(rs.getDouble("PRECO"));
                items.add(item);
            
            }
        } catch (SQLException ex) {
            throw new DbException ("ERRO AO BUSCAR ITEM: " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnetion(null,stmt,rs);
        }
        
        return items;
    
    }    
    @Override
    public Item findById(int id)throws DbException{
        //Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Item item= null; 
        
        try {
            stmt= con.prepareStatement("SELECT * FROM ITENS WHERE COD_ITEM = ?");
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            if(rs.next()){
                item = new Item();
                item.setId(rs.getInt("COD_ITEM"));
                item.setDescription(rs.getString("DESCRICAO"));
                item.setPrice(rs.getDouble("PRECO"));
            }
        } catch (SQLException ex) {
            throw new DbException ("ERRO AO BUSCAR ITEM: " + ex.getMessage());
            
        }
        finally{
            ConnectionFactory.closeConnetion(null, stmt, rs);
        }
        
        
        return item;
        
    } 
    @Override
    public void update(Item item)throws DbException{
        //Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE ITENS SET DESCRICAO = ?, PRECO = ? WHERE COD_ITEM = ?");
            //stmt.setInt(1,item.getId());
            stmt.setString(1, item.getDescription());
            stmt.setDouble(2,item.getPrice());
            stmt.setInt(3, item.getId());
            stmt.executeUpdate();
            System.out.println("Update realizado com sucesso!");
        }catch (SQLException ex) {
            throw new DbException ("ERRO AO SALVAR O ITEM: " + ex.getMessage());
            
        }finally{
            ConnectionFactory.closeConnetion(null, stmt);
        }
    }
    @Override
    public void delete (Item item)throws DbException{
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM ITENS WHERE COD_ITEM = ?");
            stmt.setInt(1, item.getId());
            stmt.executeUpdate();
            System.out.println("delete realizado com sucesso!");
        }catch (SQLException ex) {
            throw new DbException ("ERRO AO DELETAR ITEM: " + ex.getMessage());
            
        }finally{
            ConnectionFactory.closeConnetion(null, stmt);
        }    
    }
    
}
