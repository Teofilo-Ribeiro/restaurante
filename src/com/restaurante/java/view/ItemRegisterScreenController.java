/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;

import com.restaurante.java.model.Item;
import com.restaurante.java.controller.ItemRegistration;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.view.listeners.DataChangeListener;
import com.restaurante.java.view.util.Alerts;
import com.restaurante.java.view.util.Constraints;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author teo
 */
public class ItemRegisterScreenController  implements Initializable {
   
    private static List <DataChangeListener> dataChangeListeners= new ArrayList<>();
    
    @FXML
    private Label lbTitle;

    @FXML
    private Label lbItemDescription;

    @FXML
    private Label lbPrice;

    @FXML
    private Label lbCifrao;
    

    @FXML
    private TextField tfItemDescription;
    
    @FXML
    private TextField tfPrice;

    @FXML
    private Button btConfirm;
    
    private static Scene scene;
    private static Stage stage;
    
    public ItemRegisterScreenController(){}
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Constraints.setTextFieldDouble(tfPrice);        
        //Constraints.setTextFieldMaxLength(tfPrice, 4);
    }
    public void start(){
    
        
        try {
            stage= new Stage();       
            Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/ItemRegisterScreen.fxml"));
            scene = new Scene(fxmlPrincipal);
        } catch (IOException ex) {
            Alerts.showAlert("ERRO", null, "Erro ao abrir tela de registro de Itens!\n"+ex.getMessage(), Alert.AlertType.ERROR);
        }

        
        stage.setScene(scene);
        stage.setTitle("Cadastro de Itens"); 
        stage.show();
          
   }
    public void close (){
        stage.close();
        stage=null;
       
   }
    
    @FXML
    public void onRegister (){
       
       
        try {
           Item newItem;
           ItemRegistration itemReg = new ItemRegistration();
           newItem = getDataForm();
           itemReg.register(newItem);           
           Alerts.showAlert("Cadastrado com sucesso!", null,"Este é o ID do seu novo Item: " + newItem.getId(), Alert.AlertType.INFORMATION);
           //verificationItemReg.start(itemVerification,ScreenType.VISUALIZATION);
           clearDataForm(); 
           notifyDataChcangeListeners();
           stage.close();
           
        }
        catch(IllegalStateException e ){
            Alerts.showAlert("ATENÇÃO!", null, e.getMessage(), Alert.AlertType.WARNING);
        }
       catch(DbException e){
           Alerts.showAlert("ERRO!", null, e.getMessage(), Alert.AlertType.ERROR);       
       }
    }
    public void onCancel(){
        stage.close();
    }
    
    public void subscribeDataChangeListener(DataChangeListener listener){
        dataChangeListeners.add(listener);
    }
    public Item getDataForm(){
        Item item = new Item();
        if(tfItemDescription.getText() == null || tfItemDescription.getText().trim().equals("")){
            throw new IllegalStateException("Todos os campos devem está devidamente preenchidos!");
        }
        item.setDescription(tfItemDescription.getText());
        
        try{
            item.setPrice(Double.parseDouble(tfPrice.getText()));
        }catch(NumberFormatException ex){
            throw new IllegalStateException ("O preço digitado é invalido!");
        }
        
        
        return item;
    }
    public void clearDataForm(){
        tfItemDescription.clear();
        tfPrice.clear();
    }

    private void notifyDataChcangeListeners() {
        for(DataChangeListener listener: dataChangeListeners){
            listener.onDataChanged();
        }
    }
    
    

    
    
}
