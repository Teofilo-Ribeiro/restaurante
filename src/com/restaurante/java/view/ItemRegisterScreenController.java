/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;

import com.restaurante.java.model.Item;
import com.restaurante.java.controller.ItemRegistration;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author teo
 */
public class ItemRegisterScreenController  {
   
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
    
    
    @FXML
    public void register (){
       Item item, itemVerification;
       ItemRegistration itemReg = new ItemRegistration();
       VerifyItemRegScreenController verificationItemReg = new VerifyItemRegScreenController();
       try {
           item = getFormData();
           itemVerification = itemReg.find(item.getDescription());
           if(itemVerification.getDescription() == null){
               //item foi cadastrador mostar id
               item.setId(itemReg.register(item));
               verificationItemReg.start("Item cadastrado com sucesso!", item, false);               
           }
           else{
               verificationItemReg.start("Item já cadastrado, deseja salvar alterações?", itemVerification, true);
           }
           
       }
       catch(IllegalStateException e){
          System.out.println("ERROR: " + e.getMessage());
       }
    }
    
    
    public Item getFormData(){
        Item item = new Item();
        if(tfItemDescription.getText() == null || tfItemDescription.getText().trim().equals("")){
            throw new IllegalStateException("Todos os campos deve está devidamente preenchidos!");
        }
        item.setDescription(tfItemDescription.getText());
        
        try{
            item.setPrice(Double.parseDouble(tfPrice.getText()));
        }catch(NumberFormatException ex){
            throw new IllegalStateException ("O preço digitado é invalido!");
        }
        
        
        return item;
    }
    
    
    public void start(){
    
        
        try {
            stage= new Stage();
        //Parent fxmlPrincipal; 
            Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/ItemRegisterScreen.fxml"));
            scene = new Scene(fxmlPrincipal);
        } catch (IOException ex) {
            System.out.println("AQUI!");

            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        stage.setScene(scene);
        stage.setTitle("Cadastro de Itens"); 
        stage.show();
       // texto("Oi");    
   }
    public void close (){
        stage.close();
        stage=null;
       
   }
    
    
}
