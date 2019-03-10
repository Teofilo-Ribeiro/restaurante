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
public class Order {
    private int id;
    private Date dateHour;
    private Staff staff;
    private String note;
    private Item item;
    private int qty;
    private int tableId;
    private int commandId;
    private Boolean done;

    
    
    public Order(){}

    public Order(int id, Date dateHour, Staff staff, String note, Item item, int qty, int commandId, int tableId) {
        this.dateHour = dateHour;
        this.staff = staff;
        this.note = note;
        this.item = item;
        this.qty = qty;
        this.tableId = tableId;   
        this.id = id;
    }
    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
        
    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public Item getItem(){
        return item;
    }
    public void setItem(Item item){
        this.item = item;
    }
    
    public int getQty(){
        return qty;
    }
    
    public Date getDateHour() {
        return dateHour;
    }
    
    public Staff getStaff(){
        return staff;
    }
    public String getNote(){
        return note;
    }
    public int getCommandId(){
        return commandId;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public int getTableId() {
        return tableId;
    }
    
    
    public void setDateHour(Date dateHour) {
        this.dateHour = dateHour;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    
    public void setNote(String note){
        this.note = note;
    }
    
}
