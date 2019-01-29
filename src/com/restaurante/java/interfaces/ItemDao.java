/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.interfaces;

import com.restaurante.java.model.Item;
import java.util.List;

/**
 *
 * @author teo
 */
public interface ItemDao {
    public void save(Item item);
    public List<Item> findAll();
    public Item findById(int id);
    public Item findByDescription(String description);
    public void update(Item item);
    public void remove (Item item);
}
