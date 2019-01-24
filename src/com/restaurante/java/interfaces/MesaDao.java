/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.interfaces;

import com.restaurante.java.model.Mesa;
import java.util.List;

/**
 *
 * @author teo
 */
public interface MesaDao {
    public void salvar(Mesa mesa);
    public Mesa buscarPorId(int id);
    public List<Mesa> buscarTodos();
    public void atualizar(Mesa mesa);
    public void remover (Mesa mesa);
}
