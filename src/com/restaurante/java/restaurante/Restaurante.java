/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.restaurante;

import com.restaurante.java.model.Item;
import com.restaurante.java.dao.ItemDaoJDBC;
import com.restaurante.java.view.TelaPrincipal;
import com.restaurante.java.view.TelaVerificacaoCadItem;

/**
 *
 * @author teo
 */
public class Restaurante {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Prato p = new Prato();
        PratoDAO dao = new PratoDAO();
        p.setDescricao("Pizza de Frango");
        p.setPreco(27.00);
        dao.create(p);
        System.out.println("Fim Do programa!");
       */
        TelaPrincipal tp = new TelaPrincipal();
        tp.start();
    }
    
}
