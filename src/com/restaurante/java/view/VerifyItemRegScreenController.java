/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;

import com.restaurante.java.model.Item;
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
public  class VerifyItemRegScreenController {
    
       
    @FXML
    private Label lbTitle;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfPrice;

    @FXML
    private Button btConfirm;

    @FXML
    private Button btCancel;
    
        
    private static Scene popup;
    private static Stage stage;
    
    private static String title;
    private static int id;
    private static String Descricao;
    private static double preco;
    private static Item itemData;
    private static boolean ehUpdate;
    
    
    
    @FXML
    void initialize() {
        assert lbTitle != null : "fx:id=\"lbTitulo\" was not injected: check your FXML file 'VerificacaoCadPratos.fxml'.";
        assert tfId != null : "fx:id=\"tfId\" was not injected: check your FXML file 'VerificacaoCadPratos.fxml'.";
        assert tfDescription != null : "fx:id=\"tfDescricao\" was not injected: check your FXML file 'VerificacaoCadPratos.fxml'.";
        assert tfPrice != null : "fx:id=\"tfPreco\" was not injected: check your FXML file 'VerificacaoCadPratos.fxml'.";
        assert btConfirm != null : "fx:id=\"btConfirmar\" was not injected: check your FXML file 'VerificacaoCadPratos.fxml'.";
        assert btCancel != null : "fx:id=\"btCancelar\" was not injected: check your FXML file 'VerificacaoCadPratos.fxml'.";
        lbTitle.setText(title);
        tfId.setText(Integer.toString(itemData.getId()));
        tfDescription.setText(itemData.getDescription());
        tfPrice.setText(Double.toString(itemData.getPrice()));
        btConfirm.setVisible(ehUpdate);
    }
    
    public void start (String title, Item item, boolean isUpdate){
        //lbTitulo.setText(titulo);
       //initialize("Teste000");
        this.title = title;
        this.itemData= item;
        this.ehUpdate= isUpdate;
        stage= new Stage();
        Parent fxmlPrincipal; 
        try {
            fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/VerifyItemRegScreen.fxml"));
            popup = new Scene(fxmlPrincipal);
        } catch (IOException ex) {
            System.out.println("AQUI!");
                    
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(popup);
        stage.show();
       // texto("Oi");    
   }
   public void close (){
       stage.close();
       stage=null;
       
   }
    
    
    
}
