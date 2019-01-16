/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.dao;

import com.restaurante.java.connection.ConnectionFactory;
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
public class MesaDAO {
    
    
    public List<Mesa> listarMesas(){
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
            Logger.getLogger(PratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        
        return mesas;
    }
    
}
