/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.controller;

import com.restaurante.java.factory.ItemDaoFactory;
import com.restaurante.java.interfaces.ItemDao;
import com.restaurante.java.model.Item;

/**
 *
 * @author teo
 */
public class ItemRegistration {
    public int register(Item item){
        ItemDao itemDao = ItemDaoFactory.createItemDao();
        
        itemDao.save(item);        
        
        return find(item.getDescription()).getId();   
    }
    public Item find(String description){
        Item item;
        ItemDao itemDao = ItemDaoFactory.createItemDao();
        item= itemDao.findByDescription(description);       
        return item;       
    }
}
