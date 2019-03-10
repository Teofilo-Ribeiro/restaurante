/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.dao;

import com.restaurante.java.connection.ConnectionFactory;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.interfaces.CommandDao;
import com.restaurante.java.model.Command;
import com.restaurante.java.model.Item;
import com.restaurante.java.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author teo
 */
public class CommandDaoJDBC implements CommandDao {
    Connection con;

    public CommandDaoJDBC() throws DbException{
        this.con = ConnectionFactory.getConnection();
    }
    
    @Override
    public void create(Command command)throws DbException{
        //Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;   
        try {
            stmt = con.prepareStatement("INSERT INTO COMANDAS(COD_MESA)VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,command.getTable());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if(rs.next())
                command.setId(rs.getInt(1));
            else
                throw new DbException("ERRO AO SALVAR COMANDA, ID DA COMANDA NÃO FOI RETORNADO!");
        } catch (SQLException ex) {            
            throw new DbException ("ERRO AO SALVAR COMANDA: " + ex.getMessage());
        }
        finally{
            ConnectionFactory.closeConnetion(null,stmt);
        }        
    }
    @Override
    public List<Command> findAll()throws DbException{
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean newCommand = false;
        
        List<Command> commands = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT PEDIDOS.*, " +
"                                        COMANDAS.ABERTA AS COMANDA_ABERTA, COMANDAS.NOME_CLIENTE, COMANDAS.COD_MESA, " +
"                                        ITENS.DESCRICAO, ITENS.PRECO " +
"                                        FROM PEDIDOS INNER JOIN COMANDAS " +
"                                        ON PEDIDOS.N_COMANDA = COMANDAS.N_COMANDA " +
"                                        INNER JOIN ITENS " +
"                                        ON ITENS.COD_ITEM = PEDIDOS.COD_ITEM;");
            rs=stmt.executeQuery();
            
            Map<Integer, Command> map = new HashMap<>();
            
            
            
            while(rs.next()){
                
                Command command = map.get(rs.getInt("N_COMANDA"));
                
                if (command == null){
                    command = new Command();
                    command.setId(rs.getInt("N_COMANDA"));
                    command.setIsOpen(rs.getBoolean("COMANDA_ABERTA"));
                    command.setTable(rs.getInt("COD_MESA"));
                    //command.setDateHour(rs.getDate(""));
                    //command.setStaff(rs.getString(""));                    
                    map.put(rs.getInt("N_COMANDA"), command);
                    newCommand =true;
                }
                
                Item item = new Item();
                item.setId(rs.getInt("COD_ITEM"));
                item.setDescription(rs.getString("DESCRICAO"));
                item.setPrice(rs.getFloat("PRECO"));                
                                
                Order order = new Order(rs.getInt("COD_PEDIDO"),null, null, rs.getString("OBS"),item,rs.getInt("QUANTIDADE"),rs.getInt("N_COMANDA"),rs.getInt("COD_MESA")); 
                command.addOrder(order);
                
                
                if (newCommand){
                    commands.add(command);
                    newCommand = false;
                }
                
            }
        } catch (SQLException ex) {
            throw new DbException ("ERRO AO BUSCAR COMANDA " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnetion(null,stmt,rs);
        }
        
        return commands;
    }
    @Override
    public Command findById(int id)throws DbException{        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Command command = null; 
        
        try {
            stmt= con.prepareStatement("SELECT * FROM COMANDAS WHERE N_COMANDA = ?");
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            if(rs.next()){
                command = new Command();
                command.setId(rs.getInt("N_COMANDA"));
                //command.setDateHour(rs.getDate(""));
                command.setIsOpen(rs.getBoolean("ABERTA"));
                //command.setStaff(rs.getString(""));
                command.setTable(rs.getInt("COD_MESA"));
                
                
            }
        } catch (SQLException ex) {
            throw new DbException ("ERRO AO BUSCAR COMANDA: " + ex.getMessage());
            
        }
        finally{
            ConnectionFactory.closeConnetion(null, stmt, rs);
        }
        
        
        return command;
        
    }
  
    @Override
    public Command findCommandOpen(int tableId)throws DbException{
        //Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Command command = null; 
        
        try {
            //stmt= con.prepareStatement("SELECT * FROM COMANDAS WHERE COD_MESA = ? AND ABERTA = TRUE");
            stmt = con.prepareStatement("SELECT PEDIDOS.*, " +
"                                        COMANDAS.ABERTA AS COMANDA_ABERTA, COMANDAS.NOME_CLIENTE, COMANDAS.COD_MESA, " +
"                                        ITENS.DESCRICAO, ITENS.PRECO " +
"                                        FROM PEDIDOS INNER JOIN COMANDAS " +
"                                        ON PEDIDOS.N_COMANDA = COMANDAS.N_COMANDA " +
"                                        INNER JOIN ITENS " +
"                                        ON ITENS.COD_ITEM = PEDIDOS.COD_ITEM "+
"                                        WHERE COMANDAS.COD_MESA = ? AND COMANDAS.ABERTA = TRUE");
            
            stmt.setInt(1, tableId);
            rs=stmt.executeQuery();
            while(rs.next()){
                /*command = new Command();
                 command.setId(rs.getInt("N_COMANDA"));
                //command.setDateHour(rs.getDate(""));
                command.setIsOpen(rs.getBoolean("ABERTA"));
                //command.setStaff(rs.getString(""));
                command.setTable(rs.getInt("COD_MESA"));*/
                
                if (command == null){
                    command = new Command();
                    command.setId(rs.getInt("N_COMANDA"));
                    command.setIsOpen(rs.getBoolean("COMANDA_ABERTA"));
                    command.setTable(rs.getInt("COD_MESA"));
                    //command.setDateHour(rs.getDate(""));
                    //command.setStaff(rs.getString(""));                    
                    
                }
                
                Item item = new Item();
                item.setId(rs.getInt("COD_ITEM"));
                item.setDescription(rs.getString("DESCRICAO"));
                item.setPrice(rs.getFloat("PRECO"));                
                                
                Order order = new Order(rs.getInt("COD_PEDIDO"),null, null, rs.getString("OBS"),item,rs.getInt("QUANTIDADE"),rs.getInt("N_COMANDA"),rs.getInt("COD_MESA")); 
                command.addOrder(order);
            }
        } catch (SQLException ex) {
            throw new DbException ("ERRO AO BUSCAR COMANDA: " + ex.getMessage());
            
        }
        finally{
            ConnectionFactory.closeConnetion(null, stmt, rs);
        }
        
        
        return command;
        
    } 
     @Override
    public void update(Command command)throws DbException{
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE COMANDAS SET ABERTA = ? WHERE N_COMANDA = ?");
            
            //stmt,setDate(1,null);
            stmt.setBoolean(1, command.getIsOpen());
            stmt.setInt(2, command.getId());      
            
            stmt.executeUpdate();
            
        }catch (SQLException ex) {
            throw new DbException ("ERRO AO ATUALIZAR COMANDA: " + ex.getMessage());
            
        }finally{
            ConnectionFactory.closeConnetion(null, stmt);
        }
    }
    @Override
    public void delete (Command command)throws DbException{
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM COMANDAS WHERE N_COMANDA = ?");
            stmt.setInt(1, command.getId());
            stmt.executeUpdate();            
        }catch (SQLException ex) {
            throw new DbException ("ERRO AO DELETAR COMANDA " + ex.getMessage());
            
        }finally{
            ConnectionFactory.closeConnetion(null, stmt);
        }    
    }
}
