/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.factory;

import com.restaurante.java.dao.CommandDaoJDBC;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.interfaces.CommandDao;


/**
 *
 * @author teo
 */
public class CommandDaoFactory {
    public static CommandDao createCommandDao()throws DbException{
        return new CommandDaoJDBC();
    }
}
