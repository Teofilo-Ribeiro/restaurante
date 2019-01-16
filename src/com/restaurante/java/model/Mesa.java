/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.model;

import com.restaurante.java.model.enums.EstadoMesa;

/**
 *
 * @author teo
 */
public class Mesa {
    private int id;
    private EstadoMesa estado;
    private int qtdCadeiras;

    public int getId() {
        return id;
    }

    public void setId(int cod_mesa) {
        this.id = cod_mesa;
    }

    public EstadoMesa getEstado() {
        return estado;
    }

    public void setEstado(EstadoMesa estado) {
        this.estado = estado;
    }

    public int getQtdCadeiras() {
        return qtdCadeiras;
    }

    public void setQtdCadeiras(int qtdCadeiras) {
        this.qtdCadeiras = qtdCadeiras;
    }

    @Override
    public String toString() {
        return "Mesa{" + "cod_mesa=" + id + ", estado=" + estado + ", qtdCadeiras=" + qtdCadeiras + '}';
    }
    
}
