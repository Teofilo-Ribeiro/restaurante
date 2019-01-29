/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.model;

import com.restaurante.java.model.enums.TableStatus;

/**
 *
 * @author teo
 */
public class Table {
    private int id;
    private TableStatus status;
    private int qtyChairs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public int getQtyChairs() {
        return qtyChairs;
    }

    public void setQtyChairs(int qtyChairs) {
        this.qtyChairs = qtyChairs;
    }

    @Override
    public String toString() {
        return "Mesa{" + "cod_mesa=" + id + ", estado=" + status + ", qtdCadeiras=" + qtyChairs + '}';
    }
    
}
