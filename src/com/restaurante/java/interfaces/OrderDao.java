/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.interfaces;

import com.restaurante.java.exception.DbException;
import com.restaurante.java.model.Order;
import java.util.List;

/**
 *
 * @author teo
 */
public interface OrderDao {
    public void create(Order order)throws DbException;
    public List<Order> findAll()throws DbException;
    public Order findById(int id)throws DbException;    
    public void update(Order order)throws DbException;
    public void delete (Order order)throws DbException;
    public List<Order> findAllNotDone()throws DbException;
    public List <Order> findByCommand(int id)throws DbException;
}
