/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.factory;

import com.restaurante.java.dao.MesaDaoJDBC;
import com.restaurante.java.interfaces.MesaDao;

/**
 *
 * @author teo
 */
public class MesaDaoFactory {
    public static MesaDao criarMesaDao(){
        return new MesaDaoJDBC();
    }
}
