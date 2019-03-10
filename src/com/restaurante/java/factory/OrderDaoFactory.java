/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.factory;

import com.restaurante.java.dao.OrderDaoJDBC;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.interfaces.OrderDao;

/**
 *
 * @author teo
 */
public class OrderDaoFactory {
    public static OrderDao createOrderDao()throws DbException{
        return new OrderDaoJDBC();
    }
}
