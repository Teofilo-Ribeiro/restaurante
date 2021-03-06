/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author teo
 */
public class Command {
    private int id;
    private Date dateHour;  //when the command was close
    private Staff staff;    //who close
    private Boolean isOpen;
    private int table;
    private List <Order> orders;
    

    public Command(){
        orders = new ArrayList();
    }

   
    
    
    public Command(int table) {        
        this.dateHour = dateHour;
        this.table = table;
        orders = new ArrayList();
    }
    
    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public int getId() {
        return id;
    }

    public Date getDateHour() {
        return dateHour;
    }

    public Staff getStaff() {
        return staff;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void closeCommand(Date dateHour) {
        this.isOpen = false;
        this.dateHour = dateHour;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    public void addOrder(Order order){
        orders.add(order);
    }
    public float getTotal(){
        float total=0;
        for (Order order: orders){
            total += order.getItem().getPrice();
        }
        return total;
    }
    public int getTotalItems(){
        int total = 0;
        for (Order order:orders){
            total+= order.getQty();
        }
        return total;
    }   
    
}
