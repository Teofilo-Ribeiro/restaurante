/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.interfaces;

import com.restaurante.java.exception.DbException;
import com.restaurante.java.model.Command;
import java.util.List;

/**
 *
 * @author teo
 */
public interface CommandDao {
    public void create(Command command)throws DbException;
    public List<Command> findAll()throws DbException;
    public Command findById(int id)throws DbException;    
    public void update(Command command)throws DbException;
    public void delete (Command command)throws DbException;
    public Command findCommandOpen(int tableId)throws DbException;
}
