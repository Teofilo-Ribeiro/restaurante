/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.interfaces;

import com.restaurante.java.model.Table;
import java.util.List;

/**
 *
 * @author teo
 */
public interface TableDao {
    public void save(Table table);
    public Table findById(int id);
    public List<Table> findAll();
    public void update(Table table);
    public void remove (Table table);
}
