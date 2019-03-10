/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.interfaces;

import com.restaurante.java.exception.DbException;
import com.restaurante.java.model.Table;
import java.util.List;

/**
 *
 * @author teo
 */
public interface TableDao {
    public void create(Table table)throws DbException;
    public Table findById(int id)throws DbException;
    public List<Table> findAll()throws DbException;
    public void update(Table table)throws DbException;
    public void remove (Table table)throws DbException;
}
