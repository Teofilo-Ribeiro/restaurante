/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.factory;

import com.restaurante.java.dao.ItemDaoJDBC;
import com.restaurante.java.interfaces.ItemDao;

/**
 *
 * @author teo
 */
public class ItemDaoFactory {
    public static ItemDao criarItemDao(){
        return new ItemDaoJDBC();
    }
}
