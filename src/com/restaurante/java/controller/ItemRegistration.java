/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.controller;

import com.restaurante.java.exception.DbException;
import com.restaurante.java.factory.ItemDaoFactory;
import com.restaurante.java.interfaces.ItemDao;
import com.restaurante.java.model.Item;
import java.util.List;

/**
 *
 * @author teo
 */
public class ItemRegistration  {
    ItemDao itemDao;

    public ItemRegistration() throws DbException {
        this.itemDao = ItemDaoFactory.createItemDao();
    }
    public void register (Item newItem) throws DbException, IllegalStateException{
        Item checkItem = findByDescription(newItem.getDescription());        
        if(checkItem != null){
            newItem = checkItem;
            throw new IllegalStateException("Item já cadastrado! \n"
                                            + "ID: "+ checkItem.getId()
                                            + "\nPreço: " + checkItem.getPrice());
        }
        itemDao.create(newItem);        
        
        //return findByDescription(item.getDescription()).getId();   
    }
    public Item findByDescription(String description) throws DbException{
        Item item= itemDao.findByDescription(description);       
        return item;       
    }
    public Item findById (int id) throws DbException{
        return itemDao.findById(id);  
    
    }
    public List<Item> findAll() throws DbException{
        return itemDao.findAll();
    }
    public void update(Item item) throws DbException{
        itemDao.update(item);
    }
    public void delete(Item item) throws DbException{
        itemDao.delete(item);
    }
    public List<Item> smartFind(String description)throws DbException{
        return itemDao.smartFindByDescription(description);
    }
    
}
