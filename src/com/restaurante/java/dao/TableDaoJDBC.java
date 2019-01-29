/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.dao;

import com.restaurante.java.connection.ConnectionFactory;
import com.restaurante.java.model.Table;
import com.restaurante.java.model.enums.TableStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.restaurante.java.interfaces.TableDao;

/**
 *
 * @author teo
 */
public class TableDaoJDBC implements TableDao{
    
    @Override
    public void update(Table table){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE MESAS SET ESTADO = ? WHERE COD_MESA = ?");
            stmt.setString(1,table.getStatus().name());
            stmt.setDouble(2, table.getId());
            stmt.executeUpdate();
            System.out.println("Update realizado com sucesso!");
        }catch (SQLException ex) {
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            ConnectionFactory.closeConnetion(con, stmt);
        }
    }
    
    @Override
    public List<Table> findAll(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Table> tables = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM MESAS");
            rs=stmt.executeQuery();
            
            while(rs.next()){
                Table table = new Table();
                table.setId(rs.getInt("COD_MESA"));
                table.setQtyChairs(rs.getInt("QTD_CADEIRAS"));
                table.setStatus(TableStatus.valueOf(rs.getString("Estado")));
                tables.add(table);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        
        return tables;
    }

    @Override
    public void save(Table table) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("INSERT INTO MESAS (QTD_CADEIRAS) VALUES (?)");
            stmt.setInt(1,table.getQtyChairs());
            stmt.executeUpdate();
        
        }catch(SQLException ex){
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnetion(con, stmt);
        }
        
    }

    @Override
    public Table findById(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Table table = new Table();
        
        try {
            stmt= con.prepareStatement("SELECT * FROM MESAS WHERE COD_MESA = ?");
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            if(rs.next()){
                table.setId(rs.getInt("COD_MESA"));
                table.setQtyChairs(rs.getInt("QTD_CADEIRAS"));
                table.setStatus(TableStatus.valueOf(rs.getString("ESTADO")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConnectionFactory.closeConnetion(con, stmt, rs);
        }       
        
        return table;
    }
    @Override
    public void remove (Table table){}
    
    
}
