/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.interfaces;

import com.restaurante.java.exception.DbException;
import com.restaurante.java.model.Item;
import java.util.List;

/**
 *
 * @author teo
 */
public interface ItemDao  {
    public void save(Item item)throws DbException;
    public List<Item> findAll()throws DbException;
    public Item findById(int id)throws DbException;
    public Item findByDescription(String description)throws DbException;
    public void update(Item item)throws DbException;
    public void delete (Item item)throws DbException;
}
