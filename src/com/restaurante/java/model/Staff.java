/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.model;

import java.util.Date;

/**
 *
 * @author teo
 */
public class Staff extends Person{
    private int id;
    private float salary;
    private Date hiringDate;

    public Staff(int id, float salary, Date hiringDate, String name, String cpf) {
        super(name, cpf);
        this.id = id;
        this.salary = salary;
        this.hiringDate = hiringDate;
    }   

    public void setId(int id) {
        this.id = id;
    }

    public float getSalary() {
        return salary;
    }

    public void increaseSalary(float salary) {
        this.salary = salary;
    }

    public Date getHiringDate() {
        return hiringDate;
    }   
    
    
}
