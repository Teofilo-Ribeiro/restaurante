/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.controller;

import com.restaurante.java.exception.DbException;
import com.restaurante.java.factory.OrderDaoFactory;
import com.restaurante.java.interfaces.OrderDao;
import com.restaurante.java.model.Order;
import java.util.List;

/**
 *
 * @author teo
 */
public class OrderRegistration {
    OrderDao orderDao;
    
    public OrderRegistration() throws DbException {
        this.orderDao = OrderDaoFactory.createOrderDao();
        
    }
    public void register (Order order) throws DbException{
        CommandRegistration commandReg= new CommandRegistration();
        int tableId= order.getTableId();
        int commandId =commandReg.getCommand(tableId).getId();
        order.setCommandId(commandId);
        orderDao.create(order);        
    
    }
    public List<Order> findAllNotDone() throws DbException{
        return orderDao.findAllNotDone();
    }
    public List<Order> findAll() throws DbException{
        return orderDao.findAll();
    }
    public void update(Order order) throws DbException{
        orderDao.update(order);
    }
    public List<Order> findByCommand(int commandId) throws DbException{
        return orderDao.findByCommand(commandId);
    }
    
    
}
