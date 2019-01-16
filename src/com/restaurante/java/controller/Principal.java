/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.controller;

import com.restaurante.java.dao.MesaDAO;
import com.restaurante.java.model.Mesa;
import java.util.List;

/**
 *
 * @author teo
 */
public class Principal {
    
    
    public List<Mesa> listarTodos(){
        MesaDAO mesaDao = new MesaDAO();
        return mesaDao.listarMesas();
    }
}
