/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.dao;

import com.restaurante.java.connection.ConnectionFactory;
import com.restaurante.java.interfaces.MesaDao;
import com.restaurante.java.model.Mesa;
import com.restaurante.java.model.enums.EstadoMesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author teo
 */
public class MesaDaoJDBC implements MesaDao{
    
    @Override
    public void atualizar(Mesa mesa){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE MESAS SET ESTADO = ? WHERE COD_MESA = ?");
            stmt.setString(1,mesa.getEstado().name());
            stmt.setDouble(2, mesa.getId());
            stmt.executeUpdate();
            System.out.println("Update realizado com sucesso!");
        }catch (SQLException ex) {
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            ConnectionFactory.closeConnetion(con, stmt);
        }
    }
    
    @Override
    public List<Mesa> buscarTodos(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Mesa> mesas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM MESAS");
            rs=stmt.executeQuery();
            
            while(rs.next()){
                Mesa mesa = new Mesa();
                mesa.setId(rs.getInt("COD_MESA"));
                mesa.setQtdCadeiras(rs.getInt("QTD_CADEIRAS"));
                mesa.setEstado(EstadoMesa.valueOf(rs.getString("Estado")));
                mesas.add(mesa);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        
        return mesas;
    }

    @Override
    public void salvar(Mesa mesa) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("INSERT INTO MESAS (QTD_CADEIRAS) VALUES (?)");
            stmt.setInt(1,mesa.getQtdCadeiras());
            stmt.executeUpdate();
        
        }catch(SQLException ex){
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnetion(con, stmt);
        }
        
    }

    @Override
    public Mesa buscarPorId(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Mesa mesa = new Mesa();
        
        try {
            stmt= con.prepareStatement("SELECT * FROM MESAS WHERE COD_MESA = ?");
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            if(rs.next()){
                mesa.setId(rs.getInt("COD_MESA"));
                mesa.setQtdCadeiras(rs.getInt("QTD_CADEIRAS"));
                mesa.setEstado(EstadoMesa.valueOf(rs.getString("ESTADO")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConnectionFactory.closeConnetion(con, stmt, rs);
        }       
        
        return mesa;
    }
    @Override
    public void remover (Mesa mesa){}
    
    
}
