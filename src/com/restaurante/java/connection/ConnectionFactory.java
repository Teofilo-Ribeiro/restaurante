/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.connection;

import com.restaurante.java.exception.DbException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


/**
 *
 * @author teo
 */
public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static Connection con = null;
    private static Properties loadProperties()throws DbException {
        try(FileInputStream fs = new FileInputStream ("dbAcess.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }catch (IOException e){
            throw new DbException("Erro de Leitura dos dados de conexão");
        }
    }
    
    
    public static Connection getConnection() throws DbException{
        try {
            if (con == null){
                Properties props = loadProperties();
                String URL = props.getProperty("url");
                String USER = props.getProperty("user");
                String PASS = props.getProperty("pass");
                Class.forName(DRIVER);
                return DriverManager.getConnection(URL, USER, PASS);                    
            }        
        } catch (ClassNotFoundException | SQLException e) {
            throw new DbException("Erro ao tentar conectar!");
        }
        return con;
    }
    
    public static void closeConnetion (Connection con)throws DbException{
        
        try {
            if(con!= null){
                con.close();
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao fechar conexão");
        }
    }
    public static void closeConnetion (Connection con,PreparedStatement stmt)throws DbException{
        
        closeConnetion(con);
        try {
            if(stmt!= null){
                stmt.close();
            }
        } catch (SQLException e) {
           throw new DbException("Erro ao fechar conexão!");
        }    

    }
    public static void closeConnetion (Connection con,PreparedStatement stmt, ResultSet rs)throws DbException{
        
        closeConnetion(con,stmt);
        try {
            if(rs!= null){
                rs.close();
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao fechar conexão");
        }    

    }

}
