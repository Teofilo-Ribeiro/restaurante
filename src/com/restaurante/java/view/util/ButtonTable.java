/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view.util;
import com.restaurante.java.model.enums.TableStatus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author teo
 */
public class ButtonTable extends MenuButton {
    
    private Circle circleStatus;
    private MenuItem open;
    private MenuItem close;
    private MenuItem newOrder;
    private MenuItem book;
    private MenuItem tableStatus;
    
    
    private String codMesa;
   

    public ButtonTable(String tableCode,TableStatus status, EventHandler<ActionEvent> openMethod,EventHandler<ActionEvent> closeMethod,
                    EventHandler<ActionEvent> bookMethod,EventHandler<ActionEvent> statusMethod, EventHandler<ActionEvent> newOrderMethod  ) {
        super("Mesa: " + tableCode);
        this.circleStatus = new Circle(9.0);
        this.setCircleStatus(status);
        //this.setEstado("livre");
        this.open = new MenuItem("Abrir");
        this.open.setOnAction(openMethod);
        this.open.setId(tableCode);
        
        this.close = new MenuItem("Fechar");
        this.close.setOnAction(closeMethod);
        this.close.setId(tableCode);
        
        this.book = new MenuItem("Reservar");
        this.book.setOnAction(bookMethod);
        this.book.setId(tableCode);
        
        this.newOrder = new MenuItem("Novo Pedido");
        this.newOrder.setOnAction(newOrderMethod);
        //this.novoPedido.setOnAction(mAbrir);
        this.newOrder.setId(tableCode);
        
        this.tableStatus = new MenuItem("Status");
        //this.status.setOnAction(mAbrir);
        this.tableStatus.setId(tableCode);
        
        super.getItems().addAll(this.open,this.close,this.book,this.tableStatus,this.newOrder);
        super.setGraphic(this.circleStatus);
        super.setMinSize(142, 60);
        super.setId(tableCode);       
    }
        
    public void setCircleStatus(TableStatus status){
        switch(status){
            case OCUPADA: this.circleStatus.setFill(Color.RED);            
                            break;
            case RESERVADA: this.circleStatus.setFill(Color.GREY);
                            break;
            case LIVRE: this.circleStatus.setFill(Color.GREEN);
                            break;
                    
        }
        
    }
    public static int parseId(ActionEvent e){
        String id;
        int x,y;
        
        x=e.getSource().toString().indexOf("id=");
        y=e.getSource().toString().indexOf(",");
        id = e.getSource().toString().substring(x+3, y);
        
        return Integer.parseInt(id);
    }
    
   
}
