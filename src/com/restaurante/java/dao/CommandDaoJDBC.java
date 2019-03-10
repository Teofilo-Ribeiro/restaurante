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
        
        List<Command> commands = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM COMANDAS");
            rs=stmt.executeQuery();
            
            while(rs.next()){
                Command command = new Command();
                command.setId(rs.getInt("N_COMANDA"));
                //command.setDateHour(rs.getDate(""));
                command.setIsOpen(rs.getBoolean("ABERTA"));
                //command.setStaff(rs.getString(""));
                command.setTable(rs.getInt("COD_MESA"));
                commands.add(command);
            
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
        //Connection con = ConnectionFactory.getConnection();
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
            stmt= con.prepareStatement("SELECT * FROM COMANDAS WHERE COD_MESA = ? AND ABERTA = TRUE");
            stmt.setInt(1, tableId);
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
