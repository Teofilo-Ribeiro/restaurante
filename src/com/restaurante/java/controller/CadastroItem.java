/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.controller;

import com.restaurante.java.dao.ItemDaoJDBC;
import com.restaurante.java.factory.ItemDaoFactory;
import com.restaurante.java.interfaces.ItemDao;
import com.restaurante.java.model.Item;

/**
 *
 * @author teo
 */
public class CadastroItem {
    public int cadastrar(Item item){
        ItemDao itemDao = ItemDaoFactory.criarItemDao();
        
        itemDao.salvar(item);        
        
        return buscar(item.getDescricao()).getId();   
    }
    public Item buscar(String Descricao){
        Item item;
        ItemDao itemDao = ItemDaoFactory.criarItemDao();
        item= itemDao.buscarPorDescricao(Descricao);       
        return item;       
    }
}
