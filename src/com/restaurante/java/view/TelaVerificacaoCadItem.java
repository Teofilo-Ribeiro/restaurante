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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author teo
 */
public  class TelaVerificacaoCadItem {
    
       
    @FXML
    private Label lbTitulo;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfPreco;

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btCancelar;
    
        
    private static Scene popup;
    private static Stage stage;
    
    private static String titulo;
    private static int id;
    private static String Descricao;
    private static double preco;
    private static Item infoPrato;
    private static boolean ehUpdate;
    
    
    
    @FXML
    void initialize() {
        assert lbTitulo != null : "fx:id=\"lbTitulo\" was not injected: check your FXML file 'VerificacaoCadPratos.fxml'.";
        assert tfId != null : "fx:id=\"tfId\" was not injected: check your FXML file 'VerificacaoCadPratos.fxml'.";
        assert tfDescricao != null : "fx:id=\"tfDescricao\" was not injected: check your FXML file 'VerificacaoCadPratos.fxml'.";
        assert tfPreco != null : "fx:id=\"tfPreco\" was not injected: check your FXML file 'VerificacaoCadPratos.fxml'.";
        assert btConfirmar != null : "fx:id=\"btConfirmar\" was not injected: check your FXML file 'VerificacaoCadPratos.fxml'.";
        assert btCancelar != null : "fx:id=\"btCancelar\" was not injected: check your FXML file 'VerificacaoCadPratos.fxml'.";
        lbTitulo.setText(titulo);
        tfId.setText(Integer.toString(infoPrato.getId()));
        tfDescricao.setText(infoPrato.getDescricao());
        tfPreco.setText(Double.toString(infoPrato.getPreco()));
        btConfirmar.setVisible(ehUpdate);
    }
    
    public void start (String titulo, Item p, boolean ehUpdate){
        //lbTitulo.setText(titulo);
       //initialize("Teste000");
        this.titulo = titulo;
        this.infoPrato= p;
        this.ehUpdate= ehUpdate;
        stage= new Stage();
        Parent fxmlPrincipal; 
        try {
            fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/TelaVerificacaoCadItem.fxml"));
            popup = new Scene(fxmlPrincipal);
        } catch (IOException ex) {
            System.out.println("AQUI!");
                    
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        stage.setScene(popup);
        stage.show();
       // texto("Oi");    
   }
   public void close (){
       stage.close();
       stage=null;
       
   }
    
    
    
}
