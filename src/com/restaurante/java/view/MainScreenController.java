/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;


import com.restaurante.java.controller.TableRegistration;
import com.restaurante.java.model.Table;
import com.restaurante.java.model.enums.TableStatus;
import com.restaurante.java.util.ButtonTable;
import static com.restaurante.java.util.ButtonTable.parseId;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainScreenController {

    @FXML
    private GridPane grTables;
       
    private static Stage stage;
    private static Scene scene;
    
    //private Mesa mesas [];
    private ButtonTable[] tableButtons;
    private List<Table> tables;
    
    @FXML
    void initialize (){
        TableRegistration tableReg = new TableRegistration();
        tables= tableReg.findAll();
        tableButtons= new ButtonTable[tables.size()];
        int i = 0;
        int gridH = 0, gridV =0;
       
        for(Table table : tables ){
            tableButtons[i] = new ButtonTable(Integer.toString(table.getId()),table.getStatus(),this::openTable, this::closeTable, this::bookTable, this::status,this::novoPedido);
            grTables.add(tableButtons[i],gridV,gridH);
            GridPane.setHalignment(tableButtons[i], HPos.CENTER);
            GridPane.setValignment(tableButtons[i], VPos.CENTER);
            if(gridV>12){
                gridV = 0;
            }
            else{
                gridV++;
            }
            i++;
        }

        
}
    
    public void start (){
        
       stage= new Stage();
        Parent fxmlPrincipal; 
        try {
            fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/MainScreen.fxml"));
            scene = new Scene(fxmlPrincipal);
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }
    
    public void execute(ActionEvent e){
        System.out.println(e.getSource().toString()/*.substring(10, 11)*/); //ALTERA 11 PARA 13 QUANDO ASSOCIAR COM O BNACO
        System.out.println(e.getClass());
    }
    
    public void openTable(ActionEvent e){
        TableRegistration tabReg = new TableRegistration();   
        tableButtons[parseId(e)-1].setCircleStatus(TableStatus.OCUPADA); // -1 pois o id da mesa começa em 1;        
        Table table = tables.get(parseId(e)-1);
        table.setStatus(TableStatus.OCUPADA);
        tabReg.updateStatus(table);
    }
    public void closeTable(ActionEvent e){
        //fachar a comanda e abrir tela de pagamento
        TableRegistration tabReg = new TableRegistration();
        tableButtons[parseId(e)-1].setCircleStatus(TableStatus.LIVRE); 
        Table table = tables.get(parseId(e)-1);
        table.setStatus(TableStatus.LIVRE);
        tabReg.updateStatus(table);
    }
    public void bookTable(ActionEvent e){
        TableRegistration tabReg = new TableRegistration();
                
        tableButtons[parseId(e)-1].setCircleStatus(TableStatus.RESERVADA);
                
        Table table = tables.get(parseId(e)-1);
        table.setStatus(TableStatus.RESERVADA);
        tabReg.updateStatus(table);
    }
    public void novoPedido(ActionEvent e){
        
    }
    public void status(ActionEvent e){
    
    }
    
    public void itemRegistration(){
        ItemRegisterScreenController itemRegScreen = new ItemRegisterScreenController();
        itemRegScreen.start();
    
    }
}
