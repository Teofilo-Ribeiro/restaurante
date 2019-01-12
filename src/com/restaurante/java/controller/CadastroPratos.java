/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.controller;

import com.restaurante.java.dao.PratoDAO;
import com.restaurante.java.model.Prato;

/**
 *
 * @author teo
 */
public class CadastroPratos {
    public Prato CadastrarPrato(Prato p){
        PratoDAO pDao = new PratoDAO();
        Prato verificar = Buscar (p.getDescricao());
        
        if(verificar==null || verificar.getDescricao()==null){
            pDao.create(p);        
        }
        
        return verificar;   
    }
    public Prato Buscar(String Descricao){
        Prato prato =null;
        PratoDAO pDao = new PratoDAO();
        prato= pDao.listarPratos(Descricao);
        
        if(prato == null){
            return null;
        }
        else{
           return prato;

        }
       
    }
}
