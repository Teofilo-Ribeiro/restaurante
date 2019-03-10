/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.controller;

import com.restaurante.java.exception.DbException;
import com.restaurante.java.factory.CommandDaoFactory;
import com.restaurante.java.interfaces.CommandDao;
import com.restaurante.java.model.Command;

/**
 *
 * @author teo
 */
public class CommandRegistration {
    CommandDao commandDao;

    public CommandRegistration() throws DbException {
        this.commandDao = CommandDaoFactory.createItemDao();
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
}
