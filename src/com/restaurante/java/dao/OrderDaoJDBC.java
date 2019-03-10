/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.dao;

import com.restaurante.java.connection.ConnectionFactory;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.interfaces.OrderDao;
import com.restaurante.java.model.Command;
import com.restaurante.java.model.Item;
import com.restaurante.java.model.Order;
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
public class OrderDaoJDBC implements OrderDao{
    
    Connection con;

    public OrderDaoJDBC() throws DbException{
        this.con = ConnectionFactory.getConnection();
    }
    @Override
    public void create(Order order)throws DbException{
        //Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;   
        try {
            stmt = con.prepareStatement("INSERT INTO PEDIDOS(COD_ITEM, N_COMANDA, OBS, QUANTIDADE)VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,order.getItem().getId());
            stmt.setInt(2, order.getCommandId());
            stmt.setString(3, order.getNote());
            stmt.setInt(4, order.getQty());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if(rs.next())
                order.setId(rs.getInt(1));
            else
                throw new DbException("ERRO AO SALVAR PEDIDO, ID DO PEDIDO NÃO FOI RETORNADO!");
        } catch (SQLException ex) {            
            throw new DbException ("ERRO AO SALVAR PEDIDO: " + ex.getMessage());
        }
        finally{
            ConnectionFactory.closeConnetion(null,stmt);
        }        
    }
    @Override
    public List<Order> findAll()throws DbException{
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Order> orders = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT PEDIDOS.*, " +
"                                        COMANDAS.ABERTA AS COMANDA_ABERTA, COMANDAS.NOME_CLIENTE, COMANDAS.COD_MESA, " +
"                                        ITENS.DESCRICAO, ITENS.PRECO " +
"                                        FROM PEDIDOS INNER JOIN COMANDAS " +
"                                        ON PEDIDOS.N_COMANDA = COMANDAS.N_COMANDA " +
"                                        INNER JOIN ITENS " +
"                                        ON ITENS.COD_ITEM = PEDIDOS.COD_ITEM");
            rs=stmt.executeQuery();
            
            while(rs.next()){
                              
                Item item = new Item();
                item.setId(rs.getInt("COD_ITEM"));
                item.setDescription(rs.getString("DESCRICAO"));
                item.setPrice(rs.getFloat("PRECO"));
                
                                
                Order order = new Order(rs.getInt("COD_PEDIDO"),null, null, rs.getString("OBS"),item,rs.getInt("QUANTIDADE"),rs.getInt("N_COMANDA"),rs.getInt("COD_MESA"));                
                orders.add(order);
            
            }
        } catch (SQLException ex) {
            throw new DbException ("ERRO AO BUSCAR PEDIDO " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnetion(null,stmt,rs);
        }
        
        return orders;
    }
    @Override
    public Order findById(int id)throws DbException{
        //Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Order order = null; 
        
        try {
            stmt = con.prepareStatement("SELECT PEDIDOS.*, " +
"                                        COMANDAS.ABERTA AS COMANDA_ABERTA, COMANDAS.NOME_CLIENTE, COMANDAS.COD_MESA, " +
"                                        ITENS.DESCRICAO, ITENS.PRECO " +
"                                        FROM PEDIDOS INNER JOIN COMANDAS " +
"                                        ON PEDIDOS.N_COMANDA = COMANDAS.N_COMANDA " +
"                                        INNER JOIN ITENS " +
"                                        ON ITENS.COD_ITEM = PEDIDOS.COD_ITEM "+
"                                        WHERE PEDIDOS.COD_PEDIDO = ?");
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            if(rs.next()){
                Item item = new Item();
                item.setId(rs.getInt("COD_ITEM"));
                item.setDescription(rs.getString("DESCRICAO"));
                item.setPrice(rs.getFloat("PRECO"));                
                                
                order = new Order(rs.getInt("COD_PEDIDO"),null, null, rs.getString("OBS"),item,rs.getInt("QUANTIDADE"),rs.getInt("N_COMANDA"),rs.getInt("COD_MESA"));                
                
            }
        } catch (SQLException ex) {
            throw new DbException ("ERRO AO BUSCAR PEDIDO: " + ex.getMessage());
            
        }
        finally{
            ConnectionFactory.closeConnetion(null, stmt, rs);
        }
        
        
        return order;
        
    }
    @Override
    public List <Order> findByCommand(int commandId)throws DbException{
        //Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Order order = null; 
        List<Order> orders = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT PEDIDOS.*, " +
"                                        COMANDAS.ABERTA AS COMANDA_ABERTA, COMANDAS.NOME_CLIENTE, COMANDAS.COD_MESA, " +
"                                        ITENS.DESCRICAO, ITENS.PRECO " +
"                                        FROM PEDIDOS INNER JOIN COMANDAS " +
"                                        ON PEDIDOS.N_COMANDA = COMANDAS.N_COMANDA " +
"                                        INNER JOIN ITENS " +
"                                        ON ITENS.COD_ITEM = PEDIDOS.COD_ITEM "+
"                                        WHERE PEDIDOS.N_COMANDA= ?");
            stmt.setInt(1, commandId);
            rs=stmt.executeQuery();
            while(rs.next()){
                Item item = new Item();
                item.setId(rs.getInt("COD_ITEM"));
                item.setDescription(rs.getString("DESCRICAO"));
                item.setPrice(rs.getFloat("PRECO"));                
                                
                order = new Order(rs.getInt("COD_PEDIDO"),null, null, rs.getString("OBS"),item,rs.getInt("QUANTIDADE"),rs.getInt("N_COMANDA"),rs.getInt("COD_MESA"));                
                orders.add(order);
            }
        } catch (SQLException ex) {
            throw new DbException ("ERRO AO BUSCAR PEDIDO: " + ex.getMessage());
            
        }
        finally{
            ConnectionFactory.closeConnetion(null, stmt, rs);
        }
        
        
        return orders;
        
    }
    
  
    @Override
    public List<Order> findAllNotDone()throws DbException{
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Order> orders = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT PEDIDOS.*, " +
"                                        COMANDAS.ABERTA AS COMANDA_ABERTA, COMANDAS.NOME_CLIENTE, COMANDAS.COD_MESA, " +
"                                        ITENS.DESCRICAO, ITENS.PRECO " +
"                                        FROM PEDIDOS INNER JOIN COMANDAS " +
"                                        ON PEDIDOS.N_COMANDA = COMANDAS.N_COMANDA " +
"                                        INNER JOIN ITENS " +
"                                        ON ITENS.COD_ITEM = PEDIDOS.COD_ITEM "+
"                                        WHERE PEDIDOS.FINALIZADO = FALSE;");
            rs=stmt.executeQuery();
            
            while(rs.next()){
                              
                Item item = new Item();
                item.setId(rs.getInt("COD_ITEM"));
                item.setDescription(rs.getString("DESCRICAO"));
                item.setPrice(rs.getFloat("PRECO"));
                
                                
                Order order = new Order(rs.getInt("COD_PEDIDO"),null, null, rs.getString("OBS"),item,rs.getInt("QUANTIDADE"),rs.getInt("N_COMANDA"),rs.getInt("COD_MESA"));                
                orders.add(order);
            
            }
        } catch (SQLException ex) {
            throw new DbException ("ERRO AO BUSCAR PEDIDO " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnetion(null,stmt,rs);
        }
        
        return orders;
    }
     @Override
    public void update(Order order)throws DbException{
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE PEDIDOS SET QUANTIDADE = ?, FINALIZADO = ? WHERE COD_PEDIDO = ?");
            
            //stmt,setDate(1,null);
            stmt.setInt(1, order.getQty());
            stmt.setBoolean(2, order.getDone());
            stmt.setInt(3, order.getId());      
            
            stmt.executeUpdate();
            
        }catch (SQLException ex) {
            throw new DbException ("ERRO AO ATUALIZAR PEDIDO:  " + ex.getMessage());
            
        }finally{
            ConnectionFactory.closeConnetion(null, stmt);
        }
    }
    @Override
    public void delete (Order order)throws DbException{
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM PEDIDOS WHERE COD_PEIDO = ?");
            stmt.setInt(1, order.getId());
            stmt.executeUpdate();            
        }catch (SQLException ex) {
            throw new DbException ("ERRO AO DELETAR PEDIDO " + ex.getMessage());
            
        }finally{
            ConnectionFactory.closeConnetion(null, stmt);
        }    
    }
}
