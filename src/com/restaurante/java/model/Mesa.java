/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.model;

/**
 *
 * @author teo
 */
public class Mesa {
    private int cod_mesa;
    private char estado;
    private int qtdCadeiras;

    public int getCod_mesa() {
        return cod_mesa;
    }

    public void setCod_mesa(int cod_mesa) {
        this.cod_mesa = cod_mesa;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
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
        return "Mesa{" + "cod_mesa=" + cod_mesa + ", estado=" + estado + ", qtdCadeiras=" + qtdCadeiras + '}';
    }
    
}
