/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.controller;

import com.restaurante.java.factory.MesaDaoFactory;
import com.restaurante.java.interfaces.MesaDao;
import com.restaurante.java.model.Mesa;
import java.util.List;

/**
 *
 * @author teo
 */
public class CadastroMesas {
    
    
    public List<Mesa> listarTodos(){
        MesaDao mesaDao = MesaDaoFactory.criarMesaDao();
        return mesaDao.buscarTodos();
    }
    
    public void atualizarEstado(Mesa mesa){
        MesaDao mesaDao= MesaDaoFactory.criarMesaDao();
        mesaDao.atualizar(mesa);
    }
}
