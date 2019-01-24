/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.interfaces;

import com.restaurante.java.model.Item;
import java.util.List;

/**
 *
 * @author teo
 */
public interface ItemDao {
    public void salvar(Item item);
    public List<Item> buscarTodos();
    public Item buscarPorId(int id);
    public Item buscarPorDescricao(String descricao);
    public void atualizar(Item item);
    public void remover (Item item);
}
