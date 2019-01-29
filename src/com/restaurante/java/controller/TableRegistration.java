/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.controller;

import com.restaurante.java.factory.TableDaoFactory;
import com.restaurante.java.model.Table;
import java.util.List;
import com.restaurante.java.interfaces.TableDao;

/**
 *
 * @author teo
 */
public class TableRegistration {
    
    
    public List<Table> findAll(){
        TableDao tableDao = TableDaoFactory.createMesaDao();
        return tableDao.findAll();
    }
    
    public void updateStatus(Table table){
        TableDao tableDao= TableDaoFactory.createMesaDao();
        tableDao.update(table);
    }
}
