/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;


import com.restaurante.java.controller.TableRegistration;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.model.Table;
import com.restaurante.java.model.enums.TableStatus;
import com.restaurante.java.view.util.ButtonTable;
import static com.restaurante.java.view.util.ButtonTable.parseId;
import com.restaurante.java.view.util.Alerts;
import com.restaurante.java.view.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainScreenController implements Initializable {

    @FXML
    private GridPane grTables;
    @FXML
    private MenuItem miItemReg;
    @FXML
    private MenuItem miOrders;
    @FXML
    private MenuItem miCommands;
    private static Stage stage;
    private static Scene scene;
    
    //private Mesa mesas [];
    private ButtonTable[] tableButtons;
    private List<Table> tables;
    
   
    @Override
    public void initialize(URL location, ResourceBundle resources){
        try{
        TableRegistration tableReg = new TableRegistration();
        tables= tableReg.findAll();
        }catch(DbException ex){
            Alerts.showAlert("ERRO", null, "Erro ao abrir tela Principal\n"+ex.getMessage(), Alert.AlertType.ERROR);
        }
        tableButtons= new ButtonTable[tables.size()];
        int i = 0;
        int gridH = 0, gridV =0;
       
        for(Table table : tables ){
            tableButtons[i] = new ButtonTable(Integer.toString(table.getId()),table.getStatus(),this::openTable, this::closeTable, this::bookTable, this::status,this::newOrder);
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
        try {
            stage= new Stage();        
            Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/MainScreen.fxml"));
            scene = new Scene(fxmlPrincipal);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        
        } catch (IOException ex) {            
            Alerts.showAlert("ERRO", null, "Ouve um erro inesperado ao abrir a tela Principal!\n", Alert.AlertType.ERROR);
        }
        
    }
    public void close(){
        stage.close();
    }
    
    public void execute(ActionEvent e){
        System.out.println(e.getSource().toString()/*.substring(10, 11)*/); //ALTERA 11 PARA 13 QUANDO ASSOCIAR COM O BNACO
        System.out.println(e.getClass());
    }
    
    public void openTable(ActionEvent e){
        try {
            TableRegistration tabReg = new TableRegistration();
            tableButtons[parseId(e)-1].setCircleStatus(TableStatus.OCUPADA); // -1 pois o id da mesa começa em 1;
            Table table = tables.get(parseId(e)-1);
            table.setStatus(TableStatus.OCUPADA);
            tabReg.updateStatus(table);
        } catch (DbException ex) {
           Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    public void closeTable(ActionEvent e){
        try {
            //fachar a comanda e abrir tela de pagamento
            
            CloseCommandScreenController closeCommandSc = new CloseCommandScreenController();
            closeCommandSc.start(stage,parseId(e));
            
            
            
            TableRegistration tabReg = new TableRegistration();
            tableButtons[parseId(e)-1].setCircleStatus(TableStatus.LIVRE);
            Table table = tables.get(parseId(e)-1);
            table.setStatus(TableStatus.LIVRE);
            tabReg.updateStatus(table);
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    public void bookTable(ActionEvent e){
        try {
            TableRegistration tabReg = new TableRegistration();
            
            tableButtons[parseId(e)-1].setCircleStatus(TableStatus.RESERVADA);
            
            Table table = tables.get(parseId(e)-1);
            table.setStatus(TableStatus.RESERVADA);
            tabReg.updateStatus(table);
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    public void newOrder(ActionEvent e){
        try {
            TableRegistration tabReg = new TableRegistration();
            ItemsScreenController itemSc = new ItemsScreenController();
            itemSc.start(stage,parseId(e));
            Table table = tables.get(parseId(e)-1);
            tableButtons[parseId(e)-1].setCircleStatus(TableStatus.OCUPADA); // -1 pois o id da mesa começa em 1;
            table.setStatus(TableStatus.OCUPADA);
            tabReg.updateStatus(table);
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
        
    }
    public void status(ActionEvent e){
    
    }
    
    public void itemRegistration(ActionEvent event){
        //Stage parentStage = Utils.currentStage(event);
        ItemsScreenController itemScreen = new ItemsScreenController();
        itemScreen.start(stage);
    
    }
    public void onOrders(){
        OrderScreenController orderSc = new OrderScreenController();
        orderSc.start(stage);
    }
    
    public void onCommands(){}
}
