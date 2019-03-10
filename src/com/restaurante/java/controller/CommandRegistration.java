/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.controller;

import com.restaurante.java.exception.DbException;
import com.restaurante.java.factory.CommandDaoFactory;
import com.restaurante.java.factory.OrderDaoFactory;
import com.restaurante.java.interfaces.CommandDao;
import com.restaurante.java.interfaces.OrderDao;
import com.restaurante.java.model.Command;
import com.restaurante.java.model.Order;
import java.util.List;

/**
 *
 * @author teo
 */
public class CommandRegistration {
    CommandDao commandDao;
    OrderRegistration orderReg;
    
    OrderDao orderDao;

    public CommandRegistration() throws DbException {
        this.commandDao = CommandDaoFactory.createCommandDao();
        this.orderDao= OrderDaoFactory.createOrderDao();
        orderReg= new OrderRegistration();
    }
    public Command getCommand(int tableNumber)throws DbException{
        Command command = commandDao.findCommandOpen(tableNumber);
        if (command == null){
            command = createCommand(tableNumber);
        }
        return command;
    }
    public Command createCommand(int tableNumber) throws DbException{
        Command command = new Command(tableNumber);
        commandDao.create(command);
        return command;
    }
    public Command findById(int id) throws DbException{
        
        Command command = commandDao.findById(id);
        command.setOrders(orderDao.findByCommand(id));
        return command;    
    }
    public void closeCommand(int tableId) throws DbException{
       Command command = getCommand(tableId);
       command.setIsOpen(false);
       commandDao.update(command);
    }
}
