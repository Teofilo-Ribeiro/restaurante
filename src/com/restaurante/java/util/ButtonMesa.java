/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.util;
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
public class ButtonMesa extends MenuButton {
    
    private Circle estado;
    private MenuItem abrir;
    private MenuItem fechar;
    private MenuItem novoPedido;
    private MenuItem reservar;
    private MenuItem status;
    
    
    private String codMesa;
   

    public ButtonMesa(String codMesa,EventHandler<ActionEvent> mAbrir,EventHandler<ActionEvent> mFechar,
                    EventHandler<ActionEvent> mReservar,EventHandler<ActionEvent> mStatus, EventHandler<ActionEvent> mNPedido  ) {
        super("Mesa: " + codMesa);
        estado = new Circle(9.0, Color.GREEN);
        this.abrir = new MenuItem("Abrir");
        this.abrir.setOnAction(mAbrir);
        this.abrir.setId(codMesa);
        
        this.fechar = new MenuItem("Fechar");
        this.fechar.setOnAction(mFechar);
        this.fechar.setId(codMesa);
        
        this.reservar = new MenuItem("Reservar");
        this.reservar.setOnAction(mReservar);
        this.reservar.setId(codMesa);
        
        this.novoPedido = new MenuItem("Novo Pedido");
        //this.novoPedido.setOnAction(mAbrir);
        this.novoPedido.setId(codMesa);
        
        this.status = new MenuItem("Status");
        //this.status.setOnAction(mAbrir);
        this.status.setId(codMesa);
        
        super.getItems().addAll(this.abrir,this.fechar,this.reservar,this.status,this.novoPedido);
        super.setGraphic(estado);
        super.setMinSize(142, 68);
        super.setId(codMesa);       
    }
        
    public void setEstado(String estado){
        switch(estado){
            case "abrir": this.estado.setFill(Color.RED);
                            break;
            case "reservada": this.estado.setFill(Color.GREY);
                            break;
            case "livre": this.estado.setFill(Color.GREEN);
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
