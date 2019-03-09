/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.factory;

import com.restaurante.java.dao.TableDaoJDBC;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.interfaces.TableDao;

/**
 *
 * @author teo
 */
public class TableDaoFactory {
    public static TableDao createMesaDao()throws DbException{
        return new TableDaoJDBC();
    }
}
